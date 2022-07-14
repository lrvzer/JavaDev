package per.study.creational.prototype.adapter.clazz;

import per.study.creational.prototype.adapter.Zh_JPTranslator;
import per.study.creational.prototype.adapter.Player;

/**
 * 类适配器
 */
public class JPMoviePlayerAdapter extends Zh_JPTranslator implements Player {

    private Player target;

    public JPMoviePlayerAdapter(Player target) {
        this.target = target;
    }

    @Override
    public String play() {
        String play = target.play();
        String translate = translate(play);
        System.out.println(translate);
        return play;
    }
}
