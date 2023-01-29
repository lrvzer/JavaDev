package org.example;

public class Main {
    public static void main(String[] args) {
//        List<Integer> arrayList = new ArrayList();
//
//        for (int i = 0; i < 30; i++) {
//            arrayList.add(i);
//        }
//
//        System.out.println(arrayList);
////        arrayList.remove(arrayList.size() - 1);
//        arrayList.add(arrayList.size() - 1, 10);
//        System.out.println(arrayList);
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Tom"));
        System.out.println(persons);
    }
}