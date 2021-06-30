<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>
<%@include file="/pages/common/navigator.jsp" %>

<div class="graph" id="main1"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main1'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '低消费学生工作日与非工作日就餐人数变化情况',
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
            data: ['低消费学生工作日就餐人数曲线', '低消费学生非工作日就餐人数曲线'],
            orient: 'horizontal',      // 布局方式，默认为水平布局
            x: 'center',               // 水平安放位置，默认为全图居中，可选为：
            bottom: 0,
            padding: 5,                // 图例内边距，单位px，默认各方向内边距为5，
            itemGap: 10,               // 各个item之间的间隔，单位px，默认为10，
            itemWidth: 20,             // 图例图形宽度
            itemHeight: 14,            // 图例图形高度
            textStyle: {
                color: '#333'          // 图例文字颜色
            }
        },
        xAxis: {
            name: '时间(h)',
            type: 'category',
            boundaryGap: false,
            data: [
                <c:forEach items="${timeWork}" var="time">
                '${time.costTime}',
                </c:forEach>
            ]
        },
        yAxis: {
            name: '消费人次',
            type: 'value'
        },
        series: [
            {
                name: '低消费学生工作日就餐人数曲线',
                type: 'line',
                data: [
                    <c:forEach items="${timeWork}" var="time">
                    ${time.count},
                    </c:forEach>
                ]
            },
            {
                name: '低消费学生非工作日就餐人数曲线',
                type: 'line',
                data: [
                    <c:forEach items="${timeNonWork}" var="time">
                    ${time.count},
                    </c:forEach>
                ]
            },
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
