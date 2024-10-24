/***********************************************************************
 ∗ @file: Node.java
 ∗ @description: This program implements the Node class with Comparable interface to represent nodes of the BST. Each node has a value and references to both left and right subtrees.
 ∗ @author: Max Finegan
 ∗ @date: September 24 , 2024
 ***********************************************************************/


public class Node<T extends Comparable<T>> {
    private T element;
    private Node<T> left;
    private Node<T> right;

    // Constructor that initializes the node with an element and sets left and right children to null
    public Node(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }


    // setElement method: setter for the element of the node
    public void setElement(T element) {
        this.element = element;
    }


    // setLeft method: setter for the left child of the node
    public void setLeft(Node<T> leftNode) {
        this.left = leftNode;
    }


    // setRight method: setter for the right child of the node
    public void setRight(Node<T> rightNode) {
        this.right = rightNode;
    }


    // getLeft method: getter for the left child of the node
    public Node<T> getLeft() {
        return this.left;
    }


    // getRight method: getter for the right child of the node
    public Node<T> getRight() {
        return this.right;
    }


    // getElement method: getter for the element of the node
    public T getElement() {
        return this.element;
    }


    // isLeaf method: returns true if the node is a leaf (it has no children)
    public boolean isLeaf() {
        return (this.left == null && this.right == null);
    }

}
