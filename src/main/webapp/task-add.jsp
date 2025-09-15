<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/plugins/images/favicon.png">
    <title>Pixel Admin</title>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="${pageContext.request.contextPath}/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="${pageContext.request.contextPath}/css/colors/blue-dark.css" id="theme" rel="stylesheet">
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
                        <a href="${pageContext.request.contextPath}/" class="waves-effect active"><i class="fa fa-clock-o fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                    </li>
                    <c:if test = "${userCurrent.role_id == 1 || userCurrent.role_id == 2}">
				          <li>
	                        <a href="${pageContext.request.contextPath}/users" class="waves-effect"><i class="fa fa-user fa-fw"
	                                aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
	                    </li>
				     </c:if>
                    
                    
                    <c:if test = "${userCurrent.role_id == 1}">
				          <li>
	                        <a href="${pageContext.request.contextPath}/roles" class="waves-effect"><i class="fa fa-modx fa-fw"
	                                aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
	                    </li>
				     </c:if>
				     
                      <c:if test = "${userCurrent.role_id == 1 || userCurrent.role_id == 2}">
				          <li>
	                        <a href="${pageContext.request.contextPath}/projects" class="waves-effect"><i class="fa fa-table fa-fw"
	                                aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
	                    </li>
				     </c:if>
                    
                    <c:if test = "${userCurrent.role_id == 1 || userCurrent.role_id == 2}">
				          <li>
	                        <a href="${pageContext.request.contextPath}/tasks" class="waves-effect"><i class="fa fa-table fa-fw"
	                                aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
	                    </li>
				     </c:if>
                    
                    
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
                        <c:if test="${isEdit == 0}">
								<h4 class="page-title">Thêm mới công việc</h4>

						</c:if>
				           <c:if test="${isEdit > 0}">
								<h4 class="page-title">Sửa công việc</h4>
						</c:if>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                            <form class="form-horizontal form-material" method="post" action="">
                                <div class="form-group">
                                    <label class="col-md-12" for="project">Dự án</label>
                                    <div class="col-md-12">
                                        <select class="form-control form-control-line" name="project" id="project">
	                                        <c:forEach var = "i" items="${listProjects }">
										         <option value="${i.id }" ${i.id == taskEdit.job_id ? 'selected' : '' }>${i.name }</option>
										    </c:forEach>
									    </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="nameTask">Tên công việc</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="Tên công việc" required value="${taskEdit.name }"
                                            class="form-control form-control-line" id="nameTask" name="nameTask">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="user">Người thực hiện</label>
                                    <div class="col-md-12">
                                         <select class="form-control form-control-line" name="user" id="user">
	                                        <c:forEach var = "i" items="${listUsers }">
										         <option value="${i.id }" ${taskEdit.user_id == i.id ? 'selected' : '' } >${i.fullname }</option>
										    </c:forEach>
									    </select>
                                    </div>
                                </div>
                                 <c:if test="${isEdit > 0}">
                                  <div class="form-group">
                                    <label class="col-md-12" for="statusId">Trạng thái</label>
                                    <div class="col-md-12">
                                         <select class="form-control form-control-line" name="statusId" id="statusId">
	                                        <c:forEach var = "i" items="${listStatus }">
										         <option value="${i.id }" ${taskEdit.status_id == i.id ? 'selected' : '' } >${i.name }</option>
										    </c:forEach>
									    </select>
                                    </div>
                                </div>
								</c:if>
                                <div class="form-group">
                                    <label class="col-md-12" for="startDate">Ngày bắt đầu</label>
                                    <div class="col-md-12">
                                        <input type="date" placeholder="dd/MM/yyyy"
                                            class="form-control form-control-line"
                                            name="startDate" id="startDate" required
                                            value="${taskEdit.getStartDate() }"
                                            
                                            > 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="endDate">Ngày kết thúc</label>
                                    <div class="col-md-12">
                                        <input type="date" placeholder="dd/MM/yyyy" name="endDate" id="endDate"
                                            class="form-control form-control-line" value="${ taskEdit.getEndDate()}">  
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">Lưu lại</button>
                                        <a href="${pageContext.request.contextPath}/tasks" class="btn btn-primary">Quay lại</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2 col-12"></div>
                </div>
                <!-- /.row -->
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
    <script src="${pageContext.request.contextPath}/js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="${pageContext.request.contextPath}/js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/js/custom.min.js"></script>
</body>

</html>