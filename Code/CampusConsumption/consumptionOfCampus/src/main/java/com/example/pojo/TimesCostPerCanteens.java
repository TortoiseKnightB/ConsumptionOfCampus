package com.example.pojo;

/**
 * @author T-Knight
 * @create 2021-06-09 1:10 下午
 */

/**
 * 食堂人均消费情况
 */
public class TimesCostPerCanteens {
    String name;
    int times;
    double cost;

    public TimesCostPerCanteens() {
    }

    public TimesCostPerCanteens(String name, int times, double cost) {
        this.name = name;
        this.times = times;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "TimesCostPerCanteens{" +
                "name='" + name + '\'' +
                ", times=" + times +
                ", cost=" + cost +
                '}';
    }
}
