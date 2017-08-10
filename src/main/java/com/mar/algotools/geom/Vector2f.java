package com.mar.algotools.geom;

public class Vector2f implements Vectorf {

    public float x;
    public float y;

    /**
     * Empty constructor.
     */
    public Vector2f() {
        x = 0.0f;
        y = 0.0f;
    }

    /**
     * Constructor.
     *
     * @param pX
     * @param pY
     */
    public Vector2f(float pX, float pY) {
        x = pX;
        y = pY;
    }

    /**
     * Copy constructor.
     *
     * @param pV
     */
    public Vector2f(Vector2f pV) {
        x = pV.x;
        y = pV.y;
    }

    @Override
    public void add(Vector pVector) {
        Vector2f vector = (Vector2f) pVector;
        x += vector.x;
        y += vector.y;
    }

    @Override
    public void cross(Vector pVector) {
        System.out.println("No cross product in 2D");
    }

    @Override
    public float get(int pIndex) {
        switch (pIndex) {
            case 0:
                return x;
            case 1:
                return y;
        }
        return 0.0f;
    }

    @Override
    public float[] getArray() {
        return new float[] { x, y };
    }

    @Override
    public float length() {
        return (float) (Math.sqrt(x * x + y * y));
    }

    @Override
    public void normalize() {
        float length = length();
        if (length != 0.0f) {
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
        Vector2f vector = (Vector2f) pVector;
        x -= vector.x;
        y -= vector.y;
    }

    @Override
    public void times(float pCoef) {
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
