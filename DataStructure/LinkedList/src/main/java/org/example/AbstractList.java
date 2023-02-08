package org.example;

public abstract class AbstractList<E> implements List<E> {

    /**
     * 元素的数量
     */
    protected int size;

    /**
     * 元素数量
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素到最后面
     *
     * @param element
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size)
            outOfBounds(index);
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + " out of bound.");
    }

    protected void rangeCheckAdd(int index) {
        if (index < 0 || index > size)
            outOfBounds(index);
    }
}
