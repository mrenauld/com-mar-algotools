package com.mar.algotools.noise;

import java.util.Random;

import com.mar.algotools.mathematics.utils.MathOps;

/**
 * See http://code.google.com/p/fractalterraingeneration/wiki/Perlin_Noise<br/>
 * For simplex, see: http://webstaff.itn.liu.se/~stegu/simplexnoise/simplexnoise.pdf
 * @author mrenauld
 */
public class PerlinNoiseFactoryMk1 {

    private static class Grad {
        private double x, y, z, w;

        private Grad(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        private Grad(double x, double y, double z, double w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }
    }

    private static Grad grad3[] = { new Grad(1, 1, 0), new Grad(-1, 1, 0), new Grad(1, -1, 0), new Grad(-1, -1, 0),
            new Grad(1, 0, 1), new Grad(-1, 0, 1), new Grad(1, 0, -1), new Grad(-1, 0, -1), new Grad(0, 1, 1),
            new Grad(0, -1, 1), new Grad(0, 1, -1), new Grad(0, -1, -1) };

    private static Grad grad4[] = { new Grad(0, 1, 1, 1), new Grad(0, 1, 1, -1), new Grad(0, 1, -1, 1),
            new Grad(0, 1, -1, -1), new Grad(0, -1, 1, 1), new Grad(0, -1, 1, -1), new Grad(0, -1, -1, 1),
            new Grad(0, -1, -1, -1), new Grad(1, 0, 1, 1), new Grad(1, 0, 1, -1), new Grad(1, 0, -1, 1),
            new Grad(1, 0, -1, -1), new Grad(-1, 0, 1, 1), new Grad(-1, 0, 1, -1), new Grad(-1, 0, -1, 1),
            new Grad(-1, 0, -1, -1), new Grad(1, 1, 0, 1), new Grad(1, 1, 0, -1), new Grad(1, -1, 0, 1),
            new Grad(1, -1, 0, -1), new Grad(-1, 1, 0, 1), new Grad(-1, 1, 0, -1), new Grad(-1, -1, 0, 1),
            new Grad(-1, -1, 0, -1), new Grad(1, 1, 1, 0), new Grad(1, 1, -1, 0), new Grad(1, -1, 1, 0),
            new Grad(1, -1, -1, 0), new Grad(-1, 1, 1, 0), new Grad(-1, 1, -1, 0), new Grad(-1, -1, 1, 0),
            new Grad(-1, -1, -1, 0) };

    private static short p[] = { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103,
            30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203,
            117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134,
            139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245,
            40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135,
            130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147,
            118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119,
            248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79,
            113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162,
            241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115,
            121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66,
            215, 61, 156, 180 };
    // To remove the need for index wrapping, double the permutation table length
    private static short perm[] = new short[512];
    private static short permMod12[] = new short[512];
    static {
        for (int i = 0; i < 512; i++) {
            perm[i] = p[i & 255];
            permMod12[i] = (short) (perm[i] % 12);
        }
    }

    private static final double F2 = 0.5 * (Math.sqrt(3.0) - 1.0);
    private static final double G2 = (3.0 - Math.sqrt(3.0)) / 6.0;
    private static final double F3 = 1.0 / 3.0;
    private static final double G3 = 1.0 / 6.0;
    private static final double F4 = (Math.sqrt(5.0) - 1.0) / 4.0;
    private static final double G4 = (5.0 - Math.sqrt(5.0)) / 20.0;

    private int X;
    private int Y;

    private int nbOctaves = 16;
    private double gain = 0.65;
    private double lacunarity = 2.0;

    private double x1 = 0.0;
    private double y1 = 0.0;
    private double x2 = 5.0;
    private double y2 = 5.0;

    private int borderType = NoiseUtils.TYPE_PLANE;

    private int noiseType = NoiseUtils.NOISE_PERLIN;

    private int randOffset = new Random().nextInt();

    public PerlinNoiseFactoryMk1(int pX, int pY) {
        X = pX;
        Y = pY;
    }

    public double[][] getPerlinNoise() {
        double[][] output = new double[X][Y];

        for (int x = 0; x < X; ++x) {
            for (int y = 0; y < Y; ++y) {
                output[x][y] = getPerlinNoiseAtPos(x, y);
            }
        }

        return output;
    }

