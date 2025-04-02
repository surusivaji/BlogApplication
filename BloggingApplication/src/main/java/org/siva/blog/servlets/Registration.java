package org.siva.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.siva.blog.dao.UserDao;
import org.siva.blog.dao.UserDaoImpl;
import org.siva.blog.entities.User;

@MultipartConfig
@WebServlet("/RegisterServlet")
public class Registration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("user_name");
		String email = request.getParameter("user_email");
		String password = request.getParameter("user_password");
		String gender = request.getParameter("gender");
		String about = request.getParameter("about");
		
		PrintWriter out = response.getWriter();
		
		String checkbox = request.getParameter("check");
		if (checkbox!=null) {
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setEmail(email);
			user.setGender(gender);
			user.setAbout(about);
			user.setRdate(Date.valueOf(LocalDate.now()));
			user.setProfile("default.png");
			
			UserDao dao = new UserDaoImpl();
			int saveUser = dao.saveUser(user);
			if (saveUser!=0) {
				out.println("done");
			}
			else {
				out.println("something went wrong");
			}
		}
		else {
			out.println("please check the terms and conditions");
		}
		
	}
	

}
