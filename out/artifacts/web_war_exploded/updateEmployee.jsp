<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>新增销售员</title>
<script src="layui/layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<center>
<div style="margin-left:auto;margin-right:auto;width:100%;">
   <div class="layui-inline">           
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>修改销售员</legend>
</fieldset>
 
<form class="layui-form" action="EmployeeServlet?action=update" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">编号：</label>
    <div class="layui-input-block">
      <input type="text" name="eid" lay-verify="name" autocomplete="off" readonly="readonly" value="${emp.eid}" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">姓名：</label>
    <div class="layui-input-block">
      <input type="text" name="name" lay-verify="name" autocomplete="off" value="${emp.name}"class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">性别：</label>
     <div class="layui-input-block">
      <input type="radio" name="sex" value="男" title="男" checked="">
      <input type="radio" name="sex" value="女" title="女">
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">出生日期：</label>
      <div class="layui-input-inline">
        <input type="text" name="birthday" id="birthday" lay-verify="date" value="${emp.birthday}" autocomplete="off" class="layui-input">
	</div>
    </div>
    
      <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">电话：</label>
      <div class="layui-input-inline">
        <input type="tel" name="phone" <%--lay-verify="required|phone"--%> value="${emp.phone}" autocomplete="off" class="layui-input">
      </div>
    </div>
 
    
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
</div>
</div>
</center>
</body>
<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //日期
  laydate.render({
    elem: '#birthday'
  });
});
</script>
</html>