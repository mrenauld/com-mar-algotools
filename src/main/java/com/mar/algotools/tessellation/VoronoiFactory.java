package com.mar.algotools.tessellation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.mar.algotools.mathematics.utils.MathOps;
import com.mar.algotools.sorting.Sorting;

public class VoronoiFactory {

    public static final int TYPE_DIST_EUCLIDIAN = 0;
    public static final int TYPE_DIST_MANHATTAN = 1;

    /** X size. */
    private int X;
    /** Y size. */
    private int Y;

    /** List of x coordinates for the cell centers. */
    private ArrayList<Double> cellXList;
    /** List of y coordinates for the cell centers. */
    private ArrayList<Double> cellYList;

    /** Distance type. */
    private int typeDist = TYPE_DIST_EUCLIDIAN;

    /** Block size. */
    private int blockSize = 64;

    /**
     * When evaluating the procedural version, this parameter indicates how many blocks in each direction are considered
     * as neighbors and can potentially contain a cell center to take into account.
     */
    private int proceduralBlockNeighbor = 2;

    private double clampValue = 1.0;

    private HashMap<String, VoronoiBlock> blockMap;

    /**
     * Constructor.
     */
    public VoronoiFactory() {

    }

    /**
     * Returns the distances for each point, computed based on the specified coefficients. The distance is computed as
     * follows: coef[0]*F1 + coef[1]*F2 + ... + coef[n-1]*Fn.
     * @param coef
     * @return
     */
    public double[][] getVoronoiDistances(double[] coef) {
        double[][] dist = new double[X][Y];
        for (int x = 0; x < X; ++x) {
            for (int y = 0; y < Y; ++y) {
                double[] sortedDistances = null;
                switch (typeDist) {
                    case TYPE_DIST_EUCLIDIAN:
                        sortedDistances = getSortedDistancesEuclidian(x, y);
                        break;
                    case TYPE_DIST_MANHATTAN:
                        sortedDistances = getSortedDistancesManhattan(x, y);
                        break;
                    default:
                        sortedDistances = getSortedDistancesEuclidian(x, y);
                        break;
                }

                for (int c = 0; c < coef.length; ++c) {
                    dist[x][y] += coef[c] * sortedDistances[c];
                }
            }
        }
        return dist;
    }

    /**
     * Returns the distances F_pIndex for each point.
     * @param pIndex
     * @return
     */
    public double[][] getVoronoiDistances(int pIndex) {
        double[] coef = new double[pIndex + 1];
        coef[pIndex] = 1.0;
        return getVoronoiDistances(coef);
    }

    /**
     * Returns the distances for each point, computed based on the specified coefficients. The distance is computed as
     * follows: pCoef[0]*F1 + pCoef[1]*F2 + ... + pCoef[n-1]*Fn. The computation uses the procedural version of the
     * algorithm.
     * @param pCenterX
     * @param pCenterY
     * @param pCoef
     * @return
     */
    public double[][] getVoronoiDistancesProcedural(int pCenterX, int pCenterY, double[] pCoef) {
        double[] exp = new double[pCoef.length];
        for (int i = 0; i < exp.length; ++i) {
            exp[i] = 1.0;
        }
        return getVoronoiDistancesProcedural(pCenterX, pCenterY, pCoef, exp);
    }

    /**
     * Returns the distances for each point, computed based on the specified coefficients. The distance is computed as
     * follows: pCoef[0]*F1^pExp[0] + pCoef[1]*F2^pExp[1] + ... + pCoef[n-1]*Fn^pExp[n-1]. The computation uses the
     * procedural version of the algorithm.
     * @param pCenterX
     * @param pCenterY
     * @param pCoef
     * @param pExp
     * @return
     */
    public double[][] getVoronoiDistancesProcedural(int pCenterX, int pCenterY, double[] pCoef, double[] pExp) {
        /* A map to store all blocks generated for this result. */
        blockMap = new HashMap<String, VoronoiBlock>();
        /* Determines the lower and upper bounds on the XY indexes of the blocks to generate. */
        int lowerXBlockCoord = (pCenterX - X / 2) / blockSize - 1;
        int higherXBlockCoord = (pCenterX + X / 2) / blockSize + 1;
        int lowerYBlockCoord = (pCenterY - Y / 2) / blockSize - 1;
        int higherYBlockCoord = (pCenterY + Y / 2) / blockSize + 1;

        /*
         * Generate the blocks. Each block is generated based on a Random initialized by a function of the XY values so
         * that blocks with the same XY values always have the same cells.
         */
        for (int x = lowerXBlockCoord; x <= higherXBlockCoord; ++x) {
            for (int y = lowerYBlockCoord; y <= higherYBlockCoord; ++y) {
                VoronoiBlock voronoiBlock = new VoronoiBlock(x, y, blockSize);
                String blockKey = getKey(x, y);
                blockMap.put(blockKey, voronoiBlock);
            }
        }

        /* Fill the result array. */
        double[][] dist = new double[X][Y];
        for (int x = lowerXBlockCoord + 1; x <= higherXBlockCoord - 1; ++x) {
            for (int y = lowerYBlockCoord + 1; y <= higherYBlockCoord - 1; ++y) {

                /* Fill the list of cell center coordinates based on the current and surounding blocks. */
                cellXList = new ArrayList<Double>();
                cellYList = new ArrayList<Double>();
                for (int i = x - proceduralBlockNeighbor; i <= x + proceduralBlockNeighbor; ++i) {
                    for (int j = y - proceduralBlockNeighbor; j <= y + proceduralBlockNeighbor; ++j) {
                        VoronoiBlock voronoiBlock = blockMap.get(getKey(i, j));
                        if (voronoiBlock != null) {
                            for (int k = 0; k < voronoiBlock.nbCells; ++k) {
                                cellXList.add(voronoiBlock.cellCentersX[k] + blockSize * i);
                                cellYList.add(voronoiBlock.cellCentersY[k] + blockSize * j);
                            }
                        }
                    }
                }

                /* Evaluate the distances for each pixel in the block. */
                for (int i = 0; i < blockSize; ++i) {
                    for (int j = 0; j < blockSize; ++j) {
                        int globalX = i + blockSize * x;
                        int globalY = j + blockSize * y;

                        if (globalX >= pCenterX - X / 2 && globalX < pCenterX + X / 2 && globalY >= pCenterY - Y / 2
                            && globalY < pCenterY + Y / 2) {
                            int mapX = globalX - pCenterX + X / 2;
                            int mapY = globalY - pCenterY + Y / 2;

                            double[] sortedDistances = null;
                            switch (typeDist) {
                                case TYPE_DIST_EUCLIDIAN:
                                    sortedDistances = getSortedDistancesEuclidian(globalX, globalY);
                                    break;
                                case TYPE_DIST_MANHATTAN:
                                    sortedDistances = getSortedDistancesManhattan(globalX, globalY);
                                    break;
                                default:
                                    sortedDistances = getSortedDistancesEuclidian(globalX, globalY);
                                    break;
                            }

                            for (int c = 0; c < pCoef.length; ++c) {
                                double toAdd = pCoef[c] * Math.pow(sortedDistances[c], pExp[c]);
                                toAdd = MathOps.clamp(toAdd, -clampValue, clampValue);
                                dist[mapX][mapY] += toAdd;
                            }
                        }
                    }
                }
            }
        }

        return dist;
    }

