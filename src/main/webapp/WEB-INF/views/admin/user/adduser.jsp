<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html class="no-js">
<head>
    <meta charset="utf-8">

    <title>CravX</title>

    <meta name="description" content="ProUI is a Responsive Bootstrap Admin Template created by pixelcave and published on Themeforest.">
    <meta name="author" content="pixelcave">
    <meta name="robots" content="noindex, nofollow">

    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

    <!-- Icons -->
    <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
    <link rel="shortcut icon" href="../../resources/img/favicon.png">
    <link rel="apple-touch-icon" href="../../resources/img/icon57.png" sizes="57x57">
    <link rel="apple-touch-icon" href="../../resources/img/icon72.png" sizes="72x72">
    <link rel="apple-touch-icon" href="../../resources/img/icon76.png" sizes="76x76">
    <link rel="apple-touch-icon" href="../../resources/img/icon114.png" sizes="114x114">
    <link rel="apple-touch-icon" href="../../resources/img/icon120.png" sizes="120x120">
    <link rel="apple-touch-icon" href="../../resources/img/icon144.png" sizes="144x144">
    <link rel="apple-touch-icon" href="../../resources/img/icon152.png" sizes="152x152">
    <link rel="apple-touch-icon" href="../../resources/img/icon180.png" sizes="180x180">
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/css/plugins.css">
    <link rel="stylesheet" href="../../resources/css/main.css">
    <link rel="stylesheet" href="../../resources/css/themes.css">
    <script src="../../resources/js/vendor/modernizr-respond.min.js"></script>
</head>

