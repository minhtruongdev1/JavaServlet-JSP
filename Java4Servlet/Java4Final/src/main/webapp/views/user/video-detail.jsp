<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${video.title}</title>
<%@ include file="/common/head.jsp"%>

</head>

<body>
	<div class="tm-page-wrap mx-auto">
		<%@ include file="/common/header.jsp"%>

		<!-- Page content -->
		<div class="container-fluid">
			<div class="mx-auto tm-content-container">
				<main>
					<div class="row mb-5 pb-4">
						<div class="col-12">
							<!-- Video player 1422x800 -->

							<iframe id="tm-video"
								src="https://www.youtube.com/embed/${video.href}"></iframe>

						</div>
					</div>
					<div class="row mb-5 pb-5">
						<div class="col-xl-8 col-lg-7">
							<!-- Video description -->
							<div class="tm-video-description-box">
								<h2 class="mb-5 tm-video-title">${video.title}</h2>
								<p class="mb-4">${video.description}</p>

							</div>
						</div>
						<div class="col-xl-4 col-lg-5">
							<!-- Share box -->
							<div class="tm-bg-gray tm-share-box">
								<h6 class="tm-share-box-title mb-4">Share this video</h6>
								<div class="mb-5 d-flex">
									<a href="" class="tm-bg-white tm-share-button"><i
										class="fab fa-facebook"></i></a> <a href=""
										class="tm-bg-white tm-share-button"><i
										class="fab fa-twitter"></i></a> <a href=""
										class="tm-bg-white tm-share-button"><i
										class="fab fa-pinterest"></i></a> <a href=""
										class="tm-bg-white tm-share-button"><i
										class="far fa-envelope"></i></a>
								</div>

								<a href=""
								>
									<c:if test="${not empty sessionScope.currentUser}">
										<div class="text-center mb-5">
											<button id="likeOrUnlikeBtn"
												class="btn btn-primary tm-btn-big">
												<c:choose>
													<c:when test="${flagLikedBtn == true}">
                        						Unlike
                        						</c:when>
													<c:otherwise>Like</c:otherwise>
												</c:choose>
											</button>
										</div>
										
									</c:if>
								</a>
								<div>
									<button
										class="btn btn-primary p-0 tm-btn-animate tm-btn-download tm-icon-download">
										<span>Download Video</span>
									</button>
								</div>
							</div>
						</div>
						 <input id="videoIdHdn" type="hidden" value="${video.href}" />
					</div>

					<%@ include file="/common/footer.jsp"%>
			</div>
			<!-- tm-content-container -->
		</div>

	</div>
	
	<script>
		$('#likeOrUnlikeBtn').click(function() {
			var videoId = $('#videoIdHdn').val();
			$.ajax({
				url: 'video?action=like&id=' + videoId
			}).then(function(data) {
				var text = $('#likeOrUnlikeBtn').text();
				if (text.indexOf('Like') != -1){
					$('#likeOrUnlikeBtn').text('Unlike');
				}else{
					$('#likeOrUnlikeBtn').text('Like');
				}
			}).fail(function(error) {
				alert('Oops!!! Please try again ^^');
			});
		});
	</script>	
</body>

</html>