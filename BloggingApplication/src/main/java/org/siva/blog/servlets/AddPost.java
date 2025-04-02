package org.siva.blog.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.siva.blog.dao.PostDao;
import org.siva.blog.dao.PostDaoImpl;
import org.siva.blog.entities.Post;
import org.siva.blog.entities.User;

@MultipartConfig
@WebServlet("/addPost")
public class AddPost extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String pTitle = request.getParameter("ptitle");
		String pContent = request.getParameter("pcontent");
		String pCode = request.getParameter("pcode");
		Part part = request.getPart("pic");
		String temp = request.getParameter("catid");
		Integer catid = Integer.parseInt(temp);
		
		//pid, ptitle, pcontent, pcode, ppic, pdate, catid, userid
		Post post = new Post();
		post.setPtitle(pTitle);
		post.setPcontent(pContent);
		post.setPcode(pCode);
		post.setPpic(part.getSubmittedFileName());
		post.setDate(Date.valueOf(LocalDate.now()));
		post.setCid(catid);
		User user = (User) session.getAttribute("user");
		post.setUserid(user.getId());
		
		PrintWriter out = response.getWriter();
		
		PostDao dao = new PostDaoImpl();
		boolean savePost = dao.savePost(post);
		if (savePost) {
			String path = request.getRealPath("/")+"blog_posts"+File.separator+part.getSubmittedFileName();
			InputStream inputStream = part.getInputStream();
			byte[] b = new byte[inputStream.available()];
			inputStream.read(b);
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(b);
			fos.flush();
			fos.close();
			out.println("done");
		}
		else {
			out.println("error");
		}
		
	}

}
