package com.analysis;

import com.utils.KMeansClustering;
import com.results.id_typeOfConsumption;

import java.util.Iterator;
import java.util.List;

// 实现KMeans聚类，特征值数量为2
public class KMeans2 extends KMeansClustering {
    public KMeans2() {
    }

    public KMeans2(List dataArray, int k, int maxClusterTimes) {
        super(dataArray, k, maxClusterTimes);
    }

    @Override
    public double similarScore(Object o1, Object o2) {
        id_typeOfConsumption x = (id_typeOfConsumption) o1;
        id_typeOfConsumption y = (id_typeOfConsumption) o2;
        double distance = Math.sqrt(Math.pow(x.getCostAcademic() - y.getCostAcademic(), 2) + Math.pow(x.getCostEating() - y.getCostEating(), 2));
        // 这里取负数，distance越小，其负数越大
        return distance * -1;
    }

    @Override
    public boolean equals(Object o1, Object o2) {
        return o1.equals(o2);
    }

    @Override
    public Object getCenterT(List list) {
        int x = 0, y = 0;
        Iterator<id_typeOfConsumption> iterator = list.listIterator();
        while (iterator.hasNext()) {
            id_typeOfConsumption obj = iterator.next();
            x += obj.getCostAcademic();
            y += obj.getCostEating();
        }
        x = x / list.size();
        y = y / list.size();
        // 将质心对应的id设为特殊值 -1
        return new id_typeOfConsumption(-1, x, y);
    }
}
