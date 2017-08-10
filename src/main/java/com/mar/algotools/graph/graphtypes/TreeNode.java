package com.mar.algotools.graph.graphtypes;

import java.util.ArrayList;

/**
 * The Tree class is used to manipulate trees - simple graphs with no cycles. Each node of the tree contains a data of
 * type T.
 * @author mrenauld
 * @param <T>
 */
public class TreeNode<T> {

    /** The data of the node. **/
    private T data;
    /** The parent node. **/
    private TreeNode<T> parent;
    /** The list of children nodes. **/
    private ArrayList<TreeNode<T>> children;

    /**
     * Constructs a new tree node with specified data.
     * @param nodeData
     */
    public TreeNode(T nodeData) {
        data = nodeData;
        children = new ArrayList<TreeNode<T>>();
    }

    /**
     * Adds the specified node as children to this node. Also update the parent of the added node.
     * @param node
     */
    public void addNode(TreeNode<T> node) {
        children.add(node);
        node.parent = this;
    }

    /**
     * Adds the specified node as children to this node. Does not update the parent of the added node.
     * @param node
     */
    public void addNodeNoUpdate(TreeNode<T> node) {
        children.add(node);
    }

    /**
     * Returns the list of children nodes.
     * @return
     */
    public ArrayList<TreeNode<T>> getChildren() {
        return children;
    }

    /**
     * Returns the data of the node.
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * Returns the number of children nodes.
     * @return
     */
    public int getNbrChildren() {
        return children.size();
    }

    /**
     * Returns the parent node.
     * @return
     */
    public TreeNode<T> getParent() {
        return parent;
    }

    public boolean isRoot() {
        if (parent == null) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sets the data of the node.
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

}