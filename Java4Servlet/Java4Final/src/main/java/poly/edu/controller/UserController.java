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
import poly.edu.entity.User;
import poly.edu.service.UserService;
import poly.edu.service.impl.UserServiceImpl;


/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {"/login", "/logout", "/register", "/forgotPass", "/changePass"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService = new UserServiceImpl();
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getServletPath(); // phuc vu re nhanh
		switch (path) {
			case "/login":
				doGetLogin(request, response);
				break;
			case "/register":
				doGetRegister(request, response);
				break;
			case "/logout":
				doGetLogout(session ,request, response);
				break;
			case "/forgotPass":
				doGetForgotPass(request, response);
				break;
		}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//Luu session dang nhap
		String path = request.getServletPath(); // phuc vu re nhanh
		switch (path) {
			case "/login":
				doPostLogin(session, request, response);
				break;
			case "/register":
				doPostRegister(session, request, response);
				break;
			case "/forgotPass":
				doPostForgotPass(request, response);
				break;
			case "/changePass":
				doPostChangePass(session, request, response);
				break;
		}
	}

    private void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/login.jsp");
		requestDispatcher.forward(request, response);
	}
    
    private void doGetRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/register.jsp");
		requestDispatcher.forward(request, response);
	}
    
   private void doGetLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   session.removeAttribute(SessionAttr.CURRENT_USER);
	   response.sendRedirect("index");
	}

   private void doGetForgotPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/forgotpass.jsp");
		requestDispatcher.forward(request, response);
	}
   
   private void doPostLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = userService.login(username, password);
		
		if (user != null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			response.sendRedirect("index");
			System.out.println("Dang nhap thang cong");
		}else {
			response.sendRedirect("login");
			System.out.println("Dang nhap that bai");
		}
	}
   private void doPostRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		User user = userService.register(username, password, email);
		
		if (user != null) {
//			emailService.sendEmail(getServletContext(), user, "welcome");
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			response.sendRedirect("index");
		}else {
			response.sendRedirect("register");
		}
	}
   
   private void doPostForgotPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	   response.setContentType("application/json");
//	   String email = request.getParameter("email");
//	   User userWithNewPass = userService.resetPassword(email);
//	   
//	   if (userWithNewPass != null) {
//		   emailService.sendEmail(getServletContext(), userWithNewPass, "forgot");
//		   response.setStatus(204);
//	   } else {
//		   response.setStatus(400);
//	   }
	}
   
   private void doPostChangePass(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	   response.setContentType("application/json");
//	   String currentPassword = request.getParameter("currentPassword");
//	   String newPassword = request.getParameter("newPassword");
//	   
//	   User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
//	   
//	   if (currentUser.getPassword().equals(currentPassword)) {
//		   currentUser.setPassword(newPassword);
//		   User updatedUser = userService.update(currentUser);
//		   if (updatedUser != null) {
//			   session.setAttribute(SessionAttr.CURRENT_USER, updatedUser);
//			   response.setStatus(204);
//		   } else {
//			   response.setStatus(400);
//		   }
//	   }
	}
   
   
}
