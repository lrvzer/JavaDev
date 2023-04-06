package org.example.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> {

    private int size;

    // 根节点
    private Node<E> root;

    private final Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(Visitor<E> visitor) {
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(Visitor<E> visitor) {
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        inorderTraversal(node.left, visitor);
        visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }

    /**
     * 后续遍历
     */
    public void postorderTraversal(Visitor<E> visitor) {
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        visitor.visit(node.element);
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraversal() {
        if (root == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        // 队列不为空，需要一直遍历
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll(); // 出队
            System.out.print(node.element + " ");

            // 当前node节点左右子树入队
            if (node.left != null)
                queue.offer(node.left);

            if (node.right != null)
                queue.offer(node.right);
        }
    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        // 队列不为空，需要一直遍历
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll(); // 出队
            visitor.visit(node.element);

            // 当前node节点左右子树入队
            if (node.left != null)
                queue.offer(node.left);

            if (node.right != null)
                queue.offer(node.right);
        }
    }

    /**
     * 递归：树的高度
     *
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * 递归
     * 指定节点的高度：一个节点的高度等于左右子树的高度最大值加一
     *
     * @param node
     * @return
     */
    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 非递归
     * 利用层序遍历
     * 每遍历完一层的最后一个结点时，队列的大小（levelSize）即为下一层所包含节点的数量
     *
     * @return
     */
    public int heightWithNonCursive() {
        if (root == null) return 0;

        int height = 0; // 树的高度
        int levelSize = 1; // 存储每一层的元素数量

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);

            if (levelSize == 0) { // 意味着即将要访问下一层
                levelSize = queue.size();
                height++;
            }
        }

        return height;
    }

    /**
     * 是否为完全二叉树
     * (1) node.left != null && node.right != null --> node.left node.right 入队
     * (2) node.left == null && node.right != null --> return false
     * (3) (node.left != null && node.right == null) || (node.left == null && node.right == null)
     *
     * @return
     */
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf())
                return false;

            if (node.left != null && node.right != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null)
                return false;
            else {
                leaf = true;
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return true;
    }

    public boolean isCompleteImprove() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && node.isLeaf()) return false;

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) { // 左结点空，右结点不为空
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else { // node.right == null
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 二叉树翻转
     *
     * @return
     */
    public Node<E> invertTree() {
        return invertTree(root);
    }

    public Node<E> invertTree(Node<E> node) {
        if (node != null)
            return node;

        Node<E> tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        invertTree(node.left);
        invertTree(node.right);

        return node;
    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加后续节点，并找到父节点
        Node<E> node = root;
        Node<E> parent = root;
        int comp = 0;
        while (node != null) {
            // 待插入数据，与当前遍历节点数据进行比较
            comp = compare(element, node.element);
            parent = node;
            if (comp > 0) {
                node = node.right;
                continue;
            }

            if (comp < 0) {
                node = node.left;
                continue;
            }

            // comp == 0，覆盖
            node.element = element;
            return;
        }

        // 看看插入到父节点的哪个位置
        Node<E> pNode = new Node<>(element, parent);
        if (comp > 0)
            parent.right = pNode;
        else
            parent.left = pNode;

        size++;
    }

    /**
     * @param e1
     * @param e2
     * @return = 0, e1 == e2
     * > 0, e1  > e2
     * < 0, e1  < e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    /**
     * 前驱结点
     *
     * @param node
     * @return
     */
    public Node<E> processor(Node<E> node) {
        if (node == null) return null;

        // 前驱结点在左子树当中（left.right.right...）
        if (node.left != null) {
            Node<E> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父结点、祖父结点中寻找前驱结点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 后继结点
     *
     * @param node
     * @return
     */
    public Node<E> successor(Node<E> node) {
        if (node == null) return null;

        Node<E> p = node.right;
        // 后继结点在右子树当中（right.left.left...）
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
        }

        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        return node.parent;
    }

    public void remove(E element) {
        remove(node(element));
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private void remove(Node<E> node) {
        if (node == null) return;

        if (node.left != null && node.right != null) { // 删除度为2的结点
            // 找到后继结点
            Node<E> s = successor(node);
            // 用后继结点的值覆盖度为2的结点的值
            node.element = s.element;
            // 删除后继结点
            node = s;
        }

        // 删除node结点 (node度为1或者0)
        Node<E> child = node.left != null ? node.left : node.right;
        if (child != null) { // 度为1
            // 更改parent
            child.parent = node.parent;
            // node为根结点
            if (node.parent == null) {
                root = child;
            }
            // 子结点替代父结点：更改parent的right、left指向
            else if (node == node.parent.left) {
                node.parent.left = child;
            } else {
                node.parent.right = child;
            }
        }
        // node度为0 且为根结点
        else if (node.parent == null) {
            root = null;
        }
        // node度为0，且为叶子结点
        else {
            // node父结点确定node为左树时，左树置空
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
        size--;
    }

    /**
     * 根据元素element获取结点对象
     *
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        int comp;
        while (node != null) {
            comp = compare(element, node.element);
            if (comp == 0) return node;
            if (comp > 0)
                node = node.right;
            else
                node = node.left;
        }
        return null;
    }

    /**
     * 元素不能为空
     *
     * @param element != null
     */
    private void elementNotNullCheck(E element) {
        if (element == null)
            throw new IllegalArgumentException("element must not be null!");
    }

    public static interface Visitor<E> {
        void visit(E element);
    }

    private static class Node<E> {
        Node<E> left;
        E element;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        /**
         * 当前结点是否为叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return (left == null && right == null);
        }
    }
}
