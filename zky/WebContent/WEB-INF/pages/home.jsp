<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
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
    <style type="text/css">
      .main{
      height:550px;
      width:800px;
      margin: 150px auto ;
      border:1px solid green;
      border-radius:5px;
      }
      .form{
      height:300px;
      width:250px;
      margin: 150px auto ;
      }
      .submit{
      float:right;
      border-radius:5px;
      }
    </style>
	
    
</head>
<body>

<div id="main" class="main">
   success ... 
</div>

</body>
</html>
