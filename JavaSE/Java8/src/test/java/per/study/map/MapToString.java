package per.study.map;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapToString {

    public static void main(String[] args) {

        LinkedHashMap<String, String> rets = new LinkedHashMap<>();
//        rets.put("ret1", "1");
//        rets.put("ret3", "3");
//        rets.put("ret2", "2");

        System.out.println(rets);
        if (!rets.isEmpty()) {
            Map.Entry<String, String> tailByReflection = getTailByReflection(rets);
            System.out.println(tailByReflection);

            assert tailByReflection != null;
            rets.put(tailByReflection.getKey(), "new");

            System.out.println(rets);
        }
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map.Entry<K, V> getTailByReflection(
            LinkedHashMap<K, V> map) {
        Field tail;
        try {
            tail = map.getClass().getDeclaredField("tail");
            tail.setAccessible(true);
            return (Map.Entry<K, V>) tail.get(map);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
