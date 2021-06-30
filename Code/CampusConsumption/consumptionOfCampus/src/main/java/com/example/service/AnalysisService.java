package com.example.service;

import com.example.pojo.*;

import java.util.List;

/**
 * @author T-Knight
 * @create 2021-05-23 7:30 下午
 */
public interface AnalysisService {

    /**
     * 本月各食堂就餐人次
     *
     * @return
     */
    public List<CanteenPeriod> peopleInCanteensAll();

    /**
     * 本月早餐时间段各食堂就餐人次
     *
     * @return
     */
    public List<CanteenPeriod> peopleInCanteensMorning();

    /**
     * 本月中餐时间段各食堂就餐人次
     *
     * @return
     */
    public List<CanteenPeriod> peopleInCanteensNoon();

    /**
     * 本月晚餐时间段各食堂就餐人次
     *
     * @return
     */
    public List<CanteenPeriod> peopleInCanteensEvening();

    /**
     * 本月夜宵时间段各食堂就餐人次
     *
     * @return
     */
    public List<CanteenPeriod> peopleInCanteensNight();

    /**
     * 四月份消费金额变化情况
     *
     * @return
     */
    public List<CostInApril> costInAprilAll();

    /**
     * 工作日各食堂消费情况
     *
     * @return 食堂名字，消费人次
     */
    public List<CanteenPeriod> peopleInCanteensByWork();

    /**
     * 非工作日各食堂消费情况
     *
     * @return 食堂名字，消费人次
     */
    public List<CanteenPeriod> peopleInCanteensByNonWork();

    /**
     * 工作日就餐人次随时间变化情况
     *
     * @return
     */
    public List<PeopleTime> queryPeopleTimeWork();

    /**
     * 非工作日就餐人次随时间变化情况
     *
     * @return
     */
    public List<PeopleTime> queryPeopleTimeNonWork();

    /**
     * 各专业学生的情况
     *
     * @return
     */
    public List<Major> peopleInMajor();

    /**
     * 查询低消费学生各食堂就餐人次情况
     *
     * @return 食堂名字，消费人次
     */
    public List<CanteenPeriod> peopleInLowCostCanteens();

    /**
     * 查询低消费学生工作日就餐人数变化情况
     *
     * @return 消费时间点，消费人次
     */
    public List<PeopleTime> peopleInLowCostTimeWork();

    /**
     * 查询低消费学生非工作日就餐人数变化情况
     *
     * @return 消费时间点，消费人次
     */
    public List<PeopleTime> peopleInLowCostTimeNonWork();

    /**
     * 查询聚类结果
     *
     * @return 类别结果集合
     */
    public List<ClusteringBean> peopleInClusteringKmeans();

    /**
     * 将新的聚类结果写入数据库
     *
     * @param k 聚类类别数
     */
    public void clusteringKmeans(int k);

    /**
     * 查询总体的本月人均刷卡频次和人均消费金额
     *
     * @return
     */
    public TimesCostPer timesCostPer();

    /**
     * 查询各食堂本月人均刷卡频次和人均消费金额
     *
     * @return
     */
    public List<TimesCostPerCanteens> timesCostPerOfCanteens();
}
