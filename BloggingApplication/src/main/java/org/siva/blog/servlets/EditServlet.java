package org.siva.blog.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.siva.blog.dao.UserDao;
import org.siva.blog.dao.UserDaoImpl;
import org.siva.blog.entities.User;

@MultipartConfig
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String about = request.getParameter("about");
		Part part = request.getPart("profile");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setAbout(about);
		String oldFile = user.getProfile();
		user.setProfile(part.getSubmittedFileName());			
		
		UserDao dao = new UserDaoImpl();
		int updateUser = dao.updateUser(user);
		if (updateUser!=0) {
			String path = request.getRealPath("/")+"pics"+File.separator+user.getProfile();
			String deleteFile = request.getRealPath("/")+"pics"+File.separator+oldFile;
			if (!oldFile.equals("default.png")) {
				File file = new File(deleteFile);
				file.delete();
			}
			InputStream ios = part.getInputStream();
			byte[] b = new byte[ios.available()];
			ios.read(b);
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(b);
			fos.flush();
			fos.close();
			session.setAttribute("successMsg", "Profile updated successfully...!!!");
			response.sendRedirect("home.jsp");
		}
		else {
			session.setAttribute("failMsg", "Something went wrong...!!!");
			response.sendRedirect("home.jsp");
		}
		
	}

}
