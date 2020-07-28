<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<title>修改销售信息</title>
<script src="layui/layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">
</head>
<style type="text/css">
#zt{
	margin:0 auto;
	width:600px;
	
}
</style>
<script type="text/javascript">
	function init(eid, pid) {
		e = document.getElementById("e");
		for (i = 0; i < e.options.length; ++i) {
			if (e.options[i].value == eid) {
				e.option[i].selected = ture;
			}
		}
		p = document.getElementById("p");
		for (i = 0; i < p.options.length; ++i) {
			if (p.options[i].value == pid) {
				p.option[i].selected = ture;
			}
		}
	}
</script>
<body>
<div id="zt">
		<div style="margin-left: auto; margin-right: auto; width: 100%;">
			<div class="layui-inline">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 20px;">
					<legend>
					<a href="ProductServlet"><i class="layui-icon layui-icon-return">返回</i></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改销售信息
					</legend>
				</fieldset>

				<form class="layui-form" action="SellServlet?action=update"
					method="post">

					<div class="layui-form-item">
						<label class="layui-form-label">销售单号：</label>
						<div class="layui-input-block">
							<input name="sid" lay-verify="" lay-serch typpe="text"
								class="layui-input" value="${sell.sid}" readonly="readonly">

						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">商品名称：</label>
						<div class="layui-input-block">
							<select name="pid" lay-verify="" lay-serch id="p">
								<c:forEach items="${products}" var="product">
									<option value="${product.pid}">${product.name}
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">销售员：</label>
						<div class="layui-input-block">
							<select name="eid" lay-verify="" lay-serch id="e">
								<c:forEach items="${employees}" var="employee">
									<option value="${employee.eid}">${employee.name}
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">销售数量：</label>
							<div class="layui-input-inline">
								<input type="number" name="amount" class="layui-input"
									value="${sell.amount}">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">销售日期：</label>
							<div class="layui-input-inline">
								<input type="tel" name="selldate" id="selldate"
									class="layui-input" value="${sell.selldate}">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-input-block">
							<button type="submit" class="layui-btn" lay-submit=""
								lay-filter="demo1">修改</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		</div>
</body>
<script>
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

						//日期
						laydate.render({
							elem : '#selldate'
						});
					});
</script>
</html>