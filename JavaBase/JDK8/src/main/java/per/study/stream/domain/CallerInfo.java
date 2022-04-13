package per.study.stream.domain;

public class CallerInfo {

    private String num;
    private String content;
    private String callId;
    private String template;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "CallerInfo{" +
                "num='" + num + '\'' +
                ", content='" + content + '\'' +
                ", callId='" + callId + '\'' +
                ", template='" + template + '\'' +
                '}';
    }
}
