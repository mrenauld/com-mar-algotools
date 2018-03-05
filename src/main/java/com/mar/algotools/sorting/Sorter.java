package com.mar.algotools.sorting;

import java.util.ArrayList;

public class Sorter {

    public static class IndexedDouble {
        public double v;
        public int idx;

        public IndexedDouble(double v, int idx) {
            this.v = v;
            this.idx = idx;
        }
    }

    public static class IndexedDoubleComparator implements MyComparator {
        /**
         * Compare the Indexeddouble a and b. Returns -1 if a is lower than b
         * order, 1 if b is lower than a or 0 if a is equal to b.
         *
         * @param ao
         * @param bo
         * @return -1 if a < b, 0 if a = b and 1 if b < a.
         */
        @Override
        public int compareTo(Object ao, Object bo) {
            double a = ((IndexedDouble) ao).v;
            double b = ((IndexedDouble) bo).v;

            if (a < b) {
                return -1;
            }
            if (a > b) {
                return 1;
            }

            return 0;
        }
    }

    public static class IndexedInt {
        public int v;
        public int idx;

        public IndexedInt(int v, int idx) {
            this.v = v;
            this.idx = idx;
        }
    }

    public static class IndexedIntComparator implements MyComparator {
        /**
         * Compare the IndexedInt a and b. Returns -1 if a lower than b order, 1
         * if b is lower than a or 0 if a is equal to b.
         *
         * @param ao
         * @param bo
         * @return -1 if a < b, 0 if a = b and 1 if b < a.
         */
        @Override
        public int compareTo(Object ao, Object bo) {
            int a = ((IndexedInt) ao).v;
            int b = ((IndexedInt) bo).v;

            if (a < b) {
                return -1;
            }
            if (a > b) {
                return 1;
            }

            return 0;
        }
    }

    public static class IndexedString {
        public String s;
        public int idx;

        public IndexedString(String s, int idx) {
            this.s = s;
            this.idx = idx;
        }
    }

    public static class IndexedStringComparator implements MyComparator {
        /**
         * Compare the Strings a and b. Returns -1 if a is first in
         * lexicographic order, 1 if b is first in lexicographic order or 0 if a
         * is equal to b.
         *
         * @param a
         *            an integer array.
         * @param b
         *            an integer array.
         * @return -1 if a < b, 0 if a = b and 1 if b < a.
         */
        @Override
        public int compareTo(Object ao, Object bo) {
            String a = ((IndexedString) ao).s;
            String b = ((IndexedString) bo).s;

            a = a.toLowerCase();
            b = b.toLowerCase();

            for (int i = 0; i < Math.max(a.length(), b.length()); ++i) {
                if (i >= a.length() || i >= b.length()) {
                    break;
                }
                if (a.charAt(i) < b.charAt(i)) {
                    return -1;
                }
                if (a.charAt(i) > b.charAt(i)) {
                    return 1;
                }
            }

            if (a.length() < b.length()) {
                return -1;
            }
            if (a.length() > b.length()) {
                return 1;
            }

            return 0;
        }
    }

    public static final int ASCEND = 1;

    public static final int DESCEND = -1;

    /**
     * Sorts the specified ArrayList<Integer> according to the specified order,
     * and returns the sorted indexes. idx[i] = j means that the ith element in
     * the sorted array is the element j in the original array.
     *
     * @param data
     * @param type
     * @return
     */
    public static int[] getSortedIdx(ArrayList<Integer> data, int type) {

        IndexedIntComparator comp = new IndexedIntComparator();
        IndexedInt[] idxdata = new IndexedInt[data.size()];
        for (int i = 0; i < data.size(); ++i) {
            idxdata[i] = new IndexedInt(data.get(i), i);
        }

        IndexedInt[] sorted = (IndexedInt[]) Sorting.quicksort(idxdata, comp);
        int[] idx = new int[data.size()];
        for (int i = 0; i < data.size(); ++i) {
            if (type == ASCEND) {
                idx[i] = sorted[i].idx;
            } else {
                idx[i] = sorted[data.size() - 1 - i].idx;
            }
        }

        return idx;
    }

    /**
     * Returns the new indexes that the elements of the specified array have to
     * occupy in order to be sorted according to the specified order.
     *
     * @param data
     * @param type
     * @return
     */
    public static int[] getSortedIdx(double[] data, int type) {

        IndexedDoubleComparator comp = new IndexedDoubleComparator();
        IndexedDouble[] idxdata = new IndexedDouble[data.length];
        for (int i = 0; i < data.length; ++i) {
            idxdata[i] = new IndexedDouble(data[i], i);
        }

        IndexedDouble[] sorted = (IndexedDouble[]) Sorting.quicksort(idxdata, comp);
        int[] idx = new int[data.length];
        for (int i = 0; i < data.length; ++i) {
            if (type == ASCEND) {
                idx[i] = sorted[i].idx;
            } else {
                idx[i] = sorted[data.length - 1 - i].idx;
            }
        }

        return idx;
    }

