package per.study.juc.design.chapter18;

/**
 * {@link ActiveObject#displayString(String)}
 */
public class DisplayRequest extends MethodRequest {

    private final String text;

    public DisplayRequest(Servant servant, String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        servant.displayString(text);
    }

}