    public double[][] getPerlinNoise(int a, int b) {
        double[][] output = new double[X][Y];

        int diff = b - a;
        for (int i = 0; i < X; ++i) {
            for (int j = 0; j < Y; ++j) {

                double x = (double) i * (double) diff / X;
                double y = (double) j * (double) diff / Y;

                double value = 0.0;
                double frequency = 1.0 / X;
                double amplitude = 1.0;

                double dx = x2 - x1;
                double dy = y2 - y1;
                double nx = x1 + Math.cos(x / X * 2.0 * Math.PI) * dx / (2.0 * Math.PI);
                double ny = y1 + Math.cos(y / Y * 2.0 * Math.PI) * dy / (2.0 * Math.PI);
                double nz = x1 + Math.sin(x / X * 2.0 * Math.PI) * dx / (2.0 * Math.PI);
                double nw = y1 + Math.sin(y / Y * 2.0 * Math.PI) * dy / (2.0 * Math.PI);

                for (int k = 0; k < nbOctaves; ++k) {

                    if (noiseType == NoiseUtils.NOISE_PERLIN) {
                        value += getPerlinNoise(x, y, frequency) * amplitude;
                    }
                    else {
                        value += getSimplexNoise4D(nx, ny, nz, nw) * amplitude;

                        nx *= lacunarity;
                        ny *= lacunarity;
                        nz *= lacunarity;
                        nw *= lacunarity;

                        // double nx = x * frequency;
                        // double ny = y * frequency;
                        //
                        // value += getSimplexNoise2D(nx, ny) * amplitude;
                    }

                    amplitude *= gain;
                    frequency *= lacunarity;
                }

                output[i][j] = value;
            }
        }

        return output;
    }

    public double getPerlinNoiseAtPos(double pX, double pY) {
        double value = 0.0;
        double frequency = 1.0 / X;
        double amplitude = 1.0;

        double dx = x2 - x1;
        double dy = y2 - y1;
        double nx = x1 + Math.cos(pX / X * 2.0 * Math.PI) * dx / (2.0 * Math.PI);
        double ny = y1 + Math.cos(pY / Y * 2.0 * Math.PI) * dy / (2.0 * Math.PI);
        double nz = x1 + Math.sin(pX / X * 2.0 * Math.PI) * dx / (2.0 * Math.PI);
        double nw = y1 + Math.sin(pY / Y * 2.0 * Math.PI) * dy / (2.0 * Math.PI);

        for (int i = 0; i < nbOctaves; ++i) {

            if (noiseType == NoiseUtils.NOISE_PERLIN) {
                value += getPerlinNoise(pX, pY, frequency) * amplitude;
            }
            else {
                value += getSimplexNoise4D(nx, ny, nz, nw) * amplitude;

                nx *= lacunarity;
                ny *= lacunarity;
                nz *= lacunarity;
                nw *= lacunarity;

                // double nx = x * frequency;
                // double ny = y * frequency;
                //
                // value += getSimplexNoise2D(nx, ny) * amplitude;
            }

            amplitude *= gain;
            frequency *= lacunarity;
        }

        return value;
    }

    public void setBorderType(int pBorderType) {
        borderType = pBorderType;
    }

    public void setNoiseType(int pNoiseType) {
        noiseType = pNoiseType;
    }

    public void setParametersPerlin(int pNbOctaves, double pGain, double pLacunarity) {
        nbOctaves = pNbOctaves;
        gain = pGain;
        lacunarity = pLacunarity;
    }

    public void setParametersSimplex(int pNbOctaves, double pGain, double pLacunarity, double pX1, double pY1,
        double pX2, double pY2) {
        nbOctaves = pNbOctaves;
        gain = pGain;
        lacunarity = pLacunarity;
        x1 = pX1;
        y1 = pY1;
        x2 = pX2;
        y2 = pY2;
    }

    public void setRandOffset(int pRandOffset) {
        randOffset = pRandOffset;
    }

    private double dot(Grad g, double x, double y) {
        return g.x * x + g.y * y;
    }

    private double dot(Grad g, double x, double y, double z) {
        return g.x * x + g.y * y + g.z * z;
    }

    private double dot(Grad g, double x, double y, double z, double w) {
        return g.x * x + g.y * y + g.z * z + g.w * w;
    }

    private double fade(double pX) {
        // this equates to 6x^5 - 15x^4 + 10x^3
        return pX * pX * pX * (pX * (6.0 * pX - 15.0) + 10.0);
    }

