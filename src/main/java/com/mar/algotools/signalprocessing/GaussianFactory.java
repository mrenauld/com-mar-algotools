package com.mar.algotools.signalprocessing;

import com.mar.algotools.matrix.MatrixUtils;

public class GaussianFactory {

    /**
     * Returns the convolution between the specified matrix and the Gaussian kernel of size 2*pK+1 x 2*pK+1 and with
     * specified sigma.
     * @param pMatrix
     * @param pK
     * @param pSigma
     * @return
     */
    public static double[][] applyGaussianFilter(double[][] pMatrix, int pK, double pSigma) {
        double[][] kernel = getGaussianKernel(pK, pSigma);
        return MatrixUtils.convolution(pMatrix, kernel, true);
    }

    /**
     * Returns the Gaussian kernel of size 2*pK+1 x 2*pK+1 and with specified sigma.
     * @param pK
     * @param pSigma
     * @return
     */
    public static double[][] getGaussianKernel(int pK, double pSigma) {
        double[][] kernel = new double[2 * pK + 1][2 * pK + 1];
        double coef_1 = 1.0 / (2.0 * Math.PI * pSigma * pSigma);
        double coef_2 = 2.0 * pSigma * pSigma;
        for (int i = 0; i < 2 * pK + 1; ++i) {
            for (int j = 0; j < 2 * pK + 1; ++j) {
                double exp = Math.pow((i + 1) - (pK + 1), 2.0) + Math.pow((j + 1) - (pK + 1), 2.0);
                kernel[i][j] = coef_1 * Math.exp(-exp / coef_2);
            }
        }
        return kernel;
    }

}