<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="position-relative">
	<div class="position-absolute tm-site-header">
		<div class="container-fluid position-relative">
			<div class="row">
				<div class="col">
					<div class="tm-site-nav">
						<nav class="navbar navbar-expand-lg mr-0 ml-auto" id="tm-main-nav">
							<button
								class="navbar-toggler tm-bg-black py-2 px-3 mr-0 ml-auto collapsed"
								type="button" data-toggle="collapse" data-target="#navbar-nav"
								aria-controls="navbar-nav" aria-expanded="false"
								aria-label="Toggle navigation">
								<span> <i class="fas fa-bars tm-menu-closed-icon"></i> <i
									class="fas fa-times tm-menu-opened-icon"></i>
								</span>
							</button>
							<div class="collapse navbar-collapse tm-nav" id="navbar-nav">
								<ul class="navbar-nav text-uppercase mx-auto">
									<c:choose>
										<c:when test="${not empty sessionScope.currentUser}">
											<li class="nav-item active"><a
												class="nav-link tm-nav-link" href="admin" >
													Welcome,${sessionScope.currentUser.username}</a></li>
											<li class="nav-item"><a class="nav-link tm-nav-link"
												href="favorites">My favorites</a></li>
											<li class="nav-item"><a class="nav-link tm-nav-link"
												href="history">History</a></li>
												<li class="nav-item"><a class="nav-link nav-link-2"
							data-toggle="modal" data-target="#changePassModel">Change
								Password</a></li>
											<li class="nav-item"><a class="nav-link tm-nav-link"
												href="logout">Log out</a></li>
										</c:when>
										<c:otherwise>

											<li class="nav-item"><a class="nav-link tm-nav-link"
												href="login">Login</a></li>
											<li class="nav-item"><a class="nav-link tm-nav-link"
												href="register">Register</a></li>
											<li class="nav-item"><a class="nav-link tm-nav-link"
												href="forgotpass">Forgot password</a></li>

										</c:otherwise>
									</c:choose>

								</ul>
							</div>
						</nav>
					</div>
				</div>

			</div>
			<div class="tm-welcome-container text-center text-white">
				<div class="tm-welcome-container-inner">

					<a href="#content"
						class="btn tm-btn-animate tm-btn-cta tm-icon-down"> <span>Xem
							tiếp</span>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="tm-video-container">
	<video autoplay muted loop id="tm-video">
		<!-- <source src="video/sunset-timelapse-video.mp4" type="video/mp4"> -->
		<source src="<c:url value='WebSite/User/video/taki.mp4'/>"
			type="video/mp4">
	</video>

</div>


-
<i id="tm-video-control-button" class="fas fa-pause"></i>
</div>

<!-- Modal -->
<div class="modal fade" id="changePassModel" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Change Password</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<input type="password" name="currentPassword" id="currentPassword"
						class="form-control rounded-0" placeholder="Current Password"
						required />
				</div>
				<div class="form-group">
					<input type="password" name="newPassword" id="newPassword"
						class="form-control rounded-0" placeholder="New Password" required />
				</div>
				<h5 style="color: red" id="messageChangePass"></h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-success" id="changePassBtn">Save
					changes</button>
			</div>
		</div>
	</div>
</div>