<%
/**
 * template_start.jsp
 *
 * Author: pixelcave
 *
 * The first block of code used in every page of the template
 *
 */
%>
<!DOCTYPE html>
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">

      
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

        <!-- Icons -->
        <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
<%--         <link rel="shortcut icon" href="/resources/img/favicon.ico"> --%>
        <link rel="apple-touch-icon" href="/resources/img/icon57.png" sizes="57x57">
        <link rel="apple-touch-icon" href="/resources/img/icon72.png" sizes="72x72">
        <link rel="apple-touch-icon" href="/resources/img/icon76.png" sizes="76x76">
        <link rel="apple-touch-icon" href="/resources/img/icon114.png" sizes="114x114">
        <link rel="apple-touch-icon" href="/resources/img/icon120.png" sizes="120x120">
        <link rel="apple-touch-icon" href="/resources/img/icon144.png" sizes="144x144">
        <link rel="apple-touch-icon" href="/resources/img/icon152.png" sizes="152x152">
        <!-- END Icons -->

        <!-- Stylesheets -->
        <!-- Bootstrap is included in its original form, unaltered -->
        <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

        <!-- Related styles of various icon packs and plugins -->
        <link rel="stylesheet" href="/resources/css/plugins.css">

        <!-- The main stylesheet of this template. All Bootstrap overwrites are defined in here -->
        <link rel="stylesheet" href="/resources/css/main.css">

        <!-- Include a specific file here from css/themes/ folder to alter the default theme of the template -->

        <!-- The themes stylesheet of this template (for using specific theme color in individual elements - must included last) -->
        <link rel="stylesheet" href="/resources/css/themes.css">
        <!-- END Stylesheets -->



	<link rel="shortcut icon" href="/resources/img/g7favicon.ico">
    
        <!-- Modernizr (browser feature detection library) & Respond.js (Enable responsive CSS code on browsers that don't support it, eg IE8) -->
        <script src="/resources/js/vendor/modernizr-2.7.1-respond-1.4.2.min.js"></script>
        
    </head>
    <body>
    <!-- Add lead user -->
		<div id="check-leaduser-settings" class="modal fade" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h2 class="modal-title">Check User</h2>
					</div>

					<div class="modal-body">
						<form action="/ManageUser/CheckLead" method="post"
							class="form-horizontal" id="LeadUser_Form">
						<div class="row">
							<div class=" col-md-offset-2 col-md-8">
							<fieldset>
								<div class="form-group">
									<label class="col-md-4 control-label" for="user-settings-repassword">Mobile</label>
									<div class="col-md-8">
										<input id="mobile1" name="mobile" class="form-control"
											type="text" placeholder="mobile">
									</div>
								</div>
								<p class="help-text col-md-offset-4 col-md-8" 
									style="color: #e74c3c; display: none;" id="enteredError"></p>
									</fieldset>
								<div class="text-center">
									<button type="button" class="btn btn-sm btn-default"
										data-dismiss="modal">
										CLOSE
									</button>
									<button id="submit_leaddetails" style="margin-right: -54px;"
										type="submit" class="btn btn-sm btn-primary save">
										Check for User
									</button>
								</div>
							</div>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
<!-- Edit lead user -->
    