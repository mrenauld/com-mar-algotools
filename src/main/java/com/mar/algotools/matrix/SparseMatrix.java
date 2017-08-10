package com.mar.algotools.matrix;

public class SparseMatrix {

    private int[][] columnData;
    private float[][] valueData;

    public SparseMatrix(int pNbRow, int pNbCol) {
        columnData = new int[pNbRow][];
        valueData = new float[pNbRow][];

        for (int i = 0; i < pNbRow; ++i) {
            columnData[i] = new int[0];
            valueData[i] = new float[0];
        }
    }

    public float get(int pX, int pY) {
        float v = 0.0f;
        for (int i = 0; i < columnData[pX].length; ++i) {
            if (columnData[pX][i] == pY) {
                v = valueData[pX][i];
                break;
            }
        }
        return v;
    }

    public int[][] getColumnData() {
        return columnData;
    }

    public float[][] getValueData() {
        return valueData;
    }

    public void set(int pX, int pY, float pV) {
        int[] tempIdx = new int[columnData[pX].length + 1];
        float[] tempValue = new float[columnData[pX].length + 1];
        boolean found = false;
        boolean newValueSet = false;
        for (int i = 0; i < columnData[pX].length; ++i) {
            if (columnData[pX][i] == pY) {
                valueData[pX][i] = pV;
                found = true;
                break;
            }
            else if (columnData[pX][i] > pY) {
                tempIdx[i] = pY;
                tempValue[i] = pV;
                for (int j = i; j < columnData[pX].length; ++j) {
                    tempIdx[j + 1] = columnData[pX][j];
                    tempValue[j + 1] = valueData[pX][j];
                }
                newValueSet = true;
                break;
            }
            else {
                tempIdx[i] = columnData[pX][i];
                tempValue[i] = valueData[pX][i];
            }
        }

        if (!found) {

            if (!newValueSet) {
                tempIdx[tempIdx.length - 1] = pY;
                tempValue[tempValue.length - 1] = pV;
            }

            columnData[pX] = tempIdx;
            valueData[pX] = tempValue;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columnData.length; ++i) {
            if (columnData[i].length > 0) {
                for (int j = 0; j < columnData[i].length; ++j) {
                    sb.append("[" + columnData[i][j] + " " + valueData[i][j] + "]");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}