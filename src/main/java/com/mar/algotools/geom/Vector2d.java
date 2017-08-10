package com.mar.algotools.geom;

public class Vector2d implements Vectord {
    public double x;
    public double y;

    /**
     * Empty constructor.
     */
    public Vector2d() {
        x = 0.0;
        y = 0.0;
    }

    /**
     * Constructor.
     *
     * @param pX
     * @param pY
     */
    public Vector2d(double pX, double pY) {
        x = pX;
        y = pY;
    }

    /**
     * Copy constructor.
     *
     * @param pV
     */
    public Vector2d(Vector2d pV) {
        x = pV.x;
        y = pV.y;
    }

    @Override
    public void add(Vector pVector) {
        Vector2d vector = (Vector2d) pVector;
        x += vector.x;
        y += vector.y;
    }

    @Override
    public void cross(Vector pVector) {
        System.out.println("No cross product in 2D");
    }

    @Override
    public double get(int pIndex) {
        switch (pIndex) {
            case 0:
                return x;
            case 1:
                return y;
        }
        return 0.0f;
    }

    @Override
    public double[] getArray() {
        return new double[] { x, y };
    }

    @Override
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public void normalize() {
        double length = length();
        if (length != 0.0) {
            x /= length;
            y /= length;
        }
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public void subtract(Vector pVector) {
        Vector2d vector = (Vector2d) pVector;
        x -= vector.x;
        y -= vector.y;
    }

    @Override
    public void times(double pCoef) {
        x *= pCoef;
        y *= pCoef;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(x + " " + y);
        return sb.toString();
    }
}
