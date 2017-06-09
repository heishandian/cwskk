<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    //设置无缓存
    response.addHeader("Progma","no-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires",0);
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Cache-Control", "must-revalidate");
%>

<!DOCTYPE html>
<html>
<head>
    <!-- Standard Meta -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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
        #myinput {
       color: blue;
        }
        #table {
            height:100%;margin: 0px 60px ;
        }
        .blank{width: 100%;height: 18px}
        #abnormal_description{
            width:500px;
            height:200px;
        }
        #abnormal_description{
            margin: 10px;
            width: 80%;
        }
    </style>
    <script>

        function getContextPath() {
            var pathName = document.location.pathname;
            var index = pathName.substr(1).indexOf("/");
            var result = pathName.substr(0,index+1);
            return result;
        }

        $(document).ready(function() {
               // alert(123);
                $('.ui.dropdown')
                    .dropdown({
                        on: 'click'
                    })
                ;

                getselectdata();
            });

        function getselectdata(){
            // var options=$("#aa option:selected");  //获取选中的项
           /* alert($("#goodsnameselect").val());
            alert($("#goodsnameselect option:selected").text());*/

            //$("#goodsnameselect").append("<option value='3'>123123123412</option>");
            //1.利用ajax获取select需要的数据
            var contextPath = getContextPath();
            var getUrl = contextPath+"/goods_abnoral/getselectdata.do";
            $.ajax({
                type : "POST",
                url : getUrl,
                dataType : "json",
                contentType : "application/json;charset=utf-8",
                async : false,
                success : function(data) {
                    //如果拿到的是
                    var goods = data.goods;
                    var goods_origins = data.goods_origins;
                    var goods_abnormal_types = data.goods_abnormal_types;

                    var goods_n = goods.length;
                    var goods_origins_n = goods_origins.length;
                    var goods_abnormal_types_n = goods_abnormal_types.length;

                    for(var i=0;i<goods_n;i++){//遍历数组中的每一个元素
                          var id = goods[i].id;
                          var name = goods[i].name;
                          var number = goods[i].number;
                            $("#goods").append("<option value='"+id+"'>"+name+"</option>");
                            $("#goodsnumber").append("<option value='"+id+"'>"+number+"</option>");
                        }
                    for(var i=0;i<goods_origins_n;i++){//遍历数组中的每一个元素
                        var id = goods_origins[i].id;
                        var name = goods_origins[i].name;
                        $("#goodsorigin").append("<option value='"+id+"'>"+name+"</option>");
                    }

                    for(var i=0;i<goods_abnormal_types_n;i++){//遍历数组中的每一个元素
                        var id = goods_abnormal_types[i].id;
                        var name = goods_abnormal_types[i].name;
                        $("#abnormaltype").append("<option value='"+id+"'>"+name+"</option>");
                    }

                        /* $.each(goodsName,function(key,item){
                         alert(key);
                         alert(item);
                          /!*  var key = item.sewageid;
                            var value= item.name;
                            $("#sewageid").append("<option value='"+key+"'>"+value+"</option>");*!/
                        });*/

                     /*   var key = ;
                        var value= data.goodsName[i];
                        $("#goodsnameselect").append("<option value='"+key+"'>"+value+"</option>");
                        $("#goodsnumberselect").append("<option value='"+key+"'>"+value+"</option>");*/

                    }


            //2.成功返回数据后将数据填充到select中
        });}
    </script>
</head>
<body>

<div id="main" >
    <h3 style="text-align: center">故障录入</h3>
    <div class="ui divider"></div>
    <br/>
    物品名称：
    <select  id="goods"  class="ui dropdown"  >
       <option value="">--请选择物品名称--</option>
        <%--<option value="1">Male</option>
        <option value="0">Female</option>--%>
    </select>
    或者请输入：
    <div class="ui input">
        <input type="text" id="goodsinput" placeholder="请输入物品名称">
    </div>
    <br/><br/>

    物品编码：
    <select  id="goodsnumber"  class="ui dropdown" >
       <option value="">--请选择物品编码--</option>

    </select>
    或者请输入：
    <div id="goodsnumberinput" class="ui input">
        <input type="text" placeholder="请输入物品编码">
    </div>
    <br/><br/>

    物品来源：
    <select  id="goodsorigin"  class="ui dropdown" >
        <option value="">--请选择物品来源--</option>
    </select>
    或者请输入：
    <div class="ui input">
        <input id="goodsorigininput" type="text" placeholder="请输入物品编码">
    </div>
    <br/><br/>

    故障类型：
    <select  id="abnormaltype"  class="ui dropdown" >
        <option value="">--请选择故障类型--</option>
    </select>
    或者请输入：
    <div class="ui input" id="abnormaltypeinput" >
        <input type="text" placeholder="请添加故障类型">
    </div>
    <br/><br/>
    故障描述：<br/><br/>
        <form name="sentMessage"  id="abnormaldescription" >
            <div class="control-group">
                <div class="controls">
                    <textarea rows="10" cols="100" class="form-control" id="message"></textarea>
                </div>
            </div>
            <button type="submit" class="btn btn-primary pull-right" onclick="test()" style="margin: 10px">全部提交</button>
        </form>
    </div>


</body>
</html>
