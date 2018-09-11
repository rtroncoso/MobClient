package entity.character.status;

import com.artemis.Component;
import interfaces.IUpdatable;
import interfaces.IUpdateProcessor;

import java.io.Serializable;

public class Stamina extends Component implements Serializable{

    public int min;
    public int max;

    public Stamina() {}
}
