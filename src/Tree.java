public interface Tree<T extends Comparable<T>> {

    Tree<T> insert(T data);
    boolean isEmpty();
    T getMin();
    T getMax();
    void traverse();
    void delete(T data);
}
