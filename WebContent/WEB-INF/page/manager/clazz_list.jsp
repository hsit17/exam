<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<html lang="en">
  <head>
    <title>班级管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${basePath }css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  	<style type="text/css">
  		.my_del{
  			height:23px;
  			line-height:6px;
  			padding-top:3px;
  			padding-left:10px;
  			width:50px;
  		}
  	</style>
  </head>
  <body class="app sidebar-mini rtl">
    <main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-th-list"></i> 班级管理</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item active"><a href="#" >班级管理</a></li>
        </ul>
      </div>
      <div class="row">
      	<div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <form class="row" method="post" action="${basePath }manager/clazz">
                <div class="form-group col-md-3">
                  <label class="control-label">年级名称</label>
                  <input class="form-control" name="gradeName" type="text" value="${gradeName }" placeholder="请输入年级">
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label">专业名称</label>
                  <input class="form-control" name="majorName" type="text" value="${majorName }" placeholder="请输入专业">
                </div>
                <div class="form-group col-md-4 align-self-end">
                  <button class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>查询</button>
                  <button class="btn btn-primary" type="button" onclick="toAdd()"><i class="fa fa-fw fa-lg fa-plus-circle"></i>添加</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <h3 class="tile-title">班级列表</h3>
            <table class="table table-hover">
              <thead align="center">
                <tr>
                  <th>序号</th>
                  <th>年级</th>
                  <th>专业</th>
                  <th>班级</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody align="center">
              	<c:forEach items="${page.data }" var="clazz" varStatus="status">
              		<tr id="tr${clazz.id }">
	              		<td>${status.count }</td>
	              		<td>${clazz.gradeName }</td>
	              		<td>${clazz.majorName }</td>
	              		<td>${clazz.cno }</td>
	              		<td>
	              			<button class="btn btn-danger my_del" type="button" onclick="toDel('${clazz.id}')">删除</button>
	              		</td>
              		</tr>
              	</c:forEach>
              </tbody>
            </table>
            <p:page action="manager/clazz"/>
          </div>
        </div>
      </div>
    </main>
    <!-- Essential javascripts for application to work-->
    <script src="${basePath }js/jquery-3.2.1.min.js"></script>
    <script src="${basePath }js/popper.min.js"></script>
    <script src="${basePath }js/bootstrap.min.js"></script>
    <script src="${basePath }js/main.js"></script>
    <script type="text/javascript">
      function toAdd(){
    	  window.location.href='${basePath}manager/ToClazzAddServlet';
      }
      
      //删除班级
      function toDel(clazzId){
    	  if(confirm("您确定要删除吗")){
    		  $.ajax({
        		  url:'${basePath}manager/DelClazzServlet',
        		  type:'get',
        		  data:{'clazzId':clazzId},
        		  dataType:'text',
        		  success:function(data){
        			  if(data=='ok'){
        				  alert("删除成功！");
        				  //删除该行
        				  $("#tr"+clazzId).remove();
        			  }else{
        				  alert("删除失败，请稍后再试");
        			  }
        		  }
        	  });
    	  }
      }
    </script>
  </body>
</html>