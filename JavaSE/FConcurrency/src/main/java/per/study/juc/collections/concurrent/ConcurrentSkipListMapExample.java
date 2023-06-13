package per.study.juc.collections.concurrent;

import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample
{
    public static void main(String[] args) {
        // testCeiling();
        // testFloor();
        // testFirst();
        // testLast();
        // testMerge();
        testCompute();
    }

    private static void testCeiling() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "Java");
        map.put(5, "Scale");
        map.put(10, "C");
        System.out.println(map);
        System.out.println(map.ceilingKey(2));
        System.out.println(map.ceilingEntry(2).getValue());
        System.out.println(map.ceilingEntry(5).getValue());
    }

    private static void testFloor() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "Java");
        map.put(5, "Scale");
        map.put(10, "C");
        System.out.println(map.floorKey(2));
        System.out.println(map.floorEntry(2).getValue());
    }

    private static void testFirst() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "Java");
        map.put(5, "Scale");
        map.put(10, "C");
        System.out.println(map.firstKey());
        System.out.println(map.firstEntry().getValue());
    }

    private static void testLast() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "Java");
        map.put(5, "Scale");
        map.put(10, "C");
        System.out.println(map.lastKey());
        System.out.println(map.lastEntry().getValue());
    }

    private static void testMerge() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "Java");
        map.put(5, "Scale");
        map.put(10, "C");

        String ret = map.merge(1, "C++", (ov, v) -> {
            System.out.println(ov);
            System.out.println(v);
            return ov + v;
        });

        System.out.println(ret);
        System.out.println(map.get(1));
    }

    private static void testCompute() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "Java");
        map.put(5, "Scale");
        map.put(10, "C");

        String ret = map.compute(1, (k, v) -> {
            System.out.println(k);
            System.out.println(v);
            return "hello";
        });
        System.out.println(ret);
        System.out.println(map.get(1));
    }

}
