<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">
<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
<script type="text/javascript" src="<%=basePath %>js/adapterWindowJs/adapterwindow.js"></script>
<script src="../huangkai/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath %>js/login/tooltips.js"></script>
<script src="../huangkai/bootstrap/layer/layer.js"></script>

<body>
<table id="showForm" class="easyui-datagrid" title="故障统计" style="width:100%;height:420px">
</table>
<div id="tb" style="padding:5px;height:auto;display:none">
    <div>
        <input type="radio" name="abnormalstastic" value="month"><span>月度统计</span>
        <input type="radio" name="abnormalstastic" value="year"><span>年度统计</span><br/><br/>
        请选择日期：<input id="time"  type="text"  style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 20px;" onfocus="monthandyearchange()"/>
        <select id="origin"   style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 25px;" >
            <option value="-1">-请选择来源-</option>
            <c:forEach items="${goodsOrigins}" var="item">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
        <a href="#" id="search" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:10%">查询</a>
        <a href="#" id="downloadexcel" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%">下载excel</a>
    </div>
</div>
</body>
</html>

<script type="text/javascript">

    function format(value, row, index) {
        return new Date(row.testingtime).format("yyyy-MM-dd hh:mm:ss");
    }

    $('#showForm').datagrid({ //加载基本信息
        fitColumns: true,
        rownumbers: true,
        pagination: true,//显示分页
        pageSize: 20,//分页大小
        pageList: [20, 25, 30],//每页的个数
        toolbar: '#tb'
    });

    $("#showForm").datagrid({
        onClickRow: function (index, row) {
            alert(index);
            alert(row);
        }
    });

    $('#showForm').datagrid({ //加载表头
        columns: [[
            {field: 'id', title: 'id', width: 225, align: "center", hidden: "true"},
            {field: 'name', title: '物品名称', width: 225, align: "center"},
            {field: 'awaitdetectionamounts', title: '待检数量', width: 225, align: "center"},
            {field: 'processingamounts', title: '在处理数量', width: 225, align: "center"},
            {field: 'scrapedamounts', title: '报废数量', width: 225, align: "center"},
            {field: 'completedamounts', title: '维修完成数量', width: 225, align: "center"}
        ]]
    });

    function monthandyearchange() {
        var temp = document.getElementsByName("abnormalstastic");
        if (temp[0].checked && !temp[1].checked) {
            new WdatePicker({startDate: '%y-%M', dateFmt: 'yyyy-MM', alwaysUseStartDate: true})
        } else if (!temp[0].checked && temp[1].checked) {
            new WdatePicker({startDate: '%y-%M', dateFmt: 'yyyy', alwaysUseStartDate: true})
        } else {
            layui.use('layer');
            layer.alert('请先选择"月度统计"或者"年度统计"!', {icon: 5});
        }
    }

    $("#search").click(function () {
        var time = document.getElementById("time");
        var timevalue = $.trim(time.value);
        var flag;
        var origin = $('#origin option:selected').text();
        if (timevalue == null || timevalue == "") {
            layer.alert('请输入查询时间!', {icon: 5});
        } else {
            if (timevalue.length == 7) {
                flag = "month";
            } else if (timevalue.length == 4) {
                flag = "year";
            } else {
                layer.alert('时间格式不正确!', {icon: 5});
                return;
            }
            var url = getContextPath() + "/goodsabnoral/abnormalstasticsearch.do";
            $("#showForm").datagrid({
                "url": url,
                "method": 'POST',
                "queryParams": {
                    "time": timevalue,
                    "type": "search",
                    "origin":origin,
                    "flag": flag
                }
            });
        }
    });

    //下载Excel报表
    $("#downloadexcel").click(
        function () {
            var time = document.getElementById("time");
            var timevalue = $.trim(time.value);
            var flag;
            var origin = $('#origin option:selected').text();
            if (timevalue == null || timevalue == "") {
                layer.alert('请输入查询时间!', {icon: 5});
            } else {
                if (timevalue.length == 7) {
                    flag = "month";
                } else if (timevalue.length == 4) {
                    flag = "year";
                } else {
                    layer.alert('时间格式不正确!', {icon: 5});
                    return;
                }
                var options = $("#showForm").datagrid("getPager").data("pagination").options;
                var page = options.pageNumber;
                var rows = options.pageSize;
                var type = 'download';
                var url = getContextPath() + "/goodsabnoral/abnormalstasticsearch.do?type=" + type + "&&page=" + page + "&&rows=" + rows + "&&time=" + timevalue + "&&flag=" + flag + "&&origin="+origin ;
                window.open(url, "_self");//打开下载窗口
            }
        });

    function getContextPath() {
        var pathName = document.location.pathname;
        var index = pathName.substr(1).indexOf("/");
        var result = pathName.substr(0, index + 1);
        return result;
    }
</script>