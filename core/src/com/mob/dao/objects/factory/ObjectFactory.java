package com.mob.dao.objects.factory;

import com.mob.dao.objects.types.*;
import com.mob.dao.objects.types.common.*;

import java.io.DataInputStream;

public class ObjectFactory {

    public Obj createObject(Type kind, String name, int grhIndex) {
        switch (kind) {
            case ANVIL:
                return new AnvilObj(name, grhIndex);
            case GEM:
                return new GemObj(name, grhIndex);
            case AURA:
            case BOAT:
                return new BoatObj(name, grhIndex) ;
            case BOOK:
                return new BookObj(name, grhIndex);
            case DOOR:
                return new DoorObj(name, grhIndex);
            case FOOD:
                return new Food(name, grhIndex);
            case GOLD:
                return new GoldObj(name, grhIndex);
            case KEYS:
                return new KeyObj(name, grhIndex);
            case RING:
                return new MagicObj(name, grhIndex);
            case TREE:
                return new TreeObj(name, grhIndex);
            case WOOD:
                return new WoodObj(name, grhIndex);
            case ARMOR:
                return new ArmorObj(name, grhIndex);
            case ARROW:
                return new ArrowObj(name, grhIndex);
            case DRINK:
                return new DrinkObj(name, grhIndex);
            case FORGE:
                return new ForgeObj(name, grhIndex);
            case FORUM:
                return new ForumObj(name, grhIndex);
            case JEWEL:
                return new JewelObj(name, grhIndex);
            case METAL:
                return new MineralObj(name, grhIndex);
            case SPELL:
                return new SpellObj(name, grhIndex);
            case STAIN:
                return new StainObj(name, grhIndex);
            case BOTTLE:
                return new DrinkObj(name, grhIndex);
            case FLOWER:
                return new FlowerObj(name, grhIndex);
            case HELMET:
                return new HelmetObj(name, grhIndex);
            case POSTER:
                return new PosterObj(name, grhIndex);
            case POTION:
                return new PotionObj(name, grhIndex);
            case SHIELD:
                return new ShieldObj(name, grhIndex);
            case WEAPON:
                return new WeaponObj(name, grhIndex);
            case BONFIRE:
                return new BonfireObj(name, grhIndex);
            case DEPOSIT:
                return new DepositObj(name, grhIndex);
            case MUSICAL:
                return new MusicalObj(name, grhIndex);
            case TELEPORT:
                return new TeleportObj(name, grhIndex);
            case CONTAINER:
                return new ContainerObj(name, grhIndex);
            case FURNITURE:
                return new FurnitureObj(name, grhIndex);
            case EMPTY_BOTTLE:
                default:
                    return new Obj(name, grhIndex) {
                        @Override
                        public Type getType() {
                            return null;
                        }
                    };
        }
    }

    public void fillObject(Obj obj, DataInputStream input){
        boolean collectable = false;
        int value = 0;
        boolean crucial = false;
        boolean newbie = false;
        boolean notDrop = false;
        // TODO consume from input

        obj.setCollectable(collectable);
        obj.setValue(value);
        obj.setCrucial(crucial);
        obj.setNewbie(newbie);
        obj.setNotDrop(notDrop);
    }
}
