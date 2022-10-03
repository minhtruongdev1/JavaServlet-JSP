package poly.edu.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.edu.constant.SessionAttr;
import poly.edu.entity.User;
import poly.edu.entity.Video;
import poly.edu.service.VideoService;
import poly.edu.service.impl.VideoServiceImpl;

/**
 * Servlet implementation class VideoControllerAdmin
 */
@WebServlet(urlPatterns = {"/admin/video"}, name = "VideoControllerOfAdmin")
public class VideoControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VideoService videoService = new VideoServiceImpl(); 
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser != null && currentUser.getIsAdmin()== Boolean.TRUE) {
			String action = request.getParameter("action");
			switch (action) {
			case "view":
				doGetOverView(request, response);
				break;
			case "delete":
				doGetDelete(request, response);
				break;
			case "add":
				request.setAttribute("isEdit", false);
				doGetAdd(request, response);
				break;
			case "edit":
				request.setAttribute("isEdit", true);
				doGetEdit(request, response);
				break;
			}
		}else {
			response.sendRedirect("index");
		}
	}

	private void doGetOverView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Video> videos = videoService.findAll();
		request.setAttribute("videos", videos);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/video-overview.jsp");
        requestDispatcher.forward(request, response);
	}
	
	private void doGetDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String href = request.getParameter("href");
		Video videoDeleted = videoService.delete(href);
		if (videoDeleted != null) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
	}
	
	private void doGetAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/video-edit.jsp");
        requestDispatcher.forward(request, response);
	}
	
	private void doGetEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String href = request.getParameter("href");
		Video video = videoService.findByHref(href);
		request.setAttribute("video", video);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/video-edit.jsp");
        requestDispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
			String action = request.getParameter("action");
			switch (action) {
			case "add":
				doPostAdd(request, response);
				break;
			case "edit":
				doPostEdit(request, response);
				break;
		}
	}
	
	private void doPostAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String href = request.getParameter("href");
		String poster = request.getParameter("poster");
		
		Video video = new Video();
		video.setTitle(title);
		video.setHref(href);
		video.setDescription(description);
		video.setPoster(poster);
		
		Video videoReturn = videoService.create(video);
		if (videoReturn != null) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
	}
	
	private void doPostEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String href = request.getParameter("href");
		String poster = request.getParameter("poster");
		String hrefOrigin = request.getParameter("hrefOrigin");
		
		Video video = videoService.findByHref(hrefOrigin);
		video.setTitle(title);
		video.setHref(href);
		video.setDescription(description);
		video.setPoster(poster);
		
		Video videoReturn = videoService.update(video);
		if (videoReturn != null) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
	}
}
