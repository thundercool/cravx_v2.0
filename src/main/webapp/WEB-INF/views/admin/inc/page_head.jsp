<style>
.els {
	overflow: hidden;
	text-overflow: ellipsis;
	-o-text-overflow: ellipsis;
	white-space: nowrap;
	width: 100%;
}
</style>
<div id="page-container" >
	<div id="sidebar">
		<div class="sidebar-scroll">
			<div class="sidebar-content">
				<!-- Brand -->
				<a style="background-color: #f9fafc;" href="/Index" class="sidebar-brand">
					<img style="width:149px; border:0px; height:50px; border-radius:0px;" src="/resources/img/gourmet7-admin.png">
				</a>
				<!-- END Brand -->
				<!-- User Info -->
				<div class="sidebar-section sidebar-user clearfix">
					<div class="sidebar-user-avatar">


						<img
							src="/resources/img/application/default_profile.png"
							alt="avatar" title="Click here to change photo"
							onclick="OpenFileDialog();">

					
						<form action="/ChangePhoto" id="changePhoto"
						method="post" enctype="multipart/form-data">
						<input type="file" id="imageChooser" style="display: none;"
							onchange="changePhoto();" name="imageChooser"
							accept="image/jpg,image/jpeg,image/png,image/gif,image/bmp,image/tiff" />
					</form>

					</div>

					<div class="sidebar-user-name els"></div>
					<div class="sidebar-user-links">
						<a href="/Admin/EditAdminProfile"
							data-toggle="tooltip" data-placement="bottom" title="Profile"><i
							class="gi gi-user"></i></a> <a href="#logout_popup"
							data-toggle="modal" data-placement="bottom"
							class="enable-tooltip" title="Logout"><i class="gi gi-exit"></i></a>
					</div>
				</div>
				<ul class="sidebar-nav">
					<li><a href="/Index" class=" active"
						id="dashboard"><i class="gi gi-stopwatch sidebar-nav-icon"></i></a></li>
						<li class="sidebar-header"><span
							class="sidebar-header-options clearfix"><i
								class="gi gi-settings"></i></span> <span class="sidebar-header-title">Manage Restaurants</span></li>
						<li class="sidebar-header"><span
							class="sidebar-header-options clearfix"><i
								class="gi gi-settings"></i></span> <span class="sidebar-header-title">Manage Brand</span></li>
						<li class="sidebar-header"><span
							class="sidebar-header-options clearfix"><i
								class="gi gi-settings"></i></span> <span class="sidebar-header-title">Brand Management</span></li>
					
						<li id="manageCompany"><a href="#" class="sidebar-nav-menu"><i
								class="fa fa-angle-left sidebar-nav-indicator"></i><i
								class="gi gi-crown sidebar-nav-icon"></i> Manage Company</a>
							<ul>
								<li><a href="/ManageCompany/CompanyList"
									id="companyList">Company List</a></li>
							</ul></li>
							</ul>
							
							


				
			
			</div>
			</div>
			</div>
			</div>
			
    
		    
	
		</header>
		<!-- END Header -->