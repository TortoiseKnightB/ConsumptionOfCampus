<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        div.row {
            margin-top: 60px;
            margin-left: 10px;
            margin-right: 10px;
        }

        form {
            margin: 0;
            display: inline;
        }

        span.errorMsg{
            color: #fd7688;
            margin-left: 7px;
        }
    </style>

    <script>
        function test() {
            var path = "peopleInClusteringServlet?clusteringK=" + $("#clusteringK").val();
            window.location.href = path;
        }
    </script>


</head>
<body>
<%@include file="/pages/common/navigator.jsp" %>

<div class="row">
    <div class="col-lg-8">
        <%--        聚类图像展示框--%>
        <div class="graph" id="main1"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main1'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'K-means聚类结果',
                    x: 'center'
                },
                grid: {
                    left: '3%',
                    right: '7%',
                    bottom: '7%',
                    containLabel: true
                },
                tooltip: {
                    // trigger: 'axis',
                    showDelay: 0,
                    formatter: function (params) {
                        if (params.value.length > 1) {
                            return params.seriesName + ' :<br/>'
                                + params.value[0] + 'cm '
                                + params.value[1] + 'kg ';
                        } else {
                            return params.seriesName + ' :<br/>'
                                + params.name + ' : '
                                + params.value + 'kg ';
                        }
                    },
                    axisPointer: {
                        show: true,
                        type: 'cross',
                        lineStyle: {
                            type: 'dashed',
                            width: 1
                        }
                    }
                },
                toolbox: {
                    feature: {
                        dataZoom: {},
                        brush: {
                            type: ['']
                        }
                    }
                },
                brush: {},
                legend: {
                    data: [
                        <c:forEach items="${clusteringBeans}" var="clustering">
                        '${clustering.name}',
                        </c:forEach>
                    ],
                    left: 'center',
                    bottom: 0
                },
                xAxis: [
                    {
                        type: 'value',
                        name: '学习及生活消费金额(元)',
                        min: 0,
                        max: 500,
                        nameLocation: 'middle',
                        nameGap: 30,
                        scale: true,
                        axisLabel: {
                            formatter: '{value}'
                        },
                        splitLine: {
                            show: false
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '饮食消费金额(元)',
                        min: 0,
                        max: 900,
                        nameLocation: 'middle',
                        nameGap: 30,
                        scale: true,
                        axisLabel: {
                            formatter: '{value}'
                        },
                        splitLine: {
                            show: false
                        }
                    }
                ],
                series: [
                    <c:forEach items="${clusteringBeans}" var="clustering">
                    {
                        name: '${clustering.name}',
                        type: 'scatter',
                        data: [
                            <c:forEach items="${clustering.data}" var="clusteringData">
                            [${clusteringData.x}, ${clusteringData.y}],
                            </c:forEach>
                        ]
                    },
                    </c:forEach>
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        </script>
    </div>

    <div class="col-lg-4">
        <%--        聚类数目输入框--%>
        <div class="input-group">
            <input type="text" class="form-control" id="clusteringK" placeholder="请输入聚类数目">
            <span class="input-group-btn">
                <button class="btn btn-default" type="button" onclick="test()">Go!</button>
<%--                <button class="btn btn-default" type="submit" onclick="window.location.href='peopleInClusteringServlet'">Go!</button>--%>
            </span>
        </div><!-- /input-group -->
        <span class="errorMsg">${requestScope.msg}</span>
    </div>
</div><!-- /.row -->

<div class="graph" id="main1"></div>


</body>
</html>
