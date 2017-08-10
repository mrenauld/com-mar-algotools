package com.mar.algotools.markov;

import java.util.ArrayList;

public interface IMarkovChain {

    public int getNbNodes();

    public int getNextIndexWithProba(int pNodeIdx);

    public String getNodeName(int pNodeIdx);

    public float getTransitionProba(int pNodeIdx1, int pNodeIdx2);

    public void normalizeProba();

    public void setNbNodes(int pNbNodes);

    public void setNodeName(int pNodeIdx, String pNodeName);

    public void setNodeNameList(ArrayList<String> pNodeNameList);

    public void setTransitionProba(int pNodeIdx1, int pNodeIdx2, float pProba);

}
