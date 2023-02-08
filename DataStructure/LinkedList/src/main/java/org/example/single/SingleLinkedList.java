package org.example.single;

import org.example.AbstractList;

public class SingleLinkedList<E> extends AbstractList<E> {
    private Node<E> firstNode;

    /**
     * 返回index位置对应的元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = getNode(index);

        E oldEle = node.element;
        node.element = element;

        return oldEle;
    }

    /**
     * 往index位置添加元素
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckAdd(index);
        if (index == 0) {
            firstNode = new Node<>(element, firstNode);
        } else {
            Node<E> prev = getNode(index - 1);
            prev.nextNode = new Node<>(element, prev.nextNode);
        }
        size++;
    }

    /**
     * 删除index位置对应的元素
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = firstNode;
        if (index == 0) {
            firstNode = firstNode.nextNode;
        } else {
            Node<E> prev = getNode(index - 1);
            node = prev.nextNode;
            prev.nextNode = node.nextNode;
        }
        size--;
        return node.element;
    }

    /**
     * @param element
     */
    @Override
    public void remove(E element) {
        remove(indexOf(element));
    }

    /**
     * 查看元素的位置
     *
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> head = firstNode;
            for (int i = 0; i < size; i++) {
                if (head.element == null) return i;
                head = head.nextNode;
            }
        } else {
            Node<E> head = firstNode;
            for (int i = 0; i < size; i++) {
                if (element.equals(head.element)) return i;
                head = head.nextNode;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 清空所有元素
     */
    @Override
    public void clear() {
        size = 0;
        firstNode = null;
    }

    private static class Node<E> {
        E element;
        Node<E> nextNode;

        public Node(E element, Node<E> nextNode) {
            this.element = element;
            this.nextNode = nextNode;
        }
    }

    private Node<E> getNode(int index) {
        rangeCheck(index);

        Node<E> head = firstNode;
        for (int i = 0; i < index; i++) {
            head = head.nextNode;
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size = ").append(size).append(", [");
        Node<E> head = firstNode;
        while (head != null) {
            builder.append(head.element).append(", ");
            head = head.nextNode;
        }
        builder.replace(builder.lastIndexOf(", "), builder.length(), "]");
        return builder.toString();

    }
}
