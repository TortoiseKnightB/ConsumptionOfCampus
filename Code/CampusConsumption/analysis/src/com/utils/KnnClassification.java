package com.utils;
/**
 * @Description: KNN分类
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.utils.KnnValueBean;
import com.utils.KnnValueSort;
//import com.lulei.util.JsonUtil;

@SuppressWarnings({"rawtypes"})
public abstract class KnnClassification<T> {

    private List<KnnValueBean> dataArray;   // 存储已知分类的数据
    private int K = 3;  // 选取k个最邻近数据

    public KnnClassification() {
    }

    public KnnClassification(List<KnnValueBean> dataArray, int k) {
        this.dataArray = dataArray;
        K = k;
    }

    public int getK() {
        return K;
    }

    public void setK(int K) {
        if (K < 1) {
            throw new IllegalArgumentException("K must greater than 0");
        }
        this.K = K;
    }

    /**
     * @param value
     * @param typeId
     * @Description: 向模型中添加1条记录
     */
    public void addRecord(T value, String typeId) {
        if (dataArray == null) {
            dataArray = new ArrayList<KnnValueBean>();
        }
        dataArray.add(new KnnValueBean<T>(value, typeId));
    }

    /**
     * @param value 需要判断的数据
     * @Description: KNN分类判断value的类别
     */
    public String getTypeId(T value) {
        // 最邻近k个数据
        KnnValueSort[] array = getKType(value);
        // 记录最近k个数据中，每个类别出现的次数
        HashMap<String, Integer> map = new HashMap<String, Integer>(K);
        for (KnnValueSort bean : array) {
            if (bean != null) {
                if (map.containsKey(bean.getTypeId())) {
                    map.put(bean.getTypeId(), map.get(bean.getTypeId()) + 1);
                } else {
                    map.put(bean.getTypeId(), 1);
                }
            }
        }
        // 找出出现次数最多的类别
        String maxTypeId = null;
        int maxCount = 0;
        Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Integer> entry = iter.next();
            if (maxCount < entry.getValue()) {
                maxCount = entry.getValue();
                maxTypeId = entry.getKey();
            }
        }
        return maxTypeId;
    }

    /**
     * @param value
     * @Description: 获取距离最近的K个分类
     */
    private KnnValueSort[] getKType(T value) {
        int k = 0;
        KnnValueSort[] topK = new KnnValueSort[K];  // 最邻近数组，记录最近的k个数据，按邻近距离升序排列
        // 遍历样本数据集
        for (KnnValueBean<T> bean : dataArray) {
            double score = similarScore(bean.getValue(), value);
            if (k == 0) {
                // 数组中的记录个数为0是直接添加
                topK[k] = new KnnValueSort(bean.getTypeId(), score);
                k++;
            } else {
                // 当最邻近k个值没有插满，或者比其中的某个值邻近距离更短时，将该点插入最邻近数组
                if (k < K || score < topK[k - 1].getScore()) {
                    //找到要插入的位置下标
                    int index;
                    for (index = 0; index < k && score >= topK[index].getScore(); index++) ;
                    // 插入位置之后的数组元素后移
                    for (int i = K - 1; i > index; i--) {
                        topK[i] = topK[i - 1];
                    }
                    topK[index] = new KnnValueSort(bean.getTypeId(), score);
                    k = Math.min(K, k + 1);
                }
            }
        }
        return topK;
    }

    /**
     * @param o1
     * @param o2
     * @Description: o1 o2之间的相似度
     */
    public abstract double similarScore(T o1, T o2);
}

