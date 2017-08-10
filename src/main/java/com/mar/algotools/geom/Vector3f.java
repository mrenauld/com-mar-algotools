package com.mar.algotools.geom;

import com.mar.algotools.matrix.Matrix;

public class Vector3f implements Vectorf {

    public float x;
    public float y;
    public float z;

    public Vector3f() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    public Vector3f(float pX, float pY, float pZ) {
        x = pX;
        y = pY;
        z = pZ;
    }

    public Vector3f(Vector3f pV) {
        x = pV.x;
        y = pV.y;
        z = pV.z;
    }

    public Vector3f(Vector4f pVector4f) {
        x = pVector4f.x;
        y = pVector4f.y;
        z = pVector4f.z;
    }

    @Override
    public void add(Vector pVector) {
        Vector3f vector = (Vector3f) pVector;
        x += vector.x;
        y += vector.y;
        z += vector.z;
    }

    @Override
    public Vector3f clone() {
        return new Vector3f(x, y, z);
    }

    @Override
    public void cross(Vector pVector) {
        Vector3f vector = (Vector3f) pVector;
        float tempX = x;
        float tempY = y;
        float tempZ = z;
        x = tempY * vector.z - tempZ * vector.y;
        y = tempZ * vector.x - tempX * vector.z;
        z = tempX * vector.y - tempY * vector.x;
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
        }
        return 0.0f;
    }

    @Override
    public float[] getArray() {
        return new float[] { x, y, z };
    }

    @Override
    public float length() {
        return (float) (Math.sqrt(x * x + y * y + z * z));
    }

    @Override
    public void normalize() {
        float length = length();
        if (length != 0.0f) {
            x /= length;
            y /= length;
            z /= length;
        }
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public void subtract(Vector pVector) {
        Vector3f vector = (Vector3f) pVector;
        x -= vector.x;
        y -= vector.y;
        z -= vector.z;
    }

    @Override
    public void times(float pCoef) {
        x *= pCoef;
        y *= pCoef;
        z *= pCoef;
    }

    public void times(Matrix pMat) {
        Vector3f temp = new Vector3f(x, y, z);
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        for (int i = 0; i < 3; ++i) {
            x += pMat.get(i, 0) * temp.get(i);
            y += pMat.get(i, 1) * temp.get(i);
            z += pMat.get(i, 2) * temp.get(i);
        }
    }

    public void timesLeft(Matrix pMat) {
        Vector3f temp = new Vector3f(x, y, z);
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        for (int i = 0; i < 3; ++i) {
            x += pMat.get(0, i) * temp.get(i);
            y += pMat.get(1, i) * temp.get(i);
            z += pMat.get(2, i) * temp.get(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(x + " " + y + " " + z);
        return sb.toString();
    }

}