    private double getPerlinNoise(double pX, double pY, double pFrequency) {
        int fX = myFloor(pX * pFrequency);
        int fY = myFloor(pY * pFrequency);
        int fXp1 = fX + 1;
        int fYp1 = fY + 1;

        double fracX = pX * pFrequency - fX;
        double fracY = pY * pFrequency - fY;

        if (borderType == NoiseUtils.TYPE_TORUS || borderType == NoiseUtils.TYPE_SPHERE) {
            fX = MathOps.mod(fX, (int) (X * pFrequency));
            fXp1 = MathOps.mod(fXp1, (int) (X * pFrequency));
        }
        if (borderType == NoiseUtils.TYPE_SPHERE) {
            fY = MathOps.mod(fY, (int) (Y * pFrequency));
            fYp1 = MathOps.mod(fYp1, (int) (Y * pFrequency));
        }
        if (borderType == NoiseUtils.TYPE_SPHERE_ROTATED) {
            /* Does not work. */
            int XxF = (int) (X * pFrequency);
            int YxF = (int) (Y * pFrequency);
            if (fX == XxF) {
                fX = XxF - 1;
                fY = YxF - fY - 1;

                fXp1 = XxF - 2;
                fYp1 = YxF - fYp1 - 1;
            }
            if (fXp1 == XxF) {
                fXp1 = XxF - 1;
                fYp1 = YxF - fYp1 - 1;
            }
            if (fY == YxF) {
                fY = YxF - 1;
                fX = XxF - fX - 1;

                fYp1 = YxF - 2;
                fXp1 = XxF - fXp1 - 1;
            }
            if (fYp1 == YxF) {
                fYp1 = YxF - 1;
                fXp1 = XxF - fXp1 - 1;
            }
        }

        double angle11 = NoiseUtils.getRandom(fX, fY, randOffset).nextDouble() * 2.0 * Math.PI;
        double angle12 = NoiseUtils.getRandom(fXp1, fY, randOffset).nextDouble() * 2.0 * Math.PI;
        double angle21 = NoiseUtils.getRandom(fX, fYp1, randOffset).nextDouble() * 2.0 * Math.PI;
        double angle22 = NoiseUtils.getRandom(fXp1, fYp1, randOffset).nextDouble() * 2.0 * Math.PI;

        double[] vec11 = new double[2];
        vec11[0] = Math.sin(angle11);
        vec11[1] = Math.cos(angle11);
        double[] vec12 = new double[2];
        vec12[0] = Math.sin(angle12);
        vec12[1] = Math.cos(angle12);
        double[] vec21 = new double[2];
        vec21[0] = Math.sin(angle21);
        vec21[1] = Math.cos(angle21);
        double[] vec22 = new double[2];
        vec22[0] = Math.sin(angle22);
        vec22[1] = Math.cos(angle22);

        double noise11 = vec11[0] * fracX + vec11[1] * fracY;
        double noise12 = vec12[0] * (fracX - 1.0) + vec12[1] * fracY;
        double noise21 = vec21[0] * fracX + vec21[1] * (fracY - 1.0);
        double noise22 = vec22[0] * (fracX - 1.0) + vec22[1] * (fracY - 1.0);

        fracX = fade(fracX);
        fracY = fade(fracY);

        double interpolatedx1 = lerp(noise11, noise12, fracX);
        double interpolatedx2 = lerp(noise21, noise22, fracX);

        double interpolatedxy = lerp(interpolatedx1, interpolatedx2, fracY);

        return interpolatedxy;
    }

