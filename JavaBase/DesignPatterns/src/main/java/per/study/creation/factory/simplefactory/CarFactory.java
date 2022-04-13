package per.study.creation.factory.simplefactory;

public class CarFactory {
    // 核心方法：一切从简
    public AbstractCar newCar(String type) {
        if ("van".equals(type)) {
            return new VanCar();
        }
        else if ("mini".equals(type)) {
            return new MiniCar();
        }

        // 更多产品，违反开闭原则
        return null;
    }
}
