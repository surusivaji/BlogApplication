package org.siva.blog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.siva.blog.dao.CategoryDao;
import org.siva.blog.dao.CategoryDaoImpl;

@WebServlet("/saveCategory")
public class SaveCategory extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String name = request.getParameter("categoryName");
		String description = request.getParameter("description");
		
		CategoryDao categoryDao = new CategoryDaoImpl();
		int addCategory = categoryDao.addCategory(name, description);
		
		if (addCategory!=0) { 
			session.setAttribute("successMsg", "category added successfully..!!");
		}
		else {
			session.setAttribute("failMsg", "something went wrong..!!");
		}
		response.sendRedirect("home.jsp");
	}
	
	

}
