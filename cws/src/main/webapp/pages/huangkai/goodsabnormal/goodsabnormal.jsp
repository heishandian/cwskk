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
    <script src="<%=basePath %>js/login/jquery-1.8.2.min.js"></script>
    <script src="../huangkai/bootstrap/layer/layer.js"></script>
    <link rel="favicon" href="../huangkai/bootstrap/assets/images/favicon.png">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/bootstrap-theme.css" media="screen">
    <link rel="stylesheet" href="../huangkai/bootstrap/assets/css/style.css">
    <script src="../huangkai/layui/layui.js" charset="utf-8"></script>
    <script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
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

        .goodsselect {
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

        $(document).ready(function () {
            getselectdata();
        });

        function isGoodsNameAndNumberUniqueness() {
            var goodsName;
            var goodsNameSelect = $("#goods").find("option:selected").text();  //物品名称-select
            var goodsNameInput = $.trim($("#goodsinput").val());   //物品名称-input
            var goodsNumberInput = $.trim($("#goodsnumberinput").val());   //物品编码
            if (goodsNameInput != "") {
                goodsName = goodsNameInput;
            } else if (goodsNameSelect == "--请选择物品名称--") {
                layer.alert('请选择或输入物品名称!', {icon: 5});
                $('#goodsinput').focus();
                return;
            } else {
                goodsName = goodsNameSelect;
            }

            if ( goodsNumberInput == null || goodsNumberInput == "") {
                layer.alert('请输入物品编码!', {icon: 5});
            } else {
                var goodsNameAndNumber = {"name":goodsName,"number":goodsNumberInput };
                var contextPath = getContextPath();
                var url = contextPath + "/goodsabnoral/isgoodsnameandnumberuniqueness.do";
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify(goodsNameAndNumber),
                    async: true,
                    success: function (backdata) {
                        if (backdata.key == "existence") {
                            layer.alert("当前物品名，物品编码下已经存在故障！请输入新的物品编码！！", {icon: 5});
                        }
                    }
                });
            }
        }

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
                    var goods = data.goods;
                    var goods_origins = data.goods_origins;
                    var goods_abnormal_types = data.goods_abnormal_types;
                    var goods_n = goods.length;
                    var goods_origins_n = goods_origins.length;
                    var goods_abnormal_types_n = goods_abnormal_types.length;

                    for (var i = 0; i < goods_n; i++) {//遍历数组中的每一个元素
                        var id = i;
                        var name = goods[i];
                        $("#goods").append("<option value='" + id + "'>" + name + "</option>");
                    }

                    for (var i = 0; i < goods_origins_n; i++) {//遍历数组中的每一个元素
                        var id = goods_origins[i].id;
                        var name = goods_origins[i].name;
                        $("#goodsorigin").append("<option value='" + id + "'>" + name + "</option>");
                    }

                    for (var i = 0; i < goods_abnormal_types_n; i++) {//遍历数组中的每一个元素
                        var id = goods_abnormal_types[i].id;
                        var name = goods_abnormal_types[i].name;
                        $("#abnormaltype").append("<option value='" + id + "'>" + name + "</option>");
                    }
                }
            });
        }

        function sendAbnormalMessage() {
            //1.先从选择框中获取数据，如果输入框中也输入了数据，那么以输入框为准
            var goodsId = 0, goodsName, goodsNumber, goodsOrigin, goodsOriginId = 0, abnormalType, abnormalTypeId = 0, abnormalDescription,abnormalAnalysis,abnormalTime;
            var goodsNameSelect = $("#goods").find("option:selected").text();  //物品名称-select
            var goodsNameInput = $.trim($("#goodsinput").val());   //物品名称-input
            var goodsNumberInput = $.trim($("#goodsnumberinput").val());   //物品编码
            var goodsOriginSelect = $("#goodsorigin").find("option:selected").text(); //物品来源-select
            var goodsOriginInput = $.trim($("#goodsorigininput").val());   //物品来源-input
            var abnormalTypeSelect = $("#abnormaltype").find("option:selected").text();  //故障类型-select
            var abnormalTypeInput = $.trim($("#abnormaltypeinput").val());   //故障类型-input
            abnormalTime = $.trim($("#abnormaltime").val());
            abnormalDescription = $.trim($("#abnormaldescription").val());
            abnormalAnalysis = $.trim($("#abnormalanalysis").val());

            if (goodsNameInput != "") {
                goodsName = goodsNameInput;
            } else if (goodsNameSelect == "--请选择物品名称--") {
                layer.alert('请选择或输入物品名称!', {icon: 5});
                $('#goodsinput').focus();
                return;
            } else {
                goodsId = $.trim($("#goods").val());
                goodsName = goodsNameSelect;
            }

            if (goodsNumberInput == "") {
                layer.alert('请输入物品编码！', {icon: 5});
                return;
            } else {
                goodsNumber = goodsNumberInput;
            }

            if (goodsOriginInput != "") {
                goodsOrigin = goodsOriginInput;
            } else if (goodsOriginSelect == "--请选择物品来源--") {
                layer.alert('请选择或输入物品来源!', {icon: 5});
                return;
            } else {
                goodsOriginId = $.trim($("#goodsorigin").val());
                goodsOrigin = goodsOriginSelect;
            }

            if (abnormalTypeInput != "") {
                abnormalType = abnormalTypeInput;
            } else if (abnormalTypeSelect == "--请选择故障类型--") {
                layer.alert('请选择或输入故障类型！', {icon: 5});
                return;
            } else {
                abnormalTypeId = $.trim($("#abnormaltype").val());
                abnormalType = abnormalTypeSelect;
            }

            if (abnormalTime == "") {
                layer.alert('请输入故障发生时间！', {icon: 5});
                return;
            }

            if (abnormalDescription == "") {
                layer.alert('请输入故障分析！', {icon: 5});
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
                "abnormalDescription":abnormalDescription,
                "abnormalAnalysis": abnormalAnalysis,
                "abnormalTime": abnormalTime
            };
            var contextPath = getContextPath();
            var postUrl = contextPath + "/goodsabnoral/insertgoodsabnormal.do";
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
                        data: JSON.stringify(goodsAbnormalData),
                        async: false,
                        success: function (data) {
                            if (data.key == "success") {
                                layer.msg('故障录入成功!', {icon: 6});
                                setTimeout(function () { window.location.href=getContextPath()+"/goodsabnoral/gotogoodsabnoralinputpage.do"; }, 2000);
                            } else {
                                layer.msg('故障录入失败，已回退，请成功录入!', {icon: 5});
                                setTimeout(function () { window.location.href=getContextPath()+"/goodsabnoral/gotogoodsabnoralinputpage.do"; }, 2000);
                            }
                        }
                    });
                }
                ,btn2:function(){
                    layer.msg('成功取消!', {icon: 5});
                    setTimeout(function () { window.location.href=getContextPath()+"/goodsabnoral/gotogoodsabnoralinputpage.do"; }, 2000);
                }});
        }
    </script>
