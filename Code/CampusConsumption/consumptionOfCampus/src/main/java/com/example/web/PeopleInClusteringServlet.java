package com.example.web; /**
 * @author T-Knight
 * @create 2021-05-25 2:48 下午
 */

import com.example.pojo.ClusteringBean;
import com.example.service.AnalysisService;
import com.example.service.impl.AnalysisServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class PeopleInClusteringServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String k = request.getParameter("clusteringK");
        if (k == null || "".equals(k)) {
            // k为空，查询处理，直接转发页面
            AnalysisService analysisService = new AnalysisServiceImpl();
            List<ClusteringBean> clusteringBeans = analysisService.peopleInClusteringKmeans();
            request.setAttribute("clusteringBeans", clusteringBeans);
            request.getRequestDispatcher("/pages/clusteringAnalysis/peopleInClusteringKmeans.jsp").forward(request, response);
        } else {
            // k不空，需要重新聚类，聚类后执行重定向处理
            if (isNum(k)) {
                // 输入的k合法
                new AnalysisServiceImpl().clusteringKmeans(Integer.parseInt(k));
                response.sendRedirect("peopleInClusteringServlet");
            } else {
                // 输入的k不合法
                request.setAttribute("msg", "聚类类别数必须为大于等于2的整数！");
                request.getRequestDispatcher("/pages/clusteringAnalysis/peopleInClusteringKmeans.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 判断k是否为大于等于2的整数
     *
     * @param k
     * @return
     */
    public static boolean isNum(String k) {
        for (int i = 0; i < k.length(); i++) {
            if (k.charAt(i) < '0' || k.charAt(i) > '9') {
                return false;
            }
        }
        return Integer.parseInt(k) >= 2;
    }
}




