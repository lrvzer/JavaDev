package org.example.tree;

import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 处理恢复平衡的逻辑
     *
     * @param node 新添加结点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 平衡、更新高度
                updateHeight(node);
            } else {
                // 恢复平衡操作
//                reBalance(node);
                reBalanceUnify(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 平衡、更新高度
                updateHeight(node);
            } else {
                // 恢复平衡操作
//                reBalance(node);
                reBalanceUnify(node);
            }
        }
    }

    /**
     * 判断当前结点是否平衡
     *
     * @param node 当前结点
     * @return true is balance, else false
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 统一旋转
     */
    private void reBalanceUnify(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        // L
        if (parent.isLeftChild()) {
            // LL
            if (node.isLeftChild()) {
                rotateUnify(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            }
            // LR
            else {
                rotateUnify(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        }
        // R
        else {
            // RL
            if (node.isLeftChild()) {
                rotateUnify(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            }
            // RR
            else {
                rotateUnify(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    private void rotateUnify(
            Node<E> r, // 这颗子树的根结点
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g) {

        // 让d成为这颗子树的根结点
        d.parent = r.parent;
        if (r.isRightChild())
            r.parent.right = d;
        else if (r.isLeftChild())
            r.parent.left = d;
        else
            root = d;

        // a-b-c
        b.left = a;
        b.right = c;
        if (a != null) a.parent = b;
        if (c != null) c.parent = b;
        updateHeight(b);

        // e-f-g
        f.left = e;
        f.right = g;
        if (e != null) e.parent = f;
        if (g != null) g.parent = f;
        updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;
        f.parent = d;
        b.parent = d;
        updateHeight(d);
    }

    /**
     * 使树恢复平衡
     *
     * @param grand 高度最低的不平衡结点
     */
    private void reBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        // L
        if (parent.isLeftChild()) {
            // LL
            if (node.isLeftChild()) {
                rotateRight(grand);
            }
            // LR
            else {
                rotateLeft(parent);
                rotateRight(grand);
            }
        }
        // R
        else {
            // RL
            if (node.isLeftChild()) {
                rotateRight(parent);
                rotateLeft(grand);
            }
            // RR
            else {
                rotateLeft(grand);
            }
        }
    }

    /**
     * 左旋
     * --grand
     * ----\
     * -----\
     * ----parent
     * ----/     \
     * ---/       \
     * --child     n
     *
     * @param grand
     */
    private void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 右旋
     * -----grand
     * -------/
     * ------/
     * ----parent
     * ----/     \
     * ---/       \
     * --n       child
     *
     * @param grand
     */
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;

        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 旋转之后更新结点性质
     *
     * @param grand
     * @param parent
     * @param child
     */
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 更新parent
        parent.parent = grand.parent;
        if (grand.isLeftChild())
            grand.parent.left = parent;
        else if (grand.isRightChild())
            grand.parent.right = parent;
        else
            root = parent;

        // 更新child
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand
        grand.parent = parent;

        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 更新当前结点的高度
     *
     * @param node
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 平衡因子
         *
         * @return
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新当前结点的高度
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;

            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null)
                parentString = parent.element.toString();
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
