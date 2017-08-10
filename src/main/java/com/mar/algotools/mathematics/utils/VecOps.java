package com.mar.algotools.mathematics.utils;

public class VecOps {

    /**
     * Returns an array containing the absolute values of the elements of a.
     * @param a
     * @return
     */
    public static double[] abs(double[] a) {
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; ++i) {
            b[i] = Math.abs(a[i]);
        }
        return b;
    }

    /**
     * Returns the covariance matrix estimated from the vectors v ( nvector x dim ).
     * @param v
     * @return
     */
    public static double[][] covmatrix(double[][] v) {
        int dim = v[0].length;
        double[] mean = new double[dim];
        for (int d = 0; d < dim; ++d) {
            for (int n = 0; n < v.length; ++n) {
                mean[d] += v[n][d];
            }
        }
        for (int d = 0; d < dim; ++d) {
            mean[d] /= v.length;
        }

        double[][] cov = new double[dim][dim];
        for (int n = 0; n < v.length; ++n) {
            for (int i = 0; i < dim; ++i) {
                cov[i][i] += Math.pow(v[n][i] - mean[i], 2);
                for (int j = i + 1; j < dim; ++j) {
                    double delta = (v[n][i] - mean[i]) * (v[n][j] - mean[j]);
                    cov[i][j] += delta;
                    cov[j][i] += delta;
                }
            }
        }
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                cov[i][j] /= (v.length - 1);
            }
        }

        return cov;
    }

    /**
     * Returns the scalar product (dot product) between the two specified vectors.
     * @param a
     * @param b
     * @return
     */
    public static double dot(double[] a, double[] b) {
        if (a.length != b.length) {
            System.out.println("VecOps.dot - error: a and b should have the same length.");
            return 0;
        }
        double c = 0.0;
        for (int i = 0; i < a.length; ++i) {
            c += a[i] * b[i];
        }
        return c;
    }

    /**
     * Returns the 2D vector that is at specified angle from the specified vector. Angle in radians.
     * @param a
     * @param angle
     * @return
     */
    public static double[] getVectAtAngle2D(double[] a, double angle) {
        double[] b = new double[2];
        b[0] = Math.cos(angle) * a[0] + Math.sin(angle) * a[1];
        b[1] = -Math.sin(angle) * a[0] + Math.cos(angle) * a[1];
        return b;
    }

    /**
     * Returns the mean value of the elements in the specified array.
     * @param a
     * @return
     */
    public static double mean(double[] a) {
        double mean = 0.0;
        for (int i = 0; i < a.length; ++i) {
            mean += a[i];
        }
        mean /= a.length;
        return mean;
    }

    /**
     * Returns a new array equal to the specified array plus the specified scalar value.
     * @param a
     * @param b
     * @return
     */
    public static double[] plus(double[] a, double b) {
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; ++i) {
            c[i] = a[i] + b;
        }
        return c;
    }

    /**
     * Returns the standard deviation of the elements in the specified array.
     * @param a
     * @return
     */
    public static double std(double[] a) {
        return Math.sqrt(var(a));
    }

    /**
     * Returns the sum of the elements in the specified array.
     * @param a
     * @return
     */
    public static double sum(double[] a) {
        double sum = 0.0;
        for (int i = 0; i < a.length; ++i) {
            sum += a[i];
        }
        return sum;
    }

    /**
     * Returns a new array equal to the specified array times the specified scalar value.
     * @param a
     * @param b
     * @return
     */
    public static double[] times(double[] a, double b) {
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; ++i) {
            c[i] = a[i] * b;
        }
        return c;
    }

    /**
     * Returns the variance of the elements in the specified array.
     * @param a
     * @return
     */
    public static double var(double[] a) {
        double mean = mean(a);
        double var = 0.0;
        for (int i = 0; i < a.length; ++i) {
            var += Math.pow(a[i] - mean, 2);
        }
        var /= a.length;
        return var;
    }
}