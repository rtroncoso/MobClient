package character;

import com.artemis.Component;

public class Dialog extends Component {

    public String text;
    public float time;
    public float alpha;

    public Dialog() {}

    public Dialog(String text, float time, float alpha) {
        this.text = text;
        this.time = time;
        this.alpha = alpha;
    }
}
