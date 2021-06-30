package com.example.pojo;

/**
 * @author T-Knight
 * @create 2021-06-09 12:55 下午
 */
// 人均刷卡频次   人均消费金额
public class TimesCostPer {
    int times;
    double cost;

    public TimesCostPer() {
    }

    public TimesCostPer(int times, double cost) {
        this.times = times;
        this.cost = cost;
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

}
