package com.mar.algotools.combinatorics.test;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.mar.algotools.combinatorics.PermutationEnumerator;
import com.mar.algotools.mathematics.utils.ArrayUtils;

public class TestPermutationEnumerator {

    @Test
    public void testPermutationEnumerator() {
        int n = 3;
        PermutationEnumerator permEnum = new PermutationEnumerator(n);

        ArrayList<Integer> p = null;
        int[] pArray = null;

        /* Reference permutation list (non-lexicographic order!) */
        int[][] perm = { { 0, 1, 2 }, { 0, 2, 1 }, { 2, 0, 1 }, { 2, 1, 0 }, { 1, 2, 0 }, { 1, 0, 2 } };

        /* Check that all permutations are correct. */
        int cpt = 0;
        while (permEnum.isNotBeyondLastPermutation()) {
            p = permEnum.getCurrentPermutation();
            pArray = ArrayUtils.list2arrayi(p);

            Assertions.assertThat(ArrayUtils.areArraysEqual(pArray, perm[cpt])).isTrue();

            permEnum.increment();
            cpt++;
        }

        /* Check that further increment always give the last permutation. */
        p = permEnum.getCurrentPermutation();
        pArray = ArrayUtils.list2arrayi(p);

        Assertions.assertThat(ArrayUtils.areArraysEqual(pArray, perm[perm.length - 1])).isTrue();
    }

}