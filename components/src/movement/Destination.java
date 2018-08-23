package movement;

import com.artemis.Component;

import java.io.Serializable;

/**
 * Destination Class
 *
 * @author rt
 */
public class Destination extends Component implements Serializable {

    public int x;
    public int y;

    public Destination() {}

    public Destination(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
