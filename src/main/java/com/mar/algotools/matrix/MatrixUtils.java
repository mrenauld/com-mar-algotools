package com.mar.algotools.matrix;

import com.mar.algotools.mathematics.utils.MathOps;

public class MatrixUtils {

    /**
     * Returns the 2D discrete convolution between the specified matrix and the
     * specified kernel. If pClamp is set to true, the data outside the matrix
     * borders are replaced by the closest ones (by clamping). See
     * http://www.songho.ca/dsp/convolution/convolution2d_example.html.
     *
     * @param pMatrix
     * @param pKernel
     * @param pClamp
     * @return
     */
    public static double[][] convolution(double[][] pMatrix, double[][] pKernel, boolean pClamp) {
        int X = pMatrix.length;
        int Y = pMatrix[0].length;

        int kX = pKernel.length;
        int kY = pKernel[0].length;

        double[][] result = new double[X][Y];
        for (int x = 0; x < X; ++x) {
            for (int y = 0; y < Y; ++y) {

                result[x][y] = 0.0;
                for (int i = 0; i < kX; ++i) {
                    for (int j = 0; j < kY; ++j) {
                        int kx = kX - i - 1;
                        int ky = kY - j - 1;
                        int mx = x - (kX - 1) / 2 + i;
                        int my = y - (kY - 1) / 2 + j;

                        if (!pClamp) {
                            if (mx >= 0 && mx < X && my >= 0 && my < Y) {
                                result[x][y] += pMatrix[mx][my] * pKernel[kx][ky];
                            }
                        } else {
                            /*
                             * If a value is outside the matrix, take the
                             * closest value instead.
                             */
                            result[x][y] += pMatrix[MathOps.clamp(mx, 0, X - 1)][MathOps.clamp(my, 0, Y - 1)]
                                    * pKernel[kx][ky];
                        }
                    }
                }

            }
        }

        return result;
    }

    /**
     * Returns the 2D discrete convolution between the specified matrix and the
     * specified kernel. If pClamp is set to true, the data outside the matrix
     * borders are replaced by the closest ones (by clamping). See
     * http://www.songho.ca/dsp/convolution/convolution2d_example.html.
     * 
     * @param pMatrix
     * @param pKernel
     * @param pClamp
     * @return
     */
    public static double[][] convolution(double[][] pMatrix, Kernel pKernel, boolean pClamp) {
        int X = pMatrix.length;
        int Y = pMatrix[0].length;

        int kX = pKernel.getXSize();
        int kY = pKernel.getYSize();

        double[][] result = new double[X][Y];
        for (int x = 0; x < X; ++x) {
            for (int y = 0; y < Y; ++y) {

                double[][] k = pKernel.getKernelForPixel(x, y);

                result[x][y] = 0.0;
                for (int i = 0; i < kX; ++i) {
                    for (int j = 0; j < kY; ++j) {
                        int kx = kX - i - 1;
                        int ky = kY - j - 1;
                        int mx = x - (kX - 1) / 2 + i;
                        int my = y - (kY - 1) / 2 + j;

                        if (!pClamp) {
                            if (mx >= 0 && mx < X && my >= 0 && my < Y) {
                                result[x][y] += pMatrix[mx][my] * k[kx][ky];
                            }
                        } else {
                            /*
                             * If a value is outside the matrix, take the
                             * closest value instead.
                             */
                            result[x][y] += pMatrix[MathOps.clamp(mx, 0, X - 1)][MathOps.clamp(my, 0, Y - 1)]
                                    * k[kx][ky];
                        }
                    }
                }

            }
        }

        return result;
    }

    /**
     * Returns an identity matrix of size n x n.
     *
     * @param n
     * @return
     */
    public static Matrix getIdentityMatrix(final int n) {
        final Matrix matrix = new Matrix(n, n);
        for (int i = 0; i < n; ++i) {
            matrix.set(i, i, 1.0f);
        }
        return matrix;
    }

