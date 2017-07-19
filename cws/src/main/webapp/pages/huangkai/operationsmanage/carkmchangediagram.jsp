<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    //设置无缓存
    response.addHeader("Progma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Cache-Control", "must-revalidate");
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <title>车辆里程变化曲线</title>
    <script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
    <script src="../huangkai/layui/layui.js" charset="utf-8"></script>
    <script src="<%=basePath %>js/login/jquery-1.8.2.min.js"></script>
    <style type="text/css">
        * {
            padding: 0px;
            margin: 0px;
        }
        .month{
            width:130px;
            border-radius:5px;
            border:1px solid #95b8e7;
            height: 22px;
            text-align:center;
        }
        .btn {
            width:80px;
            border-radius:5px;
            border:1px solid #95b8e7;
            height: 22px;
            text-align:center;
        }
        .message {

        }
    </style>
</head>
<body>
<div id="main" style="width:100%;height: 100%; ">
    <div style="width: 70%;height: 80px;margin:0 auto;padding: 25px;">
        车牌号:
        <select id="carnumber" style="width:25%;border-radius:5px;border:1px solid #95b8e7;height: 25px; padding: 0 0 0 8%;">
            <c:forEach items="${groups}" var="item">
                <c:choose>
                    <c:when test="${item.carnumber!=''}">
                        <option value="${item.id}" name="${item.name}">${item.carnumber}</option>
                    </c:when>
                </c:choose>
            </c:forEach>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;选择月份：<input id="time" type="text" class = "month" onfocus="new WdatePicker({startDate:'%y-%M',dateFmt:'yyyy-MM',alwaysUseStartDate:true})"/>
        &nbsp;&nbsp;&nbsp;&nbsp;<button id="search" class="btn">查询</button>
    </div>
    <div id="e" style="width: 95%;height: 500px;">
    </div>
</div>
<script src="<%=basePath %>js/echarts/echarts-all.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            if ($.trim($("#carnumber").find("option:selected").text()) == '' || $.trim($("#carnumber").find("option:selected").text()) == null) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.message('很遗憾！居然没有一个车牌号！', {icon: 5});
                });
            } else {
                $("#time").val(getCurrentYearAndMonth());//初始化年月
                getMonthCarKMBySearchCondition('init');
            }

            $("#search").click(function(){
                // 1. 获取查询条件
                if ($.trim($("#carnumber").find("option:selected").text()) == '' || $.trim($("#carnumber").find("option:selected").text()) == null) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('请选择车牌号！', {icon: 5});
                    });
                } else if ($.trim($("#time").val()) == '' || $.trim($("#time").val()) == null) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('请选择月份！', {icon: 5});
                    });
                } else {
                    getMonthCarKMBySearchCondition('search');
                }
            });

        });


        function getMonthCarKMBySearchCondition(flag) {//默认情况下查询当月的
            var searchCondition = {
                "carnumber":  $.trim($("#carnumber").find("option:selected").text()),
                "time": (flag=='search') ? $.trim($("#time").val()) : getCurrentYearAndMonth()
            };
            var contextPath = getContextPath();
            var postUrl = contextPath + "/operationsmanage/getcarkmchangediaramdata.do";
            $.ajax({
                type: "POST",
                url: postUrl,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(searchCondition),
                async: false,
                success: function (data) {
                    var backData = JSON.parse(data);
                        drivers = backData.data.drivers;
                        applications = backData.data.applications;
                    if(typeof (backData.data.kms) == "undefined") {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.alert('没有任何记录！', {icon: 5});
                        });
                    }
                    var myChart = echarts.init(document.getElementById("e"));
                    var option = {
                        title: {
                            text: '车辆里程变化曲线图',
                            x: 'center',
                            y: 'top'
                        },
                        tooltip: {
                            trigger: 'axis',
                            formatter: function (params) {
                                var yearAndMonth = searchCondition.time;
                                var day = (params[0].name < 10 ? ('0'+ params[0].name) : params[0].name) ;
                                var km = ((typeof params[0].data == "undefined") || ( params[0].data == null) || ( params[0].data == ''))? "<lable style='color: red;'>空</lable>" : params[0].data;
                                var driver = (typeof drivers[params[0].name-1] == "undefined" || drivers[params[0].name-1] == null || drivers[params[0].name-1] == '')? "<lable style='color: red;'>空</lable>" : drivers[params[0].name-1];
                                var application = (typeof applications[params[0].name-1] == "undefined" || applications[params[0].name-1] == null || applications[params[0].name-1] == '')? "<lable style='color: red;'>空</lable>" : applications[params[0].name-1];
                                return '<strong>日期：</strong>' + '<i>'+yearAndMonth+'-' + day +'</i>' + '<br/>' +'<strong>车辆里程数：</strong>' + '<i>'+ km + '</i>'+ '  KM'+'<br/>' +'<strong>司机：</strong>' +'<i>'+driver+ '</i>'+'<br/>' +
                                    '<strong>用途：</strong>' + '<i>'+ application+'</i>';
                            }
                        },
                        //右上角工具条
                        toolbox: {
                            show: true,
                            feature: {
                                mark: {show: true},
                                dataView : {show: true, readOnly: false},
                                magicType: {show: true, type: ['line', 'bar']},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        xAxis: [
                            {
                                name: '日期',
                                type: 'category',
                                boundaryGap: false,
                                data: backData.data.days ,
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis: {
                            axisPointer: {label: {show: false}},
                            min: 'dataMin',
                            max: 'dataMax',
                            interval: 2,
                            position: 'left',
                            type: 'value',
                            name: '里程/KM',
                            minInterval: 1,
                            lineStyle: {
                                type: 'dashed'
                            },
                            width: 5,
                            splitNumber:10,
                            axisLabel : {
                                formatter: '{value} KM'
                            },
                            axisTick: false
                        },
                        series: [
                            {
                                type: 'line',
                                data : (typeof (backData.data.kms) == "undefined") ? [] : backData.data.kms ,
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最大值'},
                                        {type : 'min', name: '最小值'}
                                    ]
                                },
                                itemStyle: {
                                    normal: {
                                        color: '#ff4443',
                                        lineStyle: {
                                            color: '#3cff32'
                                        }
                                    }
                                },
                                markLine: {
                                    color: '#3cff32',
                                    data: [
                                        {type: 'average', name: '平均值'}
                                    ]
                                }
                            }
                        ]
                    };
                    myChart.clear();
                    myChart.setOption(option);}
            });
        }

        function    getCurrentYearAndMonth() {
            var currentDate = new Date();
            return (currentDate.getMonth().length == 2)?currentDate.getFullYear()+'-'+(currentDate.getMonth()+1):currentDate.getFullYear()+'-0'+(currentDate.getMonth()+1);
        }


        function getContextPath() {
            var pathName = document.location.pathname;
            var index = pathName.substr(1).indexOf("/");
            var result = pathName.substr(0, index + 1);
            return result;
        }
    </script>
</body>
</html>