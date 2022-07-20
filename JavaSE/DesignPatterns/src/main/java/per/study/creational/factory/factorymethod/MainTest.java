package per.study.creational.factory.factorymethod;

public class MainTest {

    public static void main(String[] args) {
        AbstractCarFactory carFactory = new VanCarFactory();
        AbstractCar abstractCar = carFactory.newCar();
        abstractCar.run();

        carFactory = new MiniCarFactory();
        AbstractCar abstractCar1 = carFactory.newCar();
        abstractCar1.run();
    }

}
