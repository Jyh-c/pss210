<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改商品信息</title>
<script src="layui/layui.js"></script>
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
<form action="ProductServlet?action=update" method="post">
<div class="centerbox">
<table class="layui-table" style="width:30%;height:50%;margin:auto auto;margin-top:50px;" align="center">
	<tr><td colspan="2">修改商品信息</td></tr>
	<tr><td>商品编号：</td><td><input class="layui-input" type="number" name="pid" value="${p.pid}" readonly="readonly"/></td></tr>
	<tr><td>商品名称</td><td><input class="layui-input" type="text" name="name" value="${p.name}"/></td></tr>
	<tr><td>商品价格</td><td><input class="layui-input" type="text" name="price" value="${p.price}"/></td></tr>
	<tr><td>商品库存</td><td><input class="layui-input" type="number" name="store" value="${p.store}" /></td></tr>
	<tr><td colspan="2"><button class="layui-btn layui-btn-sm" type="submit">保存</button></td></tr>
</table>
</div>
</form>
</body>
</html>