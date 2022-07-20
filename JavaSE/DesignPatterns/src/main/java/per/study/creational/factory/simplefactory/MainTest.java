package per.study.creational.factory.simplefactory;

public class MainTest {

    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        AbstractCar van = carFactory.newCar("van");
        AbstractCar mini = carFactory.newCar("mini");
        AbstractCar no = carFactory.newCar("no");
        van.run();
        mini.run();
        no.run();
    }

}
