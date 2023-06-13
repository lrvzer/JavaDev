package per.study.creational.factory.abstractfactory;

public class VanCar extends AbstractCar {

    public VanCar() {
        this.engine = "单缸柴油机";
    }

    @Override
    public void run() {
        System.out.println(engine + " --> 哒哒哒");
    }
}
