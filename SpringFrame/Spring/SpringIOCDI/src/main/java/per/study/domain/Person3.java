package per.study.domain;

import java.util.*;

public class Person3
{

    private String[] strings;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;
    private Properties prop;

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "Person3{" + "strings=" + Arrays.toString(strings) + ", list=" + list + ", set=" + set + ", map=" + map
                + ", prop=" + prop + '}';
    }
}
