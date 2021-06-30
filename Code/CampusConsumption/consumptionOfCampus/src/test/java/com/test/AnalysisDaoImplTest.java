package com.test;

import com.example.dao.impl.AnalysisDaoImpl;
import com.example.pojo.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author T-Knight
 * @create 2021-05-23 7:05 下午
 */
class AnalysisDaoImplTest {

    @Test
    void queryPeopleInCanteens() {
        List<CanteenPeriod> canteenPeriods = new AnalysisDaoImpl().queryPeopleInCanteens(7);
        canteenPeriods.forEach(System.out::println);
    }

    @Test
    void queryPeopleInCanteensByPeriod() {
        List<CanteenPeriod> canteenPeriods = new AnalysisDaoImpl().queryPeopleInCanteensByPeriod(5, "早餐");
        canteenPeriods.forEach(System.out::println);
    }

    @Test
    void queryCostInAprilAll() {
        List<CostInApril> res = new AnalysisDaoImpl().queryCostInAprilAll();
        res.forEach(System.out::println);
    }

    @Test
    void queryPeopleInCanteensByWorkOrNonWork() {
        List<CanteenPeriod> res = new AnalysisDaoImpl().queryPeopleInCanteensByWorkOrNonWork(7, true);
        List<CanteenPeriod> res2 = new AnalysisDaoImpl().queryPeopleInCanteensByWorkOrNonWork(7, false);
        res2.forEach(System.out::println);
    }

    @Test
    void queryPeopleTime() {
        List<PeopleTime> res = new AnalysisDaoImpl().queryPeopleTime(true);
        List<PeopleTime> res2 = new AnalysisDaoImpl().queryPeopleTime(false);
        res.forEach(System.out::println);
    }

    @Test
    void queryPeopleInMajor() {
        List<Major> majors = new AnalysisDaoImpl().queryPeopleInMajor();
        majors.forEach(System.out::println);
    }

    @Test
    void queryPeopleInLowCostCanteens() {
        List<CanteenPeriod> canteenPeriodList = new AnalysisDaoImpl().queryPeopleInLowCostCanteens(7);
        canteenPeriodList.forEach(System.out::println);
    }

    @Test
    void queryPeopleInLowCostTime() {
        List<PeopleTime> res = new AnalysisDaoImpl().queryPeopleInLowCostTime(true);
        List<PeopleTime> res2 = new AnalysisDaoImpl().queryPeopleInLowCostTime(false);
        res2.forEach(System.out::println);
    }

    @Test
    void queryPeopleInClusteringKmeans() {
        List<ClusteringBean> clusteringBeans = new AnalysisDaoImpl().queryPeopleInClusteringKmeans();
        clusteringBeans.forEach(System.out::println);
    }

    @Test
    void updateClusteringKmeans() {
        new AnalysisDaoImpl().updateClusteringKmeans(8);
    }

    @Test
    void queryTimesCostPer() {
        TimesCostPer timesCostPer = new AnalysisDaoImpl().queryTimesCostPer();
        System.out.println(timesCostPer);
    }

    @Test
    void queryTimesCostPerOfCanteens() {
        List<TimesCostPerCanteens> timesCostPers = new AnalysisDaoImpl().queryTimesCostPerOfCanteens();
        timesCostPers.forEach(System.out::println);
    }
}