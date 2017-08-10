package com.mar.algotools.noise;

import java.util.Random;

public class NoiseUtils {

    public static final int TYPE_PLANE = 0;
    public static final int TYPE_TORUS = 1;
    public static final int TYPE_SPHERE = 2;
    public static final int TYPE_SPHERE_ROTATED = 3;

    public static final int NOISE_PERLIN = 0;
    public static final int NOISE_SIMPLEX = 1;

    public static Random getRandom(int pX, int pY, int pRandOffset) {
        long seed = 702395077 * pX + 915488749 * pY + pRandOffset;
        return new Random(seed);
    }
}
