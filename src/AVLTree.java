public class AVLTree<T extends Comparable<T>> implements  Tree<T> {

    private Node<T> root;

    public AVLTree() {
        root = null;
    }

    @Override
    public Tree<T> insert(T data) {
        if (isEmpty()) {
            System.out.println("Inserting " + data + " at root");
            root = new Node<T>(data);
        } else {
            insert(data, root);
        }
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        //
        if (node == null) {
            System.out.println("Inserting " + data + " at " + node);
            return new Node<>(data);
        }
        // If the data is less than the current node, go left
        System.out.println(
                "Comparing " + data + " with " + node.getData() + " to go left or right"
        );
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        // If the data is greater than the current node, go right
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        }
        else {
            return node;
            }
        newHeight(node);
        return rotation(node);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    
    private T getMin(Node <T> node) {
        Node<T> current = root;
        while (current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return current.getData();
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node <T> node) {
        Node<T> current = root;
        while (current.getRightChild() != null) {
            current = current.getRightChild();
        }
        return current.getData();
    }

    @Override
    public void traverse() {
        if (isEmpty()) {
            return;
        }
        inOrderTraversal(root);
    }

    // In-order traversal: left, root, right
    private void inOrderTraversal(Node<T> node) {
        if (node != null) {
            // Traverse the left subtree
            inOrderTraversal(node.getLeftChild());
            System.out.print(node);
            // Traverse the right subtree
            inOrderTraversal(node.getRightChild());
        }

    }

    @Override
    public void delete(T data) {
        if (isEmpty()) {
            return;
        }
        root = delete(data, root);
    }

    private Node<T> delete(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        // If the data to be deleted is less than the current node, go left
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        // If the data to be deleted is greater than the current node, go right
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            // Leaf node case or one child case
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            }
            else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }
            // Two children case
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(), node.getLeftChild()));
        }
        newHeight(node);
        return rotation(node);

    }

    private void newHeight(Node <T> node) {
        // Set the height of the node by getting the max height of the left and right child and adding 1
        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
    }

    private int height(Node <T> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    private int balanceFactor(Node <T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    private Node<T> rotation(Node<T> node) {
        // If the balance factor is greater than 1, the tree is left heavy
        if (balanceFactor(node) > 1) {
            // If the balance factor of the left child is less than 0, the left subtree is right heavy
            if (balanceFactor(node.getLeftChild()) < 0) {
                // Left rotation on the left child
                node.setLeftChild(leftRotation(node.getLeftChild()));
            }
            // Right rotation on the parent
            return rightRotation(node);
        // If the balance factor is less than -1, the tree is right heavy
        } if (balanceFactor(node) < -1) {
            // If the balance factor of the right child is greater than 0, the right subtree is left heavy
            if (balanceFactor(node.getRightChild()) > 0) {
                // Right rotation on the right child
                node.setRightChild(rightRotation(node.getRightChild()));
            }
            // Left rotation on the parent
            return leftRotation(node);
        }
        return node;
    }

    private Node<T> rightRotation(Node<T> node) {
        System.out.println("Rotating to the right on node " + node);
        Node<T> tempLeftNode = node.getLeftChild();
        Node<T> tempCentreNode = tempLeftNode.getRightChild();
        tempLeftNode.setRightChild(node);
        node.setLeftChild(tempCentreNode);
        newHeight(node);
        newHeight(tempLeftNode);
        return tempLeftNode;
    }

    private Node<T> leftRotation(Node<T> node) {
        System.out.println("Rotating to the left on node " + node);
        Node<T> tempRightNode = node.getRightChild();
        Node<T> tempCentreNode = tempRightNode.getLeftChild();
        tempRightNode.setLeftChild(node);
        node.setRightChild(tempCentreNode);
        newHeight(node);
        newHeight(tempRightNode);
        return tempRightNode;
    }
    
}

    