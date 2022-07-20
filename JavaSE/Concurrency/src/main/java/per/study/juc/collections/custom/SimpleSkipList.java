package per.study.juc.collections.custom;

import java.util.Random;

/**
 * 跳表
 * 
 * <pre>
 * 特点
 *      1.一种随机的数据结构
 *      2.最底层包含整个跳表的所有元素
 *      3.典型的空间换时间的算法
 * </pre>
 */
public class SimpleSkipList
{

    private static final byte HEAD_BIT = (byte) -1; // 头结点
    private static final byte DATA_BIT = (byte) 0;
    private static final byte TAIL_BIT = (byte) 1; // 尾结点

    private static class Node
    {
        private Integer value;
        private Node up, down, left, right;
        private byte bit;

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        private Node(Integer value) {
            this(value, DATA_BIT);
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private int height;
    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null, HEAD_BIT);
        this.tail = new Node(null, TAIL_BIT);
        head.right = tail;
        tail.left = head;
        random = new Random(System.currentTimeMillis());
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public int size() {
        return size;
    }

    private Node find(Integer element) {
        Node current = head;

        for (;;) {
            while (current.right.bit != TAIL_BIT && current.right.value <= element) {
                current = current.right;
            }
            if (current.down != null) {
                current = current.down;
            }
            else {
                break;
            }
        }
        return current;
    }

    public boolean contains(Integer element) {
        Node node = this.find(element);
        return (node.value == element);
    }

    public Integer get(Integer element) {
        Node node = this.find(element);
        return (node.value == element) ? node.value : null;
    }

    public void add(Integer element) {
        Node index = find(element);
        Node cur = new Node(element);
        cur.left = index;
        cur.right = index.right;
        index.right.left = cur;
        index.right = cur;

        int currentLevel = 0;
        while (random.nextDouble() < 0.5d) { // 随机算法

            if (currentLevel >= height) {
                height++;

                Node dumyHead = new Node(null, HEAD_BIT);
                Node dumyTail = new Node(null, TAIL_BIT);

                dumyHead.right = dumyTail;
                dumyHead.down = head;
                head.up = dumyHead;

                dumyTail.left = dumyHead;
                dumyTail.down = tail;
                tail.up = dumyTail;

                head = dumyHead;
                tail = dumyTail;
            }

            while (index != null && index.up == null) {
                index = index.left;
            }
            index = index.up;
            Node upNode = new Node(element);
            upNode.left = index;
            upNode.right = index.right;
            upNode.down = cur;

            index.right.left = upNode;
            index.right = upNode;

            cur.up = upNode;
            cur = upNode;
            currentLevel++;
        }
        size++;
    }

    public void dumpSkipList() {
        Node tmp = head;
        for (int i = height + 1; i > 0; i--) {
            System.out.printf("Total [%d] height [%d]", height + 1, i);
            Node node = tmp.right;
            while (node.bit == DATA_BIT) {
                System.out.printf("->%d", node.value);
                node = node.right;
            }
            System.out.println("");

            tmp = tmp.down;
        }
    }

    public static void main(String[] args) {
        SimpleSkipList skipList = new SimpleSkipList();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            skipList.add(r.nextInt(1000));
        }
        skipList.dumpSkipList();
    }

}
