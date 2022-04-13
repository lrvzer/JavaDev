package per.study.creation.factory.factorymethod;

/**
 * 具体工场：ConcreteFactory
 */
public class MiniCarFactory extends AbstractCarFactory{
    @Override
    AbstractCar newCar() {
        return new MiniCar();
    }
}
