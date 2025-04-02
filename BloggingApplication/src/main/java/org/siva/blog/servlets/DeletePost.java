package org.siva.blog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.siva.blog.dao.LikeDao;
import org.siva.blog.dao.LikeDaoImpl;
import org.siva.blog.dao.PostDao;
import org.siva.blog.dao.PostDaoImpl;
import org.siva.blog.entities.User;

@WebServlet("/deletePost")
public class DeletePost extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int postId = Integer.parseInt(request.getParameter("postId"));
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		LikeDao likeDao = new LikeDaoImpl();
		boolean deleteLike = likeDao.deleteLike(user.getId(), postId);
		
		PostDao dao = new PostDaoImpl();
		int deletePost = dao.deletePost(postId);
		if (deletePost!=0 && deleteLike) {
			session.setAttribute("successMsg", "post deleted");
		}
		else {
			session.setAttribute("failMsg", "something went wrong");
		}
		response.sendRedirect("home.jsp");
	}

}
