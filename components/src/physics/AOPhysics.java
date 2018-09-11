/*******************************************************************************
 * Copyright (C) 2015  Rodrigo Troncoso
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package physics;

import com.artemis.Component;

import java.io.Serializable;
import java.util.Deque;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

public class AOPhysics extends Component implements Serializable {

    public final static float MAX_VELOCITY = Float.MAX_VALUE;
    public final static float WALKING_VELOCITY = 140.0f;

    public Deque<Movement> intentions = new ConcurrentLinkedDeque<Movement>();

    public AOPhysics() {}

    public Optional<Movement> getMovementIntention() {
        return Optional.ofNullable(intentions.isEmpty() ? null : intentions.getFirst());
    }

    public void addIntention(Movement movement) {
        intentions.add(movement);
    }

    public void removeIntention(Movement movement) {
        intentions.remove(movement);
    }

    public enum Movement {
        UP,
        DOWN,
        RIGHT,
        LEFT;

        Movement() {}
    }

}
