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
<style type="text/css">
    * {
        padding: 0px;
        margin: 0px;
    }

    #main {
        width: 90%;
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
        border-radius:5px;
        border:1px solid #95b8e7;
    }

    .memberPosition {
        width: 80%;
        height: 60%;
        text-align: center;
        border-radius:5px;
        border:1px solid #95b8e7;
    }

    .memberTelephoneNumber {
        width: 80%;
        height: 60%;
        text-align: center;
        border-radius:5px;
        border:1px solid #95b8e7;
    }

    select {
        padding: 0 0 0 30%;
    }
</style>
</head>

<body>
<div id="main" style="display:none;overflow: hidden">
    <div class="secondtitle">
        车辆使用信息录入
    </div>
    <div class="column">
        <div style="width: 14.7%;border-right: 1px solid black;float: left">
            车牌号：
        </div>
        <div style="width: 80%;height:100%;float: left" >
            <select id="carnumber" class="inputstyle" style="height: 70%;">
                <option value="-1">请选择车牌号码</option>
                <c:forEach items="${groups}" var="item">
                    <c:choose>
                        <c:when test="${item.carnumber!=''}">
                            <option value="${item.id}" name="${item.name}">${item.carnumber}</option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                小组：
            </div>
            <div class="column12">
                <input id="group" class="inputstyle" style="width: 46%;" readonly="true"/>
                <lable style="color:white;">&nbsp;KM</lable>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                日期：
            </div>
            <div class="column12">
                <input id="time" class="inputstyle" style="width: 46%;" onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>

            </div>
        </div>
    </div>
    <div class="secondtitle">
        行驶里程录入
    </div>
    <div class="column">
        <div class="column1">
            <div class="column11">
                结束里程数：
            </div>
            <div class="column12">
                <input id="endkm" class="inputstyle" style="width: 46%;"/>
                <lable>&nbsp;km</lable>
            </div>
        </div>
        <div class="column2">
            <div class="column11">
                驾驶人员：
            </div>
            <div class="column12">
                <input id="driver" class="inputstyle" style="width: 46%;"/>
            </div>
        </div>
    </div>
    <div class="column">
        <div style="width: 14.7%;border-right: 1px solid black;float: left">
                车辆用途：
        </div>
        <div style="width: 80%;height:100%;float: left" >
            <input  id="application" class="inputstyle" />
        </div>
    </div>
    <div id="senddiv" style="height:40px;margin:10px 95px 95px;">
        <button id="newcarusemessage" class="button blue" style="float:right;">提交</button>
    </div>
</div>

</body>
</html>
