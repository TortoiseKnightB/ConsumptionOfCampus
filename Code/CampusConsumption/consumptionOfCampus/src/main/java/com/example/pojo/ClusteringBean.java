package com.example.pojo;

import java.util.List;

/**
 * @author T-Knight
 * @create 2021-05-25 3:07 下午
 */

/**
 * 存储K-Means聚类结果
 */
public class ClusteringBean {
    String name;    // 类别
    List<Data> data;    // 坐标

    public ClusteringBean() {
    }

    public ClusteringBean(String name, List<Data> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClusteringBean{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
