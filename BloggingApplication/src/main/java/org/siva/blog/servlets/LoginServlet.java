package org.siva.blog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.siva.blog.dao.UserDao;
import org.siva.blog.dao.UserDaoImpl;
import org.siva.blog.entities.User;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDao dao = new UserDaoImpl();
		User user = dao.findUserByEmailAndPassword(email, password);
		
		if (user!=null) {
			session.setAttribute("user", user);
			response.sendRedirect("home.jsp");
		}
		else {
			session.setAttribute("failMsg", "Invalid credientials");
			response.sendRedirect("login.jsp");
		}
		
	}

}
