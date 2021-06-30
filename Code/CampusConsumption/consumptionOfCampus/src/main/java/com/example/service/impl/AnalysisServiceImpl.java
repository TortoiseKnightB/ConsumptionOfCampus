package com.example.service.impl;

import com.example.dao.impl.AnalysisDaoImpl;
import com.example.pojo.*;
import com.example.service.AnalysisService;

import java.util.List;

/**
 * @author T-Knight
 * @create 2021-05-23 7:31 下午
 */
public class AnalysisServiceImpl implements AnalysisService {
    @Override
    public List<CanteenPeriod> peopleInCanteensAll() {
        return new AnalysisDaoImpl().queryPeopleInCanteens(7);
    }

    @Override
    public List<CanteenPeriod> peopleInCanteensMorning() {
        return new AnalysisDaoImpl().queryPeopleInCanteensByPeriod(5, "早餐");
    }

    @Override
    public List<CanteenPeriod> peopleInCanteensNoon() {
        return new AnalysisDaoImpl().queryPeopleInCanteensByPeriod(7, "中餐");
    }

    @Override
    public List<CanteenPeriod> peopleInCanteensEvening() {
        return new AnalysisDaoImpl().queryPeopleInCanteensByPeriod(7, "晚餐");
    }

    @Override
    public List<CanteenPeriod> peopleInCanteensNight() {
        return new AnalysisDaoImpl().queryPeopleInCanteensByPeriod(6, "夜宵");
    }

    @Override
    public List<CostInApril> costInAprilAll() {
        return new AnalysisDaoImpl().queryCostInAprilAll();
    }

    @Override
    public List<CanteenPeriod> peopleInCanteensByWork() {
        return new AnalysisDaoImpl().queryPeopleInCanteensByWorkOrNonWork(7, true);
    }

    @Override
    public List<CanteenPeriod> peopleInCanteensByNonWork() {
        return new AnalysisDaoImpl().queryPeopleInCanteensByWorkOrNonWork(7, false);
    }

    @Override
    public List<PeopleTime> queryPeopleTimeWork() {
        return new AnalysisDaoImpl().queryPeopleTime(true);
    }

    @Override
    public List<PeopleTime> queryPeopleTimeNonWork() {
        return new AnalysisDaoImpl().queryPeopleTime(false);
    }

    @Override
    public List<Major> peopleInMajor() {
        return new AnalysisDaoImpl().queryPeopleInMajor();
    }

    @Override
    public List<CanteenPeriod> peopleInLowCostCanteens() {
        return new AnalysisDaoImpl().queryPeopleInLowCostCanteens(7);
    }

    @Override
    public List<PeopleTime> peopleInLowCostTimeWork() {
        return new AnalysisDaoImpl().queryPeopleInLowCostTime(true);
    }

    @Override
    public List<PeopleTime> peopleInLowCostTimeNonWork() {
        return new AnalysisDaoImpl().queryPeopleInLowCostTime(false);
    }

    @Override
    public List<ClusteringBean> peopleInClusteringKmeans() {
        return new AnalysisDaoImpl().queryPeopleInClusteringKmeans();
    }

    @Override
    public void clusteringKmeans(int k) {
        new AnalysisDaoImpl().updateClusteringKmeans(k);
    }

    @Override
    public TimesCostPer timesCostPer() {
        return new AnalysisDaoImpl().queryTimesCostPer();
    }

    @Override
    public List<TimesCostPerCanteens> timesCostPerOfCanteens() {
        return new AnalysisDaoImpl().queryTimesCostPerOfCanteens();
    }
}
