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
            text: '低消费学生群体各消费地点消费人次占比饼图',
            x: 'center',
            padding: 5,                // 标题内边距，单位px，默认各方向内边距为5，
            textStyle: {
                fontSize: 18,
                fontWeight: 'bolder',
                color: '#333'          // 主标题文字颜色
            }
        },
        legend: {
            orient: 'horizontal',      // 布局方式，默认为水平布局
            x: 'center',               // 水平安放位置，默认为全图居中，可选为：
            y: 'bottom',               // 垂直安放位置，默认为全图顶端，可选为：
            padding: 5,                // 图例内边距，单位px，默认各方向内边距为5，
            itemGap: 10,               // 各个item之间的间隔，单位px，默认为10，
            itemWidth: 20,             // 图例图形宽度
            itemHeight: 20,            // 图例图形高度
            textStyle: {
                color: '#333'          // 图例文字颜色
            }
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                data: [          // 数据数组，name 为数据项名称，value 为数据项值
                    <c:forEach items="${canteens}" var="canteen">
                    {value:${canteen.count}, name: '${canteen.name}'},
                    </c:forEach>
                ],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            position: 'outer',
                            formatter: '{b} : {c} ({d}%)'
                        },
                        labelLine: {
                            show: true
                        }
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
