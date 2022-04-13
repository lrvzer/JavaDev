package Epoint;

import org.junit.Test;

import javax.sound.sampled.Line;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestW {

    @Test
    public void test() {
        final CopyOnWriteArrayList<LineInfo> lineInfoArrayList = new CopyOnWriteArrayList<>();
        LineInfo lineInfo = new LineInfo();
        lineInfoArrayList.add(lineInfo);
        lineInfo.setLine(1);
        lineInfoArrayList.stream().forEach(System.out::println);
        LineInfo bline = null;
        for (LineInfo a : lineInfoArrayList) {
            if (a.line == 1) {
                a.setName("hello");
                bline = a;
            }
        }
        System.out.println("----1-----");
        System.out.println(bline);
        System.out.println("----2-----");
        lineInfoArrayList.stream().forEach(System.out::println);

        boolean flag = lineInfoArrayList.remove(bline);
        System.out.println("----3-----\n" + flag);
        lineInfoArrayList.stream().forEach(System.out::println);
        flag = lineInfoArrayList.remove(bline);
        System.out.println("----4-----\n" + flag);


//        System.out.println(lineInfo);
    }

    @Test
    public void testCar() {

        LineInfo lineInfo = new LineInfo();
        Car car = lineInfo.getCar();
        car.setName("Tom");
        car.setMoney(1000f);
        car.setUuid(UUID.randomUUID().toString());

        System.out.println(lineInfo);
    }


    @Test
    public void testCCC() {
         ConcurrentHashMap<Integer, Car> lineNum_status_maps = new ConcurrentHashMap<>();
         Car car = lineNum_status_maps.get(1);
         if (car == null) {
             car = new Car();
             lineNum_status_maps.put(1, car);
         }
         car.setMoney(11);
         System.out.println(lineNum_status_maps);

    }

    class Car {
        private String uuid;
        private String name;
        private String band;
        private float money;

        @Override
        public String toString() {
            return "Car{" +
                    "uuid='" + uuid + '\'' +
                    ", name='" + name + '\'' +
                    ", band='" + band + '\'' +
                    ", money=" + money +
                    '}';
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBand() {
            return band;
        }

        public void setBand(String band) {
            this.band = band;
        }

        public float getMoney() {
            return money;
        }

        public void setMoney(float money) {
            this.money = money;
        }
    }

    class LineInfo {
        private int line;
        private String name;
        private int type;
        private Car car = new Car();


        public Car getCar() {
            return car;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "LineInfo{" +
                    "line=" + line +
                    ", name='" + name + '\'' +
                    ", type=" + type +
                    ", car=" + car +
                    '}';
        }
    }

}
