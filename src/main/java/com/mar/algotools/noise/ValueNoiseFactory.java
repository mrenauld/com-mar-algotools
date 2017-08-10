package com.mar.algotools.noise;

import java.util.Random;

/**
 * See http://code.google.com/p/fractalterraingeneration/wiki/Value_Noise
 * @author mrenauld
 */
public class ValueNoiseFactory {

    public static final int SMOOTH_LINEAR = 0;
    public static final int SMOOTH_COSINE = 1;
    public static final int SMOOTH_CUBIC = 2;

    private int X;
    private int Y;

    private int nbOctaves = 12;
    private double persistance = 0.65;

    private int smoothType = SMOOTH_COSINE;
    private int randOffset = new Random().nextInt();

    public ValueNoiseFactory(int pX, int pY) {
        X = pX;
        Y = pY;
    }

    public double[][] getValueNoise() {
        double[][] output = new double[X][Y];

        for (int x = 0; x < X; ++x) {
            for (int y = 0; y < Y; ++y) {
                double value = 0.0;

                double frequency = 1.0 / X;
                double amplitude = persistance;

                for (int i = 0; i < nbOctaves; ++i) {
                    value += smoothedNoise(x * frequency, y * frequency) * amplitude;
                    frequency *= 2;
                    amplitude *= persistance;
                }

                output[x][y] = value;
            }
        }

        return output;
    }

    public void setRandOffset(int pRandOffset) {
        randOffset = pRandOffset;
    }

    public void setSmoothType(int pSmoothType) {
        smoothType = pSmoothType;
    }

    private double cosine(double pX1, double pX2, double pA) {
        // not 100% sure how this one works
        double temp = (1.0f - Math.cos(pA * 3.1415927f)) / 2.0f;

        return (pX1 * (1.0f - temp) + pX2 * temp);
    }

    private double cubic(double pX1, double pX2, double pX3, double pX4, double pA) {
        // I honestly have no idea how this works
        double c1 = pX4 - pX3 - pX1 + pX2;
        double c2 = pX1 - pX2 - c1;
        double c3 = pX3 - pX1;
        double c4 = pX2;

        return (c1 * pA * pA * pA + c2 * pA * pA + c3 * pA + c4);
    }

    private double linear(double pX1, double pX2, double pA) {
        return (pX1 * (1 - pA) + pX2 * pA);
    }

    private double smoothedNoise(double pX, double pY) {
        int intX = (int) pX;
        int intY = (int) pY;

        double remX = pX - intX;
        double remY = pY - intY;

        double value = 0.0;

        switch (smoothType) {
            case SMOOTH_LINEAR: {
                double v1 = NoiseUtils.getRandom(intX, intY, randOffset).nextDouble();
                double v2 = NoiseUtils.getRandom(intX + 1, intY, randOffset).nextDouble();
                double v3 = NoiseUtils.getRandom(intX, intY + 1, randOffset).nextDouble();
                double v4 = NoiseUtils.getRandom(intX + 1, intY + 1, randOffset).nextDouble();

                double t1 = linear(v1, v2, remX);
                double t2 = linear(v3, v4, remX);

                value = linear(t1, t2, remY);
                break;
            }
            case SMOOTH_COSINE: {
                double v1 = NoiseUtils.getRandom(intX, intY, randOffset).nextDouble();
                double v2 = NoiseUtils.getRandom(intX + 1, intY, randOffset).nextDouble();
                double v3 = NoiseUtils.getRandom(intX, intY + 1, randOffset).nextDouble();
                double v4 = NoiseUtils.getRandom(intX + 1, intY + 1, randOffset).nextDouble();

                double t1 = cosine(v1, v2, remX);
                double t2 = cosine(v3, v4, remX);

                value = cosine(t1, t2, remY);
                break;
            }
            case SMOOTH_CUBIC: {
                double v1 = NoiseUtils.getRandom(intX - 1, intY - 1, randOffset).nextDouble();
                double v2 = NoiseUtils.getRandom(intX, intY - 1, randOffset).nextDouble();
                double v3 = NoiseUtils.getRandom(intX + 1, intY - 1, randOffset).nextDouble();
                double v4 = NoiseUtils.getRandom(intX + 2, intY - 1, randOffset).nextDouble();

                double t1 = cubic(v1, v2, v3, v4, remX);

                v1 = NoiseUtils.getRandom(intX - 1, intY, randOffset).nextDouble();
                v2 = NoiseUtils.getRandom(intX, intY, randOffset).nextDouble();
                v3 = NoiseUtils.getRandom(intX + 1, intY, randOffset).nextDouble();
                v4 = NoiseUtils.getRandom(intX + 2, intY, randOffset).nextDouble();

                double t2 = cubic(v1, v2, v3, v4, remX);

                v1 = NoiseUtils.getRandom(intX - 1, intY + 1, randOffset).nextDouble();
                v2 = NoiseUtils.getRandom(intX, intY + 1, randOffset).nextDouble();
                v3 = NoiseUtils.getRandom(intX + 1, intY + 1, randOffset).nextDouble();
                v4 = NoiseUtils.getRandom(intX + 2, intY + 1, randOffset).nextDouble();

                double t3 = cubic(v1, v2, v3, v4, remX);

                v1 = NoiseUtils.getRandom(intX - 1, intY + 2, randOffset).nextDouble();
                v2 = NoiseUtils.getRandom(intX, intY + 2, randOffset).nextDouble();
                v3 = NoiseUtils.getRandom(intX + 1, intY + 2, randOffset).nextDouble();
                v4 = NoiseUtils.getRandom(intX + 2, intY + 2, randOffset).nextDouble();

                double t4 = cubic(v1, v2, v3, v4, remX);

                value = cubic(t1, t2, t3, t4, remY);
            }
        }

        return value;
    }

}