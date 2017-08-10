package com.mar.algotools.signalprocessing;

import com.mar.algotools.matrix.MatrixUtils;

public class CannyFactory {

    public static double[][] cannyEdgeDetection(double[][] pMatrix, int pK, double pSigma) {
        int x = pMatrix.length;
        int y = pMatrix[0].length;

        double[][] gaussianKernel = GaussianFactory.getGaussianKernel(pK, pSigma);
        double[][] gaussFiltered = MatrixUtils.convolution(pMatrix, gaussianKernel, true);

        double[][] Gx = SobelFactory.getGradientX(gaussFiltered);
        double[][] Gy = SobelFactory.getGradientY(gaussFiltered);

        double[][] G = new double[x][y];
        double[][] theta = new double[x][y];
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                G[i][j] = Math.hypot(Gx[i][j], Gy[i][j]);
                theta[i][j] = Math.atan2(Gy[i][j], Gx[i][j]);
            }
        }

        double[][] magnitude = new double[x][y];
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                // TODO continue
            }
        }

        return gaussFiltered;
    }

}
