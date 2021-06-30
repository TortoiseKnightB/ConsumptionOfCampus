package com.example.pojo;

/**
 * @author T-Knight
 * @create 2021-05-24 1:02 下午
 */

/**
 * 四月份消费金额变化情况
 */
public class CostInApril {
    int date;
    int costMorning;
    int costNoon;
    int costEvening;
    int costNight;

    public CostInApril() {
    }

    public CostInApril(int date, int costMorning, int costNoon, int costEvening, int costNight) {
        this.date = date;
        this.costMorning = costMorning;
        this.costNoon = costNoon;
        this.costEvening = costEvening;
        this.costNight = costNight;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getCostMorning() {
        return costMorning;
    }

    public void setCostMorning(int costMorning) {
        this.costMorning = costMorning;
    }

    public int getCostNoon() {
        return costNoon;
    }

    public void setCostNoon(int costNoon) {
        this.costNoon = costNoon;
    }

    public int getCostEvening() {
        return costEvening;
    }

    public void setCostEvening(int costEvening) {
        this.costEvening = costEvening;
    }

    public int getCostNight() {
        return costNight;
    }

    public void setCostNight(int costNight) {
        this.costNight = costNight;
    }

    @Override
    public String toString() {
        return "CostInApril{" +
                "date=" + date +
                ", costMorning=" + costMorning +
                ", costNoon=" + costNoon +
                ", costEvening=" + costEvening +
                ", costNight=" + costNight +
                '}';
    }
}
