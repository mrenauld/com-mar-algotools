package com.mar.algotools.noise;

import java.util.Random;

public class GaussianUtils {

    /**
     * Returns a gaussian noise sample using the polar form of Box-Muller
     * transformation. The mean is 0.0 and the standard deviation is 1.0.
     *
     * @return
     */
    public static double getGaussianNoise() {
        return getGaussianNoise(new Random());
    }

    /**
     * Returns a gaussian noise sample using the polar form of Box-Muller
     * transformation with specified mean and standard deviation.
     *
     * @param pMu
     * @param pSigma
     * @return
     */
    public static double getGaussianNoise(double pMu, double pSigma) {
        return getGaussianNoise(new Random(), pMu, pSigma);
    }

    /**
     * Returns a gaussian noise sample using the polar form of Box-Muller
     * transformation. The mean is 0.0 and the standard deviation is 1.0. Uses
     * the specified {@link Random}.
     *
     * @param pRandom
     * @return
     */
    public static double getGaussianNoise(Random pRandom) {
        double w = 1.0;
        double x1 = 0.0;
        double x2 = 0.0;
        while (w >= 1.0) {
            x1 = 2.0 * pRandom.nextDouble() - 1.0;
            x2 = 2.0 * pRandom.nextDouble() - 1.0;
            w = x1 * x1 + x2 * x2;
        }

        w = Math.sqrt(-2.0 * Math.log(w) / w);
        double y1 = x1 * w;

        /* Not used. */
        // double y2 = x2 * w;

        return y1;
    }

    /**
     * Returns a gaussian noise sample using the polar form of Box-Muller
     * transformation with specified mean and standard deviation. Uses the
     * specified {@link Random}.
     * 
     * @param pRandom
     * @param pMu
     * @param pSigma
     * @return
     */
    public static double getGaussianNoise(Random pRandom, double pMu, double pSigma) {
        double x = getGaussianNoise(pRandom);

        x *= pSigma;
        x += pMu;

        return x;
    }

    /**
     * Returns a double array containing the mean and the standard deviation for
     * the Gaussian probability density function built from the specified
     * samples.
     *
     * @param pSamples
     * @return
     */
    public static double[] getGaussianParams(double[] pSamples) {
        int n = pSamples.length;

        /* mean = 1/n * sum_i^n x_i */
        double mean = 0.0;
        for (int i = 0; i < n; ++i) {
            mean += pSamples[i];
        }
        mean /= n;

        /* std = sqrt( 1 / (n-1) * sum_i^n (x_i-mean)^2 ) */
        double std = 0.0;
        for (int i = 0; i < n; ++i) {
            std += Math.pow(pSamples[i] - mean, 2.0);
        }
        std /= (n - 1);
        std = Math.sqrt(std);

        double[] out = new double[2];
        out[0] = mean;
        out[1] = std;

        return out;
    }

    /**
     * Returns the probability density for the normal distribution with
     * specified mean and standard deviation at the specified point pX.
     *
     * @param pMean
     * @param pStd
     * @param pX
     * @return
     */
    public static double getProbabilityDensity(double pMean, double pStd, double pX) {
        double coef = 1.0 / Math.sqrt(2.0 * pStd * pStd * Math.PI);
        double exp = -Math.pow(pX - pMean, 2.0) / (2.0 * pStd * pStd);
        return coef * Math.exp(exp);
    }

}