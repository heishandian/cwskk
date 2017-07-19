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
    <link rel="favicon" href="../huangkai/bootstrap/assets/images/favicon.png">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/bootstrap-theme.css" media="screen">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/style.css">
    <script src="../huangkai/bootstrap/layer/layer.js"></script>
    <script src="../huangkai/layui/layui.js" charset="utf-8"></script>
    <script src="<%=basePath %>js/login/jquery-1.8.2.min.js"></script>
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
        .statusinput {
            height:30px;
            width:200px;
            border-radius:5px;
            border:1px solid #95b8e7;
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
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.alert('请选择物品名称,并重新输入物品编码！', {icon: 5});
                });
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
                        var obj = JSON.parse(backdata);
                        if (obj.key == "error") {
                            $("#currrentstate").html("");
                            $("#abnormalanalysis").html("");
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.alert(obj.message, {icon: 5});
                            });
                      } else {
                            if (obj.key == "success") {
                                $("#currrentstate").html(obj.data[0].name);
                                $("#abnormalanalysis").attr("name",obj.data[1].id);
                                $("#abnormalanalysis").html(obj.data[1].goodsAbnormalAnalyse);
                            } else {
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.alert("其他错误，请联系管理员！", {icon: 5});
                                });
                            }
                      }
                    }
                });
            }
        }

        $(document).ready(function () {
            getselectdata();
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

        function sendStatusChangeMessage() {
            var goodsName = $("#goods").find("option:selected").text();
            var goodsNumber = $.trim($("#goodsnumberinput").val());
            var currentState = $.trim($("#currrentstate").html());
            var changeStatusId =  $('#statuschange').val();
            var changeStatus = $("#statuschange").find("option:selected").text();
            var  abnormalId = $("#abnormalanalysis").attr("name");
            var abnormalAnalysis = $.trim($("#abnormalanalysis").val());

                if (goodsName == "--请选择物品名称--") {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('请选择物品名称!', {icon: 5});
                    });
                } else if (goodsNumber == "") {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('请输入物品编码!', {icon: 5});
                    });
                    document.getElementById('goodsnumberinput').focus();
                } else if ( currentState == "" ) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('无效的物品编码！请重新输入!', {icon: 5});
                    });
                } else if (changeStatus == "--请选择物品状态类型--") {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('请选择需要更改的物品状态!', {icon: 5});
                    });
                } else if (currentState == changeStatus) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('新状态不能与老状态一样!', {icon: 5});
                    });
                } else if (abnormalAnalysis == "") {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('故障分析不能为空!', {icon: 5});
                    });
                } else {
                    var statuschangedata = {
                        "goodsName": goodsName,
                        "goodsNumber": goodsNumber,
                        "currentState": currentState,
                        "changeStatusId":changeStatusId,
                        "changeStatus": changeStatus,
                        "abnormalId":abnormalId,
                        "abnormalAnalysis": abnormalAnalysis
                    };
                    var contextPath = getContextPath();
                    var postUrl = contextPath + "/goodsabnoral/statuschange.do";
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('确认要提交？', {
                            skin: 'layui-layer-molv' //样式类名 自定义样式
                            ,closeBtn: 1  // 是否显示关闭按钮
                            ,anim: 5 //动画类型
                            ,btn: ['确定','取消'] //按钮
                            ,icon: 6  // icon
                            ,yes:function(){
                                $.ajax({
                                    type: "POST",
                                    url: postUrl,
                                    dataType: "json",
                                    contentType: "application/json;charset=utf-8",
                                    data: JSON.stringify(statuschangedata),
                                    async: false,
                                    success: function (data) {
                                        layer.msg('成功提交!', {icon: 6});
                                        setTimeout(function () { window.location.href=getContextPath()+"/goodsabnoral/gotostatuschangepage.do"; }, 2000);
                                    }
                                });
                            }
                            ,btn2:function(){
                                layer.msg('成功取消!', {icon: 5});
                                setTimeout(function () { window.location.href=getContextPath()+"/goodsabnoral/gotostatuschangepage.do"; }, 2000);
                            }});
                    });
                }
        }
    </script>
</head>
<body>

<div id="main">
    <h3 style=" text-align: center">状态变换</h3>
    <div style="width: 100%;border-bottom:2px dashed #dedede;"></div>
    <br/>
    物品名称：
    <select id="goods" class="statusinput" onchange="searchcurrentgoodstate();" >
        <option value="-1" >--请选择物品名称--</option>
    </select>

    <br/><br/>

    物品编码：
        <input id="goodsnumberinput" type="text" placeholder="请输入物品编码" class="statusinput" onblur="searchcurrentgoodstate();">
    <br/><br/>

    当前状态：  <label id="currrentstate" style="color:red"></label>
    <br/><br/>

    状态更改：
    <select id="statuschange"  class="statusinput" class="ui dropdown">
        <option value="-1">--请选择物品状态类型--</option>
    </select>
   <br/><br/>

    原因分析：<br/><br/>
    <form >
        <div class="control-group">
            <div class="controls">
                <textarea rows="10" name="" cols="100" class="form-control" id="abnormalanalysis" style="height: 200px;border:1px solid #95b8e7; border-radius:5px;"></textarea>
            </div>
        </div>
        <button id="submit_btn"  type="submit" class="btn btn-primary pull-right" style="margin: 10px" onclick="sendStatusChangeMessage();return false;">全部提交</button>
    </form>
</div>
</div>

</body>
</html>
