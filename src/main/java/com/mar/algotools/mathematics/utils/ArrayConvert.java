package com.mar.algotools.mathematics.utils;

public class ArrayConvert {

    /**
     * Converts a 2D double array into a 2D float array.
     * @param pDoubleArray
     * @return
     */
    public static float[][] doubleToFloat2dArray(double[][] pDoubleArray) {
        int x = pDoubleArray.length;
        int y = x > 0 ? pDoubleArray[0].length : 0;
        float[][] floatArray = new float[x][y];
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                floatArray[i][j] = (float) pDoubleArray[i][j];
            }
        }
        return floatArray;
    }

    /**
     * Converts a 2D float array into a 2D double array.
     * @param pFloatArray
     * @return
     */
    public static double[][] floatToDouble2dArray(float[][] pFloatArray) {
        int x = pFloatArray.length;
        int y = x > 0 ? pFloatArray[0].length : 0;
        double[][] doubleArray = new double[x][y];
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                doubleArray[i][j] = pFloatArray[i][j];
            }
        }
        return doubleArray;
    }

}