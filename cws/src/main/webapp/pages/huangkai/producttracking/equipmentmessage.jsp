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
<script src="../../huangkai/layui/layui.js" charset="utf-8"></script>
<script src="<%=basePath %>js/login/jquery-1.8.2.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">

<script>
    function autoFocus(objId) {
        var id = "serialnumber" + (objId + 1);
        var next = document.getElementById(id);
        if (objId == 4) {
            if (document.getElementById("serialnumber" + objId).value.length >= 3 && next != undefined) {
                next.focus();
            }
        } else {
            if (document.getElementById("serialnumber" + objId).value.length >= 2 && next != undefined) {
                next.focus();
            }
        }
    }

    $(document).ready(function () {
        $(":input[id^='serialnumber']").bind('keyup', handleAddr);
        inputStatusControll();
        var main=document.getElementById("main");
        var search=document.getElementById("search");
        var senddiv=document.getElementById("senddiv");
        main.style.display = 'block';
        search.style.display = 'block';
        senddiv.style.display = 'block';
    });

    function handleAddr() {
        var current = this;
        var currvalue = current.value;
        var index = parseInt(current.id.substring(12));
        autoFocus(index);
    }

    function inputStatusControll() {
        var flag = $("#basicinformation").attr("name");
        if (flag == 'equipassemble') {//设备安装页面
            /* 组装时间（可选）、组装人员（可录）安装地点（可录，录入格式为填空形式，**省**市**区**镇****村）*/
            $("#assemblytime").removeAttr("readonly");
            $("#assemblytime").removeAttr("disabled");
            $("#assemblers").removeAttr("readonly");
            $("#assemblers").removeAttr("disabled");
            var installsites = document.getElementsByName("installsite");
            for (var i = 0; i < installsites.length; i++) {
                $(installsites[i]).removeAttr("readonly");
            }
        } else if (flag == 'equiptest') {//设备测试页面
            // 产品序列号（录入）测试人员（可录）测试时间（可选）
            $("#assemblytime").removeAttr("onfocus");
            $("#testers").removeAttr("readonly");
            $("#testers").removeAttr("disabled");
            $("#testtime").removeAttr("readonly");
            $("#testtime").removeAttr("disabled");
        } else if (flag == 'equipentry') {//设备入库页面
            /* A、显示内容：产品序列号（录入）、组装时间（显示）、
             组装人员（显示）、测试人员（显示）、测试时间（显示）、
             安装时间（灰色）、安装人员（灰色）、入库时间（可选）、
             出库时间（灰色）、领用部门（灰色）、领用人（灰色）、
             状态（显示）、安装地点（灰色，录入格式为填空形式，**省**市**区**镇****村）、
             控制ID（灰色）、归属项目（灰色）
             */
            //产品序列号（录入）、入库时间（可选）
            $("#assemblytime").removeAttr("onfocus");
            $("#testtime").removeAttr("onfocus");
            $("#entrytime").removeAttr("readonly");
            $("#entrytime").removeAttr("disabled");
        } else if (flag == 'equipdelivery') {//设备出库页面
            /*  出库时间（可选）、领用部门（可录）、领用人（可录）*/
            /* A、显示内容：产品序列号（录入）、组装时间（显示）、
             组装人员（显示）、测试人员（显示）、测试时间（显示）、
             安装时间（灰色）、安装人员（灰色）、入库时间（显示）、
             出库时间（可选）、领用部门（可录）、领用人（可录）、
             状态（显示）、安装地点（灰色，录入格式为填空形式，
             **省**市**区**镇****村）、控制ID（灰色）*/

            $("#assemblytime").removeAttr("onfocus");
            $("#testtime").removeAttr("onfocus");
            $("#entrytime").removeAttr("onfocus");
            $("#deliverytime").removeAttr("readonly");//出库时间可选
            $("#deliverytime").removeAttr("disabled");
            $("#recdepartment").removeAttr("readonly");//领用部门（可录）
            $("#recdepartment").removeAttr("disabled");
            $("#recpeople").removeAttr("readonly");//领用人（可录）
            $("#recpeople").removeAttr("disabled");
        } else if (flag == 'equipinstall') {//设备安装页面
            /*
             A、显示内容：产品序列号（录入）、组装时间（显示）、
             组装人员（显示）、测试人员（显示）、测试时间（显示）、
             安装时间（可选）、安装人员（可录）、入库时间（显示）、
             出库时间（显示）、领用部门（显示）、领用人（显示）、状态（显示）、
             安装地点（可录，录入格式为填空形式，**省**市**区**镇****村）、
             控制ID（可录）、归属项目（可录）*/
            $("#assemblytime").removeAttr("onfocus");
            $("#testtime").removeAttr("onfocus");
            $("#entrytime").removeAttr("onfocus");
            $("#deliverytime").removeAttr("onfocus");

            $("#installtime").removeAttr("readonly");//安装时间（可选）
            $("#installtime").removeAttr("disabled");
            $("#installpeople").removeAttr("readonly");//安装人员（可录）
            $("#installpeople").removeAttr("disabled");

            $("#controllID").removeAttr("readonly");//控制ID（可录）
            $("#controllID").removeAttr("disabled");

            $("#proaffiliation").removeAttr("readonly");//项目归属（可录）
            $("#proaffiliation").removeAttr("disabled");

            var installsites = document.getElementsByName("installsite");//安装地点可录
            for (var i = 0; i < installsites.length; i++) {
                $(installsites[i]).removeAttr("readonly");
                $(installsites[i]).removeAttr("disabled");
            }
        }
    }

    function search() {//查询数据库中现有点的物品信息，添加在页面上
        var serialnumber;
        var serialnumber1 = $.trim($("#serialnumber1").val());
        var serialnumber2 = $.trim($("#serialnumber2").val());
        var serialnumber3 = $.trim($("#serialnumber3").val());
        var serialnumber4 = $.trim($("#serialnumber4").val());
        var serialnumber5 = $.trim($("#serialnumber5").val());
        if (serialnumber1 != "" && serialnumber2 != "" && serialnumber3 != "" && serialnumber4 != "" && serialnumber5 != "") {
            serialnumber = serialnumber1 + '-' + serialnumber2 + '-' + serialnumber3 + '-' + serialnumber4 + '-' + serialnumber5;
            if (serialnumber.length != 16) {

                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.alert('<label style="color:red;font-style:italic">序列号</label>格式错误!', {icon: 5});
                });
                return;

            }
        } else {

            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请输入<label style="color:red;font-style:italic">序列号</label> !', {icon: 5});
            });
            return;

        }
        var contextPath = getContextPath();
        var url = contextPath + "/producttracking/" + serialnumber + "/getequipemntmessage.do";
        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            async: true,
            success: function (backdata) {

                if (backdata.key == "failure") {
                    layui.use('layer', function () {
                        layer.msg('没有相关信息记录', {icon: 5});
                    });

                } else if (backdata.key == "success") {
                    var status = backdata.data.currentstatus;
                    $("#currentstatus").html(status);
                    if (status == "待测试") {

                        $("#assemblytime").val(backdata.data.assemblytime);
                        $("#assemblers").val(backdata.data.assemblers);

                    } else if (status == "待入库") {

                        $("#assemblytime").val(backdata.data.assemblytime);
                        $("#assemblers").val(backdata.data.assemblers);
                        $("#testtime").val(backdata.data.testtime);
                        $("#testers").val(backdata.data.testers);

                    } else if (status == "存库") {

                        $("#assemblytime").val(backdata.data.assemblytime);
                        $("#assemblers").val(backdata.data.assemblers);
                        $("#testtime").val(backdata.data.testtime);
                        $("#testers").val(backdata.data.testers);
                        $("#entrytime").val(backdata.data.entrytime);

                    } else if (status == "待安装") {

                        $("#assemblytime").val(backdata.data.assemblytime);
                        $("#assemblers").val(backdata.data.assemblers);
                        $("#testtime").val(backdata.data.testtime);
                        $("#testers").val(backdata.data.testers);
                        $("#entrytime").val(backdata.data.entrytime);
                        $("#deliverytime").val(backdata.data.deliverytime);
                        $("#recdepartment").val(backdata.data.recdepartment);
                        $("#recpeople").val(backdata.data.recpeople);

                    } else if (status == "已安装") {

                        $("#assemblytime").val(backdata.data.assemblytime);
                        $("#assemblers").val(backdata.data.assemblers);
                        $("#testtime").val(backdata.data.testtime);
                        $("#testers").val(backdata.data.testers);
                        $("#entrytime").val(backdata.data.entrytime);
                        $("#deliverytime").val(backdata.data.deliverytime);
                        $("#recdepartment").val(backdata.data.recdepartment);
                        $("#recpeople").val(backdata.data.recpeople);
                        $("#installtime").val(backdata.data.installtime);
                        $("#installpeople").val(backdata.data.installpeople);
                        $("#controllID").val(backdata.data.controllid);
                        $("#proaffiliation").val(backdata.data.proaffiliation);
                        splitAdrress(backdata.data.installsite);
                    }
                } else {
                    layui.use('layer', function () {
                        layer.msg('没有相关信息记录', {icon: 5});
                    });
                }
            }
        });
    }

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


    function send() {
        // 提取页面中的所有信息 存储在数据库中
        var serialnumber;
        var serialnumber1 = $.trim($("#serialnumber1").val());
        var serialnumber2 = $.trim($("#serialnumber2").val());
        var serialnumber3 = $.trim($("#serialnumber3").val());
        var serialnumber4 = $.trim($("#serialnumber4").val());
        var serialnumber5 = $.trim($("#serialnumber5").val());
        if (serialnumber1 != "" && serialnumber2 != "" && serialnumber3 != "" && serialnumber4 != "" && serialnumber5 != "") {
            serialnumber = serialnumber1 + '-' + serialnumber2 + '-' + serialnumber3 + '-' + serialnumber4 + '-' + serialnumber5;
            if (serialnumber.length != 16) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.alert('<label style="color:red;font-style:italic">序列号</label>格式错误!', {icon: 5});
                });
                return;
            }
        } else {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请输入<label style="color:red;font-style:italic">序列号</label> !', {icon: 5});
            });
            return;
        }
        var assemblytime = $("#assemblytime").val();
        var assemblers = $("#assemblers").val();
        var testtime = $("#testtime").val();
        var testers = $("#testers").val();
        var entrytime = $("#entrytime").val();//入库时间
        var deliverytime = $("#deliverytime").val();//出库时间
        var recdepartment = $("#recdepartment").val();//领用部门
        var recpeople = $("#recpeople").val();//领用人员
        var installtime = $("#installtime").val();
        var installpeople = $("#installpeople").val();
        var controllID = $("#controllID").val();
        var proaffiliation = $("#proaffiliation").val();
        var installsite = "";
        var currentstatus;
        var installsites = document.getElementsByName("installsite");//安装地点可录
        if ($(installsites[0]).val() != "") {//拼接安装地址
            installsite = $(installsites[0]).val() + "省";
            if ($(installsites[1]).val() != "") {
                installsite = installsite + $(installsites[1]).val() + "市";
                if ($(installsites[2]).val() != "") {
                    installsite = installsite + $(installsites[1]).val() + "区";
                    if ($(installsites[3]).val() != "") {
                        installsite = installsite + $(installsites[3]).val() + "镇";
                        if ($(installsites[4]).val() != "") {
                            installsite = installsite + $(installsites[4]).val() + "村";
                        }
                    }
                }
            }
        }
        var flag = $("#basicinformation").attr("name");
        if (flag == 'equipassemble') {    //下面的这些判断是防止用户没有查询设备之前录入过的相关信息就录入信息提交
            currentstatus = "待测试";
            if (serialnumber == "" || assemblytime == "" || assemblers == "") {
                layui.use('layer', function () {
                    layer.alert('请输入设备<label style="color:red;font-style:italic">待测试</label>状态下的相关信息!', {icon: 5});
                });
                return;
            }
        } else if (flag == 'equiptest') {
            currentstatus = "待入库";
            if (serialnumber == "" || assemblytime == "" || assemblers == ""
                || (testtime == "") || (testers == "")
            ) {
                layui.use('layer', function () {
                    layer.alert('请录入设备<label style="color:red;font-style:italic">待入库</label>状态下的相关信息!', {icon: 5});
                });
                return;
            }
        } else if (flag == 'equipentry') {
            currentstatus = "存库";
            if (serialnumber == "" || assemblytime == "" || assemblers == ""
                || (testtime == "") || (testers == "") || (entrytime == "")
            ) {
                layui.use('layer', function () {
                    layer.alert('请输入完整后再提交!', {icon: 5});
                });
                return;
            }
        } else if (flag == 'equipdelivery') {
            currentstatus = "待安装";
            if (serialnumber == "" || assemblytime == "" || assemblers == ""
                || (testtime == "") || (testers == "") || (entrytime == "") || (deliverytime == "") || (recdepartment == "") || (recpeople == "")
            ) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.alert('请输入完整后再提交!', {icon: 5});
                });
                return;
            }
        } else if (flag == 'equipinstall') {
            currentstatus = "已安装";
            if (serialnumber == "" || assemblytime == "" || assemblers == ""
                || (testtime == "") || (testers == "") || (entrytime == "") || (deliverytime == "") || (recdepartment == "") || (recpeople == "")
                || (installtime == "") || (installpeople == "") || (controllID == "") || (proaffiliation == "") || (installsite == "")
            ) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.alert('请输入完整后再提交!', {icon: 5});
                });
                return;
            }
        }

        var equipmentmessage = {
            "serialnumber": serialnumber,
            "assemblytime": assemblytime,
            "assemblers": assemblers,
            "testtime": testtime,
            "testers": testers,
            "entrytime": entrytime,
            "recdepartment": recdepartment,
            "deliverytime": deliverytime,
            "recpeople": recpeople,
            "installtime": installtime,
            "installpeople": installpeople,
            "controllid": controllID,
            "proaffiliation": proaffiliation,
            "installsite": installsite,
            "currentstatus": currentstatus
        };
        var contextPath = getContextPath();
        var url = contextPath + "/producttracking/updateproductmessage.do";
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.alert('确认要提交？', {
                skin: 'layui-layer-molv' //样式类名 自定义样式
                , closeBtn: 1  // 是否显示关闭按钮
                , anim: 5 //动画类型
                , btn: ['确定', '取消'] //按钮
                , icon: 6  // icon
                , yes: function () {
                    $.ajax({
                        type: "POST",
                        url: url,
                        dataType: "json",
                        contentType: "application/json;charset=utf-8",
                        data: JSON.stringify(equipmentmessage),
                        async: true,
                        success: function (backdata) {
                            if (backdata.key == "failure") {
                                layui.use('layer', function () {
                                    layer.msg('设备信息插入<label style="color:red;font-style:italic">失败</label> !', {icon: 5});
                                });
                            } else if (backdata.key == "success") {
                                layui.use('layer', function () {
                                    layer.msg('设备信息插入<label style="color:green;font-style:italic">成功</label> !', {icon: 6});
                                });
                            }

                        }
                    });
                }
                , btn2: function () {
                    layer.msg('成功取消!', {icon: 6});
                }
            });


        });


    }

    function getContextPath() {
        var pathName = document.location.pathname;
        var index = pathName.substr(1).indexOf("/");
        var result = pathName.substr(0, index + 1);
        return result;
    }
</script>
</head>
<body>
<c:import url="/pages/huangkai/producttracking/floatwindow.jsp"></c:import>
</body>
</html>
