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
<%--<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>--%>
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
        margin: 30px auto;
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
        padding: 0 0 0 26%;
    }
</style>

<script>
    var flag = 4;
    function removeGroupMember() {
        if (flag > 4) {
            $("#main").children("div:last-child").remove();
            --flag;
        }
    }
    function addGroupMember() {
        if (flag < 10) {
            ++flag;
            var str = $("#main").children("div:last-child");
            str.after(
                '<div  class="column" style="border-top: 1px solid black;border-bottom: none;">' +
                '<div class="column1">' +
                '<div class="column11">' +
                '组员' + flag + '姓名：' +
                '</div>' +
                '<div class="column12">' +
                ' <div class="column121">' +
                '<input  class="memberName" />' +
                '</div>' +
                '<div class="column122">' +
                ' 岗位名称：' +
                '</div>' +
                '</div>' +
                '</div>' +
                ' <div class="column2">' +
                '<div class="column11">' +
                '<input  class="memberPosition" />' +
                '  </div>' +
                '<div class="column12">' +
                '<div class="column121">' +
                ' 联系电话：' +
                ' </div>' +
                '<div class="column122">' +
                '<input  class="memberTelephoneNumber" />' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'
            );
        }
    }


    function sendMessage() {
        var groupMessage = new Array();
        var memberName = document.getElementsByClassName("memberName");
        var memberPosition = document.getElementsByClassName("memberPosition");
        var memberTelephoneNumber = document.getElementsByClassName("memberTelephoneNumber");
        for (var i = 0; i < memberName.length; i++) { // 构造成员对象
            var workGroupMessage = new Object(); //后台不好接收复杂的json对象，只能丑陋的这样做了
            workGroupMessage.groupName = '第' + $.trim($("#groupName").val()) + '小组';
            workGroupMessage.areaId = $("#area").find("option:selected").val();
            workGroupMessage.groupLeader = $.trim($("#groupLeader").val());
            workGroupMessage.leaderTelephone = $.trim($("#leaderTelephone").val());
            workGroupMessage.carType = $.trim($("#carType").val());
            workGroupMessage.carNumber = $.trim($("#carNumber").val());
            workGroupMessage.region = $.trim($("#region").val());
            workGroupMessage.numberOfStation = $.trim($("#numberOfStation").val());

            workGroupMessage.memberName = $.trim(memberName[i].value);
            workGroupMessage.memberPosition = $.trim(memberPosition[i].value);
            workGroupMessage.memberTelephone = $.trim(memberTelephoneNumber[i].value);
            groupMessage.push(workGroupMessage);
        }
        if (($.trim($("#groupName").val()) == null) || ($.trim($("#groupName").val()) == '')) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('小组名称不能为空！', {icon: 5});
            });
            $("#groupName").focus();
            return;
        } else if ((($.trim($("#groupLeader").val())) == null) || (($.trim($("#groupLeader").val())) == '') ){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('组长姓名不能为空！', {icon: 5});
            });
            return;
        } else {
            var contextPath = getContextPath();
            var postUrl = contextPath + "/operationsmanage/insertworkgroupmessage.do";
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
                            data: JSON.stringify(groupMessage),
                            async: false,
                            success: function (data) {
                                 if (data == "success") {
                                 layer.msg('小组信息录入成功!', {icon: 6});
                                 setTimeout(function () { window.location.href=getContextPath()+"/operationsmanage/gotoworkgroupmanagerpage.do"; }, 2000);
                                 } else {
                                 layer.msg('信息录入失败，已回退，请重新录入!', {icon: 5});
                                 setTimeout(function () { window.location.href=getContextPath()+"/operationsmanage/gotoworkgroupmanagerpage.do"; }, 2000);
                                 }
                            }
                        });
                    }
                    , btn2: function () {

                        layer.msg('成功取消!', {icon: 6});
                        setTimeout(function () {
                            window.location.href=getContextPath()+"/operationsmanage/gotoworkgroupmanagerpage.do";
                        }, 2000);

                    }
                });
            });
        }

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
        小组信息
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                小组名称：
            </div>
            <div class="column12">
                <label>第&nbsp;&nbsp;</label><input id="groupName" class="inputstyle" style="width: 46%;"
                                                   onblur="isGroupNameExisted()"/>
                <lable>&nbsp;&nbsp;小组</lable>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                归属区县：
            </div>
            <div class="column12">
                <select id="area" class="inputstyle" style="height: 70%;">
                    <option value="-1">--请选择区县--</option>
                    <c:forEach items="${areas}" var="item">
                        <option value=${item.id}>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                组长姓名：
            </div>
            <div class="column12">
                <input id="groupLeader" class="inputstyle"/>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                联系电话：
            </div>
            <div class="column12">
                <input id="leaderTelephone" class="inputstyle"/>
            </div>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                车辆型号：
            </div>
            <div class="column12">
                <input id="carType" class="inputstyle"/>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                车牌号码：
            </div>
            <div class="column12">
                <input id="carNumber" class="inputstyle"/>
            </div>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                管辖区域：
            </div>
            <div class="column12">
                <input id="region" class="inputstyle"/>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                站点数目：
            </div>
            <div class="column12">
                <input id="numberOfStation" class="inputstyle"/>
            </div>
        </div>
    </div>
    <div class="secondtitle">
        组员信息 <img src="<%=basePath %>css/easyui/themes/icons/edit_add.png" alt="添加组员信息" onclick="addGroupMember();"/>
        <img src="<%=basePath %>css/easyui/themes/icons/edit_remove.png" alt="添加组员信息" onclick="removeGroupMember();"/>
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                组员1姓名：
            </div>
            <div class="column12">
                <div class="column121">
                    <input class="memberName"/>
                </div>
                <div class="column122">
                    岗位名称：
                </div>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                <input class="memberPosition"/>
            </div>
            <div class="column12">
                <div class="column121">
                    联系电话：
                </div>
                <div class="column122">
                    <input class="memberTelephoneNumber"/>
                </div>
            </div>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                组员2姓名：
            </div>
            <div class="column12">
                <div class="column121">
                    <input class="memberName"/>
                </div>
                <div class="column122">
                    岗位名称：
                </div>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                <input class="memberPosition"/>
            </div>
            <div class="column12">
                <div class="column121">
                    联系电话：
                </div>
                <div class="column122">
                    <input class="memberTelephoneNumber"/>
                </div>
            </div>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                组员3姓名：
            </div>
            <div class="column12">
                <div class="column121">
                    <input class="memberName"/>
                </div>
                <div class="column122">
                    岗位名称：
                </div>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                <input class="memberPosition"/>
            </div>
            <div class="column12">
                <div class="column121">
                    联系电话：
                </div>
                <div class="column122">
                    <input class="memberTelephoneNumber"/>
                </div>
            </div>
        </div>
    </div>
    <div class="column" style="border-bottom: none">
        <div class="column1">
            <div class="column11">
                组员4姓名：
            </div>
            <div class="column12">
                <div class="column121">
                    <input class="memberName"/>
                </div>
                <div class="column122">
                    岗位名称：
                </div>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                <input class="memberPosition"/>
            </div>
            <div class="column12">
                <div class="column121">
                    联系电话：
                </div>
                <div class="column122">
                    <input class="memberTelephoneNumber"/>
                </div>
            </div>
        </div>
    </div>


</div>
<div style="height:40px;margin:10px 95px 95px;">
    <button id="send" class="button blue" style="float:right;" onclick="sendMessage();">提交</button>
</div>
</body>
</html>
