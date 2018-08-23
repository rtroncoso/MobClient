package character;

import com.artemis.Component;

public class Info extends Component {

    public String name;
    public String clan;
    public String desc;

    public Info() {}

    public Info(String name, String clan, String desc) {
        this.name = name;
        this.clan = clan;
        this.desc = desc;
    }
}
