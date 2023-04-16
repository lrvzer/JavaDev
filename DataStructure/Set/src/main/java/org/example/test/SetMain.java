package org.example.test;

import org.example.set.ListSet;
import org.example.set.Set;
import org.example.set.TreeSet;

public class SetMain {

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        Set<Integer> listSet = new TreeSet<>();
        listSet.add(10);
        listSet.add(11);
        listSet.add(11);
        listSet.add(11);
        listSet.add(13);
        listSet.add(10);

        listSet.traversal(new Set.Visitor<Integer>() {
            /**
             * @param element
             * @return
             */
            @Override
            protected boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public static void test1() {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(10);
        listSet.add(11);
        listSet.add(11);
        listSet.add(11);
        listSet.add(13);
        listSet.add(10);

        listSet.traversal(new Set.Visitor<Integer>() {
            /**
             * @param element
             * @return
             */
            @Override
            protected boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

}
