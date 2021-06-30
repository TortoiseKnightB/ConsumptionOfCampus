<%--
  Created by IntelliJ IDEA.
  User: liuyang
  Date: 2021/5/23
  Time: 4:28 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            </button>
            <a class="navbar-brand" href="homeServlet">校园消费数据分析平台</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">消费地点分析 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="peopleInCanteensServlet?period=All">全时间段各消费地点情况</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="peopleInCanteensServlet?period=Morning">早餐时间段各消费地点情况</a></li>
                        <li><a href="peopleInCanteensServlet?period=Noon">中餐时间段各消费地点情况</a></li>
                        <li><a href="peopleInCanteensServlet?period=Evening">晚餐时间段各消费地点情况</a></li>
                        <li><a href="peopleInCanteensServlet?period=Night">夜宵时间段各消费地点情况</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">消费时间分析 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="peopleInTimeServlet?view=April">四月份消费金额变化情况</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="peopleInTimeServlet?view=Canteen">工作日与非工作日各消费地点情况对比</a></li>
                        <li><a href="peopleInTimeServlet?view=Time">工作日与非工作日就餐时间变化情况对比</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">各专业消费情况分析 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="peopleInMajorServlet?view=Cost">各专业人均消费情况</a></li>
                        <li><a href="peopleInMajorServlet?view=Penetration">各专业学生用餐普及率</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">学生群体聚类分析 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="peopleInClusteringServlet">K-Means聚类结果</a></li>
                        <li><a href="peopleInLowCostServlet?view=Canteens">低消费学生群体各消费地点消费情况</a></li>
                        <li><a href="peopleInLowCostServlet?view=Day">低消费学生工作日与非工作日就餐人数变化情况</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
