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
                <strong>Success!</strong> Corporate created Successfully!!!!!
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
<form action="/ManageCorporate/CorporateList/SaveCorporate" method="post" class="form-horizontal ui-formwizard"
		id="outlet_form" enctype="multipart/form-data">
		<div class="row">

			<div class="col-md-6">
				<div class="block">
					<div class="block-title">
						<h2>
							<strong>Corporate Info</strong>
						</h2>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="outlet_Name">Corporate Name <span class="text-danger">*</span>
						</label>
						<div class="col-md-6">

							<input id="outlet_Name" name="corporateName" class="form-control"
								placeholder="Corporate Name"
								type="text" value="${corporate.corporateName}">
						</div>
					</div>
					
					
					<div class="form-group" id="companyDiv">
						<label class="col-md-4 control-label" for="chosenIndustryId">Industry Type<span class="text-danger">*</span></label>
						<div class="col-md-6">
							<select class="select-chosen" style="width: 200px;"
								id="chosenIndustryId"
								data-placeholder="Choose Industry.."
								name="industryTypeId">
								<option value=""></option>
								<c:forEach items="${industryList}" var="industry">
									<option value="${industry.id}"  <c:if test="${industry.id eq corporate.industryType.id}">selected="selected"</c:if> >${industry.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="example-daterange1">Agreement Date
							</label>
						<div class="col-md-8">
							<div class="input-group input-daterange "
								data-date-format="mm/dd/yyyy">
								<input type="text" id="compAgreementStart"
									name="corporateAgreementStart" class="form-control text-center"
									placeholder="From" value="${corporate.restAgreementStart}"> <span class="input-group-addon"><i
									class="fa fa-angle-right"></i></span> <input type="text"
									id="compAgreementEnd" name="corporateAgreementEnd"
									class="form-control text-center" placeholder="To" value="${corporate.restAgreementEnd}">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="example-daterange1">Agreement Document
							</label>
						<div class="col-md-6">
							<input type="file" name="file" accept="application/pdf" size="50" />
						</div>


					</div>

										<!-- <div class="form-group">
											<label class="col-md-3 control-label">Feature Control
												</label>
											<div class="col-md-9">
												<label class="checkbox-inline"
													for="example-inline-checkbox1"> <input
													type="checkbox" id="example-inline-checkbox1"
													name="multiscreen" >
													Multi Screen
												</label> <br><label class="checkbox-inline"
													for="example-inline-checkbox2"> <input
													type="checkbox" id="example-inline-checkbox2"
													name="restaurantLoyalityCards" >
													Restaurant Loyality Cards
												</label>
												 <label class="checkbox-inline"
													for="example-inline-checkbox3"> <input
													type="checkbox" id="example-inline-checkbox3"
													name="restaurantPersonalDeals" >
													Restaurant Personal Deals
												</label>
												<label class="checkbox-inline"
													for="example-inline-checkbox3"> <input
													type="checkbox" id="example-inline-checkbox4"
													name="askForDiscountOnline" >
													Ask For Discount Online
												</label>
												<label class="checkbox-inline"
													for="example-inline-checkbox3"> <input
													type="checkbox" id="example-inline-checkbox5"
													name="personalRecordManagement" >
													Personal Record Management
												</label>
												<label class="checkbox-inline"
													for="example-inline-checkbox3"> <input
													type="checkbox" id="example-inline-checkbox6"
													name="userEngagement" >
													User Engagement
												</label>
											</div>
										</div>
 -->


									</div>

	
				
				<div class="block">  
					<div class="block-title">
						<h2>
							<strong>Contact Info</strong>
							<small style="float:left; margin-top: 4px;">Communication Info</small>
						</h2>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="telephone1">Telephone <span class="text-danger">*</span></label>
						<div class="col-md-6">

							<input id="corporateTelephone1" name="corporateTelephone"
								class="form-control"
								placeholder="Telephone"
								type="text" value="${corporate.corporateTelephone}" >
						</div>
					</div>


					<div class="form-group">
						<label class="col-md-4 control-label" for="rest_email">Corporate Email Id<span class="text-danger">*</span></label>
						<div class="col-md-6">

							<input id="corporate_email" name="corporateEmail"
								class="form-control"
								placeholder="Corporate Email Id"
								type="text" value="${corporate.corporateEmail}">
						</div>
					</div>

				<%-- 	<div class="form-group">
						<label class="col-md-4 control-label" for="mobile1">Mobile<span class="text-danger">*</span></label>
						<div class="col-md-6">

							<input id="outletMobile1" name="outletMobile1"
								class="form-control"
								placeholder="Mobile"
								type="text" value="${outlet.outletMobile1}">
						</div>
					</div> --%>

					

					<div class="form-group">
						<label class="col-md-4 control-label" for="addressline1">Address Line 1<span class="text-danger">*</span>
						</label>
						<div class="col-md-6">

							<input id="outletAddressline1" name="corporateAddressLine1"
								class="form-control"
								placeholder="Address Line 1"
								type="text" value="${corporate.corporateAddressLine1}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="addressline2">Address Line 2</label>
						<div class="col-md-6">

							<input id="corporateAddressline2" name="corporateAddressLine2"
								class="form-control"
								placeholder="Address Line 2"
								type="text" value="${corporate.corporateAddressLine2}">
						</div>
					</div>
					
					<div class="form-group" id="stateDiv">
						<label class="col-md-4 control-label " for="chosenStateId">State<span class="text-danger">*</span></label>
						<div class="col-md-6">
							<select class="select-chosen" style="width: 200px;"
								id="chosenStateId"
								data-placeholder="Choose State"
								name="chosenStateId">
								<option value=""></option>
								<c:forEach items="${stateList}" var="state">
											<option value="${state.stateId}" 
											<c:if test="${state.stateId eq corporate.corporateState.stateId}">selected="selected"</c:if>>${state.stateName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group" id="cityDiv">
						<label class="col-md-4 control-label" for="chosenCityId">City<span class="text-danger">*</span></label>
						<div class="col-md-6">
							<select class="select-chosen" style="width: 200px;"
								id="chosenCityId"
								data-placeholder="Choose City.."
								name="chosenCityId">
								<c:forEach items="${cityList}" var="city">
									<option value="${city.cityId}"  <c:if test="${city.cityId eq corporate.corporateCity.cityId}">selected="selected"</c:if> >${city.cityName}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group" id="areaDiv">
						<label class="col-md-4 control-label" for="chosenAreaId">Area<span class="text-danger">*</span></label>
						<div class="col-md-6">
							<select class="select-chosen" style="width: 200px;"
								id="chosenAreaId"
								data-placeholder="Choose Area"
								name="chosenAreaId">
								<c:forEach items="${areaList}" var="area">
												<option value="${area.areaId}"  <c:if test="${area.areaId eq corporate.corporateArea.areaId}">selected="selected"</c:if> >${area.areaName}</option>
									</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="corporatePincode">Pincode<span class="text-danger">*</span></label>
						<div class="col-md-6">

							<input id="corporatePincode" name="corporatePincode"
								class="form-control"
								placeholder="Pincode"
								type="text" value="${corporate.corporatePincode}">
						</div>
					</div>
				</div> 
			</div>
			

			<div class="col-md-6">
				<div class="block">
					<div class="block-title">
						<h2>
							<strong>Admin Info</strong>
							<small style="float:left; margin-top: 4px;">Corporate Login Info</small>
						</h2>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="first_Name">First Name<span class="text-danger">*</span> </label>
						<div class="col-md-6">

							<input id="first_Name" name="firstName" class="form-control"
								placeholder="First Name"
								type="text" value="${corporate.corporateAdmin.firstName}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="last_Name">Last Name<span class="text-danger">*</span> </label>
						<div class="col-md-6">

							<input id="last_Name" name="lastName" class="form-control"
								placeholder="Last Name"
								type="text" value="${corporate.corporateAdmin.lastName}">
						</div>
					</div>


					<div class="form-group">
						<label class="col-md-4 control-label" for="email">Email Id<span class="text-danger">*</span></label>
						<div class="col-md-6">
							<input id="email" name="emailId" class="form-control" 
								placeholder="test@example.com" type="text" value="${corporate.corporateAdmin.emailId}"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="email">Mobile<span class="text-danger">*</span></label>
						<div class="col-md-6">
							<input id="mobile" name="mobile" class="form-control" 
								placeholder="Mobile No" type="text" value="${corporate.corporateAdmin.mobile}"/>
						</div>
					</div>

					
					<div class="form-group">
						<label class="col-md-4 control-label" for="login">Login Id <span class="text-danger">*</span> </label>
						<div class="col-md-6">
							<div class="input-group">
								<input id="login" name="loginId" class="form-control col-md-4 "
									placeholder='Login Id' type="text" value="${corporate.corporateAdmin.loginId}">
							</div>
						</div>
				</div>
				
				<div class="form-group">
						 <label class="col-md-4 control-label" for="login">User Count <span class="text-danger">*</span> </label>
						 <div class="col-md-6">
							<div class="input-group">
                            <input type="number" id="example-if-email" name="noOfUser" class="form-control" placeholder="Enter User Count..">
                             </div>
						</div>
						</div>
				<!--  <div class="form-group">
						 <label class="col-md-4 control-label" for="login">User Count <span class="text-danger">*</span> </label>
						 <div class="col-md-6">
							<div class="input-group">
                                            <input type="number" id="example-if-email" name="userCount" class="form-control" placeholder="Enter User Count..">
                                       </div>
						</div>
						</div> -->
			</div>
			
		</div>


		<div class="form-group form-actions">
				<div class="col-md-9 col-md-offset-3">
					<button id="outlet_submit" type="submit"
						class="btn btn-sm btn-primary save">
						<i class="fa fa-angle-right"></i>Save
					</button>
						<a class="btn btn-sm btn-primary save" href="/Manageoutlet/outletList"><i class="gi gi-remove"></i>
							Cancel </a>
				</div>
			</div>
			</div>
	</form>
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
    <!-- END Page Wrapper -->

    <!-- Scroll to top link, initialized in js/app.js - scrollToTop() -->
    <a href="#" id="to-top"><i class="fa fa-angle-double-up"></i></a>

    <!-- outletAdmin Settings, modal which opens from Settings link (found in top right outletAdmin menu) and the Cog link (found in sidebar outletAdmin info) -->
    <div id="modal-outletAdmin-settings" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
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
                                <label class="col-md-4 control-label">outletAdminname</label>
                                <div class="col-md-8">
                                    <p class="form-control-static">Admin</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="outletAdmin-settings-email">Email</label>
                                <div class="col-md-8">
                                    <input type="email" id="outletAdmin-settings-email" name="outletAdmin-settings-email" class="form-control"
                                        value="admin@example.com">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="outletAdmin-settings-notifications">Email
                                    Notifications</label>
                                <div class="col-md-8">
                                    <label class="switch switch-primary">
                                        <input type="checkbox" id="outletAdmin-settings-notifications" name="outletAdmin-settings-notifications"
                                            value="1" checked>
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend>Password Update</legend>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="outletAdmin-settings-password">New Password</label>
                                <div class="col-md-8">
                                    <input type="password" id="outletAdmin-settings-password" name="outletAdmin-settings-password"
                                        class="form-control" placeholder="Please choose a complex one..">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="outletAdmin-settings-repassword">Confirm New
                                    Password</label>
                                <div class="col-md-8">
                                    <input type="password" id="outletAdmin-settings-repassword" name="outletAdmin-settings-repassword"
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
    <!-- END outletAdmin Settings -->

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
<!--     <script src="../../resources/js/helpers/gmaps.min.js"></script>
 -->
    <!-- Load and execute javascript code used only in this page -->
    <script src="../../resources/js/pages/index.js"></script>
    <script type="text/javascript">
$(document).ready(function() {
		
		
		$('select[name="chosenStateId"]').chosen().change( function() {
			changeState($(this).val());
		});
		
		$('select[name="chosenCityId"]').chosen().change( function() {
			changeCity($(this).val());
		});
		
		$('select[name="chosenCompanyId"]').chosen().change( function() {
			changeCompany($(this).val());
		});
});
    function changeState(stateId){
		$.ajax({
			type: "GET",
			async: false,
			url: "/citybystateid/"+stateId,
			success: function(data, textStatus, jqXHR){
				var obj = jQuery.parseJSON(data);
				var len=obj.length;
				var myOptions = "<option value></option>";
				for(var i=0; i<len; i++){
				myOptions += '<option value="'+obj[i].cityId+'">'+obj[i].cityName+'</option>';
				}
				$("#chosenCityId").html(myOptions).chosen().trigger("chosen:updated");
			},
			dataType: 'html'
		});
	}
    
    function changeCompany(companyId){
		$.ajax({
			type: "GET",
			async: false,
			url: "/brandbycompanyid/"+companyId,
			success: function(data, textStatus, jqXHR){
				var obj = jQuery.parseJSON(data);
				var len=obj.length;
				var myOptions = "<option value></option>";
				for(var i=0; i<len; i++){
				myOptions += '<option value="'+obj[i].brandId+'">'+obj[i].brandName+'</option>';
				}
				$("#chosenBrandId").html(myOptions).chosen().trigger("chosen:updated");
			},
			dataType: 'html'
		});
	}
    
    function changeCity(cityId){
		$.ajax({
			type: "GET",
			async: false,
			url: "/areabycityid/"+cityId,
			success: function(data, textStatus, jqXHR){
				var obj = jQuery.parseJSON(data);
				var len=obj.length;
				var myOptions = "<option value></option>";
				for(var i=0; i<len; i++){
				myOptions += '<option value="'+obj[i].areaId+'">'+obj[i].areaName+'</option>';
				}
				$("#chosenAreaId").html(myOptions).chosen().trigger("chosen:updated");
			},
			dataType: 'html'
		});
	}
       /*  $(function () {
            Index.init();
        }); */
    </script>
</body>

</html>