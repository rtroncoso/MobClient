package character;

import com.artemis.Component;

public class States extends Component {

    // states
    public boolean writing;
    public boolean meditating;
    public boolean resting;

    public States(){}

    public boolean isInAnyState() {
        return writing || meditating || resting;
    }
}
