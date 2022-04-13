package per.study.creation.factory.abstractfactory;

public class N95Mask extends AbstractMask{

    public N95Mask() {
        this.level = 1;
    }

    @Override
    public void protect() {
        System.out.println("N95 .. super");
    }
}
