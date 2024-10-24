// AvlTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// boolean remove( x )    --> Return true if x was present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate


/**
 ** @file: AvlTree.java
 *  @description: This program implements an AVL tree, which is a self-balancing
 *                binary search tree. It maintains a logarithmic height, ensuring
 *                O(log n) time complexity for insertion, deletion, and search
 *                operations.
 *  @author: Max Finegan
 *  @date: October 23, 2024
 */

/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>> {
    /**
     * Construct the tree.
     */
    public AvlTree( ) {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x ) {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x ) {
        root = remove( x, root );
    }


    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<AnyType> remove( AnyType x, AvlNode<AnyType> t ) {
	// FINISH ME
        if (t == null) {
            return null;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left); // Continue searching in the left subtree
        }
        else if (compareResult > 0) {
            t.right = remove(x, t.right); // Continue searching in the right subtree
        }
        else {
            // Node found
            if (t.left == null) { // One child
                return t.right;
            }
            else if (t.right == null) { // One child
                return t.left;
            }
            // Two children
            t.element = findMin(t.right).element; // Find the smallest element in right subtree
            t.right = remove(t.element, t.right); // Remove that element from right subtree
        }
        return balance(t);
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( ) {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( ) {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean contains( AnyType x ) {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( ) {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( ) {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( ) {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    private static final int ALLOWED_IMBALANCE = 1;

    // Assume t is either balanced or within one of being balanced
    private AvlNode<AnyType> balance( AvlNode<AnyType> t ) {
	// FINISH ME
        if (t == null) {
            return t;
        }

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) { // Checks for imbalance
            if (height(t.left.left) >= height(t.left.right)) {
                t = rotateWithLeftChild(t); // Single rotation
            }
            else {
                t = doubleWithLeftChild(t); // Double rotation
            }
        }
        else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) { // Checks for imbalance
            if (height(t.right.right) >= height(t.right.left)) {
                t = rotateWithRightChild(t); // Single rotation
            }
            else {
                t = doubleWithRightChild(t); // Double rotation
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;

        return t;
    }

    public void checkBalance( ) {
        checkBalance( root );
    }

    private int checkBalance( AvlNode<AnyType> t ) {
        if( t == null )
            return -1;

        if( t != null ) {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }

        return height( t );
    }


    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t ) {
	// FINISH ME
        if (t == null) {
            return new AvlNode<>(x, null, null);
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) { // x is less than current root
            t.left = insert(x, t.left);
        }
        else if (compareResult > 0) { // x is greater than current root
            t.right = insert(x, t.right);
        }
        else {
            // Duplicate; do nothing
        }
        return balance(t);
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlNode<AnyType> findMin( AvlNode<AnyType> t ) {
	// FINISH ME
        if (t == null) {
            return null;
        }
        else if (t.left == null) {
            return t;
        }
        return findMin(t.left); // Keep going to the left subtree to find min
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private AvlNode<AnyType> findMax( AvlNode<AnyType> t ) {
	// FINISH ME
        if (t == null) {
            return null;
        }
        else if (t.right == null) {
            return t;
        }
        return findMax(t.right); // Keep going to the right to find max
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return true if x is found in subtree.
     */
    private boolean contains( AnyType x, AvlNode<AnyType> t ) {
	// FINISH ME
        if (t == null) {
            return false;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) { // Go left
            return contains(x, t.left);
        }
        else if (compareResult > 0) { // Go right
            return contains(x, t.right);
        }
        return true;
    }

    /**
     * Internal method to print a subtree in (sorted) order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlNode<AnyType> t ) {
	// FINISH ME
        // Prints inorder traversal
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element); // + " "
            printTree(t.right);

        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlNode<AnyType> t ) {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithLeftChild( AvlNode<AnyType> k2 ) {
	// FINISH ME
        AvlNode<AnyType> k1 = k2.left; // k1 is k2's left child
        k2.left = k1.right; // Move k1's right child to be k2's left child
        k1.right = k2; // Do the rotation
        // Update the heights
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1; // Return the new root
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 ) {
	// FINISH ME
        AvlNode<AnyType> k2 = k1.right; // k2 is the right child of k1
        k1.right = k2.left; // Move k2's left child to be k1's right child
        k2.left = k1; // Do the rotation
        // Update the heights
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2; // Return the new root
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 ) {
	// FINISH ME
        k3.left = rotateWithRightChild(k3.left); // Rotate the left child
        return rotateWithLeftChild(k3); // Rotate the new subtree root
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 ) {
	// FINISH ME
        k1.right = rotateWithLeftChild(k1.right); // Rotate the right child
        return rotateWithRightChild(k1); // Rotate the new subtree root
    }

    private static class AvlNode<AnyType> {
        // Constructors
        AvlNode( AnyType theElement ) {
            this( theElement, null, null );
        }

        AvlNode( AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt ) {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }

        AnyType           element;      // The data in the node
        AvlNode<AnyType>  left;         // Left child
        AvlNode<AnyType>  right;        // Right child
        int               height;       // Height
    }

    /** The tree root. */
    private AvlNode<AnyType> root;
}
