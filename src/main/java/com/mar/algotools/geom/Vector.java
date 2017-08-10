package com.mar.algotools.geom;

public interface Vector {

    public void add(Vector pVector);

    public void cross(Vector pVector);

    public void normalize();

    public int size();

    public void subtract(Vector pVector);

}
