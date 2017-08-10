package com.mar.algotools.geom;

/**
 * VectorUtils class. Some notes:
 * <p>
 * <ul>
 * <li>Directed angle: Given any two non-parallel lines l and m, we define the
 * directed angle &lt;lm to be the measure of the angle starting from l and
 * ending at m, measured counterclockwise (between 0 and 2*PI).</li>
 * <li>Curvature angle: Given any two vectors AB and BC, we define the curvature
 * angle &lt;ABC to be the measure of the angle ABC (between 0 and PI).</li>
 * </ul>
 *
 * @author mrenauld
 *
 */
public class VectorUtils {

    public static final float THRESHOLD = 0.05f;

    /**
     * Returns the directed angle ABC between the specified vectors AB and BC,
     * equal to the directed angle between BA and BC (between 0 and 2*PI).
     *
     * @param pVecAB
     * @param pVecBC
     * @return
     */
    public static double getAngleABC(Vector2d pVecAB, Vector2d pVecBC) {
        Vector2d vecBA = new Vector2d(pVecAB);
        vecBA.times(-1.0);
        return getAngleBAC(vecBA, pVecBC);
    }

    /**
     * Returns the directed angle BAC between the specified vectors AB and AC
     * (between 0 and 2*PI).
     *
     * @param pVecAB
     * @param pVecAC
     * @return
     */
    public static double getAngleBAC(Vector2d pVecAB, Vector2d pVecAC) {
        double angle = Math.atan2(pVecAC.y, pVecAC.x) - Math.atan2(pVecAB.y, pVecAB.x);
        if (angle < 0.0) {
            angle += 2.0 * Math.PI;
        }
        return angle;
    }

    /**
     * Returns the curvature angle between the specified vectors AB and BC
     * (between 0 and PI).
     *
     * @param pVecAB
     * @param pVecBC
     * @return
     */
    public static double getCurvatureAngleABC(Vector2d pVecAB, Vector2d pVecBC) {
        double angle = getAngleABC(pVecAB, pVecBC);
        if (angle > Math.PI) {
            angle = 2.0 * Math.PI - angle;
        }
        return angle;
    }

    /**
     * Returns a normalized random vector orthogonal to vector pVector, and
     * vertical (along y-axis) if possible.
     *
     * @param pVector
     * @return
     */
    public static Vector3f getOrthogonalVerticalVector(Vector3f pVector) {
        /* Get vertical vector, if not too close from pVector. */
        Vector3f vert = new Vector3f(0.0f, 1.0f, 0.0f);

        Vector3f dir = new Vector3f(pVector);
        dir.normalize();
        if (Math.abs(dir.x) < THRESHOLD && Math.abs(dir.z) < THRESHOLD) {
            vert = new Vector3f(1.0f, 0.0f, 0.0f);
        }

        /* Double cross to get the orthogonal vertical vector. */
        Vector3f orthogonal = new Vector3f(dir);
        orthogonal.cross(vert);
        orthogonal.cross(dir);
        orthogonal.normalize();

        return orthogonal;
    }

    /**
     * Returns a normalized random vector perpendicular to vector pVec.
     *
     * @param pVec
     * @return
     */
    public static Vector3f getRandomOrthogonalVector(Vector3f pVec) {
        /* Find a random point not aligned with the vector. */
        Vector3f t1 = new Vector3f(pVec);
        Vector3f t2 = new Vector3f(pVec);
        t2.x += 1f;
        if (Math.abs(t2.x) < THRESHOLD) {
            t2.x += 1f;
        }
        if (Math.abs(t2.y) < THRESHOLD && Math.abs(t2.z) < THRESHOLD) {
            t2.y += 1f;
        }

        /* Compute a perpendicular vector. */
        t1.cross(t2);
        t1.normalize();

        /* Return a random rotation around pVec. */
        return rotate(t1, pVec, Math.random() * 2.0 * Math.PI);
    }

    /**
     * Rotates the vector pVec in the 2D plane by the angle pAngle.
     *
     * @param pVec
     * @param pAngle
     * @return
     */
    public static Vector2f rotate(Vector2f pVec, double pAngle) {
        float cosTheta = (float) Math.cos(pAngle);
        float sinTheta = (float) Math.sin(pAngle);
        Vector2f rVec = new Vector2f();
        rVec.x = pVec.x * cosTheta - pVec.y * sinTheta;
        rVec.y = pVec.x * sinTheta + pVec.y * cosTheta;

        return rVec;
    }

    /**
     * Rotates the vector pVec around axis pAxis by the angle pAngle.
     *
     * @param pVec
     * @param pAxis
     * @param pAngle
     * @return
     */
    public static Vector3f rotate(Vector3f pVec, Vector3f pAxis, double pAngle) {
        float cosTheta = (float) Math.cos(pAngle);
        float sinTheta = (float) Math.sin(pAngle);
        Vector3f t = new Vector3f(pVec);
        Vector3f u = new Vector3f(pAxis);
        Vector3f p = new Vector3f();
        p.x = (cosTheta + u.x * u.x * (1 - cosTheta)) * t.x + (u.x * u.y * (1 - cosTheta) - u.z * sinTheta) * t.y
                + (u.x * u.z * (1 - cosTheta) + u.y * sinTheta) * t.z;
        p.y = (u.y * u.x * (1 - cosTheta) + u.z * sinTheta) * t.x + (cosTheta + u.y * u.y * (1 - cosTheta)) * t.y
                + (u.y * u.z * (1 - cosTheta) - u.x * sinTheta) * t.z;
        p.z = (u.z * u.x * (1 - cosTheta) - u.y * sinTheta) * t.x + (u.z * u.y * (1 - cosTheta) + u.x * sinTheta) * t.y
                + (cosTheta + u.z * u.z * (1 - cosTheta)) * t.z;

        return p;
    }

}