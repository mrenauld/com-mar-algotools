package com.mar.algotools.graph.solvers;

import java.util.ArrayList;

import com.mar.algotools.graph.graphtypes.DirectedWeightedGraph;
import com.mar.algotools.graph.graphtypes.Edge;
import com.mar.algotools.graph.graphtypes.TreeNode;
import com.mar.algotools.mathematics.utils.MathOps;

public class TreeSolver {

    /**
     * Finds and returns a node with the specified data. Starts with a node and explore its children. Returns null if no
     * such node is found.
     * @param root
     * @param data
     * @return
     */
    public static <T> TreeNode<T> findNodeWithData(TreeNode<T> root, T data) {
        if (root.getData().equals(data)) {
            return root;
        }
        if (root.getNbrChildren() == 0) {
            return null;
        }
        for (int i = 0; i < root.getNbrChildren(); ++i) {
            TreeNode<T> node = findNodeWithData(root.getChildren().get(i), data);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    /**
     * Returns a tree with maximum sum of the node weights that can be built with sum of the edges lower or equal to
     * nMax. The root of the tree is fixed.
     * @param graph
     * @param nMax
     * @param root
     * @return
     */
    public static TreeNode<Integer> findTreeWithMaxWeight(final DirectedWeightedGraph graph, int sumEdgesMax, int root) {

        TreeNode<Integer> tree = new TreeNode<Integer>(root);
        int nNodeInTree = 1;

        while (nNodeInTree < graph.getNbrNode()) {
            ArrayList<Edge> edgeList = MiscSolver.getEdgesFromTree(graph, tree);

            double[] edgeValue = new double[edgeList.size()];
            boolean enoughLeft = false;
            for (int i = 0; i < edgeValue.length; ++i) {
                Edge edge = edgeList.get(i);

                if (graph.getEdgeWeight(edge.node1, edge.node2) > sumEdgesMax) {
                    edgeValue[i] = Double.NEGATIVE_INFINITY;
                    continue;
                }
                enoughLeft = true;

                double nodeW = graph.getNodeWeight(edge.node2);
                double edgeW = graph.getEdgeWeight(edge.node1, edge.node2);
                if (edgeW == 0.0) {
                    edgeValue[i] = Double.POSITIVE_INFINITY;
                }
                else if (edgeW == Double.POSITIVE_INFINITY) {
                    edgeValue[i] = 0.0;
                }
                else {
                    edgeValue[i] = nodeW / edgeW;
                }
            }

            if (enoughLeft == false) {
                break;
            }

            int idxMax = MathOps.maxIdx(edgeValue);
            Edge edgeMax = edgeList.get(idxMax);

            TreeNode<Integer> parent = TreeSolver.findNodeWithData(tree, edgeMax.node1);
            parent.addNode(new TreeNode<Integer>(edgeMax.node2)); // addNodeNoUpdate?

            sumEdgesMax -= graph.getEdgeWeight(edgeMax.node1, edgeMax.node2);
            nNodeInTree++;
        }

        return tree;
    }

    /**
     * Returns the list of all edges between nodes<Integer> identified by their int data value.
     * @param tree
     * @return the list of all edges.
     */
    public static ArrayList<Edge> getAllEdges(TreeNode<Integer> tree) {
        ArrayList<Edge> edgeList = new ArrayList<Edge>();
        populateListAllEdges(tree, edgeList);
        return edgeList;
    }

    /**
     * Returns the list of all nodes in a tree.
     * @param tree
     * @return the list of all nodes in the tree.
     */
    public static <T> ArrayList<TreeNode<T>> getAllNodes(TreeNode<T> tree) {
        ArrayList<TreeNode<T>> nodeList = new ArrayList<TreeNode<T>>();
        populateListAllNodes(tree, nodeList);
        return nodeList;
    }

    /**
     * Populates the list of all edges of a tree by recursively calling itself on the children.
     * @param node
     * @param edgeList
     */
    public static void populateListAllEdges(TreeNode<Integer> node, ArrayList<Edge> edgeList) {
        for (int i = 0; i < node.getNbrChildren(); ++i) {
            Edge edge = new Edge(node.getData(), node.getChildren().get(i).getData());
            edgeList.add(edge);
            populateListAllEdges(node.getChildren().get(i), edgeList);
        }
    }

    /**
     * Populates the list of all nodes of a tree by recursively calling itself on the children.
     * @param node
     * @param nodeList
     */
    private static <T> void populateListAllNodes(TreeNode<T> node, ArrayList<TreeNode<T>> nodeList) {
        nodeList.add(node);
        for (int i = 0; i < node.getNbrChildren(); ++i) {
            populateListAllNodes(node.getChildren().get(i), nodeList);
        }
    }

}