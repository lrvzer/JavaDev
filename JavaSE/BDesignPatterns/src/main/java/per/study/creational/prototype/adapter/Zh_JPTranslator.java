package per.study.creational.prototype.adapter;

public class Zh_JPTranslator implements Translator{
    @Override
    public String translate(String content) {
        if ("你好".equals(content)) {
            return "哭你几哇";
        }
        if ("什么".equals(content)) {
            return "纳ni";
        }
        return "******";
    }
}
