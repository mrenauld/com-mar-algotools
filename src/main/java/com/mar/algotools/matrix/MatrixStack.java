package com.mar.algotools.matrix;

import java.util.Stack;

import com.mar.algotools.geom.Vector3f;

public class MatrixStack {

    private final Stack<Matrix> matrixStack;

    private Matrix currentMatrix;

    public MatrixStack() {
        currentMatrix = MatrixUtils.getIdentityMatrix(4);
        matrixStack = new Stack<Matrix>();
        matrixStack.push(currentMatrix.clone());
    }

    public void applyMatrix(Matrix pMatrix) {
        currentMatrix.timesLeft(pMatrix);
    }

    public Matrix getTop() {
        return currentMatrix;
    }

    /**
     * Code from https://github.com/wchristian/gltut/blob/master/glsdk/glm/glm/gtc /matrix_transform.inl
     * @param pRadFOV
     * @param pAspectRatio
     * @param pZNear
     * @param pZFar
     */
    public void perspective(float pDegFOV, float pAspectRatio, float pZNear, float pZFar) {
        float range = (float) (Math.tan(pDegFOV / 360.0f * Math.PI) * pZNear);
        float left = -range * pAspectRatio;
        float right = range * pAspectRatio;
        float bottom = -range;
        float top = range;

        Matrix perspective = new Matrix(4, 4);
        perspective.set(0, 0, (2.0f * pZNear) / (right - left));
        perspective.set(1, 1, (2.0f * pZNear) / (top - bottom));
        perspective.set(2, 2, -(pZFar + pZNear) / (pZFar - pZNear));
        perspective.set(2, 3, -1.0f);
        perspective.set(3, 2, -(2.0f * pZFar * pZNear) / (pZFar - pZNear));

        currentMatrix.timesLeft(perspective);
    }

    public Matrix pop() {
        currentMatrix = matrixStack.pop();
        return currentMatrix;
    }

    public void push() {
        matrixStack.push(currentMatrix.clone());
    }

    public void rotate(Vector3f pAxis, float pAngle) {
        float c = (float) Math.cos(pAngle);
        float s = (float) Math.sin(pAngle);
        Vector3f axis = new Vector3f(pAxis);
        axis.normalize();
        Vector3f temp = new Vector3f(axis);
        temp.times(1.0f - c);
        Matrix rotateMatrix = new Matrix(4, 4);
        rotateMatrix.set(0, 0, c + temp.x * axis.x);
        rotateMatrix.set(0, 1, 0 + temp.x * axis.y + s * axis.z);
        rotateMatrix.set(0, 2, 0 + temp.x * axis.z - s * axis.y);
        rotateMatrix.set(1, 0, 0 + temp.y * axis.x - s * axis.z);
        rotateMatrix.set(1, 1, c + temp.y * axis.y);
        rotateMatrix.set(1, 2, 0 + temp.y * axis.z + s * axis.x);
        rotateMatrix.set(2, 0, 0 + temp.z * axis.x + s * axis.y);
        rotateMatrix.set(2, 1, 0 + temp.z * axis.y - s * axis.x);
        rotateMatrix.set(2, 2, c + temp.z * axis.z);
        rotateMatrix.set(3, 3, 1.0f);

        Matrix result = new Matrix(4, 4);
        // Row or columns?
        for (int i = 0; i < 4; ++i) {
            result.set(i, 0,
                currentMatrix.get(i, 0) * rotateMatrix.get(0, 0) + currentMatrix.get(i, 1) * rotateMatrix.get(0, 1)
                    + currentMatrix.get(i, 2) * rotateMatrix.get(0, 2));
            result.set(i, 1,
                currentMatrix.get(i, 0) * rotateMatrix.get(1, 0) + currentMatrix.get(i, 1) * rotateMatrix.get(1, 1)
                    + currentMatrix.get(i, 2) * rotateMatrix.get(1, 2));
            result.set(i, 2,
                currentMatrix.get(i, 0) * rotateMatrix.get(2, 0) + currentMatrix.get(i, 1) * rotateMatrix.get(2, 1)
                    + currentMatrix.get(i, 2) * rotateMatrix.get(2, 2));
            result.set(i, 3, currentMatrix.get(i, 3));
        }

        // currentMatrix = result;
        currentMatrix.timesLeft(rotateMatrix);
    }

    public void rotateX(final float angle) {
        final float cos = (float) Math.cos(angle);
        final float sin = (float) Math.sin(angle);

        final Matrix rotateMatrix = MatrixUtils.getIdentityMatrix(4);
        rotateMatrix.set(1, 1, cos);
        rotateMatrix.set(1, 2, sin);
        rotateMatrix.set(2, 1, -sin);
        rotateMatrix.set(2, 2, cos);

        currentMatrix.timesLeft(rotateMatrix);
    }

    public void rotateY(final float angle) {
        final float cos = (float) Math.cos(angle);
        final float sin = (float) Math.sin(angle);

        final Matrix rotateMatrix = MatrixUtils.getIdentityMatrix(4);
        rotateMatrix.set(0, 0, cos);
        rotateMatrix.set(0, 2, -sin);
        rotateMatrix.set(2, 0, sin);
        rotateMatrix.set(2, 2, cos);

        currentMatrix.timesLeft(rotateMatrix);
    }

    public void rotateZ(final float angle) {
        final float cos = (float) Math.cos(angle);
        final float sin = (float) Math.sin(angle);

        final Matrix rotateMatrix = MatrixUtils.getIdentityMatrix(4);
        rotateMatrix.set(0, 0, cos);
        rotateMatrix.set(0, 1, sin);
        rotateMatrix.set(1, 0, -sin);
        rotateMatrix.set(1, 1, cos);

        currentMatrix.timesLeft(rotateMatrix);
    }

    public void scale(final float s) {
        scale(s, s, s);
    }

    public void scale(final float x, final float y, final float z) {
        final Matrix scaleMatrix = MatrixUtils.getIdentityMatrix(4);
        scaleMatrix.set(0, 0, x);
        scaleMatrix.set(1, 1, y);
        scaleMatrix.set(2, 2, z);

        currentMatrix.timesLeft(scaleMatrix);
    }

    public void setMatrix(Matrix pMatrix) {
        currentMatrix = pMatrix.clone();
        matrixStack.push(currentMatrix.clone());
    }

    public void translate(final float x, final float y, final float z) {
        final Matrix translateMatrix = MatrixUtils.getIdentityMatrix(4);
        translateMatrix.set(3, 0, x);
        translateMatrix.set(3, 1, y);
        translateMatrix.set(3, 2, z);

        currentMatrix.timesLeft(translateMatrix);
    }

    public void translate(Vector3f pVector) {
        translate(pVector.x, pVector.y, pVector.z);
    }
}
