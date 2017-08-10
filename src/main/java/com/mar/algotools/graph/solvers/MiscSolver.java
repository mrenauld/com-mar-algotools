package com.mar.algotools.graph.solvers;

import java.util.ArrayList;

import com.mar.algotools.graph.graphtypes.Edge;
import com.mar.algotools.graph.graphtypes.Graph;
import com.mar.algotools.graph.graphtypes.TreeNode;
import com.mar.algotools.sorting.Sorting;

public class MiscSolver {
	
	/**
	 * Returns a list of all the edges linking one node of the tree to a node of the graph that is not in the tree.
	 * The data of each node of the tree is the index of the same node in the graph.
	 * 
	 * @param graph the graph.
	 * @param tree a tree defined on the graph.
	 * @return the list of all the edges linking the tree to the remaining of the graph.
	 */
	public static ArrayList<Edge> getEdgesFromTree( Graph graph, TreeNode<Integer> tree ) {
		// The adjacency matrix.
		boolean[][] adjMatrix = graph.getAdjacencyMatrix();
		// The list that will contain the edges.
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		
		// Get the nodes in the tree and the nodes in the remaining nodes in the graph.
		ArrayList<TreeNode<Integer>> nodesInTree = TreeSolver.getAllNodes(tree);
		ArrayList<Integer> nodesInGraph = new ArrayList<Integer>(graph.getNbrNode());
		for( int i = 0; i < graph.getNbrNode(); ++i )
			nodesInGraph.add(i);
		for( int i = 0; i < nodesInTree.size(); ++i ) {
			int idxToRemove = Sorting.dichotomicSearch(nodesInGraph, nodesInTree.get(i).getData());
			nodesInGraph.remove(idxToRemove);
		}
		
		// For each node in the tree, check if there is an edge with the remaining nodes in the graph.
		for( int i = 0; i < nodesInTree.size(); ++i ) {
			for( int j = 0; j < nodesInGraph.size(); ++j ) {
				int node1 = nodesInTree.get(i).getData();
				int node2 = nodesInGraph.get(j);
				if( adjMatrix[node1][node2] == true )
					edgeList.add(new Edge(node1, node2));
			}
		}
		
		return edgeList;
	}
	
}