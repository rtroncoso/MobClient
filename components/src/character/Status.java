package character;

import com.artemis.Component;

import java.io.Serializable;

public class Status extends Component implements Serializable {

    public int level;
    public int exp;
    public int elv;

    public int health;
    public int mana;
    public int stamina;
    public int hungry;
    public int thirst;

    public int maxHealth;
    public int maxMana;
    public int maxStamina;
    public int maxHungry;
    public int maxThirst;

    public boolean criminal;
    public boolean newbie;
    public boolean gm;

    public Status() {}

}
