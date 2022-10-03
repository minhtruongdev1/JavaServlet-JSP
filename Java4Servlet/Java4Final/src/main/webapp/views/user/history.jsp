<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HistoryVideo</title>
<%@ include file="/common/head.jsp"%>

</head>

<body>
	<div class="tm-page-wrap mx-auto">
		<%@ include file="/common/header.jsp"%>

		<div class="container-fluid">
			<div id="content" class="mx-auto tm-content-container">
				<main>
					<div class="row">
						<div class="col-12">
							<h2 class="tm-page-title mb-4">Video được đề xuất</h2>
							<div class="tm-categories-container mb-5">
								<h3 class="tm-text-primary tm-categories-text">Khám phá:</h3>
								<ul class="nav tm-category-list">
									<li class="nav-item tm-category-item"><a href="#"
										class="nav-link tm-category-link active">Tất cả</a></li>
									<li class="nav-item tm-category-item"><a href="#"
										class="nav-link tm-category-link">Âm nhạc</a></li>
									<li class="nav-item tm-category-item"><a href="#"
										class="nav-link tm-category-link">Trò chơi</a></li>
									<li class="nav-item tm-category-item"><a href="#"
										class="nav-link tm-category-link">Bóng đá</a></li>
									<li class="nav-item tm-category-item"><a href="#"
										class="nav-link tm-category-link">Hoạt hình</a></li>
								</ul>
							</div>
						</div>
					</div>

					<div class="row tm-catalog-item-list">
						<c:forEach items="${videos }" var="video">
							<div class="col-lg-4 col-md-6 col-sm-12 tm-catalog-item">

								<div class="position-relative tm-thumbnail-container">
									<img src="<c:url value='${video.poster}'/>"alt="Image" class="img-fluid tm-catalog-item-img"> <a
										href="<c:url value='/video?action=watch&id=${video.href}' />"
										class="position-absolute tm-img-overlay"> <i
										class="fas fa-play tm-overlay-icon"></i>
									</a>
								</div>
								<div class="p-4 tm-bg-gray tm-catalog-item-description">
									<h3 class="tm-text-primary mb-3 tm-catalog-item-title">${video.title}</h3>
									<p class="tm-catalog-item-text">
										Video thumbnail has a link to another HTML page. Categories <span
											class="tm-text-secondary">do not need</span> any JS. They are
										just separated HTML pages.
									</p>
									<span>${video.shares} shares</span> <span>${video.views}
										views</span>

								</div>
							</div>
						</c:forEach>


					</div>


					<!-- Catalog Paging Buttons -->
					<div>
						<ul class="nav tm-paging-links">
							<li class="nav-item active"><a href="#"
								class="nav-link tm-paging-link">1</a></li>
							<li class="nav-item"><a href="#"
								class="nav-link tm-paging-link">2</a></li>
							<li class="nav-item"><a href="#"
								class="nav-link tm-paging-link">3</a></li>
							<li class="nav-item"><a href="#"
								class="nav-link tm-paging-link">4</a></li>
							<li class="nav-item"><a href="#"
								class="nav-link tm-paging-link">></a></li>
						</ul>
					</div>
				</main>

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