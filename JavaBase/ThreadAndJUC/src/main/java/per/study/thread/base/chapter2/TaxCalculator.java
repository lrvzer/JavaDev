package per.study.thread.base.chapter2;

public class TaxCalculator {
    private final double salary;
    private final double bonus;
    private CalculatorStrategy calculatorStrategy;

    /*
    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }
    */

    public TaxCalculator(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    public double calcTax() {
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double calculate() {
        return calcTax();
    }

    protected double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }
}
