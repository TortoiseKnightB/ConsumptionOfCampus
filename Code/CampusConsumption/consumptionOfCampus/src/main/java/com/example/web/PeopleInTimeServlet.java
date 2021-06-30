package com.example.web; /**
 * @author T-Knight
 * @create 2021-05-24 2:42 下午
 */

import com.example.pojo.CanteenPeriod;
import com.example.pojo.CostInApril;
import com.example.pojo.PeopleTime;
import com.example.service.AnalysisService;
import com.example.service.impl.AnalysisServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class PeopleInTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        AnalysisService analysisService = new AnalysisServiceImpl();
        if ("April".equals(view)) {
            List<CostInApril> costInAprils = analysisService.costInAprilAll();
            request.setAttribute("costInAprils", costInAprils);
            request.getRequestDispatcher("/pages/timeAnalysis/peopleInTimeAll.jsp").forward(request, response);
        } else if ("Canteen".equals(view)) {
            List<CanteenPeriod> canteenWork = analysisService.peopleInCanteensByWork();
            List<CanteenPeriod> canteenNonWork = analysisService.peopleInCanteensByNonWork();
            request.setAttribute("canteenWork", canteenWork);
            request.setAttribute("canteenNonWork", canteenNonWork);
            request.getRequestDispatcher("/pages/timeAnalysis/peopleInTimeCanteens.jsp").forward(request, response);
        } else if ("Time".equals(view)) {
            List<PeopleTime> peopleTimesWork = analysisService.queryPeopleTimeWork();
            List<PeopleTime> peopleTimesNonWork = analysisService.queryPeopleTimeNonWork();
            request.setAttribute("peopleTimesWork", peopleTimesWork);
            request.setAttribute("peopleTimesNonWork", peopleTimesNonWork);
            request.getRequestDispatcher("/pages/timeAnalysis/peopleInTimeTime.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
