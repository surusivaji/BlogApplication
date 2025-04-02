<%@page import="org.siva.blog.dao.LikeDaoImpl"%>
<%@page import="org.siva.blog.dao.LikeDao"%>
<%@page import="org.siva.blog.entities.User"%>
<%@page import="java.util.List" %>
<%@page import="org.siva.blog.entities.Post"%>
<%@page import="org.siva.blog.dao.PostDao"%>
<%@page import="org.siva.blog.dao.PostDaoImpl"%>
<div class="row">
	<%
	User user = (User) session.getAttribute("user");
	Thread.sleep(1000);
	PostDao dao = new PostDaoImpl();
	Integer catId = Integer.parseInt(request.getParameter("cid"));
	List<Post> posts = null;
	if (catId==0) {
		posts = dao.getAllPosts();
	}
	else {
		posts = dao.getPostByCategory(catId);
	}
	if (posts.size()==0) {
		out.println("<h3 class='dispay-3 text-center fw-bold text-danger'>No posts available in the category...</h3>");
		return;
	}
	else {	
		for (Post post : posts) 
		{%> 
			<div class="col-md-4 mb-3">
				<div class="card" style="box-shadow: 0 0 2px 2px #B2B2B2;">
				  <img src="./blog_posts/<%=post.getPpic() %>" alt="post pic" height="200px">
				  <div class="card-body">
				    <b><%= post.getPtitle() %></b>
				    <p>
				     <% String content = post.getPcontent();
				         String smallContent = content.substring(0, 20);
				      %>
				    <%= smallContent %>...
				    </p>
				  </div>
				  <div class="card-footer text-center primary-background">
				  	<%
				  		LikeDao likeDao = new LikeDaoImpl();
				  	%>
				  	<a onclick="doLike(<%= user.getId() %>, <%= post.getPid() %>)" class="btn btn-outline-light btn-sm"> <span class="fa fa-thumbs-o-up"></span> <span class="like-counter"><%= likeDao.countLikesOnPost(post.getPid())  %></span></a>
				  	<a href="show_post.jsp?postId=<%=post.getPid() %>" class="btn btn-outline-light btn-sm"> Read More... </a>
				  	<a class="btn btn-outline-light btn-sm"> <span class="fa fa-commenting-o"></span> <span>20</span></a>
				  </div>
				</div>
			</div>
		<%}
	}
%>
</div>