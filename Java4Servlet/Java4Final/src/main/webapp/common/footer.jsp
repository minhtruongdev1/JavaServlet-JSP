<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Subscribe form and footer links -->
<div class="row mt-5 pt-3">
	<div class="col-xl-6 col-lg-12 mb-4">
		<div class="tm-bg-gray p-5 h-100">
			<h3 class="tm-text-primary mb-3">Liên hệ với chúng tôi</h3>
			<p class="mb-5">Vui lòng nhập mail để nhận những thông tin mới
				nhất cảm ơn</p>
			<form action="" method="GET" class="tm-subscribe-form">
				<input type="text" name="email" placeholder="Your Email..." required>
				<button type="submit" class="btn rounded-0 btn-primary tm-btn-small">Subscribe</button>
			</form>
		</div>
	</div>
	<div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12 mb-4">
		<div class="p-5 tm-bg-gray">
			<h3 class="tm-text-primary mb-4">Các liên kết</h3>
			<ul class="list-unstyled tm-footer-links">
				<li><a href="#">Facebook</a></li>
				<li><a href="#">Youtobe</a></li>
				<li><a href="#">Instagram</a></li>
				<li><p>Gmail:Truonglm0@fpt.edu.vn</p></li>
				<li>Số điện thoại:0396294167</li>
			</ul>
		</div>
	</div>
	<div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12 mb-4">
		<div class="p-5 tm-bg-gray h-100">
			<h3 class="tm-text-primary mb-4">Các trang khác</h3>
			<ul class="list-unstyled tm-footer-links">
				<li><a href="#">Our Videos</a></li>
				<li><a href="#">License Terms</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="#">Contact</a></li>
				<li><a href="#">Privacy Policies</a></li>
			</ul>
		</div>
	</div>
</div>
<!-- row -->

<footer class="row pt-5">
	<div class="col-12">
		<p class="text-right">
			Copyright 2020 Trailer Website </a>
		</p>
	</div>
</footer>

<script src="<c:url value='/Website/User/js/jquery-3.4.1.min.js'/>"></script>
<script src="<c:url value='/Website/User/js/bootstrap.min.js'/>"></script>
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