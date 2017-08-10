package com.mar.algotools.signalprocessing;

import com.mar.framework.core.logging.Logger;

/**
 * See http://www.originlab.com/doc/Origin-Help/2DFFT-Filter-Algorithm<br/>
 * See http://paulbourke.net/miscellaneous/dft/
 * @author mrenauld
 */
public class FFTFactory {

    /** Low pass filter. */
    public static final int TYPE_LOW_PASS = 0;
    /** High pass filter. */
    public static final int TYPE_HIGH_PASS = 1;
    /** Power of f filter. */
    public static final int TYPE_POWER = 2;

    /**
     * Filters the specified data using the specified type of filter (and specified parameter). The data are transformed
     * into the frequency domain through an FFT, filtered, then transformed back into the normal domain.
     * @param pData
     * @param pType
     * @param pParam
     * @return
     */
    public static double[][] filter(double[][] pData, int pType, double pParam) {
        int M = pData.length;
        int N = pData[0].length;

        /* Build the corresponding complex array. The real part is the data. */
        Complex[][] complexData = new Complex[M][N];
        for (int u = 0; u < M; ++u) {
            for (int v = 0; v < N; ++v) {
                complexData[u][v] = new Complex(pData[u][v], 0.0);
            }
        }

        /* Do a FFT. */
        Fourier2D fourier2d = new Fourier2D(M, N);
        Complex[][] fft = fourier2d.fft(complexData);

        /* Reorganize quadrants to put the lower frequency in the middle. */
        reorganizeQuadrant(fft);

        /* Filter. */
        double[][] filter = null;
        switch (pType) {
            case TYPE_LOW_PASS:
                filter = getLowPassFilter(M, N, pParam);
                break;
            case TYPE_HIGH_PASS:
                filter = getHighPassFilter(M, N, pParam);
                break;
            case TYPE_POWER:
                filter = getPowerFFilter(M, N, pParam);
                break;
            default:
                Logger.logError(FFTFactory.class, "Unknown type [" + pType + "], defaulting to low pass filter.");
                filter = getLowPassFilter(M, N, pParam);
                break;
        }
        for (int u = 0; u < M; ++u) {
            for (int v = 0; v < N; ++v) {
                fft[u][v] = fft[u][v].mult(filter[u][v]);
            }
        }

        /* Reorganize back the quadrants in their initial state. */
        reorganizeQuadrant(fft);

        /* Do the inverse FFT. */
        Complex[][] ifft = fourier2d.ifft(fft);

        /* Return the real part. */
        double[][] filteredData = new double[M][N];
        for (int u = 0; u < M; ++u) {
            for (int v = 0; v < N; ++v) {
                filteredData[u][v] = ifft[u][v].real;
            }
        }

        return filteredData;
    }

    /**
     * Test the Fourier transform, see http://fourier.eng.hmc.edu/e101/lectures/Image_Processing/node6.html
     */
    public static void test() {
        Complex[][] in = new Complex[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                in[i][j] = new Complex(0.0, 0.0);
            }
        }

        in[1][2].real = 70.0;
        in[1][3].real = 80.0;
        in[1][4].real = 90.0;

        in[2][2].real = 90.0;
        in[2][3].real = 100.0;
        in[2][4].real = 110.0;

        in[3][2].real = 110.0;
        in[3][3].real = 120.0;
        in[3][4].real = 130.0;

        in[4][2].real = 130.0;
        in[4][3].real = 140.0;
        in[4][4].real = 150.0;

        Fourier2D fourier2d = new Fourier2D(8, 8);
        Complex[][] out = fourier2d.fft(in);

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                System.out.print(out[i][j].real * 8 + " ");
            }
            System.out.println();
        }

        System.out.println();

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                System.out.print(out[i][j].imag * 8 + " ");
            }
            System.out.println();
        }
    }

    /**
     * Returns the high pass filter with the specified cutoff value.
     * @param pM
     * @param pN
     * @param pCutoff
     * @return
     */
    private static double[][] getHighPassFilter(int pM, int pN, double pCutoff) {
        double[][] filter = getLowPassFilter(pM, pN, pCutoff);
        for (int u = 0; u < pM; ++u) {
            for (int v = 0; v < pN; ++v) {
                filter[u][v] = 1.0 - filter[u][v];
            }
        }
        return filter;
    }

    /**
     * Returns the low pass filter with the specified cutoff value.
     * @param pM
     * @param pN
     * @param pCutoff
     * @return
     */
    private static double[][] getLowPassFilter(int pM, int pN, double pCutoff) {
        double[][] filter = new double[pM][pN];
        for (int u = 0; u < pM; ++u) {
            for (int v = 0; v < pN; ++v) {
                /* Normalize frequency coordinates. */
                double m = (u - pM / 2.0) / pM;
                double n = (v - pN / 2.0) / pN;
                double r = Math.sqrt(m * m + n * n);
                if (r < pCutoff) {
                    filter[u][v] = 1.0;
                }
                else {
                    filter[u][v] = 0.0;
                }
            }
        }
        return filter;
    }

    /**
     * Returns the power of f filter with the specified exponent.
     * @param pM
     * @param pN
     * @param pExp
     * @return
     */
    private static double[][] getPowerFFilter(int pM, int pN, double pExp) {
        double[][] filter = new double[pM][pN];
        for (int u = 0; u < pM; ++u) {
            for (int v = 0; v < pN; ++v) {
                /* Normalize frequency coordinates. */
                double m = (u - pM / 2.0) / pM;
                double n = (v - pN / 2.0) / pN;
                double r = Math.sqrt(m * m + n * n);
                if (r != 0.0) {
                    filter[u][v] = Math.pow(r, pExp);
                }
            }
        }
        return filter;
    }

    private static void reorganizeQuadrant(Complex[][] pData) {
        int M = pData.length;
        int N = pData[0].length;

        for (int u = 0; u < M / 2; ++u) {
            for (int v = 0; v < N / 2; ++v) {
                Complex temp = new Complex(pData[u][v].real, pData[u][v].imag);
                pData[u][v] = pData[M / 2 + u][N / 2 + v];
                pData[M / 2 + u][N / 2 + v] = temp;

                temp = new Complex(pData[M / 2 + u][v].real, pData[M / 2 + u][v].imag);
                pData[M / 2 + u][v] = pData[u][N / 2 + v];
                pData[u][N / 2 + v] = temp;
            }
        }
    }

}