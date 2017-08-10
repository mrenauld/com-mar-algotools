package com.mar.algotools.series;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fibonacci {

    public static final double PHI = (1.0 + Math.sqrt(5.0)) / 2.0;

    public static final double PHI_INV = (-1.0 + Math.sqrt(5.0)) / 2.0;

    public static BigDecimal getNthFibonacciNumber(int pN) {
        BigDecimal phibd = BigDecimal.valueOf(PHI);
        BigDecimal minusphiinvbd = BigDecimal.valueOf(-PHI_INV);
        BigDecimal sqrt5 = BigDecimal.valueOf(Math.sqrt(5.0));
        BigDecimal result = phibd.pow(pN).subtract(minusphiinvbd.pow(pN)).divide(sqrt5, 10, RoundingMode.HALF_UP);
        return result;
    }

}