package com.results;

// 查询本月各食堂就餐人次
public class numbersOfCanteen {
    private String canteen; // 食堂
    private int number;     // 就餐人次

    public numbersOfCanteen() {
    }

    public numbersOfCanteen(String canteen, int number) {
        this.canteen = canteen;
        this.number = number;
    }

    public String getCanteen() {
        return canteen;
    }

    public void setCanteen(String canteen) {
        this.canteen = canteen;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "numbersOfCanteen{" +
                "canteen='" + canteen + '\'' +
                ", number=" + number +
                '}';
    }
}
