package com.mar.algotools.combinatorics;

import java.util.ArrayList;

/**
 * See
 * https://tropenhitze.wordpress.com/2010/01/25/steinhaus-johnson-trotter-permutation-algorithm-explained-and-
 * implemented-in-java/<br/>
 * Generates all permutations for a specified number of elements. Each
 * permutation is generated from the previous one sequentially, not recursively.
 * 
 * @author mrenauld
 */
public class PermutationEnumerator {

    /**
     * Element containing the value and the direction (left/right).
     * 
     * @author mrenauld
     */
    public class Element {

        protected int value;
        protected boolean isDirectionLeft;

        public Element(int pValue, boolean pIsDirectionLeft) {
            value = pValue;
            isDirectionLeft = pIsDirectionLeft;
        }
    }

    /** Number of elements in the permutation. */
    private int nbElem = 0;

    /** Current permutation. */
    private ArrayList<Element> currentPerm;

    /** False if we are beyond the last permutation. */
    private boolean isBeyondLastPermutation = false;

    /**
     * Constructor.
     * 
     * @param pNbElem
     */
    public PermutationEnumerator(int pNbElem) {
        nbElem = pNbElem;
        isBeyondLastPermutation = true;

        currentPerm = new ArrayList<Element>(pNbElem);
        for (int i = 0; i < pNbElem; ++i) {
            Element e = new Element(i, true);
            currentPerm.add(e);
        }
    }

    /**
     * Returns the values of the current permutation.
     * 
     * @return
     */
    public ArrayList<Integer> getCurrentPermutation() {
        ArrayList<Integer> perm = new ArrayList<Integer>(nbElem);
        for (Element e : currentPerm) {
            perm.add(e.value);
        }
        return perm;
    }

    /**
     * Returns false if we are beyond the last permutation.
     * 
     * @return
     */
    public boolean isNotBeyondLastPermutation() {
        return isBeyondLastPermutation;
    }

    /**
     * Generates the next permutation.
     */
    public void increment() {
        /* 1. Find the largest mobile element. */
        int idx = getLargestMobileElementIdx();

        if (idx == -1) {
            isBeyondLastPermutation = false;
            return;
        }

        int value = currentPerm.get(idx).value;

        /* 2. Swap the largest mobile element following its direction. */
        Element e1 = currentPerm.get(idx);
        Element e2 = null;
        if (e1.isDirectionLeft) {
            e2 = currentPerm.get(idx - 1);
        } else {
            e2 = currentPerm.get(idx + 1);
        }

        int valueTemp = e2.value;
        boolean dirTemp = e2.isDirectionLeft;
        e2.value = e1.value;
        e2.isDirectionLeft = e1.isDirectionLeft;
        e1.value = valueTemp;
        e1.isDirectionLeft = dirTemp;

        /* 3. Change direction if needed. */
        for (Element e : currentPerm) {
            if (e.value > value) {
                e.isDirectionLeft ^= true;
            }
        }
    }

    /**
     * Returns the index of the largest mobile value in the current permutation.
     * 
     * @return
     */
    private int getLargestMobileElementIdx() {
        int maxMobileValue = -1;
        int maxMobileIdx = -1;
        for (int i = 0; i < nbElem; ++i) {
            int value = currentPerm.get(i).value;
            if (currentPerm.get(i).isDirectionLeft && i > 0 && value > currentPerm.get(i - 1).value) {
                if (value > maxMobileValue) {
                    maxMobileValue = value;
                    maxMobileIdx = i;
                }
            } else if (!currentPerm.get(i).isDirectionLeft && i < nbElem - 1 && value > currentPerm.get(i + 1).value) {
                if (value > maxMobileValue) {
                    maxMobileValue = value;
                    maxMobileIdx = i;
                }
            }
        }
        return maxMobileIdx;
    }
}