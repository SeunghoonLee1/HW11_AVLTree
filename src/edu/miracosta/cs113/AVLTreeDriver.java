package edu.miracosta.cs113;

/**
 * AVLTreeDriver.java : This driver class inserts a collection of more than 20 randomly generated numbers.
 * It also inserts the same set of numbers in a binary search tree that is not balanced. Then it verifies that
 * each tree is correct by performing an in-order traversal. Also, it display the format of each tree that was built
 * and compares their heights.
 *
 * @author Danny Lee
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

public class AVLTreeDriver {
    /*
     *Algorithm
     * 1. Generate 20 randomly generated numbers from 1 to 100.
     * 2. Store them in a arraylist.
     * 3. Add a number to both binary search tree and avl tree.
     * 4. Print out the results by traversing in order.
     * 5. Compare the height of each tree.
     */

    public static void main(String[] args){

        Random randomNumberGenerator = new Random();
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        ArrayList randomNumberList = new ArrayList();


        //add 20 random numbers from 1 to 100 to the randomNumberList
        for(int i = 0; i < 20; i++){
            int randomNumber = randomNumberGenerator.nextInt(100) + 1;
            if(!randomNumberList.contains(randomNumber)){
                randomNumberList.add(randomNumber);
            }else{
                //regenerate a random number
                randomNumber = randomNumberGenerator.nextInt(100) + 1;
                while(randomNumberList.contains(randomNumber)){
                    randomNumber = randomNumberGenerator.nextInt(100) + 1;
                }
                randomNumberList.add(randomNumber);
            }
            binarySearchTree.add((Integer) randomNumberList.get(i));
            avlTree.add((Integer) randomNumberList.get(i));
        }

        System.out.println("binarySearchTree toStirng through in-order traversal. (value)");
        System.out.println(binarySearchTree.toString());
        System.out.println("-------------------------------------------------------");
        System.out.println("AVLtree toStirng through in-order traversal. (balance : value)");
        System.out.println(avlTree.toString());

        System.out.println("This binarySearchTree height : " + binarySearchTree.getHeight(binarySearchTree.root));
        System.out.println("This AVLtree height : " + avlTree.getHeight(avlTree.root));

    }
}