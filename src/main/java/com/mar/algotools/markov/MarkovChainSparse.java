package com.mar.algotools.markov;

import java.util.ArrayList;

import com.mar.algotools.matrix.SparseMatrix;

public class MarkovChainSparse implements IMarkovChain {

    private int nbNodes;

    private SparseMatrix chainData;

    private ArrayList<String> nodeNames;

    public MarkovChainSparse(int pNbNodes) {
        nbNodes = pNbNodes;
        chainData = new SparseMatrix(nbNodes, nbNodes);
        nodeNames = new ArrayList<String>();
        for (int i = 0; i < nodeNames.size(); ++i) {
            nodeNames.add("");
        }
    }

    @Override
    public int getNbNodes() {
        return nbNodes;
    }

    @Override
    public int getNextIndexWithProba(int pNodeIdx) {
        float[] proba = chainData.getValueData()[pNodeIdx];
        int[] column = chainData.getColumnData()[pNodeIdx];

        int nextIdx = -1;

        if (proba.length > 0) {
            float rand = (float) Math.random();
            float sum = 0.0f;
            for (int i = 0; i < proba.length; ++i) {
                if (sum > rand) {
                    nextIdx = column[i - 1];
                    break;
                }
                sum += proba[i];
            }
            if (nextIdx == -1) {
                nextIdx = column[column.length - 1];
            }
        }

        return nextIdx;
    }

    @Override
    public String getNodeName(int pNodeIdx) {
        return nodeNames.get(pNodeIdx);
    }

    @Override
    public float getTransitionProba(int pNodeIdx1, int pNodeIdx2) {
        return chainData.get(pNodeIdx1, pNodeIdx2);
    }

    @Override
    public void normalizeProba() {
        float[][] valueData = chainData.getValueData();
        for (int i = 0; i < valueData.length; ++i) {
            float sum = 0.0f;
            for (int j = 0; j < valueData[i].length; ++j) {
                sum += valueData[i][j];
            }
            for (int j = 0; j < valueData[i].length; ++j) {
                valueData[i][j] /= sum;
            }
        }
    }

    @Override
    public void setNbNodes(int pNbNodes) {
        nbNodes = pNbNodes;
        chainData = new SparseMatrix(nbNodes, nbNodes);
    }

    @Override
    public void setNodeName(int pNodeIdx, String pNodeName) {
        nodeNames.set(pNodeIdx, pNodeName);
    }

    @Override
    public void setNodeNameList(ArrayList<String> pNodeNameList) {
        nodeNames = pNodeNameList;
    }

    @Override
    public void setTransitionProba(int pNodeIdx1, int pNodeIdx2, float pProba) {
        chainData.set(pNodeIdx1, pNodeIdx2, pProba);
    }

    @Override
    public String toString() {
        int[][] columnData = chainData.getColumnData();
        float[][] valueData = chainData.getValueData();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columnData.length; ++i) {
            if (columnData[i].length > 0) {
                sb.append(nodeNames.get(i) + " -> ");
                for (int j = 0; j < columnData[i].length; ++j) {
                    sb.append("[" + nodeNames.get(columnData[i][j]) + " " + valueData[i][j] + "]");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
