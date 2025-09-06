<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <title>Pixel Admin</title>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="${pageContext.request.contextPath}/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- Animation CSS -->
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <!-- color CSS you can use different color css from css/colors folder -->
    <!-- We have chosen the skin-blue (blue.css) for this starter
          page. However, you can choose any other skin from folder css / colors .
-->
    <link href="${pageContext.request.contextPath}/css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <link rel="stylesheet" href="./css/custom.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header"> 
                <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse">
                    <i class="fa fa-bars"></i>
                </a>
                <div class="top-left-part">
                    <a class="logo" href="${pageContext.request.contextPath}/">
                        <b>
                            <img src="${pageContext.request.contextPath}/plugins/images/pixeladmin-logo.png" alt="home" />
                        </b>
                        <span class="hidden-xs">
                            <img src="${pageContext.request.contextPath}/plugins/images/pixeladmin-text.png" alt="home" />
                        </span>
                    </a>
                </div>
                <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
                    <li>
                        <form role="search" class="app-search hidden-xs">
                            <input type="text" placeholder="Search..." class="form-control"> 
                            <a href="">
                                <i class="fa fa-search"></i>
                            </a>
                        </form>
                    </li>
                </ul>
                <ul class="nav navbar-top-links navbar-right pull-right">
                    <li>
                        <div class="dropdown">
                             <c:if test = "${isLogin}">
						          <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#"> 
                                    <img src="${!userCurrent.getAvatar().isEmpty() && userCurrent.getAvatar() != null ? userCurrent.getAvatar() : pageContext.request.contextPath.concat('/plugins/images/users/default-avatar.jpg') }" alt="user-img" width="36" class="img-circle" />
                                    <b class="hidden-xs">${!userCurrent.getFullname().isEmpty() ? userCurrent.getFullname() : 'Người dùng'}</b> 
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/profile">Thông tin cá nhân</a></li>
                                    <li class="divider"></li>
                                    <li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                                </ul>
						      </c:if>
						      <c:if test = "${!isLogin}">
						          <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#"> 
                                    <img src="${pageContext.request.contextPath}/plugins/images/users/default-avatar.jpg" alt="user-img" width="36" class="img-circle" />
                                    <b class="hidden-xs">Người dùng</b> 
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
                                </ul>
						      </c:if>
                        </div>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-header -->
            <!-- /.navbar-top-links -->
            <!-- /.navbar-static-side -->
        </nav>
        <!-- Left navbar-header -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse slimscrollsidebar">
                 <ul class="nav" id="side-menu">
                    <li style="padding: 10px 0 0;">
                        <a href="${pageContext.request.contextPath}/" class="waves-effect"><i class="fa fa-clock-o fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/users" class="waves-effect"><i class="fa fa-user fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/roles" class="waves-effect"><i class="fa fa-modx fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/projects" class="waves-effect active"><i class="fa fa-table fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/tasks" class="waves-effect"><i class="fa fa-table fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/blank" class="waves-effect"><i class="fa fa-columns fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/404" class="waves-effect"><i class="fa fa-info-circle fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Error 404</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Chi tiết công việc "${projects.name}" </h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">Dashboard</a></li>
                            <li class="active">Blank Page</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="row">
	                   <c:forEach var = "i" items="${listStatus }" varStatus="loop">
	                            <!--col -->
	                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
	                                <div class="white-box">
	                                    <div class="col-in row">
	                                        <div class="col-xs-12">
	                                            <h3 class="counter text-right m-t-15 ${ListColorStatusText.get(loop.index)}">${i.getWidthStatus()}%</h3>
	                                        </div>
	                                        <div class="col-xs-12">
	                                            <i data-icon="E" class="linea-icon linea-basic"></i>
	                                            <h5 class="text-muted vb text-center text-uppercase">${i.getName()}</h5>
	                                        </div>
	                                        <div class="col-md-12 col-sm-12 col-xs-12">
	                                            <div class="progress">
	                                                <div class="progress-bar ${ListColorStatusProcess.get(loop.index)}" role="progressbar"
	                                                    aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
	                                                    style="width: ${i.getWidthStatus()}%"></div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
						    </c:forEach>
	            </div>
                 <!-- END THỐNG KÊ -->
                
                <!-- BEGIN CÔNG VIỆC CỦA PROJECT -->
                 <c:forEach var = "i" items="${listUsers }">
	                <div class="row">
	                    <div class="col-xs-12 mb-3">
	                        <a href="#" class="group-title">
	                            <img width="30" src="${!i.getAvatar().isEmpty() && i.getAvatar() != null ? i.getAvatar() : pageContext.request.contextPath.concat('/plugins/images/users/default-avatar.jpg') }" class="img-circle" />
	                            <span>${i.getFullname()}</span>
	                        </a>
	                    </div>
	                    <!-- TIẾN ĐỘ PROJECT  -->
	                    <c:forEach var = "j" items="${i.getAllStatus()}"> 
		                    <div class="col-md-4 ">
		                        <div class="white-box">
		                            <h3 class="box-title">${j.getName() }</h3>
		                            <div class="message-center">
		                             	<c:forEach var = "k" items="${j.getTasksListStatus()}">
			                                <a href="#">
			                                    <div class="mail-contnet">
				                                    <h5>${k.name }</h5>
			                                        <span class="mail-desc"></span>
			                                        <span class="time">Bắt đầu: ${k.getStartDate() }</span>
			                                        <span class="time">Kết thúc: ${k.getEndDate() }</span>
			                                    </div>
			                                </a>
										</c:forEach>
		                            </div>
		                        </div>
		                    </div>
		                  </c:forEach>
		                    
	                </div>
			     </c:forEach>
			     <!-- END CÔNG VIỆC CỦA PROJECT -->
                
             
                <!-- END DANH SÁCH CÔNG VIỆC -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2025 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="${pageContext.request.contextPath}js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="${pageContext.request.contextPath}/js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/js/custom.min.js"></script>
</body>

</html>