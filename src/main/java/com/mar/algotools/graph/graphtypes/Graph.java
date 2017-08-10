package com.mar.algotools.graph.graphtypes;

import java.util.ArrayList;

public interface Graph {
	
	public boolean[][] getAdjacencyMatrix();
	
	public ArrayList<Integer> getConnectedNode( int node );
	
	public int getNbrNode();
	
}