    private double getSimplexNoise2D(double pX, double pY) {
        double n0, n1, n2; // Noise contributions from the three corners
        // Skew the input space to determine which simplex cell we're in
        double s = (pX + pY) * F2; // Hairy factor for 2D
        int i = myFloor(pX + s);
        int j = myFloor(pY + s);
        double t = (i + j) * G2;

        double X0 = i - t; // Unskew the cell origin back to (x,y) space
        double Y0 = j - t;
        double x0 = pX - X0; // The x,y distances from the cell origin
        double y0 = pY - Y0;
        // For the 2D case, the simplex shape is an equilateral triangle.
        // Determine which simplex we are in.
        int i1, j1; // Offsets for second (middle) corner of simplex in (i,j) coords
        if (x0 > y0) {
            i1 = 1;
            j1 = 0;
        } // lower triangle, XY order: (0,0)->(1,0)->(1,1)
        else {
            i1 = 0;
            j1 = 1;
        } // upper triangle, YX order: (0,0)->(0,1)->(1,1)
          // A step of (1,0) in (i,j) means a step of (1-c,-c) in (x,y), and
          // a step of (0,1) in (i,j) means a step of (-c,1-c) in (x,y), where
          // c = (3-sqrt(3))/6
        double x1 = x0 - i1 + G2; // Offsets for middle corner in (x,y) unskewed coords
        double y1 = y0 - j1 + G2;
        double x2 = x0 - 1.0 + 2.0 * G2; // Offsets for last corner in (x,y) unskewed coords
        double y2 = y0 - 1.0 + 2.0 * G2;
        // Work out the hashed gradient indices of the three simplex corners
        int ii = i & 255;
        int jj = j & 255;
        int gi0 = permMod12[ii + perm[jj]];
        int gi1 = permMod12[ii + i1 + perm[jj + j1]];
        int gi2 = permMod12[ii + 1 + perm[jj + 1]];
        // Calculate the contribution from the three corners
        double t0 = 0.5 - x0 * x0 - y0 * y0;
        if (t0 < 0) {
            n0 = 0.0;
        }
        else {
            t0 *= t0;
            n0 = t0 * t0 * dot(grad3[gi0], x0, y0); // (x,y) of grad3 used for 2D gradient
        }
        double t1 = 0.5 - x1 * x1 - y1 * y1;
        if (t1 < 0) {
            n1 = 0.0;
        }
        else {
            t1 *= t1;
            n1 = t1 * t1 * dot(grad3[gi1], x1, y1);
        }
        double t2 = 0.5 - x2 * x2 - y2 * y2;
        if (t2 < 0) {
            n2 = 0.0;
        }
        else {
            t2 *= t2;
            n2 = t2 * t2 * dot(grad3[gi2], x2, y2);
        }
        // Add contributions from each corner to get the final noise value.
        // The result is scaled to return values in the interval [-1,1].
        return 70.0 * (n0 + n1 + n2);
    }

    private double getSimplexNoise3D(double pX, double pY, double pZ) {
        double n0, n1, n2, n3; // Noise contributions from the four corners
        // Skew the input space to determine which simplex cell we're in
        double s = (pX + pY + pZ) * F3; // Very nice and simple skew factor for 3D
        int i = myFloor(pX + s);
        int j = myFloor(pY + s);
        int k = myFloor(pZ + s);
        double t = (i + j + k) * G3;
        double X0 = i - t; // Unskew the cell origin back to (x,y,z) space
        double Y0 = j - t;
        double Z0 = k - t;
        double x0 = pX - X0; // The x,y,z distances from the cell origin
        double y0 = pY - Y0;
        double z0 = pZ - Z0;
        // For the 3D case, the simplex shape is a slightly irregular tetrahedron.
        // Determine which simplex we are in.
        int i1, j1, k1; // Offsets for second corner of simplex in (i,j,k) coords
        int i2, j2, k2; // Offsets for third corner of simplex in (i,j,k) coords
        if (x0 >= y0) {
            if (y0 >= z0) {
                i1 = 1;
                j1 = 0;
                k1 = 0;
                i2 = 1;
                j2 = 1;
                k2 = 0;
            } // X Y Z order
            else if (x0 >= z0) {
                i1 = 1;
                j1 = 0;
                k1 = 0;
                i2 = 1;
                j2 = 0;
                k2 = 1;
            } // X Z Y order
            else {
                i1 = 0;
                j1 = 0;
                k1 = 1;
                i2 = 1;
                j2 = 0;
                k2 = 1;
            } // Z X Y order
        }
        else { // x0<y0
            if (y0 < z0) {
                i1 = 0;
                j1 = 0;
                k1 = 1;
                i2 = 0;
                j2 = 1;
                k2 = 1;
            } // Z Y X order
            else if (x0 < z0) {
                i1 = 0;
                j1 = 1;
                k1 = 0;
                i2 = 0;
                j2 = 1;
                k2 = 1;
            } // Y Z X order
            else {
                i1 = 0;
                j1 = 1;
                k1 = 0;
                i2 = 1;
                j2 = 1;
                k2 = 0;
            } // Y X Z order
        }
        // A step of (1,0,0) in (i,j,k) means a step of (1-c,-c,-c) in (x,y,z),
        // a step of (0,1,0) in (i,j,k) means a step of (-c,1-c,-c) in (x,y,z), and
        // a step of (0,0,1) in (i,j,k) means a step of (-c,-c,1-c) in (x,y,z), where
        // c = 1/6.
        double x1 = x0 - i1 + G3; // Offsets for second corner in (x,y,z) coords
        double y1 = y0 - j1 + G3;
        double z1 = z0 - k1 + G3;
        double x2 = x0 - i2 + 2.0 * G3; // Offsets for third corner in (x,y,z) coords
        double y2 = y0 - j2 + 2.0 * G3;
        double z2 = z0 - k2 + 2.0 * G3;
        double x3 = x0 - 1.0 + 3.0 * G3; // Offsets for last corner in (x,y,z) coords
        double y3 = y0 - 1.0 + 3.0 * G3;
        double z3 = z0 - 1.0 + 3.0 * G3;
        // Work out the hashed gradient indices of the four simplex corners
        int ii = i & 255;
        int jj = j & 255;
        int kk = k & 255;
        int gi0 = permMod12[ii + perm[jj + perm[kk]]];
        int gi1 = permMod12[ii + i1 + perm[jj + j1 + perm[kk + k1]]];
        int gi2 = permMod12[ii + i2 + perm[jj + j2 + perm[kk + k2]]];
        int gi3 = permMod12[ii + 1 + perm[jj + 1 + perm[kk + 1]]];
        // Calculate the contribution from the four corners
        double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0;
        if (t0 < 0) {
            n0 = 0.0;
        }
        else {
            t0 *= t0;
            n0 = t0 * t0 * dot(grad3[gi0], x0, y0, z0);
        }
        double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1;
        if (t1 < 0) {
            n1 = 0.0;
        }
        else {
            t1 *= t1;
            n1 = t1 * t1 * dot(grad3[gi1], x1, y1, z1);
        }
        double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2;
        if (t2 < 0) {
            n2 = 0.0;
        }
        else {
            t2 *= t2;
            n2 = t2 * t2 * dot(grad3[gi2], x2, y2, z2);
        }
        double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3;
        if (t3 < 0) {
            n3 = 0.0;
        }
        else {
            t3 *= t3;
            n3 = t3 * t3 * dot(grad3[gi3], x3, y3, z3);
        }
        // Add contributions from each corner to get the final noise value.
        // The result is scaled to stay just inside [-1,1]
        return 32.0 * (n0 + n1 + n2 + n3);
    }

