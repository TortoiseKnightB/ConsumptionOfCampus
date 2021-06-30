package com.example.dao;

import com.example.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @author T-Knight
 * @create 2021-05-23 6:04 下午
 */
public interface AnalysisDao {

    /**
     * 查询本月各食堂就餐人次
     *
     * @param count 显示食堂的数目
     * @return 食堂名字，本月就餐人数
     */
    public List<CanteenPeriod> queryPeopleInCanteens(int count);

    /**
     * 查询本月各时间段各食堂就餐人次
     *
     * @param count  显示食堂的数目
     * @param period 时间段=[早餐,中餐,晚餐,夜宵]
     * @return 食堂名字，该时间段本月就餐人数
     */
    public List<CanteenPeriod> queryPeopleInCanteensByPeriod(int count, String period);

    /**
     * 查询四月份消费金额变化情况
     *
     * @return
     */
    public List<CostInApril> queryCostInAprilAll();

    /**
     * 查询工作日与非工作日各食堂消费情况
     *
     * @param count     显示食堂的数目
     * @param isWorkDay 是否工作日
     * @return 食堂名字，消费人次
     */
    public List<CanteenPeriod> queryPeopleInCanteensByWorkOrNonWork(int count, boolean isWorkDay);

    /**
     * 查询工作日与非工作日就餐人次随时间变化情况
     *
     * @param isWork
     * @return
     */
    public List<PeopleTime> queryPeopleTime(boolean isWork);

    /**
     * 查询各专业学生的情况
     *
     * @return
     */
    public List<Major> queryPeopleInMajor();

    /**
     * 查询低消费学生各食堂就餐人次情况
     *
     * @param count 显示食堂的数目
     * @return 食堂名字，消费人次
     */
    public List<CanteenPeriod> queryPeopleInLowCostCanteens(int count);

    /**
     * 查询低消费学生工作日与非工作日就餐人数变化情况
     *
     * @param isWork 是否工作日
     * @return 消费时间点，消费人次
     */
    public List<PeopleTime> queryPeopleInLowCostTime(boolean isWork);

    /**
     * 查询聚类结果
     *
     * @return 类别结果集合
     */
    public List<ClusteringBean> queryPeopleInClusteringKmeans();

    /**
     * 将新的聚类结果写入数据库
     *
     * @param k 聚类类别数
     */
    public void updateClusteringKmeans(int k);

    /**
     * 查询总体的本月人均刷卡频次和人均消费金额
     *
     * @return
     */
    public TimesCostPer queryTimesCostPer();

    /**
     * 查询各食堂本月人均刷卡频次和人均消费金额
     *
     * @return
     */
    public List<TimesCostPerCanteens> queryTimesCostPerOfCanteens();

}
