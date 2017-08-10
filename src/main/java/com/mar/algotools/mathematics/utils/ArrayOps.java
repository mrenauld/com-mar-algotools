package com.mar.algotools.mathematics.utils;

import java.util.ArrayList;
import java.util.Vector;

public class ArrayOps {

    /**
     * Returns the index of the first occurrence of the specified value in the specified array. Returns -1 if there are
     * no occurrences.
     * @param a
     * @param x
     * @return
     */
    public static int find(final int[] a, final int x) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the first occurrence of the specified value in the specified array. Returns -1 if there are
     * no occurrences.
     * @param a
     * @param x
     * @return
     */
    public static int find(final String[] a, final String x) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the first occurrence of the specified value in the specified vector. Returns -1 if there are
     * no occurrences.
     * @param v
     * @param x
     * @return
     */
    public static int find(final Vector<Integer> v, final int x) {
        for (int i = 0; i < v.size(); ++i) {
            if (v.get(i) == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the first occurrence of the specified value in the specified vector. Returns -1 if there are
     * no occurrences.
     * @param v
     * @param x
     * @return
     */
    public static int find(final Vector<String> v, final String x) {
        for (int i = 0; i < v.size(); ++i) {
            if (v.get(i).equals(x)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns a portion of the selected array, from index idxa to index idxb included.
     * @param a
     * @param idxa
     * @param idxb
     * @return
     */
    public static boolean[] getSelection(final boolean[] a, final int idxa, final int idxb) {
        if (idxa < 0 || idxb < 0 || idxa >= a.length || idxb >= a.length || idxb < idxa) {
            System.out.println("ArrayOps.getSelection - error: problem with the indexes.");
            return null;
        }
        final boolean[] b = new boolean[idxb - idxa + 1];
        for (int i = idxa; i <= idxb; ++i) {
            b[i - idxa] = a[i];
        }
        return b;
    }

    /**
     * Returns a portion of the selected 2D array.
     * @param a
     * @param rowa
     * @param rowb
     * @param cola
     * @param colb
     * @return
     */
    public static boolean[][] getSelection(final boolean[][] a, final int rowa, final int rowb, final int cola,
        final int colb) {
        final boolean[][] b = new boolean[rowb - rowa + 1][colb - cola + 1];
        for (int i = rowa; i <= rowb; ++i) {
            for (int j = cola; j <= colb; ++j) {
                b[i - rowa][j - cola] = a[i][j];
            }
        }
        return b;
    }

    /**
     * Returns a portion of the selected array, from index idxa to index idxb included.
     * @param a
     * @param idxa
     * @param idxb
     * @return
     */
    public static double[] getSelection(final double[] a, final int idxa, final int idxb) {
        if (idxa < 0 || idxb < 0 || idxa >= a.length || idxb >= a.length || idxb < idxa) {
            System.out.println("ArrayOps.getSelection - error: problem with the indexes.");
            return null;
        }
        final double[] b = new double[idxb - idxa + 1];
        for (int i = idxa; i <= idxb; ++i) {
            b[i - idxa] = a[i];
        }
        return b;
    }

    /**
     * Returns a portion of the selected 2D array.
     * @param a
     * @param rowa
     * @param rowb
     * @param cola
     * @param colb
     * @return
     */
    public static double[][] getSelection(final double[][] a, final int rowa, final int rowb, final int cola,
        final int colb) {
        final double[][] b = new double[rowb - rowa + 1][colb - cola + 1];
        for (int i = rowa; i <= rowb; ++i) {
            for (int j = cola; j <= colb; ++j) {
                b[i - rowa][j - cola] = a[i][j];
            }
        }
        return b;
    }

    /**
     * Returns a portion of the selected array, from index idxa to index idxb included.
     * @param a
     * @param idxa
     * @param idxb
     * @return
     */
    public static int[] getSelection(final int[] a, final int idxa, final int idxb) {
        if (idxa < 0 || idxb < 0 || idxa >= a.length || idxb >= a.length || idxb < idxa) {
            System.out.println("ArrayOps.getSelection - error: problem with the indexes.");
            return null;
        }
        final int[] b = new int[idxb - idxa + 1];
        for (int i = idxa; i <= idxb; ++i) {
            b[i - idxa] = a[i];
        }
        return b;
    }

    /**
     * Returns a portion of the selected 2D array.
     * @param a
     * @param rowa
     * @param rowb
     * @param cola
     * @param colb
     * @return
     */
    public static int[][] getSelection(final int[][] a, final int rowa, final int rowb, final int cola, final int colb) {
        final int[][] b = new int[rowb - rowa + 1][colb - cola + 1];
        for (int i = rowa; i <= rowb; ++i) {
            for (int j = cola; j <= colb; ++j) {
                b[i - rowa][j - cola] = a[i][j];
            }
        }
        return b;
    }

    /**
     * Returns the mirrored list.
     * @param pV
     * @return
     */
    public static ArrayList<Integer> mirror(ArrayList<Integer> pV) {
        ArrayList<Integer> vMirror = new ArrayList<Integer>(pV.size());
        for (int i = 0; i < pV.size(); ++i) {
            int value = pV.get(pV.size() - 1 - i);
            vMirror.add(value);
        }
        return vMirror;
    }

    /**
     * Returns a rotated version of the specified array. offset > 0 means rotating to the right, offset < 0 means
     * rotating to the left.
     * @param a
     * @param offset
     * @return
     */
    public static boolean[] rotate(final boolean[] a, final int offset) {
        final boolean[] b = new boolean[a.length];
        for (int i = 0; i < a.length; ++i) {
            b[i] = a[MathOps.mod(i - offset, a.length)];
        }
        return b;
    }

    /**
     * Returns a rotated version of the specified array. offset > 0 means rotating to the right, offset < 0 means
     * rotating to the left.
     * @param a
     * @param offset
     * @return
     */
    public static double[] rotate(final double[] a, final int offset) {
        final double[] b = new double[a.length];
        for (int i = 0; i < a.length; ++i) {
            b[i] = a[MathOps.mod(i - offset, a.length)];
        }
        return b;
    }

    /**
     * Returns a rotated version of the specified array. offset > 0 means rotating to the right, offset < 0 means
     * rotating to the left.
     * @param a
     * @param offset
     * @return
     */
    public static int[] rotate(final int[] a, final int offset) {
        final int[] b = new int[a.length];
        for (int i = 0; i < a.length; ++i) {
            b[i] = a[MathOps.mod(i - offset, a.length)];
        }
        return b;
    }
}