<body>
    
    <div id="page-wrapper">
          <div id="page-container" class="sidebar-partial sidebar-visible-lg sidebar-no-animations">
             <div id="sidebar">
                <!-- Wrapper for scrolling functionality -->
                <div id="sidebar-scroll">
                    <!-- Sidebar Content -->
                    <div class="sidebar-content">
                        <!-- Brand -->
                        <a href="index.html" class="sidebar-brand">
                            <i class="gi gi-flash"></i><span class="sidebar-nav-mini-hide"><strong>Super</strong>Admin</span>
                        </a>
                        <!-- END Brand -->

                        <!-- User Info -->
                        <div class="sidebar-section sidebar-user clearfix sidebar-nav-mini-hide">
                            <div class="sidebar-user-avatar">
                                <a href="page_ready_user_profile.html">
                                    <img src="../../resources/img/placeholders/avatars/avatar2.jpg" alt="avatar">
                                </a>
                            </div>
                            <div class="sidebar-user-name">${user.loginId}</div>
                            <div class="sidebar-user-links">
                                <a href="page_ready_user_profile.html" data-toggle="tooltip" data-placement="bottom"
                                    title="Profile"><i class="gi gi-user"></i></a>
                                
                               <c:url value="/logout" var="logoutUrl" />
								<form id="logout" action="${logoutUrl}" method="post">
								</form>
                                  <a href="javascript:document.getElementById('logout').submit()">Logout</a>
                            </div>
                        </div>
                        <!-- END User Info -->


                        <!-- Sidebar Navigation -->
                        <ul class="sidebar-nav">
                            <li>
                                <a href="index.html" class=" active"><i class="gi gi-stopwatch sidebar-nav-icon"></i><span
                                        class="sidebar-nav-mini-hide">Dashboard</span></a>
                            </li>
                            
                              <li>
                                <a href="#" class="sidebar-nav-menu"><i class="fa fa-plus sidebar-nav-indicator sidebar-nav-mini-hide"></i><i
                                        class="gi gi-shopping_cart sidebar-nav-icon"></i><span class="sidebar-nav-mini-hide">Card </span></a>
                                <ul>
                                      <li>
                                        <a href="/ManageCard/Card/AddCard">Add Cards</a>
                                    </li>
                                    <li>
                                        <a href="/ManageCard/Card/AssignCard">Assign Cards</a>
                                    </li>
                                </ul>
                            </li>
                           
                            <li>
                                <a href="#" class="sidebar-nav-menu"><i class="fa fa-plus sidebar-nav-indicator sidebar-nav-mini-hide"></i><i
                                        class="gi gi-certificate sidebar-nav-icon"></i><span class="sidebar-nav-mini-hide">Masters 
                                        </span></a>
                                <ul>
                                   <li><a href="/User/UserList">User Management</a></li>
									<li><a href="/ManageLocation/StateList">State</a></li>
									<li><a href="/ManageLocation/CityList">City</a></li>
									<li><a href="/ManageLocation/AreaList">Area</a></li>
									<li><a href="/ManageCompany/CompanyList">Company List</a></li>
									<li><a href="/ManageBrand/BrandList">Brand List</a></li>
									<li><a href="/ManageRestaurant/RestaurantList">Restaurant List</a></li>
                                </ul>
                            </li>
                           
                                
                        </ul>
                  
                    </div>
                   
                </div>
             
            </div>
        
            <div id="main-container">
                <!-- Page content -->
                <div id="page-content">
                <c:if test="${success}">
                <div class="alert alert-success">
                <strong>Success!</strong> User created Successfully!!!!!
                </div>
                </c:if>
                  <!-- Inline Form Block -->
                             <div class="block">
                                    <!-- Form Validation Example Title -->
                                    <div class="block-title">
                                        <h2><strong>Form Validation</strong> Example</h2>
                                    </div>
                                    <!-- END Form Validation Example Title -->

                                    <!-- Form Validation Example Content -->
                                    <form:form id="form-validation" action="/User/UserList/SaveUser" modelAttribute="objUser" method="post" class="form-horizontal form-bordered" novalidate="novalidate">
                                        <fieldset>
                                            <legend><i class="fa fa-angle-right"></i> Vital Info</legend>
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="val_username">First Name <span class="text-danger">*</span></label>
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <form:input type="text" id="firstname" path="firstName" class="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="val_username">Last Name <span class="text-danger">*</span></label>
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <form:input type="text" id="lastname" path="lastName" class="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="val_email">Email <span class="text-danger">*</span></label>
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <form:input type="text" id="emailId" path="emailId" class="form-control" placeholder="test@example.com"/>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="val_email">Role<span class="text-danger">*</span></label>
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                    	<c:forEach items="${roleList}" var="role">
                                                    		<c:out   value="${role.name}"/>
                                                    	</c:forEach>
                                                    
                                                        <form:input type="text" id="emailId" path="emailId" class="form-control" placeholder="test@example.com"/>
                                                    </div>
                                                </div>
                                            </div>
 
                                            
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="val_email">Login Id <span class="text-danger">*</span></label>
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <form:input type="text" id="loginId" path="loginId" class="form-control" placeholder="test@example.com"/>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                             <div class="form-group">
                                                <label class="col-md-4 control-label" for="val_email">Mobile<span class="text-danger">*</span></label>
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <form:input type="text" id="mobile" path="mobile" class="form-control" placeholder="test@example.com"/>
                                                    </div>
                                                </div>
                                            </div>
                                            
                <div class="form-group form-actions">
				<div class="col-md-9 col-md-offset-3">
					<button id="user_submit" type="submit"
						class="btn btn-sm btn-primary save">
						<i class="fa fa-angle-right"></i> Save
					</button>
					<a class="btn btn-sm btn-primary" href="/User/UserList"><i class="gi gi-remove"></i>
							Cancel</a>
				</div>
			</div>
                                           
                                        </fieldset>
                                      </form:form>
                                </div>
                </div>
                <!-- END Page Content -->

                <!-- Footer -->
                <footer class="clearfix">
                    <div class="pull-right">
                        CravX <i class="fa fa-heart text-danger"></i> by <a href="http://goo.gl/vNS3I" target="_blank">Foodies</a>
                    </div>
                    <div class="pull-left">
                        <span id="year-copy"></span> &copy; <a href="http://goo.gl/TDOSuC" target="_blank"></a>
                    </div>
                </footer>
                <!-- END Footer -->
            </div>
            <!-- END Main Container -->
        </div>
        <!-- END Page Container -->
    </div>
    <!-- END Page Wrapper -->

    <!-- Scroll to top link, initialized in js/app.js - scrollToTop() -->
    <a href="#" id="to-top"><i class="fa fa-angle-double-up"></i></a>

    <!-- User Settings, modal which opens from Settings link (found in top right user menu) and the Cog link (found in sidebar user info) -->
    <div id="modal-user-settings" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header text-center">
                    <h2 class="modal-title"><i class="fa fa-pencil"></i> Settings</h2>
                </div>
                <!-- END Modal Header -->

                <!-- Modal Body -->
                <div class="modal-body">
                    <form action="index.html" method="post" enctype="multipart/form-data" class="form-horizontal form-bordered"
                        onsubmit="return false;">
                        <fieldset>
                            <legend>Vital Info</legend>
                            <div class="form-group">
                                <label class="col-md-4 control-label">Username</label>
                                <div class="col-md-8">
                                    <p class="form-control-static">Admin</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="user-settings-email">Email</label>
                                <div class="col-md-8">
                                    <input type="email" id="user-settings-email" name="user-settings-email" class="form-control"
                                        value="admin@example.com">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="user-settings-notifications">Email
                                    Notifications</label>
                                <div class="col-md-8">
                                    <label class="switch switch-primary">
                                        <input type="checkbox" id="user-settings-notifications" name="user-settings-notifications"
                                            value="1" checked>
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend>Password Update</legend>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="user-settings-password">New Password</label>
                                <div class="col-md-8">
                                    <input type="password" id="user-settings-password" name="user-settings-password"
                                        class="form-control" placeholder="Please choose a complex one..">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="user-settings-repassword">Confirm New
                                    Password</label>
                                <div class="col-md-8">
                                    <input type="password" id="user-settings-repassword" name="user-settings-repassword"
                                        class="form-control" placeholder="..and confirm it!">
                                </div>
                            </div>
                        </fieldset>
                        <div class="form-group form-actions">
                            <div class="col-xs-12 text-right">
                                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-sm btn-primary">Save Changes</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- END Modal Body -->
            </div>
        </div>
    </div>
    <!-- END User Settings -->

    <!-- Remember to include excanvas for IE8 chart support -->
    <!--[if IE 8]><script src="js/helpers/excanvas.min.js"></script><![endif]-->

    <!-- Include Jquery library from Google's CDN but if something goes wrong get Jquery from local file -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script>
        !window.jQuery && document.write(decodeURI('%3Cscript src="js/vendor/jquery-1.11.2.min.js"%3E%3C/script%3E'));
    </script>

    <!-- Bootstrap.js, Jquery plugins and Custom JS code -->
    <script src="../../resources/js/vendor/bootstrap.min.js"></script>
    <script src="../../resources/js/plugins.js"></script>
    <script src="../../resources/js/app.js"></script>

    <!-- Google Maps API + Gmaps Plugin, must be loaded in the page you would like to use maps -->
    <script src="//maps.google.com/maps/api/js?sensor=true"></script>
    <script src="../../resources/js/helpers/gmaps.min.js"></script>

    <!-- Load and execute javascript code used only in this page -->
    <script src="../../resources/js/pages/index.js"></script>
    <script>
        $(function () {
            Index.init();
        });
    </script>
</body>

</html>