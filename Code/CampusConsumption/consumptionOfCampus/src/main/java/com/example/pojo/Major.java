package com.example.pojo;

/**
 * @author T-Knight
 * @create 2021-05-24 4:41 下午
 */

/**
 * 校内各专业的消费情况数据
 */
public class Major {
    String name;    // 专业名
    int timesPer;   // 人均消费频次
    double costPer; // 人均消费金额
    double morningPenetration;  // 早餐普及率
    double noonPenetration;  // 中餐普及率
    double eveningPenetration;  // 晚餐普及率
    double nightPenetration;  // 夜宵普及率

    public Major() {
    }

    public Major(String name, int timesPer, double costPer, double morningPenetration, double noonPenetration, double eveningPenetration, double nightPenetration) {
        this.name = name;
        this.timesPer = timesPer;
        this.costPer = costPer;
        this.morningPenetration = morningPenetration;
        this.noonPenetration = noonPenetration;
        this.eveningPenetration = eveningPenetration;
        this.nightPenetration = nightPenetration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimesPer() {
        return timesPer;
    }

    public void setTimesPer(int timesPer) {
        this.timesPer = timesPer;
    }

    public double getCostPer() {
        return costPer;
    }

    public void setCostPer(double costPer) {
        this.costPer = costPer;
    }

    public double getMorningPenetration() {
        return morningPenetration;
    }

    public void setMorningPenetration(double morningPenetration) {
        this.morningPenetration = morningPenetration;
    }

    public double getNoonPenetration() {
        return noonPenetration;
    }

    public void setNoonPenetration(double noonPenetration) {
        this.noonPenetration = noonPenetration;
    }

    public double getEveningPenetration() {
        return eveningPenetration;
    }

    public void setEveningPenetration(double eveningPenetration) {
        this.eveningPenetration = eveningPenetration;
    }

    public double getNightPenetration() {
        return nightPenetration;
    }

    public void setNightPenetration(double nightPenetration) {
        this.nightPenetration = nightPenetration;
    }

    @Override
    public String toString() {
        return "Major{" +
                "name='" + name + '\'' +
                ", timesPer=" + timesPer +
                ", costPer=" + costPer +
                ", morningPenetration=" + morningPenetration +
                ", noonPenetration=" + noonPenetration +
                ", eveningPenetration=" + eveningPenetration +
                ", nightPenetration=" + nightPenetration +
                '}';
    }
}
