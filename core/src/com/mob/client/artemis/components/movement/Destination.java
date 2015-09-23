package com.mob.client.artemis.components.movement;

import com.artemis.Component;
import com.mob.client.artemis.components.position.WorldPos;

import java.io.Serializable;

/**
 * Destination Class
 *
 * @author rt
 */
public class Destination extends Component implements Serializable {

    public WorldPos pos;

    public Destination(int x, int y) {
        this(new WorldPos(x, y));
    }

    public Destination(WorldPos pos) {
        this.pos = pos;
    }

}
