package com.mar.algotools.matrix;


public class Matrix {

    private float[] array;
    private int nbRow;
    private int nbCol;

    public Matrix(final int nbRow, final int nbCol) {
        this.nbRow = nbRow;
        this.nbCol = nbCol;
        array = new float[nbRow * nbCol];
    }

    public void add(Matrix pMatrix) {
        for (int i = 0; i < nbRow; ++i) {
            for (int j = 0; j < nbCol; ++j) {
                array[i * nbCol + j] += pMatrix.array[i * nbCol + j];
            }
        }
    }

    @Override
    public Matrix clone() {
        final Matrix cloneMatrix = new Matrix(nbRow, nbCol);
        cloneMatrix.array = array.clone();
        return cloneMatrix;
    }

    public float get(int x, int y) {
        return array[x * nbCol + y];
    }

    public int getNbCol() {
        return nbCol;
    }

    public int getNbRow() {
        return nbRow;
    }

    public Matrix getSubMatrix(int nbRow, int nbCol) {
        Matrix subMat = new Matrix(nbRow, nbCol);
        for (int i = 0; i < nbRow; ++i) {
            for (int j = 0; j < nbCol; ++j) {
                subMat.set(i, j, get(i, j));
            }
        }
        return subMat;
    }

    public void set(final int x, final int y, final float v) {
        array[x * nbCol + y] = v;
    }

    public void times(final float v) {
        for (int i = 0; i < array.length; ++i) {
            array[i] *= v;
        }
    }

    public void times(final Matrix V) {
        if (nbCol != V.nbRow) {
            System.out.println("Error: incompatible sizes.");
            return;
        }

        final float[] arrayCopy = array.clone();
        array = new float[nbRow * V.nbCol];

        for (int i = 0; i < nbRow; ++i) {
            for (int j = 0; j < V.nbCol; ++j) {
                for (int k = 0; k < nbCol; ++k) {
                    array[i * V.nbCol + j] += arrayCopy[i * nbCol + k] * V.array[k * V.nbCol + j];
                }
            }
        }

        nbCol = V.nbCol;
    }

    public void timesLeft(final Matrix V) {
        if (V.nbCol != nbRow) {
            System.out.println("Error: incompatible sizes.");
            return;
        }

        final float[] arrayCopy = array.clone();
        array = new float[V.nbRow * nbCol];

        for (int i = 0; i < V.nbRow; ++i) {
            for (int j = 0; j < nbCol; ++j) {
                for (int k = 0; k < V.nbCol; ++k) {
                    array[i * nbCol + j] += V.array[i * V.nbCol + k] * arrayCopy[k * nbCol + j];
                }
            }
        }

        nbRow = V.nbRow;
    }

    public float[][] to2DArray() {
        float[][] array2D = new float[nbRow][nbCol];
        for (int i = 0; i < nbRow; ++i) {
            for (int j = 0; j < nbCol; ++j) {
                array2D[i][j] = array[i * nbCol + j];
            }
        }
        return array2D;
    }

    public float[] toArray() {
        return array.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nbRow; ++i) {
            for (int j = 0; j < nbCol; ++j) {
                sb.append(array[i * nbCol + j] + " ");
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public void transpose() {
        if (nbRow != nbCol) {
            System.out.println("Error: nbRow must be equal to nbCol to transpose.");
            return;
        }
        for (int i = 0; i < nbRow; ++i) {
            for (int j = i + 1; j < nbCol; ++j) {
                float temp = array[i * nbCol + j];
                array[i * nbCol + j] = array[j * nbCol + i];
                array[j * nbCol + i] = temp;
            }
        }
    }
}