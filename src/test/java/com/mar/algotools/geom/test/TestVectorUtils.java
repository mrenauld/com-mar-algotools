package com.mar.algotools.geom.test;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.mar.algotools.geom.Vector2d;
import com.mar.algotools.geom.VectorUtils;

public class TestVectorUtils {

    @Test
    public void testGetAngleABC() {
        Vector2d AB = new Vector2d(1.0, 1.0);
        Vector2d BC = new Vector2d(1.0, 0.0);
        double angle = VectorUtils.getAngleABC(AB, BC);

        Assertions.assertThat(angle == 3.0 * Math.PI / 4.0).isTrue();

        AB = new Vector2d(1.0, 1.0);
        BC = new Vector2d(-1.0, 1.0);
        angle = VectorUtils.getAngleABC(AB, BC);

        Assertions.assertThat(angle == 3.0 * Math.PI / 2.0).isTrue();

        AB = new Vector2d(0.0, 1.0);
        BC = new Vector2d(3.0, -3.0);
        angle = VectorUtils.getAngleABC(AB, BC);

        Assertions.assertThat(angle == Math.PI / 4.0).isTrue();
    }

    @Test
    public void testGetAngleBAC() {
        Vector2d AB = new Vector2d(1.0, 0.0);
        Vector2d AC = new Vector2d(1.0, 1.0);
        double angle = VectorUtils.getAngleBAC(AB, AC);

        Assertions.assertThat(angle == Math.PI / 4.0).isTrue();

        AB = new Vector2d(1.0, 1.0);
        AC = new Vector2d(1.0, -1.0);
        angle = VectorUtils.getAngleBAC(AB, AC);

        Assertions.assertThat(angle == 3.0 * Math.PI / 2.0).isTrue();
    }

    @Test
    public void testGetCurvatureAngleABC() {
        Vector2d AB = new Vector2d(1.0, 1.0);
        Vector2d BC = new Vector2d(1.0, 0.0);
        double angle = VectorUtils.getCurvatureAngleABC(AB, BC);

        Assertions.assertThat(angle == 3.0 * Math.PI / 4.0).isTrue();

        AB = new Vector2d(1.0, 1.0);
        BC = new Vector2d(0.0, 1.0);
        angle = VectorUtils.getCurvatureAngleABC(AB, BC);

        Assertions.assertThat(angle == 3.0 * Math.PI / 4.0).isTrue();

        AB = new Vector2d(0.0, 1.0);
        BC = new Vector2d(-1.0, -1.0);
        angle = VectorUtils.getCurvatureAngleABC(AB, BC);

        Assertions.assertThat(angle == Math.PI / 4.0).isTrue();
    }

}
