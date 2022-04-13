package per.study.juc.thread.chapter2;

public class TaxCalculatorMain {
    public static void main(String[] args) {
        /*TaxCalculator calculator = new TaxCalculator(10000d, 2000d) {
            @Override
            public double calcTax() {
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };
        double v = calculator.calculate();
        System.out.println(v);*/
        TaxCalculator calculator = new TaxCalculator(10000d, 2000d, (salary, bonus) -> salary * 0.1 + bonus * 0.15);
        /*CalculatorStrategy calculatorStrategy = new SimpleCalculatorStrategy();
        calculator.setCalculatorStrategy(calculatorStrategy);*/
        System.out.println(calculator.calculate());
    }
}
