<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<html>
  <head>
    <title>首页</title>
    <meta charset="utf-8">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${basePath }css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
  <body class="app sidebar-mini rtl">
    <!-- 顶部工具栏-->
    <header class="app-header"><a class="app-header__logo" href="javascript:void(0)">在线考试系统</a>
      <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
      <!-- Navbar Right Menu-->
      <ul class="app-nav">
      	<c:if test="${sessionScope.user.modified == 0 }">
      	<li style="font-size:15px;line-height:50px;color:red">您当前是初始密码，请尽快修改</li>
      	</c:if>
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>
          <ul class="dropdown-menu settings-menu dropdown-menu-right">
            <li><a class="dropdown-item" href="javascript:changeMenu('ToEditPasswordServlet')"><i class="fa fa-user fa-lg"></i> 修改密码</a></li>
            <li><a class="dropdown-item" href="${basePath }logout"><i class="fa fa-sign-out fa-lg"></i> 注销</a></li>
          </ul>
        </li>
      </ul>
    </header>
    <!-- 侧边菜单-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
      <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg" alt="User Image">
        <div>
          <p class="app-sidebar__user-name">${sessionScope.user.username }</p>
          <p class="app-sidebar__user-designation">
          	<c:if test="${sessionScope.roleId == 1 }">学生</c:if>
          	<c:if test="${sessionScope.roleId == 2 }">教师</c:if>
          	<c:if test="${sessionScope.roleId == 3 }">管理员</c:if>
          </p>
        </div>
      </div>
      <ul class="app-menu">
      	<c:forEach items="${menus }" var="menu">
      		<li><a class="app-menu__item" href="javascript:changeMenu('${menu.url }')"><i class="app-menu__icon fa ${menu.icon }"></i><span class="app-menu__label">${menu.name }</span></a></li>
      	</c:forEach>
      </ul>
    </aside>
    <iframe name="pageFrame" id="pageFrame" src="${basePath }manager/grade" width="100%" height="810" frameborder="0"></iframe>
    <!-- Essential javascripts for application to work-->
    <script src="${basePath }js/jquery-3.2.1.min.js"></script>
    <script src="${basePath }js/popper.min.js"></script>
    <script src="${basePath }js/bootstrap.min.js"></script>
    <script src="${basePath }js/main.js"></script>
   	<script type="text/javascript">
   		function changeMenu(url){
   			document.getElementById("pageFrame").src='${basePath}'+url;
   		}
   	</script>
  </body>
</html>