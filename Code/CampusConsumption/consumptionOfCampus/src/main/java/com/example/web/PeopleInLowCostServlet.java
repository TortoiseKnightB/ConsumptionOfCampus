package com.example.web; /**
 * @author T-Knight
 * @create 2021-05-24 8:25 下午
 */

import com.example.pojo.CanteenPeriod;
import com.example.pojo.PeopleTime;
import com.example.service.AnalysisService;
import com.example.service.impl.AnalysisServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class PeopleInLowCostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        AnalysisService analysisService = new AnalysisServiceImpl();
        if ("Canteens".equals(view)) {
            List<CanteenPeriod> canteens = analysisService.peopleInLowCostCanteens();
            request.setAttribute("canteens", canteens);
            request.getRequestDispatcher("/pages/lowCostAnalysis/peopleInLowCostCanteens.jsp").forward(request, response);
        } else if ("Day".equals(view)) {
            List<PeopleTime> timeWork = analysisService.peopleInLowCostTimeWork();
            List<PeopleTime> timeNonWork = analysisService.peopleInLowCostTimeNonWork();
            request.setAttribute("timeWork", timeWork);
            request.setAttribute("timeNonWork", timeNonWork);
            request.getRequestDispatcher("/pages/lowCostAnalysis/peopleInLowCostTime.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
