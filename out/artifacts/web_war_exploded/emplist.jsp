<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>销售员信息管理</title>
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
		window.location.href = "EmployeeServlet?action=changePageSize&&pageSize="
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
			window.location.href = "EmployeeServlet?action=deleteAll&&checkId="
					+ checkId;
		}
	}
	function changeLikeEmployee(likeEmployeeKey) {
		$.post("EmployeeServlet", {
			action : 'changeLikeEmployee',
			likeEmployeeKey : likeEmployeeKey
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
<body onload="initPageSize(${pageBeanEmployee.pageSize})">
<ul class="layui-nav" lay-filter="">
	<li class="layui-nav-item layui-this"><a href="EmployeeServlet">销售员管理</a></li>
	<li class="layui-nav-item "><a href="ProductServlet">商品管理</a></li>
	<li class="layui-nav-item"><a href="SellServlet">销售单管理</a></li>
</ul>
<script>
	layui.use('element',function(){
		var element = layui.element;
	});
</script>
<div align="center">
	<font size="5">销售员信息管理</font>
	<div id="addpro">
		<p>
			<input id="key" type="search" value="${likeEmployeeKey}" onblur="changeLikeEmployee(this.value)">
			<a href="EmployeeServlet?action=findByPage&curPage=${pageBeanEmployee.curPage}">
			<button class="layui-btn layui-btn-xs layui-btn-radius ">
			搜索</button>
			</a>
			<a href="addEmployee.jsp"><button class="layui-btn layui-btn-xs layui-btn-radius ">
			新增</button></a>
		</p>
	</div>
<form action="" method="post">
	<table class="layui-table">
		<thead>
		<tr>
		<th><input type="checkbox" onclick="checkAll(this.checked)">全选</th>
		<th>销售员编号</th>
		<th>名称</th>
		<th>性别</th>
		<th>出生日期</th>
		<th>电话</th>
		<th>操作</th>
		</tr>
		</thead>
		<c:forEach items="${empList}" var="emp">
		<tr>
			<td><input type="checkbox" name="info" value="${emp.eid}"></td>
			<td>${emp.eid}</td>
			<td>${emp.name}</td>
			<td>${emp.sex}</td>
			<td>${emp.birthday}</td>
			<td>${emp.phone}</td>
			<td><a href="EmployeeServlet?action=queryByID&id=${emp.eid}">编辑</a>&nbsp;&nbsp;
			<a href="EmployeeServlet?action=delete&id=${emp.eid}" onclick="confirm('是否确定删除?')">删除</a></td>
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
第${pageBeanEmployee.curPage}页&nbsp;共${pageBeanEmployee.totalPage}页&nbsp;&nbsp;&nbsp;&nbsp;
<a href="EmployeeServlet?action=findByPage&curPage=1">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="EmployeeServlet?action=findByPage&curPage=${pageBeanEmployee.curPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="EmployeeServlet?action=findByPage&curPage=${pageBeanEmployee.curPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="EmployeeServlet?action=findByPage&curPage=${pageBeanEmployee.totalPage}">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;
</div>
</body>
</html>