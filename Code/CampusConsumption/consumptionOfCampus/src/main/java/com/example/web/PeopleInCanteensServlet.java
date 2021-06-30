package com.example.web; /**
 * @author T-Knight
 * @create 2021-05-23 8:05 下午
 */

import com.example.pojo.CanteenPeriod;
import com.example.service.impl.AnalysisServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PeopleInCanteensServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Class clazz = AnalysisServiceImpl.class;
        List<CanteenPeriod> canteenPeriodList = new ArrayList<>();
        try {
            Method method = clazz.getDeclaredMethod("peopleInCanteens" + request.getParameter("period"));
            method.setAccessible(true);
            AnalysisServiceImpl analysisService = (AnalysisServiceImpl) clazz.newInstance();
            Object list = method.invoke(analysisService);
            canteenPeriodList = (List<CanteenPeriod>) list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String titleText = "";
        switch (request.getParameter("period")) {
            case "All":
                titleText = "全时间段";
                break;
            case "Morning":
                titleText = "早餐时间段";
                break;
            case "Noon":
                titleText = "中餐时间段";
                break;
            case "Evening":
                titleText = "晚餐时间段";
                break;
            case "Night":
                titleText = "夜宵时间段";
        }
        request.setAttribute("titleText", titleText);
        request.setAttribute("canteenPeriodList", canteenPeriodList);
        request.getRequestDispatcher("/pages/canteensAnalysis/peopleInCanteens.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
