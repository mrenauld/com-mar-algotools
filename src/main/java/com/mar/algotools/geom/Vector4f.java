package com.mar.algotools.geom;

import com.mar.algotools.matrix.Matrix;

public class Vector4f implements Vectorf {
    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4f() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        w = 0.0f;
    }

    public Vector4f(float pV) {
        x = pV;
        y = pV;
        z = pV;
        w = pV;
    }

    public Vector4f(float pX, float pY, float pZ, float pW) {
        x = pX;
        y = pY;
        z = pZ;
        w = pW;
    }

    public Vector4f(Vector3f pVector3f) {
        x = pVector3f.x;
        y = pVector3f.y;
        z = pVector3f.z;
        w = 0.0f;
    }

    public Vector4f(Vector4f pVector4f) {
        x = pVector4f.x;
        y = pVector4f.y;
        z = pVector4f.z;
        w = pVector4f.w;
    }

    @Override
    public void add(Vector pVector) {
        Vector4f vector = (Vector4f) pVector;
        x += vector.x;
        y += vector.y;
        z += vector.z;
        w += vector.w;
    }

    @Override
    public Vector4f clone() {
        return new Vector4f(x, y, z, w);
    }

    @Override
    public void cross(Vector pVector) {
        Vector4f vector = (Vector4f) pVector;
        // TODO
    }

    @Override
    public float get(int pIndex) {
        switch (pIndex) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
                return z;
            case 3:
                return w;
        }
        return 0.0f;
    }

    @Override
    public float[] getArray() {
        return new float[] { x, y, z, w };
    }

    @Override
    public float length() {
        return (float) (Math.sqrt(x * x + y * y + z * z + w * w));
    }

    @Override
    public void normalize() {
        float length = length();
        if (length != 0.0f) {
            x /= length;
            y /= length;
            z /= length;
            w /= length;
        }
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public void subtract(Vector pVector) {
        Vector4f vector = (Vector4f) pVector;
        x -= vector.x;
        y -= vector.y;
        z -= vector.z;
        w -= vector.w;
    }

    @Override
    public void times(float pCoef) {
        x *= pCoef;
        y *= pCoef;
        z *= pCoef;
        w *= pCoef;
    }

    public void times(Matrix pMat) {
        Vector4f temp = new Vector4f(x, y, z, w);
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        w = 0.0f;
        for (int i = 0; i < 4; ++i) {
            x += pMat.get(i, 0) * temp.get(i);
            y += pMat.get(i, 1) * temp.get(i);
            z += pMat.get(i, 2) * temp.get(i);
            w += pMat.get(i, 3) * temp.get(i);
        }
    }

    public void timesLeft(Matrix pMat) {
        Vector4f temp = new Vector4f(x, y, z, w);
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        w = 0.0f;
        for (int i = 0; i < 4; ++i) {
            x += pMat.get(0, i) * temp.get(i);
            y += pMat.get(1, i) * temp.get(i);
            z += pMat.get(2, i) * temp.get(i);
            w += pMat.get(3, i) * temp.get(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(x + " " + y + " " + z + " " + w);
        return sb.toString();
    }
}
