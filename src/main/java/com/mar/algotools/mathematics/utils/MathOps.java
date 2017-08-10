package com.mar.algotools.mathematics.utils;

public class MathOps {

    /**
     * Returns the ceil value.
     * @param pV
     * @return
     */
    public static int ceil(double pV) {
        if (pV >= 0.0) {
            return (int) pV + 1;
        }
        else {
            return (int) pV;
        }
    }

    /**
     * Returns the ceil value.
     * @param pV
     * @return
     */
    public static int ceil(float pV) {
        if (pV >= 0.0f) {
            return (int) pV + 1;
        }
        else {
            return (int) pV;
        }
    }

    /**
     * Returns min(max(pV, pMin), pMax).
     * @param pV
     * @param pMin
     * @param pMax
     * @return
     */
    public static double clamp(double pV, double pMin, double pMax) {
        if (pV < pMin) {
            return pMin;
        }
        if (pV > pMax) {
            return pMax;
        }
        return pV;
    }

    /**
     * Returns min(max(pV, pMin), pMax).
     * @param pV
     * @param pMin
     * @param pMax
     * @return
     */
    public static int clamp(int pV, int pMin, int pMax) {
        if (pV < pMin) {
            return pMin;
        }
        if (pV > pMax) {
            return pMax;
        }
        return pV;
    }

    /**
     * Returns the distance between p1 (p1X, p1Y) and p2 (p2X, p2Y).
     * @param p1X
     * @param p1Y
     * @param p2X
     * @param p2Y
     * @return
     */
    public static float dist(float p1X, float p1Y, float p2X, float p2Y) {
        float x = p2X - p1X;
        float y = p2Y - p1Y;
        return (float) (Math.sqrt(x * x + y * y));
    }

    /**
     * Returns a!. Caution: does not work with values higher than ?.
     * @param a
     * @return
     */
    public static int fact(final int a) {
        if (a < 0) {
            System.out.println("BasicMath.fact - error: a should be >= 0.");
            return 0;
        }
        if (a <= 1) {
            return 1;
        }
        else {
            return a * fact(a - 1);
        }
    }

    /**
     * Returns the floor value.
     * @param pV
     * @return
     */
    public static int floor(double pV) {
        return (int) Math.floor(pV);
    }

    /**
     * Returns the floor value.
     * @param pV
     * @return
     */
    public static int floor(float pV) {
        if (pV >= 0.0f) {
            return (int) pV;
        }
        else {
            return (int) pV - 1;
        }
    }

    /**
     * Returns the maximum value in the array.
     * @param a
     * @return
     */
    public static double max(final double[] a) {
        double b = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > b) {
                b = a[i];
            }
        }
        return b;
    }

    /**
     * Returns the maximum value in the array.
     * @param a
     * @return
     */
    public static double max(final double[][] a) {
        double b = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a[i].length; ++j) {
                if (a[i][j] > b) {
                    b = a[i][j];
                }
            }
        }
        return b;
    }

    /**
     * Returns the maximum value in the array.
     * @param a
     * @return
     */
    public static int max(final int[] a) {
        int b = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > b) {
                b = a[i];
            }
        }
        return b;
    }

    /**
     * Returns the index of the maximum value in the array.
     * @param a
     * @return
     */
    public static int maxIdx(final double[] a) {
        double b = Double.NEGATIVE_INFINITY;
        int idx = -1;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > b) {
                b = a[i];
                idx = i;
            }
        }
        return idx;
    }

    /**
     * Returns the index of the maximum value in the array.
     * @param a
     * @return
     */
    public static int maxIdx(final int[] a) {
        int b = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > b) {
                b = a[i];
                idx = i;
            }
        }
        return idx;
    }

    /**
     * Returns the minimum value in the array.
     * @param a
     * @return
     */
    public static double min(final double[] a) {
        double b = Double.POSITIVE_INFINITY;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] < b) {
                b = a[i];
            }
        }
        return b;
    }

    /**
     * Returns the minimum value in the array.
     * @param a
     * @return
     */
    public static double min(final double[][] a) {
        double b = Double.POSITIVE_INFINITY;
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a[i].length; ++j) {
                if (a[i][j] < b) {
                    b = a[i][j];
                }
            }
        }
        return b;
    }

    /**
     * Returns the minimum value in the array.
     * @param a
     * @return
     */
    public static int min(final int[] a) {
        int b = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] < b) {
                b = a[i];
            }
        }
        return b;
    }

    /**
     * Returns the index of the minimum value in the array.
     * @param a
     * @return
     */
    public static int minIdx(final double[] a) {
        double b = Double.POSITIVE_INFINITY;
        int idx = -1;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] < b) {
                b = a[i];
                idx = i;
            }
        }
        return idx;
    }

    /**
     * Returns the index of the minimum value in the array.
     * @param a
     * @return
     */
    public static int minIdx(final int[] a) {
        int b = Integer.MAX_VALUE;
        int idx = -1;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] < b) {
                b = a[i];
                idx = i;
            }
        }
        return idx;
    }

    /**
     * Returns pV mod pMod. pMod must be greater than 0.
     * @param pV
     * @param pMod
     * @return
     */
    public static double mod(double pV, int pMod) {
        int intPart = floor(pV);
        double decPart = pV - intPart;
        double v = mod(intPart, pMod);
        v += decPart;
        return v;
    }

    /**
     * Returns pV mod pMod. pMod must be greater than 0.
     * @param pV
     * @param pMod
     * @return
     */
    public static float mod(float pV, int pMod) {
        int intPart = floor(pV);
        float decPart = pV - intPart;
        float v = mod(intPart, pMod);
        v += decPart;
        return v;
    }

    /**
     * Returns pV mod pMod. pMod must be greater than 0.
     * @param pV
     * @param pMod
     * @return
     */
    public static int mod(int pV, int pMod) {
        int v = pV % pMod;
        if (v < 0) {
            v += pMod;
        }
        return v;
    }

    /**
     * Returns pA^pB, pA and pB being integers.
     * @param pA
     * @param pB
     * @return
     */
    public static long pow(int pA, int pB) {
        if (pB == 0) {
            return 1;
        }
        else if (pB == 1) {
            return pA;
        }
        else {
            return pA * pow(pA, pB - 1);
        }
    }

    /**
     * Returns 2 to the specified exponent.
     * @param e
     * @return
     */
    public static int pow2(final int e) {
        if (e <= 0) {
            return 1;
        }
        else {
            return 2 * pow2(e - 1);
        }
    }
}