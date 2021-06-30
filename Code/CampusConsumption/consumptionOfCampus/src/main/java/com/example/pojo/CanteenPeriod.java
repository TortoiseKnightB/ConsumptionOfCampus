package com.example.pojo;

/**
 * @author T-Knight
 * @create 2021-05-23 6:51 下午
 */

/**
 * 食堂对应时间段的就餐人数
 */
public class CanteenPeriod {
    String name;
    int count;

    public CanteenPeriod() {
    }

    public CanteenPeriod(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CanteenPeriod{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
