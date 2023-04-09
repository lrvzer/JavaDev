package org.example.tree;

import java.util.Comparator;

public class RBTree<E> extends BinarySearchTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
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

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}