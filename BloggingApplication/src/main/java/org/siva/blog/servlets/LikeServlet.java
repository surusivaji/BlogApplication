package org.siva.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.siva.blog.dao.LikeDao;
import org.siva.blog.dao.LikeDaoImpl;

@WebServlet("/likeServlet")
public class LikeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operation = request.getParameter("operation");
		int uid = Integer.parseInt(request.getParameter("uid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		PrintWriter out = response.getWriter();
		
		LikeDao likeDao = new LikeDaoImpl();
		
		if (operation.equals("like")) {
			boolean insertLike = likeDao.insertLike(uid, pid);
			out.println(insertLike);
		}
		else {
			out.print(false);
		}
		
	}

}
