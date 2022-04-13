package per.study.creation.prototype.adapter;

public class MoviePlayer implements Player {

    @Override
    public String play() {
        System.out.println("playing");
        String content = "你好";

        // 打印字幕
        System.out.println(content);
        return content;
    }
}
