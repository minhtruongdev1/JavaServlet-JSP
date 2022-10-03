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
import poly.edu.service.UserService;
import poly.edu.service.VideoService;
import poly.edu.service.impl.UserServiceImpl;
import poly.edu.service.impl.VideoServiceImpl;

/**
 * Servlet implementation class VideoControllerAdmin
 */
@WebServlet(urlPatterns = {"/admin/user"}, name = "UserControllerOfAdmin")
public class UserControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser != null && currentUser.getIsAdmin()== Boolean.TRUE) {
			String action = request.getParameter("action");
			switch (action) {
			case "viewUser":
				doGetOverView(request, response);
				break;
			case "deleteUser":
				doGetDelete(request, response);
				break;
			case "addUser":
				request.setAttribute("isEdit", false);
				doGetAdd(request, response);
				break;
			case "editUser":
				request.setAttribute("isEdit", true);
				doGetEdit(request, response);
				break;
			}
		}else {
			response.sendRedirect("index");
		}
	}

	private void doGetOverView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = userService.findAll();
		request.setAttribute("users", users);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/user-overview.jsp");
        requestDispatcher.forward(request, response);
	}
	
	private void doGetDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String username = request.getParameter("username");
		User userDeleted = userService.delete(username);
		if (userDeleted != null) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
	}
	
	private void doGetAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/user-edit.jsp");
        requestDispatcher.forward(request, response);
	}
	
	private void doGetEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = userService.findByUserName(username);
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/user-edit.jsp");
        requestDispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
			String action = request.getParameter("action");
			switch (action) {
			case "addUser":
				doPostAdd(request, response);
				break;
			case "editUser":
				doPostEdit(request, response);
				break;
		}
	}
	
	private void doPostAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		
		
		User userReturn = userService.create(user);
				
		if (userReturn != null) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
	}
	
	private void doPostEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		String email = request.getParameter("email");
			
		String usernameOrigin = request.getParameter("usernameOrigin");		
		
		User user = userService.findByUserName(usernameOrigin);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		User userReturn =  userService.update(user);
		
		if (userReturn != null) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
	}
}
