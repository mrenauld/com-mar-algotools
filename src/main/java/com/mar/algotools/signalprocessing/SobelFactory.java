package com.mar.algotools.signalprocessing;

import com.mar.algotools.matrix.MatrixUtils;

/**
 * Sobel operator related methods. See <a href="https://en.wikipedia.org/wiki/Sobel_operator">the Wikipedia page</a>.
 * @author mrenauld
 */
public class SobelFactory {

    public static final double[][] KERNEL_X = new double[][] { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };

    public static final double[][] KERNEL_Y = new double[][] { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };

    /**
     * Returns a double array containing the Sobel operator applied to the specified matrix.
     * @param pMatrix
     * @return
     */
    public static double[][] getGradientMagnitude(double[][] pMatrix) {
        double[][] Gx = getGradientX(pMatrix);
        double[][] Gy = getGradientY(pMatrix);

        int X = pMatrix.length;
        int Y = pMatrix[0].length;
        double[][] Gm = new double[X][Y];
        for (int x = 0; x < X; ++x) {
            for (int y = 0; y < Y; ++y) {
                Gm[x][y] = Math.sqrt(Gx[x][y] * Gx[x][y] + Gy[x][y] * Gy[x][y]);
            }
        }

        return Gm;
    }

    /**
     * Returns a double array containing the X kernel applied to the specified matrix.
     * @param pMatrix
     * @return
     */
    public static double[][] getGradientX(double[][] pMatrix) {
        return MatrixUtils.convolution(pMatrix, KERNEL_X, true);
    }

    /**
     * Returns a double array containing the Y kernel applied to the specified matrix.
     * @param pMatrix
     * @return
     */
    public static double[][] getGradientY(double[][] pMatrix) {
        return MatrixUtils.convolution(pMatrix, KERNEL_Y, true);
    }

}
