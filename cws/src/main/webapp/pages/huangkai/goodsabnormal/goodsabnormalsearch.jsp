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
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<script src="../huangkai/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">
<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
<script type="text/javascript" src="<%=basePath %>js/adapterWindowJs/adapterwindow.js"></script>
<script>
    $(document).ready(function () {
        $('#win').window('close');
    });
    var easyuiPanelOnOpen = function (left, top) {
        var iframeWidth = $(this).parent().parent().width();

        var iframeHeight = $(this).parent().parent().height();

        var windowWidth = $(this).parent().width();
        var windowHeight = $(this).parent().height();

        var setWidth = (iframeWidth - windowWidth) / 2;
        var setHeight = (iframeHeight - windowHeight) / 2;
        $(this).parent().css({
            /* 修正面板位置 */
            left: setWidth,
            top: setHeight
        });

        if (iframeHeight < windowHeight) {
            $(this).parent().css({
                /* 修正面板位置 */
                left: setWidth,
                top: 0
            });
        }
        $(".win").hide();
    };
    $.fn.window.defaults.onOpen = easyuiPanelOnOpen;
</script>
<style type="text/css">
    .name{
        font-weight: bold;
    }
    .value{
        border-bottom: 1px solid #95b8e7;
    }
    .blank{
        margin-left: 50px;
        width:400px;
        display:inline;
    }
    .content{
        width: 400px;
        word-wrap: break-word;
        margin:20px;
        border-bottom: 1px solid #95b8e7;
    }


</style>
<body>
<table id="showForm" class="easyui-datagrid" title="故障溯源" style="width:100%;height:420px">

</table>
<div id="win" class="easyui-window" title="故障溯源详细">


    <div style="width: 80%;height: 60%;margin: 15px auto ;">
        <label class="name" >物品名称：</label><label id="goodsname" class="value"></label><div class="blank"></div>
        <label class="name">物品编码：</label><label  id="goodsnumber" class="value"></label><br/><br/>
        <label class="name" >物品来源：</label><label id="goodsorigin"></label><label class="blank"></label>
        <label class="name" >故障类型：</label><label id="goodsabnormaltype" class="value"></label><br/><br/>
        <label class="name" >故障时间：</label><label id="goodsabnormaltime" class="value"></label><br/><br/>
        <label class="name" >当前状态：</label><label id="goodscurrentstatus" class="value"></label><label class="blank"></label>
        <label class="name" >当前状态时间：</label><label id="goodscurrentstatustime" class="value"></label><br/><br/>
        <label class="name" >故障描述：</label> <div class="content" id="goodsabnormaldescription"></div>
        <label class="name" >原因分析：</label>
        <div class="content" id="goodsabnormalanalysis"></div>
    </div>

</div>
<div id="tb" style="padding:5px;height:auto;display:none">
    <div>
        物品名称:
        <select id="name" style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 25px;">
            <option value="-1">查询全部</option>
        </select>
        物品编码：
        <input id="number" type="text"
               style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 20px;"/><br/><br/>
        物品来源:
        <select id="origin" name="areaid" style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 25px;">
            <option value="-1">请选择物品来源</option>
        </select>
        状态:
        <select id="status" name="areaid" style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 25px;">
            <option value="-1">请选择物品状态</option>
        </select>
        起始时间：<input id="starttime" type="text"
                    style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 20px;"
                    onfocus="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
        终止时间：<input id="endtime" type="text"
                    style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 20px;"
                    onClick="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
        <a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
           style="width:10%">查找</a><br/>
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
            $('#win').window({
                width: 600,
                height: 400,
                shadow:false
            });
            $("#goodsname").html(row.name);
            $("#goodsnumber").html(row.number);
            $("#goodsorigin").html(row.origin);
            $("#goodsabnormaltype").html(row.abnormalType);
            $("#goodsabnormaltime").html(row.abnormalTime);
            $("#goodscurrentstatus").html(row.currentState);
            $("#goodscurrentstatustime").html(row.currentStateTime);
            $("#goodsabnormaldescription").html(row.description);
            $("#goodsabnormalanalysis").html(row.abnormalAnalyse);
        }
    });


    $('#showForm').datagrid({ //加载表头
        columns: [[
            {field: 'id', title: 'id', width: 225, align: "center", hidden: "true"},
            {field: 'name', title: '物品名称', width: 225, align: "center"},
            {field: 'number', title: '物品编码', width: 225, align: "center"},
            {field: 'origin', title: '来源', width: 225, align: "center"},
            {field: 'abnormalType', title: '故障类型', width: 225, align: "center"},
            {field: 'abnormalTime', title: '故障时间', width: 225, align: "center"},
            {field: 'description', title: '故障描述', width: 225, align: "center"},
            {field: 'abnormalAnalyse', title: '原因分析', width: 225, align: "center"},
            {field: 'currentState', title: '当前状态', width: 225, align: "center"},
            {field: 'currentStateTime', title: '当前状态时间', width: 225, align: "center"/*,formatter:format(value,row,index)*/
            }
        ]]
    });

    function getselectdata() {
        var contextPath = getContextPath();
        var getUrl = contextPath + "/goodsabnoral/getgoodsabnormalpageselectdata.do";
        $.ajax({
            type: "POST",
            url: getUrl,
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            async: false,
            success: function (data) {
                var goods = data.goods; //获取物品名称
                var goods_origins = data.goods_origins;
                var goods_status = data.goods_status;
                var goods_n = goods.length;
                var goods_origins_n = goods_origins.length;
                var goods_status_n = goods_status.length;

                for (var i = 0; i < goods_n; i++) {//遍历数组中的每一个元素
                    var id = i;
                    var name = goods[i];
                    $("#name").append("<option value='" + id + "'>" + name + "</option>");
                }

                for (var i = 0; i < goods_origins_n; i++) {//遍历数组中的每一个元素
                    var id = goods_origins[i].id;
                    var name = goods_origins[i].name;
                    $("#origin").append("<option value='" + id + "'>" + name + "</option>");
                }

                for (var i = 0; i < goods_status_n; i++) {//遍历数组中的每一个元素
                    var id = goods_status[i].id;
                    var name = goods_status[i].name;
                    $("#status").append("<option value='" + id + "'>" + name + "</option>");
                }
            }
        });
    }

    $(document).ready(function () {
        getselectdata();
    });


    $("#search").click(function () {
        var url = getContextPath() + "/goodsabnoral/abnormalsearch.do";
        $("#showForm").datagrid({
            "url": url,
            "method": 'POST',
            "queryParams": {
                "name": ($("#name").find("option:selected").text()) == "查询全部" ? "" : $("#name").find("option:selected").text(),
                "number": $("#number").val(),
                "origin": ($("#origin").find("option:selected").text()) == "请选择物品来源" ? "" : $("#origin").find("option:selected").text(),
                "status": ($("#status").find("option:selected").text()) == "请选择物品状态" ? "" : $("#status").find("option:selected").text(),
                "starttime": $("#starttime").val() == null ? "" : $("#starttime").val(),
                "endtime": $("#endtime").val() == null ? "" : $("#endtime").val()
            }
        });
    });

    function getContextPath() {
        var pathName = document.location.pathname;
        var index = pathName.substr(1).indexOf("/");
        var result = pathName.substr(0, index + 1);
        return result;
    }
</script>