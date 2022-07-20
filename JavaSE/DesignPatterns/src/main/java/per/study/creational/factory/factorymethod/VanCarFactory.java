package per.study.creational.factory.factorymethod;

public class VanCarFactory extends AbstractCarFactory {
    @Override
    AbstractCar newCar() {
        return new VanCar();
    }
}
