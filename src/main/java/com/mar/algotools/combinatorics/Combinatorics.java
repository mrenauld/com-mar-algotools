package com.mar.algotools.combinatorics;

import java.math.BigInteger;
import java.util.ArrayList;

import com.mar.framework.core.logging.LogUtils;

public class Combinatorics {

    /**
     * Returns the binomial coefficient "n choose k", that is the number of ways
     * to choose k elements (unordered) from a set of n.
     *
     * @param pN
     * @param pK
     * @return
     */
    public static BigInteger getBinomialCoef(int pN, int pK) {
        /* Check that n >= k >= 0. */
        if (pK < 0) {
            LogUtils.logError(Combinatorics.class, "Impossible to compute binomial coefficient with pK < 0");
            return BigInteger.ONE;
        }
        if (pN < pK) {
            LogUtils.logError(Combinatorics.class, "Impossible to compute binomial coefficient with pN < pK");
            return BigInteger.ONE;
        }

        BigInteger c = BigInteger.ONE;
        int k = pK > pN - pK ? pK : pN - pK;
        for (int i = 0; i < k; ++i) {
            c = c.multiply(BigInteger.valueOf(pN - i)).divide(BigInteger.valueOf(i + 1));
        }
        return c;
    }

    /**
     * Returns the identity permutation of specified length.
     *
     * @param n
     *            the length of the permutation.
     * @return the identity permutation of length n.
     */
    public static int[] getIdentityPerm(int n) {
        int[] out = new int[n];
        for (int i = 0; i < n; ++i) {
            out[i] = i;
        }
        return out;
    }

    /**
     * Returns the index of the first occurrence of the specified value in the
     * array. Returns -1 if no occurrence was found.
     *
     * @param vect
     *            the array.
     * @param val
     *            the value to find.
     * @return the index of the value.
     */
    public static int getIdx(int[] vect, int val) {
        for (int i = 0; i < vect.length; ++i) {
            if (vect[i] == val) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the inverse of the specified permutation, that is: w =
     * inv_perm(v) <=> w(v) = identity.
     *
     * @param v
     * @return
     */
    public static int[] getInvPerm(int[] v) {
        int[] out = new int[v.length];
        for (int i = 0; i < v.length; ++i) {
            out[v[i]] = i;
        }
        return out;
    }

    /**
     * Returns a random permutation on n values. Uses Knuth's algorithm.
     *
     * @param n
     * @return
     */
    public static int[] getRandPerm(int n) {
        int[] out = getIdentityPerm(n);
        for (int i = n - 1; i >= 1; --i) {
            int j = (int) (Math.random() * (i + 1));
            int outi = out[i];
            out[i] = out[j];
            out[j] = outi;
        }
        return out;
    }

    /**
     * Returns true if the specified permutation is the identity permutation.
     *
     * @param pV
     * @return
     */
    public static boolean isIdentityPerm(int[] pV) {
        for (int i = 0; i < pV.length; ++i) {
            if (pV[i] != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the specified array is a permutation (i.e. contains
     * exactly once all integers from 0 to pV.length-1 included).
     *
     * @param pV
     * @return
     */
    public static boolean isPermutation(int[] pV) {
        boolean[] found = new boolean[pV.length];
        for (int i = 0; i < pV.length; ++i) {
            found[i] = false;
        }
        for (int i = 0; i < pV.length; ++i) {
            if (pV[i] < 0 || pV[i] >= pV.length) {
                return false;
            }
            found[pV[i]] = true;
        }
        for (int i = 0; i < pV.length; ++i) {
            if (!found[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Applies the specified permutation to the specified array and returns the
     * result.
     *
     * @param perm
     *            the permutation.
     * @param v
     *            the vector.
     * @return the permuted vector.
     */
    public static int[] perm(int[] perm, int[] v) {
        if (perm.length != v.length) {
            LogUtils.logError(Combinatorics.class,
                    "Impossible to apply permutation: the arrays should have the same length.");
            return null;
        }

        int[] out = new int[v.length];
        for (int i = 0; i < v.length; ++i) {
            out[i] = perm[v[i]];
        }
        return out;
    }

    /**
     * Randomizes in place the order of the specified ArrayList.
     *
     * @param pArrayList
     */
    public static <T> void randomizeArrayList(ArrayList<T> pArrayList) {
        int n = pArrayList.size();
        for (int i = n - 1; i >= 1; --i) {
            int j = (int) (Math.random() * (i + 1));
            T temp = pArrayList.get(i);
            pArrayList.set(i, pArrayList.get(j));
            pArrayList.set(j, temp);
        }
    }

}