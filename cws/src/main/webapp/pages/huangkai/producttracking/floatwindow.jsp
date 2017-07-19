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

    .textfield {
        text-align: center;
        height: 60%;
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
    }

    .column1 {
        width: 19%;
        height: 100%;
        border-right: solid 1px black;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .column2 {
        width: 60%;
        height: 100%;
        border-right: solid 1px black;
        float: left;
        text-align: center;
        line-height: 40px;
    }

    .column21 {
        width: 49%;
        height: 40px;
        border-right: solid 1px black;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .column22 {
        width: 49%;
        height: 40px;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .column3 {
        width: 20%;
        height: 100%;
        float: left;
        text-align: center;
        height: 40px;
        line-height: 40px;
        font-family: '黑体';
    }

    .inputstyle {
        width: 80%;
        height: 60%;
        text-align: center;
    }

    .installsitelable {
        width: 19%;
        height: 100%;
        border-right: solid 1px black;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .installsitediv {
        width: 80%;
        height: 100%;
        float: left;
        text-align: center;
        line-height: 40px;
        font-family: '黑体';
    }

    .installsite {
        border-left: none;
        border-right: none;
        border-top: none;
        border-bottom: 1px solid #0F2543;
        outline: none;
        text-align: center;
    }
</style>
</head>

<body>
<div id="main" style="display:none;overflow: hidden;">
    <div id="basicinformation" class="secondtitle" name="${flag}">
        <div class="column1" style="border-right:none;"></div>
        <div class="column2" style="border-right:none;">
            <div style="width: 30%;float: left;display:block;color: #d0d0d0">
                占位符
            </div>
            <div style="width: 40%;float: left; text-align:center;">
                设备基本信息
            </div>
            <div id="currentstatus" style="width: 30%;float: left ;text-align:left;color: red">

            </div>
        </div>
        <div class="column3">

        </div>
    </div>
    <div class="column" id="firstcolumn">
        <div class="column1">
            序列号：
        </div>
        <div class="column2">
            <input class="textfield" type="text" id="serialnumber1"  size="2" maxlength="2" />
            -
            <input class="textfield" type="text" id="serialnumber2" size="2" maxlength="2" />
            -
            <input class="textfield" type="text" id="serialnumber3" size="2" maxlength="2"/>
            -
            <input class="textfield" type="text" id="serialnumber4" size="3" maxlength="3"/>
            -
            <input class="textfield" type="text" id="serialnumber5" size="3" maxlength="3"/>

        </div>
        <div class="column3" >
            <button  id="search" class="button" style="width: 60%;display: none;" onclick="search()">查询</button>
        </div>
    </div>
    <div class="secondtitle">
        组装测试信息
    </div>
    <div class="column">
        <div class="column1">
            组装时间：
        </div>
        <div class="column2">
            <div class="column21">
                <input id="assemblytime" class="inputstyle" readonly="true" disabled="true"
                       onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
            </div>
            <div class="column22">
                组装人员：
            </div>
        </div>
        <div class="column3">
            <input id="assemblers" class="inputstyle" readonly="true" disabled="true"/>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            测试时间：
        </div>
        <div class="column2">
            <div class="column21">
                <input id="testtime" class="inputstyle" readonly="true" disabled="true"
                       onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
            </div>
            <div class="column22">
                测试人员：
            </div>
        </div>
        <div class="column3">
            <input id="testers" class="inputstyle" readonly="true" disabled="true"/>
        </div>
    </div>
    <div class="secondtitle">
        设备出入库信息
    </div>
    <div class="column">
        <div class="column1">
            入库时间：
        </div>
        <div class="column2">
            <div class="column21">
                <input id="entrytime" class="inputstyle" readonly="true" disabled="true"
                       onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
            </div>
            <div class="column22">
                领用部门：
            </div>
        </div>
        <div class="column3">
            <input id="recdepartment" class="inputstyle" <%--readonly="true"--%> disabled="true"/>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            出库时间：
        </div>
        <div class="column2">
            <div class="column21">
                <input id="deliverytime" class="inputstyle" readonly="true" disabled="true"
                       onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
            </div>
            <div class="column22">
                领用人员：
            </div>
        </div>
        <div class="column3">
            <input id="recpeople" class="inputstyle" readonly="true" disabled="true"/>
        </div>
    </div>
    <div class="secondtitle">
        设备安装调试信息
    </div>
    <div class="column">
        <div class="column1">
            安装时间：
        </div>
        <div class="column2">
            <div class="column21">
                <input id="installtime" class="inputstyle" readonly="true" disabled="true"
                       onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
            </div>
            <div class="column22">
                安装人员：
            </div>
        </div>
        <div class="column3">
            <input id="installpeople" class="inputstyle" readonly="true" disabled="true"/>
        </div>
    </div>
    <div class="column">
        <div class="column1">
            控制ID：
        </div>
        <div class="column2">
            <div class="column21">
                <input id="controllID" class="inputstyle" readonly="true" disabled="true"/>
            </div>
            <div class="column22">
                项目归属：
            </div>
        </div>
        <div class="column3">
            <input id="proaffiliation" class="inputstyle" readonly="true" disabled="true"/>
        </div>
    </div>
    <div style="width:100%;height:40px;">
        <div class="installsitelable">
            安装地点：
        </div>
        <div class="installsitediv">

            <input type="text" name="installsite" class="installsite" size="5" maxlength="8" disabled="true"
                   readonly="true"/>
            省
            <input type="text" name="installsite" class="installsite" size="5" maxlength="8" disabled="true"
                   readonly="true"/>
            市
            <input type="text" name="installsite" class="installsite" size="5" maxlength="8" disabled="true"
                   readonly="true"/>
            区
            <input type="text" name="installsite" class="installsite" size="10" maxlength="10" disabled="true"
                   readonly="true"/>
            镇
            <input type="text" name="installsite" class="installsite" size="10" maxlength="10" disabled="true"
                   readonly="true"/>
            村
        </div>
    </div>
</div>
<div id="senddiv" style="height:40px;margin:10px 95px 95px;display:none;">
    <button id="send" class="button blue" style="float:right;" onclick="send();">提交</button>
</div>
</body>
</html>
