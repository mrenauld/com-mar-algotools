package com.mar.algotools.mathematics.utils;

import java.util.ArrayList;
import java.util.Vector;

public class ArrayUtils {

    /**
     * Returns true if the two specified arrays have the same size and contain
     * the same values.
     * 
     * @param pA
     * @param pB
     * @return
     */
    public static boolean areArraysEqual(int[] pA, int[] pB) {
        if (pA.length != pB.length) {
            return false;
        }

        for (int i = 0; i < pA.length; ++i) {
            if (pA[i] != pB[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a list containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static ArrayList<Boolean> array2list(boolean[] pA) {
        ArrayList<Boolean> list = new ArrayList<Boolean>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            list.add(new Boolean(pA[i]));
        }
        return list;
    }

    /**
     * Returns a list containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static ArrayList<Double> array2list(double[] pA) {
        ArrayList<Double> list = new ArrayList<Double>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            list.add(new Double(pA[i]));
        }
        return list;
    }

    /**
     * Returns a list containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static ArrayList<Float> array2list(float[] pA) {
        ArrayList<Float> list = new ArrayList<Float>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            list.add(new Float(pA[i]));
        }
        return list;
    }

    /**
     * Returns a list containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static ArrayList<Integer> array2list(int[] pA) {
        ArrayList<Integer> list = new ArrayList<Integer>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            list.add(new Integer(pA[i]));
        }
        return list;
    }

    /**
     * Returns a list containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static ArrayList<String> array2list(String[] pA) {
        ArrayList<String> list = new ArrayList<String>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            list.add(pA[i]);
        }
        return list;
    }

    /**
     * Returns a list containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static <T> ArrayList<T> array2list(T[] pA) {
        ArrayList<T> list = new ArrayList<T>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            list.add(pA[i]);
        }
        return list;
    }

    /**
     * Returns a vector containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static Vector<Boolean> array2vec(boolean[] pA) {
        final Vector<Boolean> v = new Vector<Boolean>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            v.add(new Boolean(pA[i]));
        }
        return v;
    }

    /**
     * Returns a vector containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static Vector<Double> array2vec(double[] pA) {
        final Vector<Double> v = new Vector<Double>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            v.add(new Double(pA[i]));
        }
        return v;
    }

    /**
     * Returns a vector containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static Vector<Float> array2vec(float[] pA) {
        final Vector<Float> v = new Vector<Float>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            v.add(new Float(pA[i]));
        }
        return v;
    }

    /**
     * Returns a vector containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static Vector<Integer> array2vec(int[] pA) {
        final Vector<Integer> v = new Vector<Integer>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            v.add(new Integer(pA[i]));
        }
        return v;
    }

    /**
     * Returns a vector containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static Vector<String> array2vec(String[] pA) {
        final Vector<String> v = new Vector<String>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            v.add(pA[i]);
        }
        return v;
    }

    /**
     * Returns a vector containing the elements of the specified array.
     * 
     * @param pA
     * @return
     */
    public static <T> Vector<T> array2vec(T[] pA) {
        final Vector<T> v = new Vector<T>(pA.length);
        for (int i = 0; i < pA.length; ++i) {
            v.add(pA[i]);
        }
        return v;
    }

    /**
     * Returns a copy of the specified boolean 2D array.
     * 
     * @param a
     * @return
     */
    public static boolean[][] copy(final boolean[][] a) {
        final boolean[][] b = new boolean[a.length][];
        for (int i = 0; i < a.length; ++i) {
            b[i] = a[i].clone();
        }
        return b;
    }

    /**
     * Returns a copy of the specified double 2D array.
     * 
     * @param a
     * @return
     */
    public static double[][] copy(final double[][] a) {
        final double[][] b = new double[a.length][];
        for (int i = 0; i < a.length; ++i) {
            b[i] = a[i].clone();
        }
        return b;
    }

    /**
     * Returns a copy of the specified integer 2D array.
     * 
     * @param a
     * @return
     */
    public static int[][] copy(final int[][] a) {
        final int[][] b = new int[a.length][];
        for (int i = 0; i < a.length; ++i) {
            b[i] = a[i].clone();
        }
        return b;
    }

    /**
     * Returns an array containing the elements of the specified list.
     * 
     * @param pList
     * @return
     */
    public static boolean[] list2arrayb(ArrayList<Boolean> pList) {
        boolean[] array = new boolean[pList.size()];
        for (int i = 0; i < pList.size(); ++i) {
            array[i] = pList.get(i);
        }
        return array;
    }

    /**
     * Returns an array containing the elements of the specified list.
     * 
     * @param pList
     * @return
     */
    public static double[] list2arrayd(ArrayList<Double> pList) {
        double[] array = new double[pList.size()];
        for (int i = 0; i < pList.size(); ++i) {
            array[i] = pList.get(i);
        }
        return array;
    }

    /**
     * Returns an array containing the elements of the specified list.
     * 
     * @param pList
     * @return
     */
    public static float[] list2arrayf(ArrayList<Float> pList) {
        float[] array = new float[pList.size()];
        for (int i = 0; i < pList.size(); ++i) {
            array[i] = pList.get(i);
        }
        return array;
    }

    /**
     * Returns an array containing the elements of the specified list.
     * 
     * @param pList
     * @return
     */
    public static int[] list2arrayi(ArrayList<Integer> pList) {
        int[] array = new int[pList.size()];
        for (int i = 0; i < pList.size(); ++i) {
            array[i] = pList.get(i);
        }
        return array;
    }

    /**
     * Returns an array containing the elements of the specified list.
     * 
     * @param pList
     * @return
     */
    public static String[] list2arrays(ArrayList<String> pList) {
        String[] array = new String[pList.size()];
        for (int i = 0; i < pList.size(); ++i) {
            array[i] = pList.get(i);
        }
        return array;
    }

    /**
     * Returns a String representation of the specified ArrayList.
     * 
     * @param a
     * @return
     */
    public static String print(final ArrayList<Integer> a) {
        String s = "";
        for (int i = 0; i < a.size(); ++i) {
            s += Integer.toString(a.get(i)) + " ";
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(boolean[] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            if (pA[i] == true) {
                s += "1 ";
            } else {
                s += "0 ";
            }
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(boolean[][] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            s += print(pA[i]) + "\r\n";
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(double[] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            s += Double.toString(pA[i]) + " ";
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(double[][] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            s += print(pA[i]) + "\r\n";
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(float[] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            s += Float.toString(pA[i]) + " ";
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(float[][] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            s += print(pA[i]) + "\r\n";
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(int[] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            s += Integer.toString(pA[i]) + " ";
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(int[][] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            s += print(pA[i]) + "\r\n";
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(String[] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            s += pA[i] + " ";
        }
        return s;
    }

    /**
     * Returns a String representation of the specified array.
     * 
     * @param pA
     * @return
     */
    public static String print(String[][] pA) {
        String s = "";
        for (int i = 0; i < pA.length; ++i) {
            for (int j = 0; j < pA[i].length; ++j) {
                s += pA[i][j] + " ";
            }
            s += "\r\n";
        }
        return s;
    }

    /**
     * Returns a copy of the specified ArrayList. Warning! This is a shallow
     * copy!
     * 
     * @param <T>
     * @param a
     * @return
     */
    public static <T> ArrayList<T> shallowCopy(final ArrayList<T> a) {
        final ArrayList<T> b = new ArrayList<T>(a.size());
        for (int i = 0; i < a.size(); ++i) {
            b.add(a.get(i));
        }
        return b;
    }

    /**
     * Returns an array containing the elements of the specified vector.
     * 
     * @param pVector
     * @return
     */
    public static boolean[] vec2arrayb(Vector<Boolean> pVector) {
        boolean[] array = new boolean[pVector.size()];
        for (int i = 0; i < pVector.size(); ++i) {
            array[i] = pVector.get(i);
        }
        return array;
    }

    /**
     * Returns an array containing the elements of the specified vector.
     * 
     * @param pVector
     * @return
     */
    public static double[] vec2arrayd(Vector<Double> pVector) {
        double[] array = new double[pVector.size()];
        for (int i = 0; i < pVector.size(); ++i) {
            array[i] = pVector.get(i);
        }
        return array;
    }

    /**
     * Returns an array containing the elements of the specified vector.
     * 
     * @param pVector
     * @return
     */
    public static float[] vec2arrayf(Vector<Float> pVector) {
        float[] array = new float[pVector.size()];
        for (int i = 0; i < pVector.size(); ++i) {
            array[i] = pVector.get(i);
        }
        return array;
    }

    /**
     * Returns an array containing the elements of the specified vector.
     * 
     * @param pVector
     * @return
     */
    public static int[] vec2arrayi(Vector<Integer> pVector) {
        int[] array = new int[pVector.size()];
        for (int i = 0; i < pVector.size(); ++i) {
            array[i] = pVector.get(i);
        }
        return array;
    }

    /**
     * Returns an array containing the elements of the specified vector.
     * 
     * @param pVector
     * @return
     */
    public static String[] vec2arrays(Vector<String> pVector) {
        String[] array = new String[pVector.size()];
        for (int i = 0; i < pVector.size(); ++i) {
            array[i] = pVector.get(i);
        }
        return array;
    }
}
