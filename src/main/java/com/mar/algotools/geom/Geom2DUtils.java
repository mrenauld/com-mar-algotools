package com.mar.algotools.geom;

public class Geom2DUtils {

    /**
     * Returns the distance between two points.
     *
     * @param pP1
     * @param pP2
     * @return
     */
    public static double getDistanceBetweenPoints(Point pP1, Point pP2) {
        return Math.sqrt((pP2.y - pP1.y) * (pP2.y - pP1.y) + (pP2.x - pP1.x) * (pP2.x - pP1.x));
    }

    /**
     * Returns the distance between the point pP and the line defined by the
     * point pP1 and pP2.
     *
     * @param pP1
     * @param pP2
     * @param pP
     * @return
     */
    public static double getDistanceFromPointToLine(Point pP1, Point pP2, Point pP) {
        double distNum = Math.abs((pP2.y - pP1.y) * pP.x - (pP2.x - pP1.x) * pP.y + pP2.x * pP1.y - pP2.y * pP1.x);
        double distDen = getDistanceBetweenPoints(pP1, pP2);
        return distNum / distDen;
    }

}