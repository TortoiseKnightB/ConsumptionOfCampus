package com.test;

import com.example.service.AnalysisService;
import com.example.service.impl.AnalysisServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author T-Knight
 * @create 2021-05-23 7:41 下午
 */
class AnalysisServiceImplTest {
    AnalysisService analysisService = new AnalysisServiceImpl();

    @Test
    void peopleInCanteens() {
        analysisService.peopleInCanteensAll().forEach(System.out::println);
    }

    @Test
    void peopleInCanteensMorning() {
        System.out.println(analysisService.peopleInCanteensMorning());
    }

    @Test
    void peopleInCanteensNoon() {
        System.out.println(analysisService.peopleInCanteensNoon());
    }

    @Test
    void peopleInCanteensEvening() {
        System.out.println(analysisService.peopleInCanteensEvening());
    }

    @Test
    void peopleInCanteensNight() {
        analysisService.peopleInCanteensNight().forEach(System.out::println);
    }
}