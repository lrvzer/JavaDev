package per.study.creational.factory.abstractfactory;

public class CommonMask extends AbstractMask{

    public CommonMask() {
        this.level = 2;
    }

    @Override
    public void protect() {
        System.out.println("Common Mask");
    }
}
