package com.results;

// 聚类准备
// 特征值：学术消费金额、饮食消费金额
public class id_typeOfConsumption {
    private int id;
    private double costAcademic;
    private double costEating;

    public id_typeOfConsumption() {
    }

    public id_typeOfConsumption(int id, double costAcademic, double costEating) {
        this.id = id;
        this.costAcademic = costAcademic;
        this.costEating = costEating;
    }

    public double getCostAcademic() {
        return costAcademic;
    }

    public void setCostAcademic(double costAcademic) {
        this.costAcademic = costAcademic;
    }

    public double getCostEating() {
        return costEating;
    }

    public void setCostEating(double costEating) {
        this.costEating = costEating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id_typeOfConsumption{" +
                "costAcademic=" + costAcademic +
                ", costEating=" + costEating +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        id_typeOfConsumption object = (id_typeOfConsumption) obj;
        return this.costAcademic == object.getCostAcademic() && this.costEating == object.getCostEating();
    }
}
