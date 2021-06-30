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
            text: '各专业学生人均消费情况',
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
            data: ['人均消费频次', '人均消费金额'],
            orient: 'vertical',      // 布局方式，默认为水平布局
            right: '10%',               // 水平安放位置，默认为全图居中，可选为：
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
                name: '人均消费频次(次)',
                min: 0,
                max: 100,
                interval: 10,
                axisLabel: {
                    formatter: '{value}'
                }
            },
            {
                type: 'value',
                name: '人均消费金额(元)',
                min: 0,
                max: 400,
                interval: 20,
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: '人均消费频次',
                type: 'bar',
                yAxisIndex: 0,
                data: [
                    <c:forEach items="${majors}" var="major">
                    '${major.timesPer}',
                    </c:forEach>
                ]
            },
            {
                name: '人均消费金额',
                type: 'bar',
                yAxisIndex: 1,
                data: [
                    <c:forEach items="${majors}" var="major">
                    '${major.costPer}',
                    </c:forEach>
                ]
            }
        ],
        grid: {
            left: '7%',
            right: '5%',
        }
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
