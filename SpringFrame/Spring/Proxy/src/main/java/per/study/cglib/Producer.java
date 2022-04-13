package per.study.cglib;

public class Producer {

    /*销售*/
    public Object saleProduct(float money) {
        System.out.println("销售产品，并拿到钱：" + money);
        return money;
    }

    /*售后*/
    public void afterService(float money) {
        System.out.println("提供售后服务，并拿到钱：" + money);
    }

}
