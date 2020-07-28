<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增商品</title>
<script src="layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">
<style type="text/css">
     .centerbox{
          width:100%;
          height:150px;
      }
      td,th{
          text-align:center;
      }

</style>
</head>
<body>
<form action="ProductServlet?action=add" method="post">
<div class="centerbox">
<table class="layui-table" style="width:30%;height:50%;margin:auto auto; margin-top:50px" align="center">
<tr><td colspan="2">新增商品</td></tr>
<tr><td>商品名称</td><td><input class="layui-input" type="text" name="name"></td></tr>
<tr><td>商品价格</td><td><input class="layui-input" type="text" name="price"></td></tr>
<tr><td>商品库存</td><td><input class="layui-input" type="number" name="store"></td></tr>
<tr><td colspan="2"><button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">提交</button></td></tr>

</table>
</div>
</form>

</body>
</html>
