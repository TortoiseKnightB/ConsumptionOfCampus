<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        div.row {
            margin-top: 100px;
            margin-left: 10px;
            margin-right: 10px;
        }
    </style>
</head>
<body>

<%@include file="/pages/common/navigator.jsp" %>

<div class="row">
    <div class="col-lg-2"></div>
    <div class="col-lg-8">
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading">本月总体情况</div>
            <div class="panel-body">
                <p>本月人均刷卡频次为 <b>${timesCostPer.times} 次</b></p>
                <p>本月人均消费金额为 <b>${timesCostPer.cost} 元</b></p>
            </div>

            <!-- Table -->
            <table class="table">
                <thead>
                    <tr>
                        <th>食堂名称</th>
                        <th>各食堂人均刷卡频次</th>
                        <th>各食堂人均消费金额</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${timesCostPerCanteens}" var="canteen">
                        <tr>
                            <th>${canteen.name}</th>
                            <td>${canteen.times}</td>
                            <td>${canteen.cost}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-lg-2"></div>
</div>

</body>
</html>