package per.study.thread.base.chapter2.demo3;

public class SimpleCalculatorStrategy implements CalculatorStrategy {
    @Override
    public double calculate(double salary, double bonus) {
        return salary * 0.1 + bonus * 0.15;
    }
}
