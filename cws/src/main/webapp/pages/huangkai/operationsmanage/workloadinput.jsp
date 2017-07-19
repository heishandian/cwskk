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
<script src="<%=basePath %>js/login/jquery-1.8.2.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">
<style type="text/css">
    * {
        padding: 0px;
        margin: 0px;
    }

    #main {
        width: 90%;
        height: 90%;
        border: solid 1px black;
        margin: 50px auto;
    }

    .button {
        display: inline-block;
        outline: none;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        font: 16px/100% 'Microsoft yahei', Arial, Helvetica, sans-serif;
        padding: .5em 2em .55em;
        text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
        -webkit-border-radius: .5em;
        -moz-border-radius: .5em;
        border-radius: .5em;
        -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
        -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
        box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
    }

    .button:hover {
        text-decoration: none;
    }

    .button:active {
        position: relative;
        top: 1px;
    }

    .blue {
        color: #d9eef7;
        border: solid 1px #0076a3;
        background: #0095cd;
        background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));
        background: -moz-linear-gradient(top, #00adee, #0078a5);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00adee', endColorstr='#0078a5');
    }

    .blue:hover {
        background: #007ead;
        background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));
        background: -moz-linear-gradient(top, #0095cc, #00678e);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0095cc', endColorstr='#00678e');
    }

    .blue:active {
        color: #80bed6;
        background: -webkit-gradient(linear, left top, left bottom, from(#0078a5), to(#00adee));
        background: -moz-linear-gradient(top, #0078a5, #00adee);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0078a5', endColorstr='#00adee');
    }

    .secondtitle {
        width: 100%;
        height: 40px;
        border-bottom: solid 1px black;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
        background: #d0d0d0;
        font-weight: bold;
    }

    .column {
        width: 100%;
        height: 40px;
        border-bottom: solid 1px black;
        text-align: center;
        line-height: 40px;
    }

    .column1 {
        width: 49%;
        height: 100%;
        border-right: solid 1px black;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .column2 {
        width: 49%;
        height: 100%;
        border-right: none;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .column11 {
        width: 30%;
        height: 100%;
        border-right: solid 1px black;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .column12 {
        width: 69%;
        height: 100%;
        border-right: none;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .column121 {
        width: 49%;
        height: 100%;
        border-right: solid 1px black;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .column122 {
        width: 49%;
        height: 100%;
        border-right: none;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .inputstyle {
        width: 80%;
        height: 60%;
        text-align: center;
    }

    .memberName {
        width: 80%;
        height: 60%;
        text-align: center;
    }

    .memberPosition {
        width: 80%;
        height: 60%;
        text-align: center;
    }

    .memberTelephoneNumber {
        width: 80%;
        height: 60%;
        text-align: center;
    }

    select {
        padding: 0 5% 0 5%;
    / / 上 右 下 左
    }
</style>

<script>

    $(document).ready(function () {
        getGroupNameByAreaId();
        sendMessage();
    });

    function getGroupNameByAreaId() {
        $("#area").change(function () {
            $("#group").empty();
            var contextPath = getContextPath();
            var areaid = $('#area option:selected').val();
            var postUrl = contextPath + "/operationsmanage/" + areaid + "/getgroupnamebyareaid.do";
            $.ajax({
                type: "GET",
                url: postUrl,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                async: false,
                success: function (data) {
                    var dat = JSON.parse(data);
                    for (var i = 0; i < dat.data.length; i++)
                        $("#group").append("<option value='" + dat.data[i].id + "'>" + dat.data[i].name + "</option>");
                }
            });
        });
    }


    function sendMessage() {
        $("#send").click(function () {
                var workLoadData = {
                    "groupid": $.trim($("#group").find("option:selected").val()),
                    "time": ($.trim($("#time").val()) == '') ? 0 : $.trim($("#time").val()),
                    "inspection": ($.trim($("#inspection").val()) == '') ? 0 : $.trim($("#inspection").val()),
                    "equipmaintain": ($.trim($("#equipmaintain").val()) == '') ? 0 : $.trim($("#equipmaintain").val()),
                    "equipprotect": ($.trim($("#equipprotect").val()) == '') ? 0 : $.trim($("#equipprotect").val()),
                    "envirprotect": ($.trim($("#envirprotect").val()) == '') ? 0 : $.trim($("#envirprotect").val()),
                    "workassist": ($.trim($("#workassist").val()) == '') ? 0 : $.trim($("#workassist").val()),
                    "other": ($.trim($("#other").val()) == '') ? 0 : $.trim($("#other").val())
                };
                if ($.trim($("#group").find("option:selected").val()) == -1) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('请选择小组！', {icon: 5});
                    });
                    return;
                }
                var contextPath = getContextPath();
                var postUrl = contextPath + "/operationsmanage/insertgroupworkload.do";
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
                                url: postUrl,
                                dataType: "json",
                                contentType: "application/json;charset=utf-8",
                                data: JSON.stringify(workLoadData),
                                async: false,
                                success: function (data) {
                                    if (data == "success") {
                                     layer.msg('工作量录入成功!', {icon: 6});
                                     setTimeout(function () {
                                     window.location.href = getContextPath() + "/operationsmanage/gotoworkloadinputpage.do";
                                     }, 2000);
                                     } else {
                                     layer.msg('工作量录入失败，请重新录入!', {icon: 5});
                                     setTimeout(function () {
                                     window.location.href = getContextPath() + "/operationsmanage/gotoworkloadinputpage.do";
                                     }, 2000);
                                     }
                                }
                            });
                        }
                        , btn2: function () {
                            layer.msg('成功取消!', {icon: 6});
                            setTimeout(function () {
                                window.location.href = getContextPath() + "/operationsmanage/gotoworkloadinputpage.do";
                            }, 2000);

                        }
                    });
                });
            }
        )
        ;
    }
    function isGroupNameExisted() {
        //var currentGroupName  = new Object();
        var groupName = '第' + $.trim($("#groupName").val()) + '小组';
        var contextPath = getContextPath();
        var postUrl = contextPath + "/operationsmanage/isgroupnameexisted.do?" + "name=" + groupName;
        $.ajax({
            type: "GET",
            url: postUrl,
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            //data: JSON.stringify(currentGroupName),
            async: false,
            success: function (data) {
                if (data) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('该小组已经存在，请输入新的小组序号！', {icon: 5});
                    });
                }


            }
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
<div id="main">
    <div class="secondtitle">
        工作量录入
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                区县：
            </div>
            <div class="column12">
                <div class="column121">
                    <select id="area" class="inputstyle" style="height: 70%;">
                        <option value="-1">--请选择区县--</option>
                        <c:forEach items="${areas}" var="item">
                            <option value=${item.id}>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="column122">
                    小组：
                </div>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                <select id="group" class="inputstyle" style="height: 70%;">
                    <option value="-1">--请选择小组--</option>
                </select>
            </div>
            <div class="column12">
                <div class="column121">
                    工作时间：
                </div>
                <div class="column122">
                    <input id="time" class="memberTelephoneNumber"
                           onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
                </div>
            </div>
        </div>
    </div>
    <div class="secondtitle">
        工作类型|工作量(次)
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                设施巡检：
            </div>
            <div class="column12">
                <input id="inspection" class="inputstyle" style="width: 46%;"/>
                <lable>&nbsp;&nbsp;次</lable>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                设备维修：
            </div>
            <div class="column12">
                <input id="equipmaintain" class="inputstyle" style="width: 46%;"/>
                <lable>&nbsp;&nbsp;次</lable>
            </div>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                设备维护：
            </div>
            <div class="column12">
                <input id="equipprotect" class="inputstyle" style="width: 46%;"/>
                <lable>&nbsp;&nbsp;次</lable>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                环境维护：
            </div>
            <div class="column12">
                <input id="envirprotect" class="inputstyle" style="width: 46%;"/>
                <lable>&nbsp;&nbsp;次</lable>
            </div>
        </div>
    </div>
    <div class="column" style="border-bottom:none;">
        <div class="column1">
            <div class="column11">
                工作协助：
            </div>
            <div class="column12">
                <input id="workassist" class="inputstyle" style="width: 46%;"/>
                <lable>&nbsp;&nbsp;次</lable>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                其它：
            </div>
            <div class="column12">
                <input id="other" class="inputstyle" style="width: 46%;"/>
                <lable>&nbsp;&nbsp;次</lable>
            </div>
        </div>
    </div>
</div>
<div style="height:40px;margin:10px 95px 95px;">
    <button id="send" class="button blue" style="float:right;">提交</button>
</div>
</body>
</html>
