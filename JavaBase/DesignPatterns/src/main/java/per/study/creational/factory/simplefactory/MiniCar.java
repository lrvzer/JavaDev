package per.study.creational.factory.simplefactory;

public class MiniCar extends AbstractCar {
    public MiniCar() {
        this.engine = "四缸柴油发动机";
    }

    @Override
    public void run() {
        System.out.println(engine + " --> 嗡嗡嗡");
    }
}
