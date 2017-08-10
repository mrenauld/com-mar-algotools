package com.mar.algotools.tessellation;

import java.util.Random;

/**
 * A block used for the procedural Voronoi diagram generation. Each block is defined by its XY position, and its size
 * (in pixels). For each block a random number of cell centers is generated. This generation uses a Random initialized
 * with a seed based on the XY block coordinates.
 * @author mrenauld
 */
public class VoronoiBlock {

    public int blockX;
    public int blockY;
    public int blockSize;
    public double[] cellCentersX;
    public double[] cellCentersY;
    public int nbCells;

    /** Maximum number of cells per block. The number of cells is in [1, maxNbCells] included. */
    public int maxNbCells = 3;

    public VoronoiBlock(int pBlockX, int pBlockY, int pBlockSize) {
        /* Set the values. */
        blockX = pBlockX;
        blockY = pBlockY;
        blockSize = pBlockSize;

        /* Initialize the Random. */
        long seed = 702395077 * blockX + 915488749 * blockY;
        Random generator = new Random(seed);

        /* Generate the cell centers. */
        nbCells = generator.nextInt(maxNbCells) + 1;

        cellCentersX = new double[nbCells];
        cellCentersY = new double[nbCells];
        for (int i = 0; i < nbCells; ++i) {
            cellCentersX[i] = generator.nextDouble() * blockSize;
            cellCentersY[i] = generator.nextDouble() * blockSize;
        }
    }

}