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
<style type="text/css">
    .startandendtime{
        width:130px;
        border-radius:5px;
        border:1px solid #95b8e7;
        height: 20px;
        text-align:center;
    }

</style>
<body>
<table id="showForm" class="easyui-datagrid" title="车辆管理" style="width:100%;height:420px">
</table>
<c:import url="/pages/huangkai/operationsmanage/carmeteradd.jsp"></c:import>
<div id="tb" style="padding:5px;height:auto;display:none">
    <div>
        车牌号:
        <select id="number" style="width:25%;border-radius:5px;border:1px solid #95b8e7;height: 25px; padding: 0 0 0 8%;">
            <option value="-1">查询全部</option>
            <c:forEach items="${groups}" var="item">
                <c:choose>
                    <c:when test="${item.carnumber!=''}">
                        <option value="${item.id}" name="${item.name}">${item.carnumber}</option>
                    </c:when>
                </c:choose>
            </c:forEach>
        </select>
        起始时间：<input id="starttime" type="text" class = "startandendtime"
                    onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
        终止时间：<input id="endtime" type="text" class = "startandendtime"
                    onClick="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
        <a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
           style="width:10%">查找</a>
        <a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
           style="width:10%" onclick="return false;">新增</a>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $('#carnumber').change(
        function () {
            $('#group').val($("#carnumber").find("option:selected").attr("name"));
            $('#group').attr("name", $("#carnumber").find("option:selected").val());
        }
    );

    $('#newcarusemessage').click(
        function () {
             carusemessagedata = {
                "gorupid": $.trim($("#group").attr("name")),
                "carnumber": $.trim($("#carnumber").find("option:selected").text()),
                "time": $.trim($("#time").val()),
                "endkm": new Number($.trim($("#endkm").val())),
                "driver": $.trim($("#driver").val()),
                "application": $.trim($("#application").val())
            };
            if ($.trim($("#group").attr("name")) == '' || $.trim($("#group").attr("name")) == null) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.alert('请选择车牌号！', {icon: 5});
                });
                return;
            }

            // 根据车牌号码和时间去查询数据库中是否存在记录，如果存在记录提醒用户是否需要更新，或者是放弃操作
            var contextPath = getContextPath();
            insertCarManageMessageURL = contextPath + "/operationsmanage/insertcarmanagemessage.do?"
            $.ajax({
                type: "POST",
                url: contextPath + "/operationsmanage/iscarmanagemessageexisted.do",
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(carusemessagedata),
                async: false,
                success: function (data) {
                   var backMessage = JSON.parse(data)
                    if (backMessage.key == "existed") {
                        layui.use('layer', function () {
                            layer.alert('车牌号：'+backMessage.data.carnumber+'<br/>'+
                                '时间：'+backMessage.data.time+'<br/>'+
                                '<lable style="color: red;">信息已存在！</lable>'+'<br/>'+
                                '结束里程数为：'+backMessage.data.endkm+'<br/>'+
                                '是否要更新已有数据？', {
                                skin: 'layui-layer-molv' //样式类名 自定义样式
                                , closeBtn: 1  // 是否显示关闭按钮
                                , anim: 5 //动画类型
                                , btn: ['更新', '取消'] //按钮
                                , icon: 6  // icon
                                , yes: function () {
                                    $.ajax({
                                        type: "POST",
                                        url: insertCarManageMessageURL+"flag=update",
                                        dataType: "json",
                                        contentType: "application/json;charset=utf-8",
                                        data: JSON.stringify(carusemessagedata),
                                        async: false,
                                        success: function (data) {
                                            if (data == "success") {
                                                layer.msg('车辆使用信息更新<lable style="color: red;font-style: italic">成功</lable>&nbsp;!', {icon: 6});
                                                /* setTimeout(function () {
                                                 $('#main').window('close');//关闭弹出窗口
                                                 }, 2000);*/
                                            } else {
                                                layer.msg('车辆使用信息更新<lable style="color: red;font-style: italic">失败</lable>!', {icon: 6});
                                            }
                                        }
                                    });
                                }
                                , btn2: function () {
                                    layer.msg('成功取消!', {icon: 6});
                                    setTimeout(function () {
                                        window.location.href = getContextPath() + "/operationsmanage/gotocarmanagepage.do";
                                    }, 2000);
                                }
                            });
                        });
                    } else {
                        ajaxSendData()
                    }
                }
            });

        }
    );

    function ajaxSendData() {
        layui.use('layer', function () {
            layer.alert('确认要提交？', {
                skin: 'layui-layer-molv' //样式类名 自定义样式
                , closeBtn: 1  // 是否显示关闭按钮
                , anim: 5 //动画类型
                , btn: ['确定', '取消'] //按钮
                , icon: 6  // icon
                , yes: function () {
                    $.ajax({
                        type: "POST",
                        url: insertCarManageMessageURL+"flag=insert",
                        dataType: "json",
                        contentType: "application/json;charset=utf-8",
                        data: JSON.stringify(carusemessagedata),
                        async: false,
                        success: function (data) {
                            if (data == "success") {
                                layer.msg('车辆使用信息录入<lable style="color: red;font-style: italic">成功</lable>&nbsp;!', {icon: 6});
                                /* setTimeout(function () {
                                 $('#main').window('close');//关闭弹出窗口
                                 }, 2000);*/
                            } else {
                                layer.msg('车辆使用信息录入<lable style="color: red;font-style: italic">失败</lable>!', {icon: 6});
                            }
                        }
                    });
                }
                , btn2: function () {
                    layer.msg('成功取消!', {icon: 6});
                    setTimeout(function () {
                        window.location.href = getContextPath() + "/operationsmanage/gotocarmanagepage.do";
                    }, 2000);
                }
            });
        });
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
                width: 600,
                height: 400,
                shadow: false
            });
        }
    });

    $('#showForm').datagrid({ //加载表头
        columns: [[
            {field: 'id', title: 'id', width: 225, align: "center", hidden: "true"},
            {field: 'time', title: '时间', width: 225, align: "center"},
            {field: 'carnumber', title: '车牌号', width: 225, align: "center"},
            {field: 'driver', title: '驾驶人', width: 225, align: "center"},
            {field: 'startkm', title: '起始里程数', width: 225, align: "center"},
            {field: 'endkm', title: '行驶里程数', width: 225, align: "center"},//特别注意这里的endkm,其实是数据库中的endkm-startkm,这里为了使用CarMesage对象方便
            {field: 'application', title: '用途', width: 225, align: "center"},
        ]]
    });


    $("#search").click(function () {
        var url = getContextPath() + "/operationsmanage/getcarmanagemessage.do";
        $("#showForm").datagrid({
            "url": url,
            "method": 'POST',
            "queryParams": {
                "carnumber": ($("#number").find("option:selected").text()) == "查询全部" ? "" : $("#number").find("option:selected").text(),
                "starttime": $("#starttime").val() == null ? "" : $("#starttime").val(),
                "endtime": $("#endtime").val() == null ? "" : $("#endtime").val()
            }
        });
    });

    $("#add").click(function () {
        var $win = $('#main').window({
            title: '新增车辆使用信息',
            width: 800,
            height: 340,
            top: 80,
            left: 120,
            shadow: true,
            modal: true,
            iconCls: 'icon-add',
            inline: false,
            fit: false,
            closed: false,
            shadow: false,
            minimizable: true,
            maximizable: true,
            collapsible: true
        });
        $win.window('open');
        var main = document.getElementById("main");
        main.style.display = 'block';
    });

    function getContextPath() {
        var pathName = document.location.pathname;
        var index = pathName.substr(1).indexOf("/");
        var result = pathName.substr(0, index + 1);
        return result;
    }
</script>