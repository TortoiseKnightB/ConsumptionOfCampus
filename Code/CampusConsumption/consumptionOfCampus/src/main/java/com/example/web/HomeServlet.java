package com.example.web; /**
 * @author T-Knight
 * @create 2021-06-09 1:25 下午
 */

import com.example.pojo.TimesCostPer;
import com.example.pojo.TimesCostPerCanteens;
import com.example.service.impl.AnalysisServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TimesCostPer timesCostPer = new AnalysisServiceImpl().timesCostPer();
        List<TimesCostPerCanteens> timesCostPerCanteens = new AnalysisServiceImpl().timesCostPerOfCanteens();
        request.setAttribute("timesCostPer", timesCostPer);
        request.setAttribute("timesCostPerCanteens", timesCostPerCanteens);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
