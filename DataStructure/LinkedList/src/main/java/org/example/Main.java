package org.example;

import org.example.single.SingleLinkedListV;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

        list.add(10);
        list.remove(0);
        list.add(0, 0);
        list.add(20);
        list.add(30);
        list.remove(2);

        System.out.println(list);
    }
}
