<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        div.graph {
            width: 1400px;
            height: 600px;
        }
    </style>
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
            text: '各专业学生用餐普及率',
            x: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false}
            }
        },
        legend: {
            data: ['早餐普及率', '中餐普及率', '晚餐普及率', '夜宵普及率'],
            orient: 'vertical',      // 布局方式，默认为水平布局
            right: '5%',               // 水平安放位置，默认为全图居中，可选为：
        },
        xAxis: [
            {
                type: 'category',
                data: [
                    <c:forEach items="${majors}" var="major">
                    '${major.name}',
                    </c:forEach>
                ],
                axisPointer: {
                    type: 'shadow'
                },
                axisLabel: {
                    interval: 'auto',
                    show: true,
                    textStyle: {
                        fontSize: 11 //更改坐标轴文字大小
                    },
                    rotate: 40,
                },
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '用餐普及率',
                min: 0,
                max: 1,
                interval: 0.1,
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: '早餐普及率',
                type: 'bar',
                yAxisIndex: 0,
                data: [
                    <c:forEach items="${majors}" var="major">
                    '${major.morningPenetration}',
                    </c:forEach>
                ]
            },
            {
                name: '中餐普及率',
                type: 'bar',
                yAxisIndex: 0,
                data: [
                    <c:forEach items="${majors}" var="major">
                    '${major.noonPenetration}',
                    </c:forEach>
                ]
            },
            {
                name: '晚餐普及率',
                type: 'bar',
                yAxisIndex: 0,
                data: [
                    <c:forEach items="${majors}" var="major">
                    '${major.eveningPenetration}',
                    </c:forEach>
                ]
            },
            {
                name: '夜宵普及率',
                type: 'bar',
                yAxisIndex: 0,
                data: [
                    <c:forEach items="${majors}" var="major">
                    '${major.nightPenetration}',
                    </c:forEach>
                ]
            },
        ],
        grid: {
            left: '5%',
            right: '2%',
        }
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
