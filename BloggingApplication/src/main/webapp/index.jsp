<%@page import="org.siva.blog.dao.LikeDaoImpl"%>
<%@page import="org.siva.blog.dao.LikeDao"%>
<%@page import="org.siva.blog.entities.Post"%>
<%@page import="org.siva.blog.dao.PostDaoImpl"%>
<%@page import="org.siva.blog.dao.PostDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
	<head>
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>Tech blog</title>
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    	<link rel="stylesheet" href="./css/style.css">
    	<style type="text/css">
    		.banner-background {
             	clip-path: polygon(30% 0%, 70% 0%, 100% 0, 100% 91%, 63% 100%, 22% 91%, 0 99%, 0 0);;
            }
    	</style>
 	</head>
	<body>
  		<!-- navbar -->
  		<%@ include file="navbar.jsp" %>
  		
  		<!-- Banner -->
  		<div class="container-fluid primary-background banner-background text-white p-0 m-0">
  			 <div class="container">
		        <div class="text p-5">
		            <h1 class="display-3">Welcome to TechBlog</h1>
		            <p>
		                This techblog will tells the complete java full stack course. it will contain
		                the java, advanced java, spring, hibernate, html, css, java script, mysql technologies.
		                In this blog we will completely focusing on java full stack course.
		            </p>
		            <p>
			            java full stack course is used to web application. it will conclude both frontend as well as
			            backend. and also i will programs sample programs for each content.
		            </p>
		            <a href="./register.jsp" class="btn btn-outline-light btn-lg"> <span class="fa fa-user-plus"></span> Start ! its Free</a>
	  				<a href="./login.jsp" class="btn btn-outline-light btn-lg"> <span class="fa fa-user-circle fa-spin"></span> Login</a>
		        </div>
    		</div>
  		</div>
  		
  		<%
  			PostDao postDao = new PostDaoImpl();
		    List<Post> posts = postDao.getAllPosts();
  		%>
  		
  		<!-- latest blogs -->
  		<div class="container mt-3">
  			<div class="row mb-3 offset-md-2">
  				<%
  					for (Post post : posts) 
  					{%>
					<div class="col-md-8">
						<div class="card mb-3" style="box-shadow: 0 0 2px 2px #B2B2B2;">
						  <img src="./blog_posts/<%=post.getPpic() %>" alt="post pic" height="200px">
						  <div class="card-body">
						    <b class="text-danger fs-4"><%= post.getPtitle() %></b>
						    <p>
						    	<%= post.getPcontent() %>
						    </p>
						  </div>
						  <div class="card-footer text-center primary-background">
						  	<%
						  		LikeDao likeDao = new LikeDaoImpl();
						  	%>
						  	<a href="login.jsp" class="btn btn-outline-light btn-sm"> <span class="fa fa-thumbs-o-up"></span> <span class="like-counter"><%= likeDao.countLikesOnPost(post.getPid())  %></span></a>
						  	<a href="login.jsp" class="btn btn-outline-light btn-sm"> Read More... </a>
						  </div>
						</div>
					</div>
  					<%}
  				%>
  			</div>
  		</div>
  			
  			
  				<!-- <div class="col-md-4">
  					<div class="card">
  						<div class="card-body">
  							<h3>Java Programming</h3>
  							<p>Java is a high level programming language it is used to create real world problems. Java is a syntatical based programming language.</p>
  							<a class="btn primary-background text-light">Read more...</a>
  						</div>
  					</div>
  				</div>
  				<div class="col-md-4">
  					<div class="card">
  						<div class="card-body">
  							<h3>Java Programming</h3>
  							<p>Java is a high level programming language it is used to create real world problems. Java is a syntatical based programming language.</p>
  							<a class="btn primary-background text-light">Read more...</a>
  						</div>
  					</div>
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-md-4">
  					<div class="card">
  						<div class="card-body">
  							<h3>Java Programming</h3>
  							<p>Java is a high level programming language it is used to create real world problems. Java is a syntatical based programming language.</p>
  							<a class="btn primary-background text-light">Read more...</a>
  						</div>
  					</div>
  				</div>
  				<div class="col-md-4">
  					<div class="card">
  						<div class="card-body">
  							<h3>Java Programming</h3>
  							<p>Java is a high level programming language it is used to create real world problems. Java is a syntatical based programming language.</p>
  							<a class="btn primary-background text-light">Read more...</a>
  						</div>
  					</div>
  				</div>
  				<div class="col-md-4">
  					<div class="card">
  						<div class="card-body">
  							<h3>Java Programming</h3>
  							<p>Java is a high level programming language it is used to create real world problems. Java is a syntatical based programming language.</p>
  							<a class="btn primary-background text-light">Read more...</a>
  						</div>
  					</div>
  				</div>
  			</div>
  		</div>  -->
  
  		
  		<!-- java scripts -->
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    	<script src="./js/script.js"></script>
	</body>
</html>