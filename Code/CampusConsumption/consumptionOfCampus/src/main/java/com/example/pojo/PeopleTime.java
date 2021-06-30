package com.example.pojo;

/**
 * @author T-Knight
 * @create 2021-05-24 1:47 下午
 */

/**
 * 消费人次与时间变化的关系
 */
public class PeopleTime {
    int costTime;
    int count;

    public PeopleTime() {
    }

    public PeopleTime(int costTime, int count) {
        this.costTime = costTime;
        this.count = count;
    }

    public int getCostTime() {
        return costTime;
    }

    public void setCostTime(int costTime) {
        this.costTime = costTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PeopleTime{" +
                "costTime=" + costTime +
                ", count=" + count +
                '}';
    }
}
