<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>
<%@include file="/pages/common/navigator.jsp" %>

<%--食堂就餐人数占比饼图--%>
<div class="graph" id="main1"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main1'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '四月份不同时间段学生消费金额变化情况',
            x: 'center',
            padding: 5,                // 标题内边距，单位px，默认各方向内边距为5，
            textStyle: {
                fontSize: 18,
                fontWeight: 'bolder',
                color: '#333'          // 主标题文字颜色
            }
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['早餐时间消费金额', '中餐餐时间消费金额', '晚餐时间消费金额', '夜宵时间消费金额'],
            orient: 'horizontal',      // 布局方式，默认为水平布局
            x: 'center',               // 水平安放位置，默认为全图居中，可选为：
            bottom: '5%',
            padding: 5,                // 图例内边距，单位px，默认各方向内边距为5，
            itemGap: 10,               // 各个item之间的间隔，单位px，默认为10，
            itemWidth: 20,             // 图例图形宽度
            itemHeight: 14,            // 图例图形高度
            textStyle: {
                color: '#333'          // 图例文字颜色
            }
        },
        xAxis: {
            name:'日期(日)',
            type: 'category',
            boundaryGap: false,
            data: [
                <c:forEach items="${costInAprils}" var="cost">
                '${cost.date}',
                </c:forEach>
            ]
        },
        yAxis: {
            name:'消费金额(元)',
            type: 'value'
        },
        series: [
            {
                name: '早餐时间消费金额',
                type: 'line',
                data: [
                    <c:forEach items="${costInAprils}" var="cost">
                    ${cost.costMorning},
                    </c:forEach>
                ]
            },
            {
                name: '中餐餐时间消费金额',
                type: 'line',
                data: [
                    <c:forEach items="${costInAprils}" var="cost">
                    ${cost.costNoon},
                    </c:forEach>
                ]
            },
            {
                name: '晚餐时间消费金额',
                type: 'line',
                data: [
                    <c:forEach items="${costInAprils}" var="cost">
                    ${cost.costEvening},
                    </c:forEach>
                ]
            },
            {
                name: '夜宵时间消费金额',
                type: 'line',
                data: [
                    <c:forEach items="${costInAprils}" var="cost">
                    ${cost.costNight},
                    </c:forEach>
                ]
            }
        ],
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
            }
        }
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
