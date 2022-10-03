<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<%@ include file="/common/head.jsp"%>

</head>

<body class="my-login-page">
	<%@ include file="/common/header.jsp"%>
	<section>
		<div class="container ">
			<div class="col-7 mx-auto">
				<div class="card-wrapper">
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title">Login</h4>
							<form method="POST" class="tm-contact-form mx-auto" id="login-form"
								action="login">
								<div class="form-group">
									<label for="id">Username</label> <input type="text"
										name="username" class="form-control rounded-0"
										placeholder="Username" required />
									<div class="invalid-feedback">User-ID is required</div>
								</div>

								<div class="form-group">
									<label for="password">Password </label> <input type="password"
										name="password" class="form-control rounded-0"
										placeholder="Password" required />
									<div class="invalid-feedback">Password is required</div>
								</div>

								<div class="form-group">
									<div class="custom-checkbox custom-control">
										<input type="checkbox" name="remember" id="remember"
											class="custom-control-input"> <label for="remember"
											class="custom-control-label">Remember Me</label>
									</div>
								</div>

								<div class="form-group m-0">
									<button type="submit" class="btn btn-primary btn-block"">
										Login</button>
								</div>
								<div class="mt-4 text-center">
									Don't have an account? <a href="">Create One</a> <a href=""
										class="float-right"> Forgot Password? </a>
								</div>
							</form>
						</div>
					</div>
					<div class="footer"></div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="/common/footer.jsp"%>
	</div>
	<!-- tm-content-container -->
	</div>

	</div>
	<!-- .tm-page-wrap -->

	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
        // set kích thước video
        function setVideoSize() {
            const vidWidth = 1920;
            const vidHeight = 1080;
            let windowWidth = window.innerWidth;
            let newVidWidth = windowWidth;
            let newVidHeight = windowWidth * vidHeight / vidWidth;
            let marginLeft = 0;
            let marginTop = 0;

            if (newVidHeight < 500) {
                newVidHeight = 500;
                newVidWidth = newVidHeight * vidWidth / vidHeight;
            }

            if(newVidWidth > windowWidth) {
                marginLeft = -((newVidWidth - windowWidth) / 2);
            }

            if(newVidHeight > 720) {
                marginTop = -((newVidHeight - $('#tm-video-container').height()) / 2);
            }

            const tmVideo = $('#tm-video');

            tmVideo.css('width', newVidWidth);
            tmVideo.css('height', newVidHeight);
            tmVideo.css('margin-left', marginLeft);
            tmVideo.css('margin-top', marginTop);
        }

        $(document).ready(function () {
            /************** Video background *********/

            setVideoSize();

            // Set video background size based on window size
            let timeout;
            window.onresize = function () {
                clearTimeout(timeout);
                timeout = setTimeout(setVideoSize, 100);
            };

            // Play/Pause button for video background      
            const btn = $("#tm-video-control-button");

            btn.on("click", function (e) {
                const video = document.getElementById("tm-video");
                $(this).removeClass();

                if (video.paused) {
                    video.play();
                    $(this).addClass("fas fa-pause");
                } else {
                    video.pause();
                    $(this).addClass("fas fa-play");
                }
            });
        })
    </script>
</body>

</html>