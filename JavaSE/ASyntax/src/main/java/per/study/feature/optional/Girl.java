package per.study.feature.optional;

public class Girl
{
    private String name;

    public Girl() {
    }

    public Girl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Girl{" + "name='" + name + '\'' + '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
