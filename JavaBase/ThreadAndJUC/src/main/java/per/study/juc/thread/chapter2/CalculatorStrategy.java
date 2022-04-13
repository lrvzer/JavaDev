package per.study.juc.thread.chapter2;

@FunctionalInterface
public interface CalculatorStrategy {
    double calculate(double salary, double bonus);
}
