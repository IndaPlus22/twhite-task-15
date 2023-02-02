public class MainAVLTree {
    public static void main(String[] args) {

        Tree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(33);
        System.out.println("lulz");
        avlTree.insert(13);
        System.out.println("lulz");
        avlTree.traverse();

        System.out.println("Max is: " + avlTree.getMax());
        System.out.println("Min is: " + avlTree.getMin());

        System.out.println("Deleting 42 from Tree");
        avlTree.delete(42);

        System.out.println("New Max is: " + avlTree.getMax());

        avlTree.traverse();
    }
    
}