    /**
     * Initializes the VoronoiFactory.
     * @param pX
     *            X size of the frame.
     * @param pY
     *            Y size of the frame.
     * @param pNbCells
     *            The number of cells (some of them can be outside the frame.
     * @param pExtraBorderSpaceRatio
     *            The ratio of extra space where the cells can be, based on the frame size.
     * @param pMinDistRatio
     *            The minimum distance attempted to be kept between cells centers, based on the frame size.
     * @param pGenerator
     *            The random number generator.
     */
    public void init(int pX, int pY, int pNbCells, double pExtraBorderSpaceRatio, double pMinDistRatio,
        Random pGenerator) {
        X = pX;
        Y = pY;
        cellXList = new ArrayList<Double>(pNbCells);
        cellYList = new ArrayList<Double>(pNbCells);

        int maxIteration = 50;
        double borderRatio = pExtraBorderSpaceRatio;
        double minSquareDist = Math.pow((pX + pY) / 2 * pMinDistRatio, 2.0);

        for (int i = 0; i < pNbCells; ++i) {
            int it = 0;
            double x = 0.0;
            double y = 0.0;
            while (it < maxIteration) {
                x = pGenerator.nextDouble() * (pX * (1 + 2 * borderRatio)) - pX * borderRatio;
                y = pGenerator.nextDouble() * (pY * (1 + 2 * borderRatio)) - pY * borderRatio;

                double squareDist = Double.MAX_VALUE;
                for (int j = 0; j < cellXList.size(); ++j) {
                    double dd = Math.pow(x - cellXList.get(j), 2.0) + Math.pow(y - cellYList.get(j), 2.0);
                    if (dd < squareDist) {
                        squareDist = dd;
                    }
                }

                if (squareDist > minSquareDist) {
                    break;
                }

                it++;
            }

            cellXList.add(x);
            cellYList.add(y);
        }
    }

    public void initForProcedural(int pX, int pY, int pBlockSize) {
        X = pX;
        Y = pY;
        blockSize = pBlockSize;
    }

    /**
     * Sets the distance type.
     * @param pTypeDist
     */
    public void setTypeDist(int pTypeDist) {
        typeDist = pTypeDist;
    }

    private String getKey(int pX, int pY) {
        return Integer.toString(pX) + "#" + Integer.toString(pY);
    }

    /**
     * Returns the euclidian distances between the specified point and all cells centers, sorted from lowest to highest.
     * @param pX
     * @param pY
     * @return
     */
    private double[] getSortedDistancesEuclidian(double pX, double pY) {
        double[] distList = new double[cellXList.size()];
        for (int i = 0; i < cellXList.size(); ++i) {
            double squareDist = Math.pow(pX - cellXList.get(i), 2.0) + Math.pow(pY - cellYList.get(i), 2.0);
            distList[i] = Math.sqrt(squareDist);
        }
        return Sorting.quicksort(distList);
    }

    /**
     * Returns the Manhattan distances between the specified point and all cells centers, sorted from lowest to highest.
     * @param pX
     * @param pY
     * @return
     */
    private double[] getSortedDistancesManhattan(double pX, double pY) {
        double[] distList = new double[cellXList.size()];
        for (int i = 0; i < cellXList.size(); ++i) {
            distList[i] = Math.abs(pX - cellXList.get(i)) + Math.abs(pY - cellYList.get(i));
        }
        return Sorting.quicksort(distList);
    }
}