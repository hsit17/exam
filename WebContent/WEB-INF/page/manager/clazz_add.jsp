<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<html lang="en">
  <head>
    <title>班级添加</title>
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
          <h1><i class="fa fa-edit"></i> 班级添加</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item">班级管理</li>
          <li class="breadcrumb-item"><a href="#">添加</a></li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="tile">
            <div class="row">
              <div class="col-lg-6">
                <form id="gradeForm" method="post">
                  <div class="form-group">
                    <label for="gradeSelect">所属年级</label>
                    <select class="form-control" id="gradeSelect" name="gradeId">
                      <option value="">--请选择--</option>
                      <c:forEach items="${gradeList }" var="g">
                      	<option value="${g.id }">${g.name }</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="majorSelect">所属专业</label>
                    <select class="form-control" id="majorSelect" name="majorId">
                      <option value="">--请选择--</option>
                      <c:forEach items="${majorList }" var="m">
                      	<option value="${m.id }">${m.name }</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="name">班级名称</label>
                    <input class="form-control" id="cno" name="cno" type="number" placeholder="请输入班级名称">
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
     		var gradeId = $("#gradeSelect").val();
     		if(gradeId==null || gradeId==''){
     			alert("年级不能为空");
     			$("#gradeSelect").focus();
     			return false;
     		}
     		var majorId = $("#majorSelect").val();
     		if(majorId==null || majorId==''){
     			alert("专业不能为空");
     			$("#majorSelect").focus();
     			return false;
     		}
     		var cno = $("#cno").val();
     		if(cno==null || cno==''){
     			alert("班级名称不能为空");
     			$("#cno").focus();
     			return false;
     		}
     		//发送ajax请求
     		$.ajax({
     			url:'${basePath}manager/DoClazzAddServlet',
     			type:'post',
     			data:{'gradeId':gradeId,'majorId':majorId,'cno':cno},
     			dataType:'text',
     			success:function(data){
     				if(data=='ok'){
     					window.location.href='${basePath}manager/clazz';
     				}else if(data=='exist'){
     					alert("该班级已添加");
     				}else{
     					alert("系统异常，请稍后再试");
     				}
     			}
     			
     		});
     	}
    </script>
  </body>
</html>