<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<html lang="en">
  <head>
    <title>修改密码</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${basePath }css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
  <body class="app sidebar-mini rtl">
    <main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-edit"></i> 修改密码</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#">修改密码</a></li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="tile">
            <div class="row">
              <div class="col-lg-6">
                <form id="gradeForm" method="post">
                  <div class="form-group">
                    <label for="name">原密码</label>
                    <input class="form-control" id="oldPwd" name="oldPwd" type="text" placeholder="请输入原密码">
                  </div>
                  <div class="form-group">
                    <label for="name">新密码</label>
                    <input class="form-control" id="newPwd" name="newPwd" type="text" placeholder="请输入新密码">
                  </div>
                  <div class="form-group">
                    <label for="name">再次输入密码</label>
                    <input class="form-control" id="rePwd" name="rePwd" type="text" placeholder="请再次输入密码">
                  </div>
                </form>
              </div>
            </div>
            <div class="tile-footer">
              <button class="btn btn-primary" type="button" onclick="toSubmit()">提交</button>
            </div>
          </div>
        </div>
      </div>
    </main>
    <!-- Essential javascripts for application to work-->
    <script src="${basePath }js/jquery-3.2.1.min.js"></script>
    <script src="${basePath }js/popper.min.js"></script>
    <script src="${basePath }js/bootstrap.min.js"></script>
    <script src="${basePath }js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script type="text/javascript">
     	function toSubmit(){
     		var oldPwd = $("#oldPwd").val();
     		if(oldPwd==null || oldPwd==''){
     			alert("原密码不能为空");
     			$("#oldPwd").focus();
     			return false;
     		}
     		var newPwd = $("#newPwd").val();
     		if(newPwd==null || newPwd==''){
     			alert("新密码不能为空");
     			$("#newPwd").focus();
     			return false;
     		}
     		var rePwd = $("#rePwd").val();
     		if(rePwd!=newPwd){
     			alert("两次输入的密码不一致");
     			$("#rePwd").focus();
     			return false;
     		}
     		//发送ajax请求
     		$.ajax({
     			url:'${basePath}DoEditPasswordServlet',
     			type:'post',
     			data:{'oldPwd':oldPwd,'newPwd':newPwd,'rePwd':rePwd},
     			dataType:'text',
     			success:function(data){
     				if(data=='ok'){
     					//注销
     					alert("修改成功");
     					parent.window.location.href='${basePath}logout';
     				}else if(data=='wrong'){
     					alert("原密码错误");
     				}else{
     					alert("系统异常，请稍后再试");
     				}
     			}
     			
     		});
     	}
    </script>
  </body>
</html>