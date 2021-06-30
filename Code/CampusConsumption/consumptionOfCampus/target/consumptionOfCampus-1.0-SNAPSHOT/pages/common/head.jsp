<%--
  Created by IntelliJ IDEA.
  User: liuyang
  Date: 2021/5/23
  Time: 4:11 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>校园消费</title>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/bootstrap-3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="static/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/echarts.min.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/graph.css">

