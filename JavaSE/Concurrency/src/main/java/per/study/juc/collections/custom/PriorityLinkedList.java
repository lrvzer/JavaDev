package per.study.juc.collections.custom;

public class PriorityLinkedList<E extends Comparable<E>>
{

    private Node<E> first;
    private final Node<E> NULL = (Node<E>) null;
    private static final String PLAIN_NULL = "null";
    private int size;

    public PriorityLinkedList() {
        this.first = NULL;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public static <E extends Comparable<E>> PriorityLinkedList<E> of(E... elements) {
        PriorityLinkedList<E> list = new PriorityLinkedList<>();
        if (elements.length > 0) {
            for (E e : elements) {
                list.addFirst(e);
            }
        }
        return list;
    }

    public PriorityLinkedList<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);

        Node<E> previous = null;
        Node<E> current = first;
        while (current != null && e.compareTo(current.value) > 0) {
            previous = current;
            current = current.next;
        }

        if (previous == NULL) {
            first = newNode;
        }
        else {
            previous.next = newNode;
        }
        newNode.next = current;
        size++;
        return this;
    }

    public boolean contains(E e) {
        Node<E> current = first;
        while (current != null) {
            if (current.value == e) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            /**
             * also return the NULL always when the linked list is empty
             */
            throw new NoElementException("The linked list is empty");
        }

        Node<E> node = first;
        first = node.next;
        size--;
        return node.value;
    }

    @Override
    public String toString() {
        if (this.isEmpty())
            return "[]";
        else {
            StringBuilder builder = new StringBuilder("[");
            Node<E> current = first;
            while (current != null) {
                builder.append(current).append(",");
                current = current.next;
            }
            builder.replace(builder.length() - 1, builder.length(), "]");
            return builder.toString();
        }
    }

    private static class Node<E>
    {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (null != value)
                return value.toString();
            return PLAIN_NULL;
        }
    }

    static class NoElementException extends RuntimeException
    {
        public NoElementException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        PriorityLinkedList<Integer> list = PriorityLinkedList.of(10, 0, -10, 0, 100, 88, 90, 2);
        System.out.println(list.size());
        System.out.println(list);
    }

}
