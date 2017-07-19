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
        $(":input[id^='serial']").bind('keyup', handleAddr);
        $('#main').window('close');
    });
    function autoFocus(objId) {
        var id = "serial" + (objId + 1);
        var next = document.getElementById(id);
        if (objId == 4) {
            if (document.getElementById("serial" + objId).value.length >= 3 && next != undefined) {
                next.focus();
            }
        } else {
            if (document.getElementById("serial" + objId).value.length >= 2 && next != undefined) {
                next.focus();
            }
        }
    }
    function handleAddr() {
        var current = this;
        var index = parseInt(current.id.substring(6));
        autoFocus(index);
    }
</script>
<style type="text/css">
    * {
        padding: 0px;
        margin: 0px;
    }
    .serialnumber {
        width: 45px;
        border-radius: 5px;
        border: 1px solid #95b8e7;
        height: 20px;
        text-align: center;
    }
</style>
<body>
<table id="showForm" class="easyui-datagrid" title="故障溯源" style="width:100%;height:420px">
</table>
<c:import url="/pages/huangkai/producttracking/floatwindow.jsp"></c:import>
<div id="tb" style="padding:5px;height:auto;display:none">
    <br/>
    <div style="float: left">
        产品序列号:&nbsp;&nbsp;&nbsp;<input class="serialnumber" type="text" id="serial1" size="2" maxlength="2"/>
        -
        <input class="serialnumber" type="text" id="serial2" size="2" maxlength="2"/>
        -
        <input class="serialnumber" type="text" id="serial3" size="2" maxlength="2"/>
        -
        <input class="serialnumber" type="text" id="serial4" size="3" maxlength="3"/>
        -
        <input class="serialnumber" type="text" id="serial5" size="3" maxlength="3"/>
    </div>

    <div style="float: left">
        &nbsp;&nbsp;&nbsp;设备状态:
        <select id="status" style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 25px;">
            <option value="-1">请选择设备状态</option>
            <c:forEach items="${equipmentsStatus}" var="item">
                <option >${item}</option>
            </c:forEach>
        </select>
    </div>
    <div style="word-wrap: break-word;float: left">
        &nbsp;&nbsp;领用部门:
        <select id="department"  style="width:130px;border-radius:5px;border:1px solid #95b8e7;height: 25px;">
            <option value="-1">请选择领用部门</option>
            <c:forEach items="${recdepartment}" var="item">
                <option >${item}</option>
            </c:forEach>
        </select>
    </div> &nbsp;
    <a href="#" id="search" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:10%">查询</a>
    <a href="#" id="downloadexcel" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:10%">下载excel</a>
    <br/><br/>
</div>
</body>
</html>

<script type="text/javascript">
    function splitAdrress(installsite) {
        var a = installsite;
        var strs = new Array(); //定义一数组
        var strs1 = new Array(); //定义一数组
        var regex;
        for (var i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    regex = "省";
                    break;
                case 1:
                    regex = "市";
                    break;
                case 2:
                    regex = "区";
                    break;
                case 3:
                    regex = "镇";
                    break;
                case 4:
                    regex = "村";
                    break;
                default:
                    break;
            }
            if (a != "" && a != null) {
                strs = a.split(regex); //字符分割
                strs1.push(strs[0]);
                a = strs[1];
            }
        }
        var installsites = document.getElementsByName("installsite");
        for (var j = 0; j < strs1.length; j++) {
            $(installsites[j]).val(strs1[j]);
        }
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
            $('#main').window({
                title: '产品溯源详细信息',
                width: 820,
                height: 525,
                top: 80,
                left: 120,
                shadow: true,
                modal: true,
                closed: false,
                shadow: false,
                minimizable: true,
                maximizable: true,
                collapsible: true
            });
            var main=document.getElementById("main");
            main.style.display = 'block';
            var numbers = new Array();
            numbers = row.serialnumber.split("-")
            for (var i=1;i<numbers.length+1;i++) {
                var temp ="#"+ "serialnumber"+i;
                $(temp).val(numbers[i-1]);
            }
            $("#currentstatus").html(row.currentstatus);
            $("#assemblytime").val(row.assemblytime);
            $("#assemblers").val(row.assemblers);
            $("#testtime").val(row.testtime);
            $("#testers").val(row.testers);
            $("#entrytime").val(row.entrytime);
            $("#deliverytime").val(row.deliverytime);
            $("#recdepartment").val(row.recdepartment);
            $("#recpeople").val(row.recpeople);
            $("#installtime").val(row.x);
            $("#installpeople").val(row.installpeople);
            $("#controllID").val(row.controllid);
            $("#proaffiliation").val(row.proaffiliation);
            splitAdrress(row.installsite);
        }
    });

    $('#showForm').datagrid({ //加载表头 、当前状态产品序列号、
        columns: [[
            {field: 'id', title: 'id', width: 225, align: "center", hidden: "true"},
            {field: 'serialnumber', title: '产品序列号', width: 225, align: "center"},
            {field: 'assemblytime',hidden: "true"},
            {field: 'assemblers',hidden: "true"},
            {field: 'testtime',hidden: "true"},
            {field: 'testers',hidden: "true"},
            {field: 'entrytime',hidden: "true"},
            {field: 'recdepartment',hidden: "true"},
            {field: 'deliverytime',hidden: "true"},
            {field: 'recpeople',hidden: "true"},
            {field: 'installtime',hidden: "true"},
            {field: 'installpeople',hidden: "true"},
            {field: 'controllid',hidden: "true"},
            {field: 'proaffiliation',hidden: "true"},
            {field: 'installsite',hidden: "true"},
            {field: 'currentstatus', title: '当前状态', width: 225, align: "center"},
        ]]
    });

    $("#search").click(function () {
        var serialnumber = "";
        var serial1 = $.trim($("#serial1").val());
        var serial2 = $.trim($("#serial2").val());
        var serial3 = $.trim($("#serial3").val());
        var serial4 = $.trim($("#serial4").val());
        var serial5 = $.trim($("#serial5").val());
        if (serial1 != "" && serial2 != "" && serial3 != "" && serial4 != "" && serial5 != "") {
            serialnumber = serial1 + '-' + serial2 + '-' + serial3 + '-' + serial4 + '-' + serial5;
            if (serialnumber.length != 16) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.alert('<label style="color:red;font-style:italic">序列号</label>格式错误!', {icon: 5});
                });
                return;
            }
        }
        var url = getContextPath() + "/producttracking/getallequipmentmessage.do";
        $("#showForm").datagrid({
            "url": url,
            "method": 'POST',
            "queryParams": {
                "serialnumber": serialnumber,//产品序列号
                "equipmentsstatus": ($("#status").find("option:selected").text()) == "请选择设备状态" ? "" : $("#status").find("option:selected").text(),
                "recdepartment": ($("#department").find("option:selected").text()) == "请选择领用部门" ? "" : $("#department").find("option:selected").text(),
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