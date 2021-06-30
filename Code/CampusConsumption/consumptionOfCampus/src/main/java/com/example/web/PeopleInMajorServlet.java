package com.example.web; /**
 * @author T-Knight
 * @create 2021-05-24 7:00 下午
 */

import com.example.pojo.Major;
import com.example.service.AnalysisService;
import com.example.service.impl.AnalysisServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class PeopleInMajorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        AnalysisService analysisService = new AnalysisServiceImpl();
        List<Major> majors = analysisService.peopleInMajor();
        request.setAttribute("majors", majors);
        if ("Cost".equals(view)) {
            request.getRequestDispatcher("/pages/majorAnalysis/peopleInMajorCost.jsp").forward(request, response);
        } else if ("Penetration".equals(view)) {
            request.getRequestDispatcher("/pages/majorAnalysis/peopleInMajorPenetration.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
