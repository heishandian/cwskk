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
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>goods_abnormal page</title>
    <link rel="stylesheet" type="text/css" href="../huangkai/semantic/dist/semantic.css">
    <script src="../huangkai/semantic/assets/library/jquery.min.js"></script>
    <script src="../huangkai/semantic/dist/semantic.js"></script>
    <script src="../huangkai/bootstrap/layer/layer.js"></script>
    <link rel="favicon" href="../huangkai/bootstrap/assets/images/favicon.png">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/bootstrap-theme.css" media="screen">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/style.css">

    <style type="text/css">
        #main {
            height: 780px;
            margin: 20px 100px;
        }
        body{
            overflow:scroll;
            overflow-x:hidden;
            overflow-y:hidden;
        }
    </style>
    <script>

        function getContextPath() {
            var pathName = document.location.pathname;
            var index = pathName.substr(1).indexOf("/");
            var result = pathName.substr(0, index + 1);
            return result;
        }

        function searchcurrentgoodstate() {
            var goodsName = $("#goods").find("option:selected").text();
            var goodsNumber = $.trim($("#goodsnumberinput").val());
            if (goodsName == null || goodsName == "--请选择物品名称--") {
                alert("请选择物品名称,并重新输入物品编码！");
                return;
            } else if ( goodsNumber == null || goodsNumber == "") {
                return  ;
            } else {
                var goodsNameAndNumber = {"name":goodsName,"number":goodsNumber };
                var contextPath = getContextPath();
                var url = contextPath + "/goodsabnoral/getgoodscurrentstatus.do";
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify(goodsNameAndNumber),
                    async: true,
                    success: function (backdata) {
                        if (backdata.key == "error") {
                            $("#currrentstate").html("");
                            $("#abnormalanalysis").html("");
                            alert(backdata.message);
                      } else {
                            var obj = JSON.parse(backdata);
                            if (obj.key == "success") {
                                $("#currrentstate").html(obj.data[0].name);
                                $("#abnormalanalysis").html(obj.data[1].goodsAbnormalAnalyse);
                          } else {
                              alert("其他错误，请联系管理员！");
                          }
                      }
                    }
                });
            }
        }




        $(document).ready(function () {
            $('.ui.dropdown')
                .dropdown({
                    on: 'click'
                })
            ;

            getselectdata();

            $("#submit_btn").on('click', function () {
                sendAbnormalMessage();
            });


        });






        function getselectdata() {
            var contextPath = getContextPath();
            var getUrl = contextPath + "/goodsabnoral/getstatuschangepageselectdata.do";
            $.ajax({
                type: "POST",
                url: getUrl,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                async: true,
                success: function (data) {
                    //如果拿到的是
                    var goods = data.goods;
                    var goods_status = data.goods_status;
                    var goods_n = goods.length;
                    var goods_status_n = goods_status.length;

                    for (var i = 0; i < goods_n; i++) {
                        var id = i;
                        var name = goods[i];
                        $("#goods").append("<option value='" + id + "'>" + name + "</option>");
                    }

                    for (var i = 0; i < goods_status_n; i++) {
                        var id = goods_status[i].id;
                        var name = goods_status[i].name;
                        $("#statuschange").append("<option value='" + id + "'>" + name + "</option>");
                    }
                }
            });
        }

        function sendAbnormalMessage() {
            //1.先从选择框中获取数据，如果输入框中也输入了数据，那么以输入框为准
            var goodsId = 0, goodsName, goodsNumber, goodsOrigin, goodsOriginId = 0, abnormalType, abnormalTypeId = 0, abnormalTime, abnormalDescription;
            abnormalTime = new Date();
            var goodsNameSelect = $("#goods").find("option:selected").text();  //物品名称-select
            var goodsNameInput = $.trim($("#goodsinput").val());   //物品名称-input
            var goodsNumberInput = $.trim($("#goodsnumberinput").val());   //物品编码
            var goodsOriginSelect = $("#goodsorigin").find("option:selected").text(); //物品来源-select
            var goodsOriginInput = $.trim($("#goodsorigininput").val());   //物品来源-input
            var abnormalTypeSelect = $("#abnormaltype").find("option:selected").text();  //故障类型-select
            var abnormalTypeInput = $.trim($("#abnormaltypeinput").val());   //故障类型-input
            abnormalDescription = $.trim($("#abnormaldescription").val());
            if (goodsNameInput != "") {
                goodsName = goodsNameInput;
            } else if (goodsNameSelect != "--请选择物品名称--") {
                goodsId = $.trim($("#goods").val());
                goodsName = goodsNameSelect;
            } else {
                alert("请选择或输入物品名称！");
                $('#goodsinput').focus();
                return;
            }

            if (goodsNumberInput == "") {
                alert("请输入物品编码！");
                $('#goodsnumberinput').focus();
                return;
            } else {
                goodsNumber = goodsNumberInput;
            }

            if (goodsOriginInput != "") {
                goodsOrigin = goodsOriginInput;
            } else if (goodsOriginSelect != "--请选择物品来源--") {
                goodsOriginId = $.trim($("#goodsorigin").val());
                goodsOrigin = goodsOriginSelect;
            } else {
                alert("请选择或输入物品来源！");
                return;
            }

            if (abnormalTypeInput != "") {
                abnormalType = abnormalTypeInput;
            } else if (abnormalTypeSelect != "--请选择故障类型--") {
                abnormalTypeId = $.trim($("#abnormaltype").val());
                abnormalType = abnormalTypeSelect;
            } else {
                alert("请输入故障类型");
                return;
            }

            if (abnormaldescription == "") {
                alert("请输入故障描述！");
                $('#abnormaldescription').focus();
                return;
            }


            var goodsAbnormalData = {
                "goodsId": goodsId,
                "goodsName": goodsName,
                "goodsNumber": goodsNumber,
                "goodsOriginId": goodsOriginId,
                "goodsOrigin": goodsOrigin,
                "abnormalTypeId": abnormalTypeId,
                "abnormalType": abnormalType,
                "abnormalDescription": abnormalDescription,
                "abnormalTime": abnormalTime
            };
            var contextPath = getContextPath();
            var postUrl = contextPath + "/goodsabnoral/insertgoodsabnormal.do";

            $.ajax({
                type: "POST",
                url: postUrl,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(goodsAbnormalData),
                async: false,
                success: function (data) {

                }

            });

        }
    </script>
