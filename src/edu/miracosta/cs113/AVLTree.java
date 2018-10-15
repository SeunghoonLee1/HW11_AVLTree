package edu.miracosta.cs113;

/**
 * AVLTree.java :Self-balancing binary serach tree using the algorithm defined by Adelson-Velskili and Landis.
 * This tree balances itself if the node goes off balance (-1 ~ +1) by rotating right or left.
 *
 * @author  Danny Lee
 * @version 1.0
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E>{

    /**
     * An inner class to represent an AVL Node. It extends the BinaryTree.Node by adding the balance field.
     */
    private static class AVLNode<E> extends Node<E>{
        /**Constant to indicate left-heavey*/
        public static final int LEFT_HEAVY = -1;
        /**Constant to indicate balanced*/
        public static final int BALANCED = 0;
        /**Constant to indicate right-heavy*/
        public static final int RIGHT_HEAVY = 1;
        /**balance is right subtree height - left subtree height*/
        private int balance;

        /**
         * Constructor.
         * Constructs a node with the given item as the data field.
         * @param item The data field.
         */
        public AVLNode(E item){
            super(item);
            balance = BALANCED;
        }

        /**
         * Return a string representation of this object.
         * The balance value is appended to the contents.
         * @return String representation of this object.
         */
        @Override
        public String toString(){
            return balance + ":" + super.toString();
        }
    }

    /**increase is true when the height of the tree increased.*/
    private boolean increase;

    /**
     * add starter method.
     * pre : them item to insert impelemtns Comparable interface.
     * @param item The item being inserted.
     * @return true if the object is inserted; false if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable.
     */
    @Override
    public boolean add(E item){
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }


    /**
     * Recursive add method. Inserts the given object into the tree.
     * post : addReturn is set true if the item is inserted, false if the item is already in the tree.
     * @param localRoot The local root of the subtree.
     * @param item The object to be inserted.
     * @return The new local root of the subtree with the item inserted.
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item){
        if(localRoot == null){
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }
        if(item.compareTo(localRoot.data) == 0){
            //Item is already in the tree
            increase = false;
            addReturn = false;
            return localRoot;
        }else if(item.compareTo(localRoot.data) < 0){
            //item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);
            if(increase){
                decrementBalance(localRoot);
                if(localRoot.balance < AVLNode.LEFT_HEAVY){
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        }else{
            //item > data
            localRoot.right = add((AVLNode<E>) localRoot.right, item);
            if(increase){
                incrementBalance(localRoot);
                if(localRoot.balance > AVLNode.RIGHT_HEAVY){
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot; //Rebalance not needed.
        }
    }

    /**
     * Method to rebalance left.
     * pre : localRoot is the root of an AVL subtree that is critically left-heavy.
     * post : Balance is restored.
     * @param localRoot Root of the AVL subtree
     * @return a new localRoot.
     */
    //The method parameter localRoot is a root of an AVL "subtree"!!!!!!!!!!!!!!! it's a subtree root!!
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot){
        //Obtain reference to left child.
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        //See whether left-right heavy.
        if(leftChild.balance > AVLNode.BALANCED){
            //Obtain reference to left-right child.
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            /**
             * Adjust the balances to be their new values after the rotations are performed.
             */
            if(leftRightChild.balance < AVLNode.BALANCED){//Left-right-left imbalance
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }else if(leftRightChild.balance > AVLNode.BALANCED){//Left-right-right imbalance
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }else if(leftRightChild.balance == AVLNode.BALANCED){//When it's just Left-right imbalance.
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            //Perform left rotation
            localRoot.left = rotateLeft(leftChild);
            //left, right node changes here!!
        }else{// Left-left case
            /**
             * In this case the leftChild(the new root) and the root (new right child) will both be balanced after the rotation,
             */
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
//        //See whether right-left heavy.!!!!!
//        if(){
//
//        }
        //Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }

    /**
     * Method to rebalance right.
     * pre : localRoot is the root of an AVL subtree that is critically right-heavy.
     * post : Balance is restored.
     * @param localRoot Root of the AVL subtree
     * @return a new localRoot.
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot){
        //Obtain reference to right child.
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        //See whether right-left heavy.
        if(rightChild.balance < AVLNode.BALANCED){
            //Obtain reference to right-left child.
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
            /**
             *Adjust the balance to be their new balances after the rotations are performed.
             */
            if(rightLeftChild.balance < AVLNode.BALANCED){//Right-left-left imbalance
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }else if(rightChild.balance > AVLNode.BALANCED){//Right-left-right imbalance.
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            }else if(rightChild.balance == AVLNode.BALANCED){//When it's just right-left imbalance
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            localRoot.right = rotateRight(rightChild);
        }else{//Right-right imbalance
            /**
             * In this case the rightChild(the new root) and the root (new left child) will both be balanced after roation.
             */
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        //Now rotate the local root left
        return (AVLNode<E>) rotateLeft(localRoot);
    }

    /**
     * Method to decrement the balance of the node that being sent as a parameter.
     * @param node a node to decrement the balance.
     */
    private void decrementBalance(AVLNode<E> node){
        //Decrement the balance.
        node.balance--;
        if(node.balance == AVLNode.BALANCED){
            /**If now balanced, overall height has not increased.*/
            increase = false;
        }
    }

    /**
     * Method to increment the balance of the node that being sent as a parameter.
     * @param node a node to increment the balance.
     */
    private void incrementBalance(AVLNode<E> node){
        //Increment the balance.
        node.balance++;
        if(node.balance == AVLNode.BALANCED){
            /**If now balanced, overall height has not increased.*/
            increase = false;
        }
    }

}