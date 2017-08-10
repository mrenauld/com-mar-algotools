package com.mar.algotools.statistics;

public class ProbaDistribUtils {

    /**
     * Returns the Kullback-Leibler divergence for two probability distributions. The probability distributions are
     * estimated based on two sample arrays using histograms with specified lower bound, upper bound and number of bins.
     * @param pX
     * @param pY
     * @param pLowerBound
     * @param pUpperBound
     * @param pNbSteps
     * @return
     */
    public static double getKullbackLeiblerDivergence(double[] pX, double[] pY, double pLowerBound, double pUpperBound,
        int pNbBins) {
        Histogram hX = new Histogram(pX, pLowerBound, pUpperBound, pNbBins);
        Histogram hY = new Histogram(pY, pLowerBound, pUpperBound, pNbBins);

        return getKullbackLeiblerDivergence(hX, hY);
    }

    /**
     * Returns the Kullback-Leibler divergence for two probability distributions represented by the specified
     * histograms. It is assumed that the two histograms have the same bins.
     * @param pHX
     * @param pHY
     * @return
     */
    public static double getKullbackLeiblerDivergence(Histogram pHX, Histogram pHY) {
        double[] hXValues = pHX.getHistogramValues();
        double[] hYValues = pHY.getHistogramValues();

        double kl = 0.0;
        for (int i = 0; i < pHX.getNbBins(); ++i) {
            if (hYValues[i] == 0.0) {
                continue;
            }

            if (hXValues[i] == 0.0) {
                System.out.println("X_i == 0.0 should imply Y_i == 0.0, but it is not the case.");
                continue;
            }

            kl += Math.log(hYValues[i] / hXValues[i]) * hYValues[i];
        }

        return kl;
    }

}