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
                <strong>Success!</strong> Brand created Successfully!!!!!
                </div>
                </c:if>
                  <!-- Inline Form Block -->
                             <div class="block">
                                    <!-- Form Validation Example Title -->
                                    <div class="block-title">
                                        <h2><strong>Form Validation</strong> Example</h2>
                                    </div>
						<a class="btn btn-sm btn-primary" href="/ManageCorporate/CorporateList/AddCorporate"> <i class="fa fa-angle-right"></i> Add Corporate</a>
						<!-- END Form Validation Example Title -->

                                    <!-- Form Validation Example Content -->
                               <div class="table-responsive">
                                <table id="example-datatable" class="table table-vcenter table-condensed table-bordered">
                                    <thead>
                                        <tr>
										<th class="text-center">ID</th>
										<th class="text-center">Name</th>
										<th class="text-center">Corporate Name</th>
										<th class="text-center">State</th>
										<th class="text-center">City</th>
										<th class="text-center">Area</th>
<!-- 										<th class="text-center">Admin</th>
 -->										<th class="text-center">LoginId</th>
										<th class="text-center">Actions</th>
									</tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="corporate" items="${activeCorporateList}" varStatus="count">
                                        <tr>
                                            <td class="text-center">${count.count}</td>
                                            <td class="text-center">${corporate.corporateName} </td>
                                            <td class="text-center">${corporate.corporateState.stateName} </td>
                                            <td class="text-center">${corporate.corporateCity.cityName} </td>
                                            <td class="text-center">${corporate.corporateArea.areaName} </td>
                                            <td class="text-center">${corporate.corporateAdmin.firstName}</td>
                                            <td class="text-center">${corporate.corporateAdmin.loginId}</td>
                                            <td class="text-center">
                                                <div class="btn-group">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-xs btn-default"><i class="fa fa-pencil"></i></a>
                                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                                </div>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        </tbody>
                                        </table>
                                        </div>
                                        
                   <div class="block full" id="gridView">
					<div class="block-title">
						<h2>
							<strong>Inactive Corporate</strong>
						</h2>
					</div>
				
					<div class="table-responsive">
						<table id="inactive-datatable"
							class="table table-vcenter table-condensed table-bordered">
							<thead>
								<tr>
									<th class="text-center">ID</th>
										<th class="text-center">Name</th>
										<th class="text-center">Corporate Name</th>
										<th class="text-center">State</th>
										<th class="text-center">City</th>
										<th class="text-center">Area</th>
<!-- 										<th class="text-center">Admin</th>
 -->										<th class="text-center">LoginId</th>
										<th class="text-center">Actions</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<c:forEach var="currentCorporate" items="${inactiveCorporateList}"
									varStatus="count">

									<tr>
										<td class="text-center">${count.count}</td>
                                            <td class="text-center">${corporate.corporateName} </td>
                                            <td class="text-center">${corporate.corporateState.stateName} </td>
                                            <td class="text-center">${corporate.corporateCity.cityName} </td>
                                            <td class="text-center">${corporate.corporateArea.areaName} </td>
                                            <td class="text-center">${corporate.corporateAdmin.firstName}</td>
                                            <td class="text-center">${corporate.corporateAdmin.loginId}</td>
                                            <td class="text-center">
                                                <div class="btn-group">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-xs btn-default"><i class="fa fa-pencil"></i></a>
                                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                                </div>
                                            </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
				                   <div class="block full" id="gridView">
					<div class="block-title">
						<h2>
							<strong>Awaiting Corporate</strong>
						</h2>
					</div>
				
					<div class="table-responsive">
						<table id="inactive-datatable"
							class="table table-vcenter table-condensed table-bordered">
							<thead>
								<tr>
									<th class="text-center">ID</th>
										<th class="text-center">Name</th>
										<th class="text-center">Corporate Name</th>
										<th class="text-center">State</th>
										<th class="text-center">City</th>
										<th class="text-center">Area</th>
