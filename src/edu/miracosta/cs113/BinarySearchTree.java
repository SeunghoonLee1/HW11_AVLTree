package edu.miracosta.cs113;

/**
 * BinaryTreeSearchTree.java : This class extends BinaryTee. It behaves similar to BinaryTree. However, the data value of the
 * right child is greater than its root node. Likewise, the data value of the left child is less than its root node.
 *
 * @author Danny Lee
 * @version 1.0
 */

public class BinarySearchTree<E extends  Comparable<E>> extends BinaryTree<E> implements SearchTree<E>{

    //Data fields
    /**Return value from the public add method.*/
    protected boolean addReturn;
    /**Return value from the public delete method.*/
    protected E deleteReturn;

    @Override
    /**
     * Starter method add.
     * pre : The object to insert must implement the Comparable interface.
     * @param item The object being inserted.
     * @return true if the object is inserted, false if the object already exists in the tree.
     */
    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    /**
     * Recursive add method.
     * post : The data field addReturn is set true if the item is added to the tree, false if the item is already in the tree
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root that now contains the inserted item.
     */
    private Node<E> add(Node<E> localRoot, E item){
        if(localRoot == null){
            //item is not in the tree - insert it.
            addReturn = true;
            return new Node<E>(item);
        }else if(item.compareTo(localRoot.data) == 0){
            //item is equal to localRoot.data
            addReturn = false;
            return localRoot;
        }else if(item.compareTo(localRoot.data) < 0){
            //item is less than localRoot.data
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        }else{
            //item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * Returns true if target is found in the tree
     * @param target The object being sought
     * @return True if the the tree contains this object.
     */
    @Override
    public boolean contains(E target) {
        E foundData = find(target);
        if(foundData == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    /**
     * Starter method find.
     * pre :  The target object must implement the Comparable interface
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null.
     */
    public E find(E target) {
        return find(root, target);
    }

    /**
     * Recursive find method
     * @param localRoot The local subtree's root
     * @param target The object being sought.
     * @return The object, if found, otherwise null.
     */
    private E find(Node<E> localRoot, E target){
        if(localRoot == null){
            return null;
        }
        //Compare the target with the data field at the root.
        int compareResult = target.compareTo(localRoot.data);
        if(compareResult == 0){
            return localRoot.data;
        }else if(compareResult < 0){
            return find(localRoot.left, target);
        }else{
            return find(localRoot.right, target);
        }
    }

    @Override
    //Stub out this method.
    public E delete(E target) {
        return null;
    }

    @Override
    //Stub out this method.
    public boolean remove(E target) {
        return false;
    }
}