package com.analysis;

import com.utils.KnnClassification;
import com.results.id_typeOfConsumption;
import com.utils.KnnValueBean;

import java.util.ArrayList;
import java.util.List;

// 实现KMeans聚类，特征值数量为2
public class KNN2 extends KnnClassification<id_typeOfConsumption> {

    public KNN2() {
    }

    public KNN2(List dataArray, int k) {
        super(dataArray, k);
    }

    // 对测试机进行分类，并返回分类后的测试集
    public List<KnnValueBean<id_typeOfConsumption>> getResults(List<id_typeOfConsumption> examples) {
        List<KnnValueBean<id_typeOfConsumption>> list = new ArrayList<>();
        for (id_typeOfConsumption exam : examples) {
            String typeId = getTypeId(exam);
            list.add(new KnnValueBean<>(exam, typeId));
        }
        return list;
    }

    @Override
    public double similarScore(id_typeOfConsumption o1, id_typeOfConsumption o2) {
        // 求两点间的距离
        double distance = Math.sqrt(Math.pow(o1.getCostAcademic() - o2.getCostAcademic(), 2) + Math.pow(o1.getCostEating() - o2.getCostEating(), 2));
        return distance;
    }

}
