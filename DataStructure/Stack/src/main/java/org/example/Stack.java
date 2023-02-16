package org.example;

import org.example.ArrayList;

public class Stack<E>{

    private ArrayList<E> list = new ArrayList<>();

    public void push(E ele) {
        list.add(ele);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E top() {
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

}
