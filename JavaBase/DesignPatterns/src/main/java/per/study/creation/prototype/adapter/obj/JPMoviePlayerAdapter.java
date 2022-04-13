package per.study.creation.prototype.adapter.obj;

import per.study.creation.prototype.adapter.Zh_JPTranslator;
import per.study.creation.prototype.adapter.Player;

/**
 * 类适配器
 */
public class JPMoviePlayerAdapter implements Player {

    private Zh_JPTranslator translator = new Zh_JPTranslator();
    private Player target;

    public JPMoviePlayerAdapter(Player target) {
        this.target = target;
    }

    @Override
    public String play() {
        String play = target.play();
        String translate = translator.translate(play);
        System.out.println(translate);
        return play;
    }
}
