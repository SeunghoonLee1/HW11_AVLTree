package edu.miracosta.cs113;

/**
 * BinaryTree.java : This class implements a binary tree by using inner Node class. Each node of the tree has 0, 1 or 2 children.
 *
 * @author Danny Lee
 * @version 1.0
 */

import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable {

    /**
     * Node<E>.java : This is an inner class of BinaryTree<E> class.
     * Just as for a linked list, a node consists of a data part and links to successor nodes.
     *
     */
    protected static class Node<E> implements Serializable{
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        /**
         * Full constructor
         * @param data the data stored in the node.
         */
        public Node(E data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        /**
         * toString method.
         * @return string value of the data.
         */
        public String toString(){
            return data.toString();
        }
    }//End of inner class Node<E>

    protected Node<E> root;
    /**
     * Default constructor
     */
    public BinaryTree(){
        root = null;
    }

    /**
     * The constructor that creates a tree with a given node at the root.
     * @param root the node that is set as the root.
     */
    public BinaryTree(Node<E> root){
        this.root = root;
    }

    /**
     * Full constructor that builds a tree from a data value and two trees.
     * @param data data value that is stored as the root.
     * @param leftTree the left subtree of the root.
     * @param rightTree the right subtree of the root.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree){
        root = new Node<E>(data);
        if(leftTree != null){
            root.left = leftTree.root;
        }else{
            root.left = null;
        }
        if(rightTree != null){
            root.right = rightTree.root;
        }else{
            root.right = null;
        }
    }

    /**
     * getter method
     * @return the left subtree of the root
     */
    public BinaryTree<E> getLeftSubtree(){
        if(root != null && root.left != null){
            return new BinaryTree<E>(root.left);
        }else{
            return null;
        }
    }

    /**
     * getter method
     * @return the right subtree of the root.
     */
    public BinaryTree<E> getRightSubtree(){
        if(root != null && root.right != null){
            return new BinaryTree<E>(root.right);
        }else{
            return null;
        }
    }

    /**
     * determines if the node is a leaf node.
     * @return boolean value
     */
    public boolean isLeaf(){
        return (root.left == null && root.right == null);
    }

    /**
     * toString method that generates a string representing a preorder traversal in which each local root is indented a
     * distance proportional to its depth.
     * @return String value of the tree.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        inOrderTraverse(root,1,sb);
        return sb.toString();
    }

    /**
     * Performs a preorder traversal of the subtree whose root is node. Appends the representation to the StringBuilder.
     * Increments the value of depth(the current tree level).
     * @param node current node while traversing
     * @param depth the current tree level
     * @param sb stringBuilder object.
     */
    public void preOrderTraverse(Node<E> node, int depth, StringBuilder sb){
        for(int i = 1; i < depth; i++){
            sb.append(" ");
        }
        if(node == null){
            sb.append("null\n");
        }else{
            sb.append(node.toString() + "\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }



    /**
     * Performs a in-order traversal of the subtree whose root is node. Appends the representation to the StringBuilder.
     * Increments the value of depth(the current tree level).
     * @param node current node while traversing
     * @param depth the current tree level
     * @param sb stringBuilder object.
     */
    public void inOrderTraverse(Node<E> node, int depth, StringBuilder sb){
        for(int i = 1; i < depth; i++){
            sb.append(" ");
        }
        if(node == null){
            sb.append("null\n");
        }else{
            inOrderTraverse(node.left, depth + 1, sb);
            sb.append(node.toString() + "\n");
            inOrderTraverse(node.right, depth + 1, sb);
        }
    }


    /**
     * Constructs a binary tree by reading its data using Scanner scan.
     * @param scan Scanner object used to read the data in the binary tree.
     * @return a binary tree.
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan){
        String data = scan.next();
        if(data.equals("null")){
            return null;
        }else{
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }

    }

    /**
     * A method that returns the height of the tree.
     * @param root the root node of this tree
     * @return the height of this tree.
     */
    public int getHeight(Node<E> root){
        if(root == null){
            return 0;
        }else{
            int leftSubtreeHeight = getHeight(root.left);
            int rightSubtreeHeight = getHeight(root.right);

            if(leftSubtreeHeight > rightSubtreeHeight){
                // System.out.println("leftSubtreeHeight  : " + leftSubtreeHeight + " > rightSubtreeHeight : "+ rightSubtreeHeight);
                return leftSubtreeHeight + 1;
            }else{
                //  System.out.println("rightSubtreeHeight : " + rightSubtreeHeight + " >= leftSubtreeHeight : " +leftSubtreeHeight);
                return rightSubtreeHeight + 1;
            }
        }
    }

}//end of BinaryTree<E> class