</head>
<body>

<div id="main">
    <h3 style="text-align: center">故障录入</h3>
    <div style="width: 100%;border-bottom:2px dashed #dedede;"></div>
    <br/>
    物品名称：
    <select id="goods" class="goodsselect" >
        <option value="-1">--请选择物品名称--</option>
    </select>
    或者请输入：
        <input type="text" id="goodsinput" class="goodsselect" placeholder="请输入物品名称">
    <br/><br/>

    物品编码：
        <input id="goodsnumberinput" type="text"  class="goodsselect" placeholder="请输入物品编码" onblur="isGoodsNameAndNumberUniqueness();" >
    <br/><br/>

    物品来源：
    <select id="goodsorigin" class="goodsselect">
        <option value="-1">--请选择物品来源--</option>
    </select>
    或者请输入：
        <input id="goodsorigininput" type="text" class="goodsselect" placeholder="请输入物品来源">
    <br/><br/>

    故障类型：
    <select id="abnormaltype" class="goodsselect">
        <option value="-1">--请选择故障类型--</option>
    </select>
    或者请输入：
        <input type="text" id="abnormaltypeinput" class="goodsselect" placeholder="请添加故障类型">
    <br/><br/>

    故障发生时间：
        <input type="text" id="abnormaltime" class="goodsselect" placeholder="请输入故障发生时间" onfocus="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})">
    <br/><br/>

    <form >
        <div class="control-group" >
            <div class="controls" >
                故障描述：<br/><br/>
                <textarea rows="10" cols="100" class="form-control" id="abnormaldescription" style="height: 100px;border:1px solid #95b8e7; border-radius:5px;"></textarea>
                <br/>原因分析：<br/><br/>
                <textarea rows="10" cols="100" class="form-control" id="abnormalanalysis" style="height: 100px;border:1px solid #95b8e7; border-radius:5px;"></textarea>
            </div>
        </div>
        <button id="submit_btn" type="submit" class="btn btn-primary pull-right" onclick="sendAbnormalMessage();return false;" style="margin: 10px">全部提交</button>
    </form>

</div>
</div>

</body>
</html>
