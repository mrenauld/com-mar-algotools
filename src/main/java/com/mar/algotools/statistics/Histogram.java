package com.mar.algotools.statistics;

public class Histogram {

    private double[] data;

    private double[] histogramValues;

    private double lowerBound;

    private double binWidth;

    private int nbBins;

    public Histogram() {

    }

    public Histogram(double[] pData, double pLowerBound, double pUpperBound, int pNbBins) {
        evaluate(pData, pLowerBound, pUpperBound, pNbBins);
    }

    public void evaluate(double[] pData, double pLowerBound, double pUpperBound, int pNbBins) {
        lowerBound = pLowerBound;
        nbBins = pNbBins;
        binWidth = (pUpperBound - pLowerBound) / pNbBins;

        histogramValues = new double[nbBins];
        for (int i = 0; i < pData.length; ++i) {
            double scaled = (pData[i] - lowerBound) / (pUpperBound - lowerBound) * nbBins;
            int bin = (int) Math.floor(scaled);

            if (bin >= 0 && bin < nbBins) {
                histogramValues[bin] += 1.0 / pData.length;
            }
        }
    }

    public void evaluateAndSaveData(double[] pData, double pLowerBound, double pUpperBound, int pNbBins) {
        data = pData;
        evaluate(pData, pLowerBound, pUpperBound, pNbBins);
    }

    public double getBinWidth() {
        return binWidth;
    }

    public double[] getData() {
        return data;
    }

    public double[] getHistogramValues() {
        return histogramValues;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public int getNbBins() {
        return nbBins;
    }

    public double getUpperBound() {
        return lowerBound + nbBins * binWidth;
    }

}