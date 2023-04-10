package org.example.tree;

import java.util.Comparator;

public class RBTree<E> extends BalanceBinarySearchTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * @param node 新添加结点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加的是根结点 或者 上溢到了根结点
        if (parent == null) {
            setBlack(node);
            return;
        }

        // 如果父结点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父结点
        Node<E> uncle = parent.getSibling();
        // 祖父结点
        Node<E> grand = setRed(parent.parent);
        // 叔父结点是红色[B树结点上溢]
        if (isRed(uncle)) {
            setBlack(parent);
            setBlack(uncle);

            // 把祖父结点当作是新添加的结点

            afterAdd(grand);
            return;
        }

        // 叔父结点不是红色
        // L
        if (parent.isLeftChild()) {
            // LL
            if (node.isLeftChild()) {
                setBlack(parent);
            }
            // LR
            else {
                setBlack(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }
        // R
        else {
            // RR
            if (node.isRightChild()) {
                setBlack(parent);
            }
            // RL
            else {
                setBlack(node);
                rotateRight(parent);
            }
            rotateLeft(grand);
        }

    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    /**
     * 辅助方法：染色
     *
     * @param node
     * @param color
     */
    private Node<E> setColor(Node<E> node, boolean color) {
        if (node == null) return null;
        ((RBNode<E>) node).color = color;
        return node;
    }

    /**
     * 辅助方法：染红色
     *
     * @param node
     * @return
     */
    private Node<E> setRed(Node<E> node) {
        return setColor(node, RED);
    }

    /**
     * 辅助方法：染黑色
     *
     * @param node
     * @return
     */
    private Node<E> setBlack(Node<E> node) {
        return setColor(node, BLACK);
    }

    /**
     * 获取node的颜色
     *
     * @param node
     * @return
     */
    private boolean getColor(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    /**
     * 结点颜色是否为黑色
     *
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node) {
        return getColor(node) == BLACK;
    }

    /**
     * 结点颜色是否为红色
     *
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node) {
        return getColor(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) str = "R_";
            return str + element.toString();
        }
    }
}