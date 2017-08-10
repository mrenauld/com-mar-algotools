package com.mar.algotools.graph.graphtypes;

import java.util.ArrayList;

public class DirectedWeightedGraph implements Graph {
	
	private int nNode;
	private double[][] edgeWeights;
	private double[] nodeWeights;
	
	public DirectedWeightedGraph( int nNode ) {
		this.nNode = nNode;
		edgeWeights = new double[nNode][nNode];
		for( int i = 0; i < nNode; ++i )
			for( int j = 0; j < nNode; ++j )
				edgeWeights[i][j] = Double.POSITIVE_INFINITY;
		nodeWeights = new double[nNode];
	}
	
	@Override
	public boolean[][] getAdjacencyMatrix() {
		boolean[][] adjacencyMatrix = new boolean[nNode][nNode];
		for( int i = 0; i < nNode; ++i )
			for( int j = 0; j < nNode; ++j )
				adjacencyMatrix[i][j] = (edgeWeights[i][j]==Double.POSITIVE_INFINITY) ? false : true;
		return adjacencyMatrix;
	}

	@Override
	public ArrayList<Integer> getConnectedNode(int node) {
		ArrayList<Integer> connected = new ArrayList<Integer>(4);
		for( int i = 0; i < nNode; ++i )
			if( edgeWeights[node][i] < Double.POSITIVE_INFINITY )
				connected.add(i);
		return connected;
	}

	@Override
	public int getNbrNode() {
		return nNode;
	}
	
	public double getEdgeWeight( int node1, int node2 ) {
		return edgeWeights[node1][node2];
	}
	
	public double[][] getEdgeWeights() {
		return edgeWeights;
	}
	
	public double getNodeWeight( int node ) {
		return nodeWeights[node];
	}
	
	public double[] getNodeWeights() {
		return nodeWeights;
	}
	
	public void setEdgeWeight( int node1, int node2, double weight ) {
		edgeWeights[node1][node2] = weight;
	}
	
	public void setNodeWeight( int node, double weight ) {
		nodeWeights[node] = weight;
	}

}
