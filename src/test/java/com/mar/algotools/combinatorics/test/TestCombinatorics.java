package com.mar.algotools.combinatorics.test;

import java.math.BigInteger;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.mar.algotools.combinatorics.Combinatorics;
import com.mar.algotools.mathematics.utils.ArrayUtils;

public class TestCombinatorics {

    @Test
    public void testBinomialCoef() {
        /* We should have n >= k >= 0. */

        /* Check k < 0 -> return default value 1. */
        Assertions.assertThat(Combinatorics.getBinomialCoef(0, -1).equals(BigInteger.valueOf(1))).isTrue();

        /* Check n < k -> return default value 1. */
        Assertions.assertThat(Combinatorics.getBinomialCoef(2, 3).equals(BigInteger.valueOf(1))).isTrue();

        /* Check some values. */
        Assertions.assertThat(Combinatorics.getBinomialCoef(0, 0).equals(BigInteger.valueOf(1))).isTrue();
        Assertions.assertThat(Combinatorics.getBinomialCoef(3, 0).equals(BigInteger.valueOf(1))).isTrue();
        Assertions.assertThat(Combinatorics.getBinomialCoef(3, 3).equals(BigInteger.valueOf(1))).isTrue();
        Assertions.assertThat(Combinatorics.getBinomialCoef(8, 4).equals(BigInteger.valueOf(70))).isTrue();
    }

    @Test
    public void testGetIdentityPerm() {
        /* Negative size. */
        Assertions.assertThatThrownBy(() -> Combinatorics.getIdentityPerm(-1))
                .isInstanceOf(NegativeArraySizeException.class);

        /* Some values. */
        int[] idPerm = Combinatorics.getIdentityPerm(0);
        Assertions.assertThat(idPerm.length == 0).isTrue();

        idPerm = Combinatorics.getIdentityPerm(1);
        Assertions.assertThat(idPerm.length == 1 && idPerm[0] == 0).isTrue();

        idPerm = Combinatorics.getIdentityPerm(5);
        int[] idRef = { 0, 1, 2, 3, 4 };
        Assertions.assertThat(idPerm.length == 5 && ArrayUtils.areArraysEqual(idPerm, idRef)).isTrue();
    }

    @Test
    public void testGetIdx() {
        int[] vect = { 3, 2, 3, 0, 1, 4 };

        Assertions.assertThat(Combinatorics.getIdx(vect, 0) == 3).isTrue();
        Assertions.assertThat(Combinatorics.getIdx(vect, 3) == 0).isTrue();
        Assertions.assertThat(Combinatorics.getIdx(vect, 6) == -1).isTrue();
    }

    @Test
    public void testGetInvPerm() {
        int[] vect = { 3, 2, 5, 0, 1, 4 };
        int[] invVect = { 3, 4, 1, 0, 5, 2 };

        Assertions.assertThat(ArrayUtils.areArraysEqual(Combinatorics.getInvPerm(vect), invVect)).isTrue();
        Assertions.assertThat(ArrayUtils.areArraysEqual(Combinatorics.getInvPerm(invVect), vect)).isTrue();
    }

    @Test
    public void testGetRandPerm() {
        int n = 10;
        int[] randPerm = Combinatorics.getRandPerm(n);

        Assertions.assertThat(randPerm.length == n).isTrue();
        for (int i = 0; i < n; ++i) {
            int idx = Combinatorics.getIdx(randPerm, i);
            Assertions.assertThat(idx >= 0 && idx < n).isTrue();
        }
    }

    @Test
    public void testIsIdentityPerm() {
        int[] idPerm = { 0, 1, 2, 3, 4, 5 };
        int[] notIdPerm1 = { 0, 1, 2, 3, 3, 5 };
        int[] notIdPerm2 = { 0, 1, 2, 3, 5, 4 };

        Assertions.assertThat(Combinatorics.isIdentityPerm(idPerm)).isTrue();
        Assertions.assertThat(Combinatorics.isIdentityPerm(notIdPerm1)).isFalse();
        Assertions.assertThat(Combinatorics.isIdentityPerm(notIdPerm2)).isFalse();
    }

    @Test
    public void testIsPermutation() {
        int[] perm = { 3, 2, 5, 0, 1, 4 };
        int[] notPerm1 = { 3, 2, 6, 0, 1, 4 };
        int[] notPerm2 = { 3, 3, 5, 0, 1, 4 };

        Assertions.assertThat(Combinatorics.isPermutation(perm)).isTrue();
        Assertions.assertThat(Combinatorics.isPermutation(notPerm1)).isFalse();
        Assertions.assertThat(Combinatorics.isPermutation(notPerm2)).isFalse();
    }

    @Test
    public void testPerm() {
        int[] vect = { 0, 3, 3, 1, 2, 0, 4 };
        int[] perm1 = { 2, 6, 4, 3, 5, 0, 1, 7 };
        int[] perm2 = { 2, 6, 4, 3, 5, 0, 1 };
        int[] permVect = { 2, 3, 3, 6, 4, 2, 5 };

        Assertions.assertThat(Combinatorics.perm(perm1, vect)).isNull();
        Assertions.assertThat(ArrayUtils.areArraysEqual(Combinatorics.perm(perm2, vect), permVect)).isTrue();
    }

    @Test
    public void testRandomizeArrayList() {
        int n = 10;
        ArrayList<Integer> idList = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            idList.add(i);
        }

        Combinatorics.randomizeArrayList(idList);

        int[] randPerm = new int[idList.size()];
        for (int i = 0; i < idList.size(); ++i) {
            randPerm[i] = idList.get(i);
        }

        Assertions.assertThat(Combinatorics.isPermutation(randPerm)).isTrue();
    }

}
