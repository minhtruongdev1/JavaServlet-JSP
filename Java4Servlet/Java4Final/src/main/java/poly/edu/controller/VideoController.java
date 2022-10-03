package poly.edu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.edu.constant.SessionAttr;
import poly.edu.entity.History;
import poly.edu.entity.User;
import poly.edu.entity.Video;
import poly.edu.service.HistoryService;

import poly.edu.service.VideoService;
import poly.edu.service.impl.HistoryServiceImpl;

import poly.edu.service.impl.VideoServiceImpl;



@WebServlet(name="/video",urlPatterns = "/video")
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private VideoService videoService = new VideoServiceImpl();
    private HistoryService historyService = new HistoryServiceImpl();
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionParam = request.getParameter("action");
		
		String href = request.getParameter("id");
		
		HttpSession session = request.getSession();
		
		switch (actionParam) {
			case "watch":
				doGetWatch(session, href, request, response);
				break;
			case "like":
				doGetLike(session, href, request, response);
				break;
		}
	}

	private void doGetWatch(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = videoService.findByHref(href);
		request.setAttribute("video", video);
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser != null) {
			History history = historyService.create(currentUser, video);
			request.setAttribute("flagLikedBtn", history.getIsLiked());
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/video-detail.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void doGetLike(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		boolean result = historyService.updateLikeOrUnlike(currentUser, href);
		if (result == true) {
			response.setStatus(204); // succeed but no data
		}else {
			response.setStatus(400);
		}
	}
}
