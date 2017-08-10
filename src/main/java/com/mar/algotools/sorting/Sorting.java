package com.mar.algotools.sorting;

import java.util.ArrayList;

public class Sorting {

    /**
     * Returns the index of the object v in the array list A. The array is supposed ordered from lowest to highest
     * value. Returns -1 if the object is not in the array.
     * @param A
     *            the array list to explore.
     * @param v
     *            the object to find.
     * @return the index of the object.
     */
    public static int dichotomicSearch(ArrayList<Integer> A, int v) {
        if (A.size() == 0) {
            return -1;
        }
        return dichotomicSearch(A, 0, A.size() - 1, v);
    }

    /**
     * Returns the index of the object v in the array list A (between indexes p and r included). The array is supposed
     * ordered from lowest to highest value. Returns -1 if the object is not in the selected part of the array.
     * @param A
     *            the array list to explore.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     * @param v
     *            the object to find.
     * @return the index of the object.
     */
    public static int dichotomicSearch(ArrayList<Integer> A, int p, int r, int v) {
        if (r < p) {
            System.err.println("Sorting - dichotomicSearch : error." + "r < p");
            return -1;
        }
        if (p == r && A.get(p) != v) {
            return -1;
        }
        int q = (p + r) / 2;
        if (A.get(q) == v) {
            return q;
        }
        else if (A.get(q) > v) {
            return dichotomicSearch(A, p, q, v);
        }
        else {
            return dichotomicSearch(A, q + 1, r, v);
        }
    }

    /**
     * Returns the index of the object v in the array A. The array is supposed ordered from lowest to highest value.
     * Returns -1 if the object is not in the array.
     * @param A
     *            the array to explore.
     * @param v
     *            the object to find.
     * @return the index of the object.
     */
    public static int dichotomicSearch(char[] A, char v) {
        if (A.length == 0) {
            return -1;
        }
        return dichotomicSearch(A, 0, A.length - 1, v);
    }

    /**
     * Returns the index of the object v in the array A (between indexes p and r included). The array is supposed
     * ordered from lowest to highest value. Returns -1 if the object is not in the selected part of the array.
     * @param A
     *            the array to explore.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     * @param v
     *            the object to find.
     * @return the index of the object.
     */
    public static int dichotomicSearch(char[] A, int p, int r, char v) {
        if (r < p) {
            System.err.println("Sorting - dichotomicSearch : error." + "r < p");
            return -1;
        }
        if (p == r && A[p] != v) {
            return -1;
        }
        int q = (p + r) / 2;
        if (A[q] == v) {
            return q;
        }
        else if (A[q] > v) {
            return dichotomicSearch(A, p, q, v);
        }
        else {
            return dichotomicSearch(A, q + 1, r, v);
        }
    }

    /**
     * Returns the index of the object v in the array A. The array is supposed ordered from lowest to highest value.
     * Returns -1 if the object is not in the array.
     * @param A
     *            the array to explore.
     * @param v
     *            the object to find.
     * @return the index of the object.
     */
    public static int dichotomicSearch(int[] A, int v) {
        if (A.length == 0) {
            return -1;
        }
        return dichotomicSearch(A, 0, A.length - 1, v);
    }

    /**
     * Returns the index of the object v in the array A (between indexes p and r included). The array is supposed
     * ordered from lowest to highest value. Returns -1 if the object is not in the selected part of the array.
     * @param A
     *            the array to explore.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     * @param v
     *            the object to find.
     * @return the index of the object.
     */
    public static int dichotomicSearch(int[] A, int p, int r, int v) {
        if (r < p) {
            System.err.println("Sorting - dichotomicSearch : error." + "r < p");
            return -1;
        }
        if (p == r && A[p] != v) {
            return -1;
        }
        int q = (p + r) / 2;
        if (A[q] == v) {
            return q;
        }
        else if (A[q] > v) {
            return dichotomicSearch(A, p, q, v);
        }
        else {
            return dichotomicSearch(A, q + 1, r, v);
        }
    }

    /**
     * Returns the index of the object v in the array A (between indexes p and r included). The array is supposed
     * ordered from lowest to highest value, according to the specified order. Returns -1 if the object is not in the
     * selected part of the array.
     * @param A
     *            the array to explore.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     * @param v
     *            the object to find.
     * @param comp
     *            the order to use.
     * @return the index of the object.
     */
    public static int dichotomicSearch(Object[] A, int p, int r, Object v, MyComparator comp) {
        if (r < p) {
            System.err.println("Sorting - dichotomicSearch : error." + "r < p");
            return -1;
        }
        if (p == r && comp.compareTo(A[p], v) != 0) {
            return -1;
        }
        int q = (p + r) / 2;
        if (comp.compareTo(A[q], v) == 0) {
            return q;
        }
        else if (comp.compareTo(A[q], v) == 1) {
            return dichotomicSearch(A, p, q, v, comp);
        }
        else {
            return dichotomicSearch(A, q + 1, r, v, comp);
        }
    }

    /**
     * Returns the index of the object v in the array A. The array is supposed ordered from lowest to highest value,
     * according the specified order. Returns -1 if the object is not in the array.
     * @param A
     *            the array to explore.
     * @param v
     *            the object to find.
     * @param comp
     *            the order to use.
     * @return the index of the object.
     */
    public static int dichotomicSearch(Object[] A, Object v, MyComparator comp) {
        if (A.length == 0) {
            return -1;
        }
        return dichotomicSearch(A, 0, A.length - 1, v, comp);
    }