<!-- 										<th class="text-center">Admin</th>
 -->										<th class="text-center">LoginId</th>
										<th class="text-center">Actions</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<c:forEach var="currentCorporate" items="${awaitingCorporateList}"
									varStatus="count">

									<tr>
										<td class="text-center">${count.count}</td>
                                            <td class="text-center">${corporate.corporateName} </td>
                                            <td class="text-center">${corporate.corporateState.stateName} </td>
                                            <td class="text-center">${corporate.corporateCity.cityName} </td>
                                            <td class="text-center">${corporate.corporateArea.areaName} </td>
                                            <td class="text-center">${corporate.corporateAdmin.firstName}</td>
                                            <td class="text-center">${corporate.corporateAdmin.loginId}</td>
                                            <td class="text-center">
                                                <div class="btn-group">
                                                <a href="#accept_corporate_popup"data-toggle="modal" onclick="acceptCorporate(${corporate.corporateId})">Approve Corporate</a>
												<a href="#reject_corporate_popup"data-toggle="modal" onclick="rejectCorporate(${corporate.corporateId})">Reject Corporate</a>
                                                </div>
                                            </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
				                   <div class="block full" id="gridView">
					<div class="block-title">
						<h2>
							<strong>Rejected Corporate</strong>
						</h2>
					</div>
				
					<div class="table-responsive">
						<table id="inactive-datatable"
							class="table table-vcenter table-condensed table-bordered">
							<thead>
								<tr>
									<th class="text-center">ID</th>
										<th class="text-center">Name</th>
										<th class="text-center">Corporate Name</th>
										<th class="text-center">State</th>
										<th class="text-center">City</th>
										<th class="text-center">Area</th>
<!-- 										<th class="text-center">Admin</th>
 -->										<th class="text-center">LoginId</th>
										<th class="text-center">Actions</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<c:forEach var="currentCorporate" items="${rejectedCorporateList}"
									varStatus="count">

									<tr>
										<td class="text-center">${count.count}</td>
                                            <td class="text-center">${corporate.corporateName} </td>
                                            <td class="text-center">${corporate.corporateState.stateName} </td>
                                            <td class="text-center">${corporate.corporateCity.cityName} </td>
                                            <td class="text-center">${corporate.corporateArea.areaName} </td>
                                            <td class="text-center">${corporate.corporateAdmin.firstName}</td>
                                            <td class="text-center">${corporate.corporateAdmin.loginId}</td>
                                            <td class="text-center">
                                                <div class="btn-group">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Edit" class="btn btn-xs btn-default"><i class="fa fa-pencil"></i></a>
                                                    <a href="javascript:void(0)" data-toggle="tooltip" title="Delete" class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
                                                </div>
                                            </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
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
    
    <div id="accept_corporate_popup" class="modal fade" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<form action="/ManageCorporate/CorporateList/AcceptCorporateRequest" method="post" class="form-horizontal form-bordered" id="accept_request_corporate_Form">
						
						<div style="padding: 10px; height: 150px;">
							<label>Do you want to accept request ?</label><br>
							<div class="row">
					<div class=" col-md-offset-2 col-md-8">
							<div class="col-xs-12 text-right">
								<button type="button" class="btn btn-sm btn-default"
									data-dismiss="modal">No</button>
								<div id="accept_corporate" class="btn btn-sm btn-primary save">Yes</div>
							</div>
						</div>
						</div>
						</div>
						<input type="hidden" name="id" id="acceptedRequestId">
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="reject_corporate_popup" class="modal fade" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<form action="/ManageCorporate/CorporateList/RejectCorporateRequest" method="post" class="form-horizontal form-bordered" id="reject_request_corporate_Form">
						<input type="hidden" name="id" id="rejectedRequestId">
						<div class="form-group form-actions">
							<div class="col-xs-12 text-center">
								<button type="button" class="btn btn-sm btn-default"
									data-dismiss="modal">No</button>
								<button id="reject_corporate" class="btn btn-sm btn-primary save" type="submit">Reject
									</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

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