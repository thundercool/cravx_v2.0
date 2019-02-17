<%
/**
 * page_footer.jsp
 *
 * Author: pixelcave
 *
 * The footer of each page
 *
 */
%>
        <!-- Footer -->
		<footer class="clearfix" style="left:0;background-color: #4f4f4f;color:#fec408;padding: 0px ;">
			<div class="row" style="padding: 0px 15px;">
				<div class="col-md-6 " style="padding: 9px 10px;">
					<div class="pull-left">
					   Technology Partner : <a href="http://www.astrikainfotech.com" target="_blank">Astrika Infotech <span class="hidden-xs">Pvt. Ltd.</span></a>
					</div>
				</div>
				<div class="col-md-6 " style="padding: 9px 10px;">
					<div class="pull-left" >
						<span id="year-copy">2014-16</span> &copy; <a href="http://www.overtheedgefoodsolution.com" target="_blank">OTEFSPL</a>
					</div>
				</div>
			</div>
		</footer>
        <!-- END Footer -->
    </div>
    <!-- END Main Container -->
</div>
<!-- END Page Container -->

<!-- Scroll to top link, initialized in js/app.js - scrollToTop() -->
<a href="#" id="to-top"><i class="fa fa-angle-double-up"></i></a>

<!-- User Settings, modal which opens from Settings link (found in top right user menu) and the Cog link (found in sidebar user info) -->



<div id="logout_popup" class="modal fade" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<form action="" method="post" class="form-horizontal form-bordered"
					id="fg">
					<div style="padding: 10px; height: 110px;">
						<label>Are you Sure want to logout?</label>
						<div class="col-xs-12 text-right">
							<button type="button" class="btn btn-sm btn-default"
								data-dismiss="modal">No</button>
							<a class="btn btn-sm btn-primary save" href="<c:url value="/j_spring_security_logout"/>"></i>
							Yes </a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<a href="#contact_null_popup" id="contact_null" data-toggle="modal" style="display:none;" ></a>
<div id="contact_null_popup" class="modal fade" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<form action="" method="post" class="form-horizontal form-bordered"
					id="contact_null_form">
					<div style="padding: 10px; height: 110px;">
						<label>Claim</label>
						<div class="col-xs-12 text-right">
							<button type="button" class="btn btn-sm btn-default"
								data-dismiss="modal">OK</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>



<div id="underConst_popup" class="modal fade" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 402px; height: 257px;">
			<div class="modal-body">
				<div style="margin-top: -20px; margin-left: -20px; height: 257px; width: 400px;">
					<a href="#" data-dismiss="modal" style="margin-top: -6px; position: fixed; margin-left: 385px;"
						class="btn btn-xs btn-danger"><i class="fa fa-times"></i></a>
					<img src="/resources/img/application/under_const.png" >
				</div>
			</div>
		</div>
	</div>
</div>


</div>
<!-- END User Settings -->
