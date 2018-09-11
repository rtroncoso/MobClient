package com.mob.client.artemis.archetypes;

import entity.Body;
import entity.Head;
import entity.character.Character;
import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.World;
import physics.AOPhysics;
import position.Pos2D;
import position.WorldPos;

public class AOArchetypes {

    public static Archetype player(World world) {
         return new ArchetypeBuilder()
                 .add(Character.class)
                 .add(WorldPos.class)
                 .add(Pos2D.class)
                 .add(Body.class)
                 .add(Head.class)
                 .add(AOPhysics.class)
                 // TODO Complete
                 .build(world);
    }
}
