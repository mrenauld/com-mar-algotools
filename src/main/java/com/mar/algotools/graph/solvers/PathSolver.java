package com.mar.algotools.graph.solvers;

import java.util.ArrayList;

import com.mar.algotools.graph.graphtypes.Graph;

public class PathSolver {
	
	/**
	 * Returns the shortest path between node1 and node2 of graph g.<br />
	 * <br />
	 * Returns the path from node1 to node2 (included) if it exists. Returns an empty path if node2 is not
	 * reachable from node1.
	 * 
	 * @param g the graph.
	 * @param node1 the start node.
	 * @param node2 the end node.
	 * @return the shortest path.
	 */
	public static ArrayList<Integer> getShortestPath_Dijkstra( Graph graph, int node1, int node2 ) {
		int n = graph.getNbrNode();
		
		// Build the node weights matrix.
		boolean[][] adjMatrix = graph.getAdjacencyMatrix();
		double[][] nodeWeights = new double[n][n];
		for( int i = 0; i < n; ++i )
			for( int j = 0; j < n; ++j )
				nodeWeights[i][j] = adjMatrix[i][j] ? 1.0 : Double.POSITIVE_INFINITY;
		
		return getShortestPath_Dijkstra(nodeWeights, node1, node2);
	}
	
	/**
	 * Returns the shortest path between node1 and node2, given the node weights matrix.<br />
	 * <br />
	 * Returns the path from node1 to node2 (included) if it exists. Returns an empty path if node2 is not
	 * reachable from node1.
	 * 
	 * @param adjMatrix the "adjacency matrix" (see above).
	 * @param node1 the start node.
	 * @param node2 the end node.
	 * @return the shortest path.
	 */
	private static ArrayList<Integer> getShortestPath_Dijkstra( final double[][] nodeWeights, int node1, int node2 ) {
		// Number of nodes.
		int n = nodeWeights.length;
		
		// Distances.
		double[] dist = new double[n];
		for( int i = 0; i < n; ++i )
			dist[i] = Double.POSITIVE_INFINITY;
		
		// Previous nodes.
		int[] previous = new int[n];
		for( int i = 0; i < n; ++i )
			previous[i] = -1;
		
		// Set the values for the starting node.
		dist[node1] = 0.0;
		previous[node1] = node1;
		
		// Remaining nodes.
		ArrayList<Integer> Q = new ArrayList<Integer>(n);
		for( int i = 0; i < n; ++i )
			Q.add(i);
		
		// Iterations.
		while( Q.size() > 0 ) {
			// Find the node u with the minimum distance.
			double minDist = Double.POSITIVE_INFINITY;
			int minIdx = -1;
			for( int i = 0; i < Q.size(); ++i ) {
				if( dist[Q.get(i)] < minDist ) {
					minDist = dist[Q.get(i)];
					minIdx = i;
				}
			}
			if( minIdx == -1 )
				minIdx = 0;
			
			// Remove u from Q.
			int u = Q.remove(minIdx);
			
			// If u is the destination, stop.
			if( u == node2 ) {
				break;
			}
			
			// If u is not reachable, stop.
			if( dist[u] == Double.POSITIVE_INFINITY ) {
				break;
			}
			
			// For each neighbor of u, update the distance and the previous node.
			for( int i = 0; i < Q.size(); ++i ) {
				if( nodeWeights[u][Q.get(i)] < Double.POSITIVE_INFINITY ) {
					double alt = dist[u] + nodeWeights[u][Q.get(i)];
					
					if( alt < dist[Q.get(i)] ) {
						dist[Q.get(i)] = alt;
						previous[Q.get(i)] = u;
					}
				}
			}
		}
		
		// The shortest path.
		ArrayList<Integer> path = new ArrayList<Integer>(8);
		int prev = previous[node2];
		
		// If no previous node, node is not reachable, return an empty path.
		if( prev == -1 )
			return path;
		
		// Add the nodes in reverse order.
		path.add(node2);
		if( node1 == node2 )
			return path;
		
		while( prev != node1 ) {
			path.add(prev);
			prev = previous[prev];
		}
		path.add(prev);
		
		// Revert the path.
		for( int i = 0; i < path.size()/2; ++i ) {
			int node = path.get(i);
			path.set(i, path.get(path.size()-1-i));
			path.set(path.size()-1-i, node);
		}
		
		return path;
	}
	
}