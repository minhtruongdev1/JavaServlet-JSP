package poly.edu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


@WebServlet(urlPatterns = {"/index", "/favorites", "/history"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int VIDEO_MAX_PAGE_SIZE = 4;
    private VideoService videoService = new VideoServiceImpl();
    private HistoryService historyService = new HistoryServiceImpl();
     
    @Override
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       	HttpSession session = request.getSession();
       	String path = request.getServletPath(); // phuc vu re nhanh
   		switch (path) {
   			case "/index":
   				doGetIndex(request, response);
   				break;
   			case "/favorites":
   				doGetFavorites(session, request, response);
   				break;
   			case "/history":
   				doGetHistory(session, request, response);
   				break;
   		}
   	}

       private void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       	List<Video> countVideo = videoService.findAll();
       	int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
       	request.setAttribute("maxPage", maxPage);		
       	//n video, muốn chia mỗi trang có 4 video >>> có tổng cộng n/4 = n trang
       	List<Video> videos;
       	String pageNumber = request.getParameter("page");
       	
       	if (pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
       		 videos = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
       		 request.setAttribute("currentPage", 1);
       	} else {
       		 videos = videoService.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
       		 request.setAttribute("currentPage", Integer.valueOf(pageNumber));
       	}
       	
       	request.setAttribute("videos", videos); 
       	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/index.jsp");
   		requestDispatcher.forward(request, response);
   	}
       
       private void doGetFavorites(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       	User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
           List<History> histories = historyService.findByUserAndIsLiked(user.getUsername());
       	List<Video> videos = new ArrayList<>();
       	histories.forEach(item -> videos.add(item.getVideo()));
       	/*
       	 	for (int i = 0; i < histories.size(); i++){
       	 		videos.add(histories.get(i).getVideo());
       	 	}
       	 */
       	request.setAttribute("videos", videos); 
       	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/favorites.jsp");
   		requestDispatcher.forward(request, response);
   	}
       
       private void doGetHistory(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       	User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
           List<History> histories = historyService.findByUser(user.getUsername());
       	List<Video> videos = new ArrayList<>();
       	histories.forEach(item -> videos.add(item.getVideo()));
       	/*
       	 	for (int i = 0; i < histories.size(); i++){
       	 		videos.add(histories.get(i).getVideo());
       	 	}
       	 */
       	request.setAttribute("videos", videos); 
       	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/history.jsp");
   		requestDispatcher.forward(request, response);
   	}
   	
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		// TODO Auto-generated method stub
   		doGet(request, response);
   	}

}
