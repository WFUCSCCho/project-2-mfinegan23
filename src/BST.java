/***********************************************************************
 ∗ @file: BST.java
 ∗ @description: This program implements the BST class with Comparable interface to manage the structure of BST.
 ∗ @author: Max Finegan
 ∗ @date: September 24 , 2024
 ***********************************************************************/


import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> root;
    private int size;

    // Constructor that initializes an empty binary search tree
    public BST() {
        root = null;
        size = 0;
    }


    // Clears the entire tree by setting the root to null and resetting the size
    public void clear() {
        root = null;
        size = 0;
    }

    // Returns the number of nodes in the tree
    public int size() {
        return size;
    }


    // Public method to insert an element into the tree
    public void insert(T element) {
        root = insertHelper(root, element);
    }

    // Private helper method to recursively insert a node in the correct position
    private Node<T> insertHelper (Node<T> node, T element) {
        if (node == null) {
            size++;
            return new Node<>(element);
        }

        // Recursively insert into the left subtree if element is smaller
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(insertHelper(node.getLeft(), element));
        }
        // Recursively insert into the right subtree if element is larger
        else if (element.compareTo(node.getElement()) > 0) {
            node.setRight(insertHelper(node.getRight(), element));
        }

        return node;
    }


    // Public method to remove an element from the tree
    public void remove(T element) {
        root = removeHelper(root, element);
    }

    // Private helper method to recursively remove a node from the tree
    private Node<T> removeHelper(Node<T> node, T element) {
        if (node == null) {
            return null;
        }

        // Traverse the left subtree if the element is smaller
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(removeHelper(node.getLeft(), element));
        }
        // Traverse the right subtree if the element is larger
        else if (element.compareTo(node.getElement()) > 0) {
            node.setRight(removeHelper(node.getRight(), element));
        }
        // Node to be removed is found
        else {
            // Case 1: No left child, replace with right child
            if (node.getLeft() == null) {
                size--;
                Node<T> rightChild = node.getRight();

                // Deallocate node (not required because of garbage collector)
                node.setElement(null);
                node = null;

                return rightChild;
            }
            // Case 2: No right child, replace with left child
            else if (node.getRight() == null) {
                size--;
                Node<T> leftChild = node.getLeft();

                // Deallocate node (not required because of garbage collector)
                node.setElement(null);
                node = null;

                return leftChild;
            }
            //ase 3: Node has two children, replace with the smallest node from the right subtree
            Node<T> temp = minLeft(node.getRight());
            node.setElement(temp.getElement());
            node.setRight(removeHelper(node.getRight(), temp.getElement()));
        }
        return node;
    }

    // Finds the smallest node in the left subtree
    private Node<T> minLeft(Node<T> node) {
        Node<T> curr = node;
        while(curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr;
    }


    // Public method to search for an element in the tree
    public Node<T> search(T element) {
        return searchHelper(root, element);
    }

    // Private recursive helper method to search for an element in the tree
    private Node<T> searchHelper(Node<T> node, T element) {
        if (node == null || element.equals(node.getElement())) {
            return node; // Return the node if found or return null if not found
        }
        // Search in the left subtree if the element is smaller
        if (element.compareTo(node.getElement()) < 0) {
            return searchHelper(node.getLeft(), element);
        }
        // Search in the right subtree if the element is larger
        return searchHelper(node.getRight(), element);
    }


    // Returns an iterator for the tree
    public Iterator<T> iterator() {
        return new BSTIterator(root);
    }


    // BSTIterator class for in-order traversal of the tree
    private class BSTIterator implements Iterator<T> {
        private Stack<Node<T>> stack = new Stack<>();

        // Constructor pushes all left nodes onto the stack to prepare for in-order traversal
        public BSTIterator(Node<T> root) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
        }

        // Returns true if there are still nodes left to visit
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        // Visits the next node in in-order traversal
        @Override
        public T next() {
            Node<T> node = stack.pop();
            T result = node.getElement();
            // If the node has a right child, push its leftmost nodes onto the stack
            if (node.getRight() != null) {
                node = node.getRight();
                while (node != null) {
                    stack.push(node);
                    node = node.getLeft();
                }
            }
            return result;
        }

    }

}