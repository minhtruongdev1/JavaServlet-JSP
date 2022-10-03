package poly.edu.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.poly.dto.UserDto;
import edu.poly.dto.VideoLikedInfo;
import poly.edu.constant.SessionAttr;
import poly.edu.entity.History;
import poly.edu.entity.User;
import poly.edu.entity.Video;
import poly.edu.service.HistoryService;
import poly.edu.service.StatsService;
import poly.edu.service.UserService;
import poly.edu.service.VideoService;
import poly.edu.service.impl.HistoryServiceImpl;
import poly.edu.service.impl.StatsServiceImpl;
import poly.edu.service.impl.UserServiceImpl;
import poly.edu.service.impl.VideoServiceImpl;

/**
 * Servlet implementation class HomeControllerAdmin
 */
@WebServlet(urlPatterns = {"/admin", "/admin/favorites"}, name = "HomeControllerOfAdmin")
public class HomeControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private StatsService statsServive = new StatsServiceImpl();
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
			String path = request.getServletPath(); // phuc vu re nhanh
			switch (path) {
				case "/admin":
					doGetHome(request, response);
					break;
				case "/admin/favorites":
					doGetFavorites(request, response);
					break;
			}
		}else {
			response.sendRedirect("index");
		}
    }

	
	private void doGetHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<VideoLikedInfo> videos = statsServive.findVideoLikedInfo();
		request.setAttribute("videos", videos);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/home.jsp");
        requestDispatcher.forward(request, response);
	}
	
	private void doGetFavorites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String videoHref = request.getParameter("href");
		List<UserDto> users = userService.findUsersLikedVideoByVideoHref(videoHref);
		if (users.isEmpty()) {
			response.setStatus(400);
		} else {
			ObjectMapper mapper = new ObjectMapper();
			String dataResponse = mapper.writeValueAsString(users);
			response.setStatus(200);
			out.print(dataResponse);
			out.flush();   
		}
	}

}
