package entity.character.info;

import com.artemis.Component;
import interfaces.IUpdatable;
import interfaces.IUpdateProcessor;

import java.io.Serializable;

public class Description extends Component implements Serializable{

    public String desc;

    public Description() {}

    public Description(String desc) {
        this.desc = desc;
    }

}
