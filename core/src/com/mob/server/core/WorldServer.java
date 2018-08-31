package com.mob.server.core;

import character.*;
import com.artemis.*;
import com.artemis.utils.IntBag;
import com.mob.client.artemis.archetypes.AOArchetypes;
import com.mob.client.artemis.systems.physics.RandomMovementSystem;
import com.mob.server.database.IDatabase;
import com.mob.server.database.model.User;
import net.mostlyoriginal.api.network.common.DeltaSubscriptionManager;
import net.mostlyoriginal.api.network.marshal.common.MarshalStrategy;

import static com.artemis.E.E;

public class WorldServer {

    public static final int INVALID_USER = -1;
    private boolean running = true;
    private boolean pause = false;
    private static World world;
    private static IDatabase database;

    protected final WorldConfigurationBuilder builder = new WorldConfigurationBuilder();
    private static Entity player;
    private static KryonetServerMarshalStrategy server;

    public static void removeEntity(int entityId) {
        world.delete(entityId);
    }

    public static User getUser(String username) {
        User user = new User();
        return user;
    }

    public static Entity createEntity(User user) {
        Archetype playerArchetype = AOArchetypes.player(world);
        player = world.createEntity(playerArchetype);
        E(player)
                .pos2DX(50)
                .pos2DY(50)
                .worldPosX(50)
                .worldPosY(50)
                .worldPosMap(1)
                .focused(true)
                .playerControllable(true)
                .headingCurrent(Heading.HEADING_NORTH)
                .headIndex(4)
                .bodyIndex(100)
                .weaponIndex(8)
                .shieldIndex(3)
                .helmetIndex(6)
                .statusHealth(120)
                .statusMaxHealth(120)
                .statusHungry(100)
                .statusMana(1000)
                .statusMaxMana(1000)
                .statusStamina(100)
                .statusThirst(100)
                .statusCriminal(true)
                .infoName("guidota")
                .infoClan("Clarinete")
                .canWrite()
                .networkId(player.getId())
                .aOPhysics();
        return player;
    }

    public void initSystems() {
        server = new KryonetServerMarshalStrategy();
        builder
                .with(new SuperMapper())
                .with(new ServerSystem(server))
                .with(new RandomMovementSystem())
                .with(new DeltaSubscriptionManager());
                // Logic systems
                // TODO AI-NPC
    }

    public static DeltaSubscriptionManager getSubscriptionManager() {
        return world.getSystem(DeltaSubscriptionManager.class);
    }

    public static Entity getEntity(int entityId) {
        return world.getEntity(entityId);
    }

    public static int getEntityId(int connectionId) {
        IntBag entitiesOf = getSubscriptionManager().getEntitiesOf(connectionId);
        if (entitiesOf.isEmpty()) {
            return INVALID_USER;
        }
        // should have only one entity
        return entitiesOf.getData()[0];
    }

    public void createWorld() {
        world = new World(builder.build());

        // testing
        Archetype playerArchetype = AOArchetypes.player(world);

        Entity player2 = world.createEntity(playerArchetype);
        E(player2)
                .pos2DX(50)
                .pos2DY(50)
                .worldPosX(50)
                .worldPosY(50)
                .worldPosMap(1)
                .headingCurrent(Heading.HEADING_NORTH)
                .headIndex(2)
                .bodyIndex(2)
                .weaponIndex(3)
                .shieldIndex(3)
                .helmetIndex(3)
                .statusNewbie(true)
                .dialogText("aa")
                .randomMovement(true)
                .infoName("guidota2")
                .aOPhysics();
    }

    public void start() {
        new Thread(() -> {
            double ns = 1000000000.0 / 60.0;
            double delta = 0;

            long lastTime = System.nanoTime();
            long timer = System.currentTimeMillis();

            while (running) {
                if (pause) {
                    continue;
                }
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                while (delta >= 1) {
                    world.process();
                    delta--;
                }
            }
            System.out.println("Server down");
            getServer().stop();
        }).start();
    }

    public static KryonetServerMarshalStrategy getServer() {
        return server;
    }

    public void pause() {
        this.pause = true;
    }

    public void resume() {
        this.pause = false;
    }

    public void stop() {
        running = false;
    }
}
