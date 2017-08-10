package com.mar.algotools.matrix;

public class Kernel {

    protected int xSize = 1;

    protected int ySize = 1;

    protected double[][] defaultArray;

    public Kernel() {
        defaultArray = new double[1][1];
        defaultArray[0][0] = 1.0;
    }

    public double[][] getKernelForPixel(int pX, int pY) {
        return defaultArray;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

}