    /**
     * Returns the inverse of the specified 3x3 matrix.
     *
     * @param pMatrix
     * @return
     */
    public static Matrix inverse3x3(Matrix pMatrix) {
        Matrix inverse = inverseTranspose3x3(pMatrix);
        inverse.transpose();
        return inverse;
    }

    /**
     * Returns the inverse of the specified 4x4 matrix.
     *
     * @param pMatrix
     * @return
     */
    public static Matrix inverse4x4(Matrix pMatrix) {
        Matrix inverse = inverseTranspose4x4(pMatrix);
        inverse.transpose();
        return inverse;
    }

    /**
     * Returns the transposed inverse of the specified 3x3 matrix.
     *
     * @param pMatrix
     * @return
     */
    public static Matrix inverseTranspose3x3(Matrix pMatrix) {
        float[][] m = pMatrix.to2DArray();
        float determinant = +m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
                - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0]) + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);

        Matrix inverse = new Matrix(3, 3);
        inverse.set(0, 0, (m[1][1] * m[2][2] - m[2][1] * m[1][2]));
        inverse.set(0, 1, -(m[1][0] * m[2][2] - m[2][0] * m[1][2]));
        inverse.set(0, 2, (m[1][0] * m[2][1] - m[2][0] * m[1][1]));
        inverse.set(1, 0, -(m[0][1] * m[2][2] - m[2][1] * m[0][2]));
        inverse.set(1, 1, (m[0][0] * m[2][2] - m[2][0] * m[0][2]));
        inverse.set(1, 2, -(m[0][0] * m[2][1] - m[2][0] * m[0][1]));
        inverse.set(2, 0, (m[0][1] * m[1][2] - m[1][1] * m[0][2]));
        inverse.set(2, 1, -(m[0][0] * m[1][2] - m[1][0] * m[0][2]));
        inverse.set(2, 2, (m[0][0] * m[1][1] - m[1][0] * m[0][1]));

        inverse.times(1.0f / determinant);

        return inverse;
    }

    /**
     * Returns the transposed inverse of the specified 4x4 matrix.
     *
     * @param pMatrix
     * @return
     */
    public static Matrix inverseTranspose4x4(Matrix pMatrix) {
        float[][] m = pMatrix.to2DArray();

        float subFactor00 = m[2][2] * m[3][3] - m[3][2] * m[2][3];
        float subFactor01 = m[2][1] * m[3][3] - m[3][1] * m[2][3];
        float subFactor02 = m[2][1] * m[3][2] - m[3][1] * m[2][2];
        float subFactor03 = m[2][0] * m[3][3] - m[3][0] * m[2][3];
        float subFactor04 = m[2][0] * m[3][2] - m[3][0] * m[2][2];
        float subFactor05 = m[2][0] * m[3][1] - m[3][0] * m[2][1];
        float subFactor06 = m[1][2] * m[3][3] - m[3][2] * m[1][3];
        float subFactor07 = m[1][1] * m[3][3] - m[3][1] * m[1][3];
        float subFactor08 = m[1][1] * m[3][2] - m[3][1] * m[1][2];
        float subFactor09 = m[1][0] * m[3][3] - m[3][0] * m[1][3];
        float subFactor10 = m[1][0] * m[3][2] - m[3][0] * m[1][2];
        float subFactor11 = m[1][1] * m[3][3] - m[3][1] * m[1][3];
        float subFactor12 = m[1][0] * m[3][1] - m[3][0] * m[1][1];
        float subFactor13 = m[1][2] * m[2][3] - m[2][2] * m[1][3];
        float subFactor14 = m[1][1] * m[2][3] - m[2][1] * m[1][3];
        float subFactor15 = m[1][1] * m[2][2] - m[2][1] * m[1][2];
        float subFactor16 = m[1][0] * m[2][3] - m[2][0] * m[1][3];
        float subFactor17 = m[1][0] * m[2][2] - m[2][0] * m[1][2];
        float subFactor18 = m[1][0] * m[2][1] - m[2][0] * m[1][1];

        Matrix inverse = new Matrix(4, 4);
        inverse.set(0, 0, (m[1][1] * subFactor00 - m[1][2] * subFactor01 + m[1][3] * subFactor02));
        inverse.set(0, 1, -(m[1][0] * subFactor00 - m[1][2] * subFactor03 + m[1][3] * subFactor04));
        inverse.set(0, 2, (m[1][0] * subFactor01 - m[1][1] * subFactor03 + m[1][3] * subFactor05));
        inverse.set(0, 3, -(m[1][0] * subFactor02 - m[1][1] * subFactor04 + m[1][2] * subFactor05));
        inverse.set(1, 0, -(m[0][1] * subFactor00 - m[0][2] * subFactor01 + m[0][3] * subFactor02));
        inverse.set(1, 1, (m[0][0] * subFactor00 - m[0][2] * subFactor03 + m[0][3] * subFactor04));
        inverse.set(1, 2, -(m[0][0] * subFactor01 - m[0][1] * subFactor03 + m[0][3] * subFactor05));
        inverse.set(1, 3, (m[0][0] * subFactor02 - m[0][1] * subFactor04 + m[0][2] * subFactor05));
        inverse.set(2, 0, (m[0][1] * subFactor06 - m[0][2] * subFactor07 + m[0][3] * subFactor08));
        inverse.set(2, 1, -(m[0][0] * subFactor06 - m[0][2] * subFactor09 + m[0][3] * subFactor10));
        inverse.set(2, 2, (m[0][0] * subFactor11 - m[0][1] * subFactor09 + m[0][3] * subFactor12));
        inverse.set(2, 3, -(m[0][0] * subFactor08 - m[0][1] * subFactor10 + m[0][2] * subFactor12));
        inverse.set(3, 0, -(m[0][1] * subFactor13 - m[0][2] * subFactor14 + m[0][3] * subFactor15));
        inverse.set(3, 1, (m[0][0] * subFactor13 - m[0][2] * subFactor16 + m[0][3] * subFactor17));
        inverse.set(3, 2, -(m[0][0] * subFactor14 - m[0][1] * subFactor16 + m[0][3] * subFactor18));
        inverse.set(3, 3, (m[0][0] * subFactor15 - m[0][1] * subFactor17 + m[0][2] * subFactor18));

        float determinant = +m[0][0] * inverse.get(0, 0) + m[0][1] * inverse.get(0, 1) + m[0][2] * inverse.get(0, 2)
                + m[0][3] * inverse.get(0, 3);

        inverse.times(1.0f / determinant);

        return inverse;
    }

    /**
     * Normalizes the specified matrix between 0 and 1.
     *
     * @param pMatrix
     */
    public static void normalize(double[][] pMatrix) {
        int m = pMatrix.length;
        int n = pMatrix[0].length;
        double min = Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (pMatrix[i][j] < min) {
                    min = pMatrix[i][j];
                }
                if (pMatrix[i][j] > max) {
                    max = pMatrix[i][j];
                }
            }
        }
        double coef = 1.0 / (max - min);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                pMatrix[i][j] = (pMatrix[i][j] - min) * coef;
            }
        }
    }

    /**
     * Normalizes the specified matrix between 0 and 1. If all values of the
     * matrix are the same, all values are set to 0.
     *
     * @param pMatrix
     */
    public static void normalize(float[][] pMatrix) {
        int m = pMatrix.length;
        int n = pMatrix[0].length;
        float min = Float.MAX_VALUE;
        float max = -Float.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (pMatrix[i][j] < min) {
                    min = pMatrix[i][j];
                }
                if (pMatrix[i][j] > max) {
                    max = pMatrix[i][j];
                }
            }
        }
        float coef = 0.0f;
        if (max != min) {
            coef = 1.0f / (max - min);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                pMatrix[i][j] = (pMatrix[i][j] - min) * coef;
            }
        }
    }
}