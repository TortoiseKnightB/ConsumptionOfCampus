package com.example.pojo;

/**
 * @author T-Knight
 * @create 2021-05-25 3:12 δΈε
 */

/**
 * εζ 
 */
public class Data {
    double x;
    double y;

    public Data() {
    }

    public Data(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Data{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
