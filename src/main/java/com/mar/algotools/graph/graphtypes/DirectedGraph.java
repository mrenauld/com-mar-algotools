package com.mar.algotools.graph.graphtypes;

import java.util.ArrayList;

import com.mar.algotools.mathematics.utils.ArrayUtils;

public class DirectedGraph implements Graph {

    private int nNode;
    private boolean[][] adjMatrix;

    public DirectedGraph(int nNode) {
        this.nNode = nNode;
        adjMatrix = new boolean[nNode][nNode];
    }

    @Override
    public DirectedGraph clone() {
        DirectedGraph graph = new DirectedGraph(nNode);
        graph.adjMatrix = ArrayUtils.copy(adjMatrix);
        return graph;
    }

    @Override
    public final boolean[][] getAdjacencyMatrix() {
        return adjMatrix;
    }

    @Override
    public ArrayList<Integer> getConnectedNode(int node) {
        ArrayList<Integer> connected = new ArrayList<Integer>(4);
        for (int i = 0; i < nNode; ++i) {
            if (adjMatrix[node][i] == true) {
                connected.add(i);
            }
        }
        return connected;
    }

    public boolean getEdge(int node1, int node2) {
        return adjMatrix[node1][node2];
    }

    @Override
    public int getNbrNode() {
        return nNode;
    }

    public void setEdge(int node1, int node2) {
        adjMatrix[node1][node2] = true;
    }
}