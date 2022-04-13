package per.study.creation.factory.abstractfactory;

public class SubFactory extends RootFactory{
    @Override
    AbstractCar newCar() {
        return new MiniCar();
    }

    @Override
    AbstractMask newMask() {
        return new N95Mask();
    }
}