    private double getSimplexNoise4D(double pX, double pY, double pZ, double pW) {

        double n0, n1, n2, n3, n4; // Noise contributions from the five corners
        // Skew the (x,y,z,w) space to determine which cell of 24 simplices we're in
        double s = (pX + pY + pZ + pW) * F4; // Factor for 4D skewing
        int i = myFloor(pX + s);
        int j = myFloor(pY + s);
        int k = myFloor(pZ + s);
        int l = myFloor(pW + s);
        double t = (i + j + k + l) * G4; // Factor for 4D unskewing
        double X0 = i - t; // Unskew the cell origin back to (x,y,z,w) space
        double Y0 = j - t;
        double Z0 = k - t;
        double W0 = l - t;
        double x0 = pX - X0; // The x,y,z,w distances from the cell origin
        double y0 = pY - Y0;
        double z0 = pZ - Z0;
        double w0 = pW - W0;
        // For the 4D case, the simplex is a 4D shape I won't even try to describe.
        // To find out which of the 24 possible simplices we're in, we need to
        // determine the magnitude ordering of x0, y0, z0 and w0.
        // Six pair-wise comparisons are performed between each possible pair
        // of the four coordinates, and the results are used to rank the numbers.
        int rankx = 0;
        int ranky = 0;
        int rankz = 0;
        int rankw = 0;
        if (x0 > y0) {
            rankx++;
        }
        else {
            ranky++;
        }
        if (x0 > z0) {
            rankx++;
        }
        else {
            rankz++;
        }
        if (x0 > w0) {
            rankx++;
        }
        else {
            rankw++;
        }
        if (y0 > z0) {
            ranky++;
        }
        else {
            rankz++;
        }
        if (y0 > w0) {
            ranky++;
        }
        else {
            rankw++;
        }
        if (z0 > w0) {
            rankz++;
        }
        else {
            rankw++;
        }
        int i1, j1, k1, l1; // The integer offsets for the second simplex corner
        int i2, j2, k2, l2; // The integer offsets for the third simplex corner
        int i3, j3, k3, l3; // The integer offsets for the fourth simplex corner
        // simplex[c] is a 4-vector with the numbers 0, 1, 2 and 3 in some order.
        // Many values of c will never occur, since e.g. x>y>z>w makes x<z, y<w and x<w
        // impossible. Only the 24 indices which have non-zero entries make any sense.
        // We use a thresholding to set the coordinates in turn from the largest magnitude.
        // Rank 3 denotes the largest coordinate.
        i1 = rankx >= 3 ? 1 : 0;
        j1 = ranky >= 3 ? 1 : 0;
        k1 = rankz >= 3 ? 1 : 0;
        l1 = rankw >= 3 ? 1 : 0;
        // Rank 2 denotes the second largest coordinate.
        i2 = rankx >= 2 ? 1 : 0;
        j2 = ranky >= 2 ? 1 : 0;
        k2 = rankz >= 2 ? 1 : 0;
        l2 = rankw >= 2 ? 1 : 0;
        // Rank 1 denotes the second smallest coordinate.
        i3 = rankx >= 1 ? 1 : 0;
        j3 = ranky >= 1 ? 1 : 0;
        k3 = rankz >= 1 ? 1 : 0;
        l3 = rankw >= 1 ? 1 : 0;
        // The fifth corner has all coordinate offsets = 1, so no need to compute that.
        double x1 = x0 - i1 + G4; // Offsets for second corner in (x,y,z,w) coords
        double y1 = y0 - j1 + G4;
        double z1 = z0 - k1 + G4;
        double w1 = w0 - l1 + G4;
        double x2 = x0 - i2 + 2.0 * G4; // Offsets for third corner in (x,y,z,w) coords
        double y2 = y0 - j2 + 2.0 * G4;
        double z2 = z0 - k2 + 2.0 * G4;
        double w2 = w0 - l2 + 2.0 * G4;
        double x3 = x0 - i3 + 3.0 * G4; // Offsets for fourth corner in (x,y,z,w) coords
        double y3 = y0 - j3 + 3.0 * G4;
        double z3 = z0 - k3 + 3.0 * G4;
        double w3 = w0 - l3 + 3.0 * G4;
        double x4 = x0 - 1.0 + 4.0 * G4; // Offsets for last corner in (x,y,z,w) coords
        double y4 = y0 - 1.0 + 4.0 * G4;
        double z4 = z0 - 1.0 + 4.0 * G4;
        double w4 = w0 - 1.0 + 4.0 * G4;
        // Work out the hashed gradient indices of the five simplex corners
        int ii = i & 255;
        int jj = j & 255;
        int kk = k & 255;
        int ll = l & 255;
        int gi0 = perm[ii + perm[jj + perm[kk + perm[ll]]]] % 32;
        int gi1 = perm[ii + i1 + perm[jj + j1 + perm[kk + k1 + perm[ll + l1]]]] % 32;
        int gi2 = perm[ii + i2 + perm[jj + j2 + perm[kk + k2 + perm[ll + l2]]]] % 32;
        int gi3 = perm[ii + i3 + perm[jj + j3 + perm[kk + k3 + perm[ll + l3]]]] % 32;
        int gi4 = perm[ii + 1 + perm[jj + 1 + perm[kk + 1 + perm[ll + 1]]]] % 32;
        // Calculate the contribution from the five corners
        double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0;
        if (t0 < 0) {
            n0 = 0.0;
        }
        else {
            t0 *= t0;
            n0 = t0 * t0 * dot(grad4[gi0], x0, y0, z0, w0);
        }
        double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1;
        if (t1 < 0) {
            n1 = 0.0;
        }
        else {
            t1 *= t1;
            n1 = t1 * t1 * dot(grad4[gi1], x1, y1, z1, w1);
        }
        double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2;
        if (t2 < 0) {
            n2 = 0.0;
        }
        else {
            t2 *= t2;
            n2 = t2 * t2 * dot(grad4[gi2], x2, y2, z2, w2);
        }
        double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3;
        if (t3 < 0) {
            n3 = 0.0;
        }
        else {
            t3 *= t3;
            n3 = t3 * t3 * dot(grad4[gi3], x3, y3, z3, w3);
        }
        double t4 = 0.6 - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4;
        if (t4 < 0) {
            n4 = 0.0;
        }
        else {
            t4 *= t4;
            n4 = t4 * t4 * dot(grad4[gi4], x4, y4, z4, w4);
        }
        // Sum up and scale the result to cover the range [-1,1]
        return 27.0 * (n0 + n1 + n2 + n3 + n4);
    }

    private double lerp(double pLeft, double pRight, double pAmount) {
        return (1.0 - pAmount) * pLeft + pAmount * pRight;
    }

    private int myFloor(double pValue) {
        return pValue >= 0.0 ? (int) pValue : (int) (pValue - 1.0);
    }
}
