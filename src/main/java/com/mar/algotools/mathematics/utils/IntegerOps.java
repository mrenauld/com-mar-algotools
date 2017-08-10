package com.mar.algotools.mathematics.utils;

public class IntegerOps {

    /**
     * Returns true if pN is an hexagonal number, verifying pN = n*(2*n-1) with n integer.
     * @param pN
     * @return
     */
    public static boolean isHexagonalNumber(long pN) {
        /* Check that 1+8*pN is square. */
        long sqrt = sqrt(1 + 8 * pN);

        if (sqrt == -1L) {
            return false;
        }

        /* Check that 1 +sqrt % 4 == 0. */
        if ((1 + sqrt) % 4 != 0) {
            return false;
        }

        return true;
    }

    /**
     * Returns true if pN is a pentagonal number, verifying pN = n*(3*n-1)/2 with n integer.
     * @param pN
     * @return
     */
    public static boolean isPentagonalNumber(long pN) {
        /* Check that 1+24*pN is square. */
        long sqrt = sqrt(1 + 24 * pN);

        if (sqrt == -1L) {
            return false;
        }

        /* Check that 1 +sqrt % 6 == 0. */
        if ((1 + sqrt) % 6 != 0) {
            return false;
        }

        return true;
    }

    /**
     * Returns true if pN is the square of an integer.
     * @param pN
     * @return
     */
    public static boolean isSquare(int pN) {
        if (pN < 0) {
            return false;
        }
        if (pN == 0) {
            return true;
        }

        int sqrt = (int) (Math.sqrt(pN));
        return sqrt * sqrt == pN;
    }

    /**
     * Returns true if pN is the square of an integer.
     * @param pN
     * @return
     */
    public static boolean isSquare(long pN) {
        if (pN < 0) {
            return false;
        }
        if (pN == 0) {
            return true;
        }

        long sqrt = (long) (Math.sqrt(pN));
        return sqrt * sqrt == pN;
    }

    /**
     * Returns true if pN is a triangle number, verifying pN = n*(n+1)/2 with n integer.
     * @param pN
     * @return
     */
    public static boolean isTriangleNumber(long pN) {
        /* Check that 1+8*pN is square and sqrt(1+8*pN) is odd. */
        long sqrt = sqrt(1 + 8 * pN);

        if (sqrt == -1L || sqrt % 2 == 0) {
            return false;
        }

        return true;
    }

    /**
     * Returns the integer square root of pN, or -1 if the square root is not an integer.
     * @param pN
     * @return
     */
    public static int sqrt(int pN) {
        if (pN < 0) {
            return -1;
        }
        if (pN == 0) {
            return -1;
        }

        int sqrt = (int) (Math.sqrt(pN));
        if (sqrt * sqrt == pN) {
            return sqrt;
        }
        else {
            return -1;
        }
    }

    /**
     * Returns the integer square root of pN, or -1 if the square root is not an integer.
     * @param pN
     * @return
     */
    public static long sqrt(long pN) {
        if (pN < 0) {
            return -1L;
        }
        if (pN == 0) {
            return -1L;
        }

        long sqrt = (long) (Math.sqrt(pN));
        if (sqrt * sqrt == pN) {
            return sqrt;
        }
        else {
            return -1L;
        }
    }

}