package com.mar.algotools.mathematics.utils;

public class Counter {

    private int radix = 10;

    private int[] counter;

    public Counter(int pLength) {
        counter = new int[pLength];
    }

    public int[] getCounterValue() {
        return counter;
    }

    public int getRadix() {
        return radix;
    }

    public void increment() {
        increment(1);
    }

    public void increment(int pIncrement) {
        int report = pIncrement;
        int i = counter.length - 1;
        while (report != 0 && i >= 0) {
            counter[i] += report;
            report = counter[i] / radix;
            counter[i] = counter[i] % radix;
            i--;
        }
    }

    public void setRadix(int pRadix) {
        radix = pRadix;
    }

}