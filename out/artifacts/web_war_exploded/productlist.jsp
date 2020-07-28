<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品信息管理</title>
<script src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">
<script type="text/javascript">
	function initPageSize(pageSize) {

		pageSizeOption = document.getElementById("pageSizeOption");
		console.log(pageSizeOption);
		for (i = 0; i < pageSizeOption.options.length; ++i) {
			if (pageSizeOption.options[i].value == pageSize) {
				pageSizeOption.options[i].selected = true;
			}
		}
	}
	function changePageSize(pageSize) {
		window.location.href = "ProductServlet?action=changePageSize&&pageSize="
				+ pageSize;
	}
	//全选/全不选
	function checkAll(checked) {
		info = document.getElementsByName('info');
		if (checked) {
			for (var i = 0; i < info.length; i++) {
				info[i].checked = true;
			}
		} else {
			for (var i = 0; i < info.length; i++) {
				info[i].checked = false;
			}
		}
	}
	function deleteAll() {
		var checkId = [];
		var j = 0;
		info = document.getElementsByName('info');
		for (var i = 0; i < info.length; i++) {
			if (info[i].checked == true) {
				checkId[j] = info[i].value;
				j++;
			}
		}
		if (checkId.length == 0) {
			alert("未选中一个！");
			return;
		}
		if (confirm("确定删除吗？")){
			window.location.href = "ProductServlet?action=deleteAll&&checkId="
					+ checkId;
		}
	}
	function changeLikeProduct(likeProductKey) {
		$.post("ProductServlet", {
			action : 'changeLikeProduct',
			likeProductKey : likeProductKey
		})
	}
</script>
<style>
	table,table tr th,table tr td{
		border:1px;solid #000;
		text-algin:center;
	}
	table{
		width:750px;
		border-collapse:collapse;
	}
	#addpro{
		width:600px;
		height:50px;
	}
	#addpro p{
		padding-top:20px;
		padding-right:200px;
	}
	a {
		text-decoration:none;
	}
	button{
		width:100px;
	}
</style>
</head>
<body onload="initPageSize(${pageBeanProduct.pageSize})">
<ul class="layui-nav" lay-filter="">
	<li class="layui-nav-item"><a href="EmployeeServlet">销售员管理</a></li>
	<li class="layui-nav-item layui-this"><a href="ProductServlet">商品管理</a></li>
	<li class="layui-nav-item"><a href="SellServlet">销售单管理</a></li>
</ul>
<script>
	layui.use('element',function(){
		var element = layui.element;
	});
</script>
<div align="center">
	<font size="5">商品信息管理</font>
	<div id="addpro">
		<p>
			<input id="key" type="search" value="${likeProductKey}" onblur="changeLikeProduct(this.value)">
			<a href="ProductServlet?action=findByPage&curPage=${pageBeanProduct.curPage}">
			<button class="layui-btn layui-btn-xs layui-btn-radius ">
			搜索</button>
			</a>
			<a href="addProduct.jsp"><button class="layui-btn layui-btn-xs layui-btn-radius ">
			新增</button></a>
		</p>
	</div>
<form action="" method="post">
	<table class="layui-table">
		<thead>
		<tr>
		<th><input type="checkbox" onclick="checkAll(this.checked)">全选</th>
		<th>商品编号</th>
		<th>产品名称</th>
		<th>价格</th>
		<th>库存</th>
		<th>操作</th>
		</tr>
		</thead>
		<c:forEach items="${pList}" var="p">
		<tr>
			<td><input type="checkbox" name="info" value="${p.pid}"></td>
			<td>${p.pid}</td>
			<td>${p.name}</td>
			<td>${p.price}</td>
			<td>${p.store}</td>
			<td><a href="ProductServlet?action=queryByID&id=${p.pid}">编辑</a>&nbsp;&nbsp;
			<a href="ProductServlet?action=delete&id=${p.pid}" onclick="confirm('是否确定删除?')">删除</a></td>
		</tr>
		</c:forEach>
	</table>
</form>
<button class="layui-btn layui-btn-xs layui-btn-radius " onclick="deleteAll()">批量删除</button>
&nbsp;&nbsp;每页显示
<select id="pageSizeOption" onchange="changePageSize(this.value)">
<option value="5">5</option>
<option value="10">10</option>
<option value="20">20</option>
<option value="50">50</option>
<option value="100">100</option>
</select>条&nbsp;&nbsp;
第${pageBeanProduct.curPage}页&nbsp;共${pageBeanProduct.totalPage}页&nbsp;&nbsp;&nbsp;&nbsp;
<a href="ProductServlet?action=findByPage&curPage=1">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="ProductServlet?action=findByPage&curPage=${pageBeanProduct.curPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="ProductServlet?action=findByPage&curPage=${pageBeanProduct.curPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="ProductServlet?action=findByPage&curPage=${pageBeanProduct.totalPage}">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;
</div>
</body>
</html>