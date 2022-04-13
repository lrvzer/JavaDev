package per.study.creation.factory.abstractfactory;

public class MainTest {
    public static void main(String[] args) {
        RootFactory rootFactory = new SubFactory();
        AbstractCar abstractCar = rootFactory.newCar();
        abstractCar.run();
    }
}