    /**
     * Returns the new indexes that the elements of the specified array have to
     * occupy in order to be sorted according to the specified order.
     *
     * @param data
     * @param type
     * @return
     */
    public static int[] getSortedIdx(int[] data, int type) {

        IndexedIntComparator comp = new IndexedIntComparator();
        IndexedInt[] idxdata = new IndexedInt[data.length];
        for (int i = 0; i < data.length; ++i) {
            idxdata[i] = new IndexedInt(data[i], i);
        }

        IndexedInt[] sorted = (IndexedInt[]) Sorting.quicksort(idxdata, comp);
        int[] idx = new int[data.length];
        for (int i = 0; i < data.length; ++i) {
            if (type == ASCEND) {
                idx[i] = sorted[i].idx;
            } else {
                idx[i] = sorted[data.length - 1 - i].idx;
            }
        }

        return idx;
    }

    // Comparator classes.

    /**
     * Sorts the specified string array according to the specified order, and
     * returns the sorted indexes. idx[i] = j means that the ith element in the
     * sorted array is the element j in the original array.
     *
     * @param data
     * @param type
     * @return
     */
    public static int[] getSortedIdx(String[] data, int type) {

        IndexedStringComparator comp = new IndexedStringComparator();
        IndexedString[] idxdata = new IndexedString[data.length];
        for (int i = 0; i < data.length; ++i) {
            idxdata[i] = new IndexedString(data[i], i);
        }

        IndexedString[] sorted = (IndexedString[]) Sorting.quicksort(idxdata, comp);
        int[] idx = new int[data.length];
        for (int i = 0; i < data.length; ++i) {
            if (type == ASCEND) {
                idx[i] = sorted[i].idx;
            } else {
                idx[i] = sorted[data.length - 1 - i].idx;
            }
        }

        return idx;
    }

    /**
     * Returns the ArrayList<Integer> sorted according to the specified
     * direction.
     *
     * @param data
     * @param type
     * @return
     */
    public static ArrayList<String> sort(ArrayList<String> data, int type) {
        IndexedStringComparator comp = new IndexedStringComparator();
        IndexedString[] idxdata = new IndexedString[data.size()];
        for (int i = 0; i < idxdata.length; ++i) {
            idxdata[i] = new IndexedString(data.get(i), i);
        }

        IndexedString[] sorted = (IndexedString[]) Sorting.quicksort(idxdata, comp);
        ArrayList<String> out = new ArrayList<String>(idxdata.length);

        for (int i = 0; i < idxdata.length; ++i) {
            if (type == ASCEND) {
                out.add(sorted[i].s);
            } else {
                out.add(sorted[idxdata.length - 1 - i].s);
            }
        }

        return out;
    }

    /**
     * Returns a new array containing the element of the specified array sorted
     * as specified by the type parameter.
     *
     * @param data
     * @param type
     * @return
     */
    public static double[] sort(double[] data, int type) {

        double[] temp = Sorting.quicksort(data);
        double[] out = new double[data.length];
        for (int i = 0; i < data.length; ++i) {
            if (type == Sorter.ASCEND) {
                out[i] = temp[i];
            } else {
                out[i] = temp[data.length - 1 - i];
            }
        }

        return out;
    }

    /**
     * Returns a new array containing the element of the specified array sorted
     * as specified by the type parameter.
     *
     * @param data
     * @param type
     * @return
     */
    public static int[] sort(int[] data, int type) {

        int[] temp = Sorting.quicksort(data);
        int[] out = new int[data.length];
        for (int i = 0; i < data.length; ++i) {
            if (type == Sorter.ASCEND) {
                out[i] = temp[i];
            } else {
                out[i] = temp[data.length - 1 - i];
            }
        }

        return out;
    }

    /**
     * Returns the string array sorted according to the specified direction.
     *
     * @param data
     * @param type
     * @return
     */
    public static String[] sort(String[] data, int type) {

        StringComparator comp = new StringComparator();
        String[] sorted = (String[]) Sorting.quicksort(data, comp);
        String[] out = new String[data.length];

        for (int i = 0; i < data.length; ++i) {
            if (type == ASCEND) {
                out[i] = sorted[i];
            } else {
                out[i] = sorted[data.length - 1 - i];
            }
        }

        return out;
    }

    /**
     * Returns the ArrayList<Integer> sorted according to the specified
     * direction.
     *
     * @param data
     * @param type
     * @return
     */
    public static ArrayList<Integer> sortInt(ArrayList<Integer> data, int type) {

        int[] arrayData = new int[data.size()];
        for (int i = 0; i < arrayData.length; ++i) {
            arrayData[i] = data.get(i);
        }
        int[] sorted = Sorting.quicksort(arrayData);
        ArrayList<Integer> out = new ArrayList<Integer>(arrayData.length);

        for (int i = 0; i < arrayData.length; ++i) {
            if (type == ASCEND) {
                out.add(sorted[i]);
            } else {
                out.add(sorted[arrayData.length - 1 - i]);
            }
        }

        return out;
    }
}