    /**
     * Rearranges the elements of the specified array that are between indexes p and r according to the specified order.
     * The elements are rearranged into two concatenated arrays: the first one with all elements below the pivot and the
     * second one with all elements over the pivot.
     * @param A
     *            the array to sort.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     * @param comp
     *            the order to use.
     */
    public static int partition(Object[] A, int p, int r, MyComparator comp) {
        Object x = A[p];
        int i = p - 1;
        int j = r + 1;
        while (true) {
            j--;
            while (j > p && comp.compareTo(A[j], x) == 1) {
                j--;
            }
            i++;
            while (i < r && comp.compareTo(A[i], x) == -1) {
                i++;
            }
            if (i < j) {
                Object temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
            else {
                return j;
            }
        }
    }

    /**
     * Returns a new array that is the specified array sorted.
     * @param A
     *            the array to sort.
     * @return a new array sorted from the lowest element to the highest element.
     */
    public static char[] quicksort(char[] A) {
        char[] B = A.clone();
        quicksort(B, 0, B.length - 1);
        return B;
    }

    /**
     * Sorts the elements of the specified array that are between indexes p and r included.
     * @param A
     *            the array to sort.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     */
    public static void quicksort(char[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            quicksort(A, p, q);
            quicksort(A, q + 1, r);
        }
    }

    /**
     * Returns a new array that is the specified array sorted.
     * @param A
     *            the array to sort.
     * @return a new array sorted from the lowest element to the highest element.
     */
    public static double[] quicksort(double[] A) {
        double[] B = A.clone();
        quicksort(B, 0, B.length - 1);
        return B;
    }

    /**
     * Sorts the elements of the specified array that are between indexes p and r included.
     * @param A
     *            the array to sort.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     */
    public static void quicksort(double[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            quicksort(A, p, q);
            quicksort(A, q + 1, r);
        }
    }

    /**
     * Returns a new array that is the specified array sorted.
     * @param A
     *            the array to sort.
     * @return a new array sorted from the lowest element to the highest element.
     */
    public static int[] quicksort(int[] A) {
        int[] B = A.clone();
        quicksort(B, 0, B.length - 1);
        return B;
    }

    /**
     * Sorts the elements of the specified array that are between indexes p and r included.
     * @param A
     *            the array to sort.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     */
    public static void quicksort(int[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            quicksort(A, p, q);
            quicksort(A, q + 1, r);
        }
    }

    /**
     * Sorts the elements of the specified array that are between indexes p and r included, according to the specified
     * order.
     * @param A
     *            the array to sort.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     * @param comp
     *            the order to use.
     */
    public static void quicksort(Object[] A, int p, int r, MyComparator comp) {
        if (p < r) {
            int q = partition(A, p, r, comp);
            quicksort(A, p, q, comp);
            quicksort(A, q + 1, r, comp);
        }
    }

    /**
     * Returns a new array that is the specified array sorted according to the specified order.
     * @param A
     *            the array to sort.
     * @param comp
     *            the order to use.
     * @return a new array sorted from the lowest element to the highest element.
     */
    public static Object[] quicksort(Object[] A, MyComparator comp) {
        Object[] B = A.clone();
        quicksort(B, 0, B.length - 1, comp);
        return B;
    }

    /**
     * Rearranges the elements of the specified array that are between indexes p and r. The elements are rearranged into
     * two concatenated arrays: the first one with all elements below the pivot and the second one with all elements
     * over the pivot.
     * @param A
     *            the array to sort.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     */
    private static int partition(char[] A, int p, int r) {
        char x = A[p];
        int i = p - 1;
        int j = r + 1;
        while (true) {
            j--;
            while (j > p && A[j] > x) {
                j--;
            }
            i++;
            while (i < r && A[i] < x) {
                i++;
            }
            if (i < j) {
                char temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
            else {
                return j;
            }
        }
    }

    /**
     * Rearranges the elements of the specified array that are between indexes p and r. The elements are rearranged into
     * two concatenated arrays: the first one with all elements below the pivot and the second one with all elements
     * over the pivot.
     * @param A
     *            the array to sort.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     */
    private static int partition(double[] A, int p, int r) {
        double x = A[p];
        int i = p - 1;
        int j = r + 1;
        while (true) {
            j--;
            while (j > p && A[j] > x) {
                j--;
            }
            i++;
            while (i < r && A[i] < x) {
                i++;
            }
            if (i < j) {
                double temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
            else {
                return j;
            }
        }
    }

    /**
     * Rearranges the elements of the specified array that are between indexes p and r. The elements are rearranged into
     * two concatenated arrays: the first one with all elements below the pivot and the second one with all elements
     * over the pivot.
     * @param A
     *            the array to sort.
     * @param p
     *            the first index.
     * @param r
     *            the last index.
     */
    private static int partition(int[] A, int p, int r) {
        int x = A[p];
        int i = p - 1;
        int j = r + 1;
        while (true) {
            j--;
            while (j > p && A[j] > x) {
                j--;
            }
            i++;
            while (i < r && A[i] < x) {
                i++;
            }
            if (i < j) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
            else {
                return j;
            }
        }
    }
}
