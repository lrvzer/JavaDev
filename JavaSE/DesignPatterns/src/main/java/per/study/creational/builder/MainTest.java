package per.study.creational.builder;

public class MainTest
{
    public static void main(String[] args) {
        AbstractBuilder abstractBuilder = new HuaweiBuilder();
        abstractBuilder.customCpu("4G").customMem("16G").customDisk("128G").customCam("1亿px");
        Phone phone = abstractBuilder.getPhone();
        System.out.println(phone);
    }
}
