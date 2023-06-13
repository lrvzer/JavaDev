package per.study.juc.atomic.chapter2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<Simple> atomic = new AtomicReference<>(new Simple("Alex", 12));
        System.out.println(atomic.get());
        boolean result = atomic.compareAndSet(new Simple("ddd", 22), new Simple("dd", 12));
        System.out.println(result);

        JButton button = new JButton();
        // default
        final AtomicReference<Simple> s = new AtomicReference<>(new Simple("test", 12));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.set(new Simple("sdfs", 24));
            }
        });
    }

    static class Simple {
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}