</head>
<body>

<div id="main">
    <h3 style="text-align: center">状态变换</h3>
    <div class="ui divider"></div>
    <br/>
    物品名称：
    <select id="goods" class="ui dropdown" onchange="searchcurrentgoodstate();" >
        <option value="">--请选择物品名称--</option>
        <%--<option value="1">Male</option>
        <option value="0">Female</option>--%>
    </select>

    <br/><br/>

    物品编码：
    <div class="ui input" data-tooltip="请输入物品编码">
        <input id="goodsnumberinput" type="text" placeholder="请输入物品编码" style="width: 195px" onblur="searchcurrentgoodstate();">
    </div>
    <br/><br/>

    当前状态：  <label id="currrentstate" style="color:red"></label>

    <br/><br/>

    状态更改：
    <select id="statuschange" class="ui dropdown">
        <option value="">--请选择物品状态类型--</option>
    </select>

   <br/><br/>
    故障分析：<br/><br/>
    <form >
        <div class="control-group">
            <div class="controls">
                <textarea rows="10" cols="100" class="form-control" id="abnormalanalysis" style="height: 200px"></textarea>
            </div>
        </div>
        <button id="submit_btn" type="submit" class="btn btn-primary pull-right" style="margin: 10px">全部提交</button>
    </form>
</div>
</div>


</body>
</html>
