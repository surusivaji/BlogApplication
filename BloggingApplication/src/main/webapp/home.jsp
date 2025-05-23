<%@page import="org.siva.blog.dao.CategoryDao"%>
<%@page import="org.siva.blog.dao.CategoryDaoImpl"%>
<%@page import="org.siva.blog.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="org.siva.blog.dao.PostDao"%>
<%@page import="org.siva.blog.dao.PostDaoImpl"%>
<%@page import="org.siva.blog.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User) session.getAttribute("user");
  	if (user==null) {
  		response.sendRedirect("login.jsp");
  	}
%>
<!doctype html>
<html lang="en">
	<head>
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>Tech blog</title>
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    	<link rel="stylesheet" href="./css/style.css">
 	</head>
	<body>		
  		<!-- navbar starts -->
  		<nav class="navbar navbar-expand-lg navbar-dark primary-background">
		  <div class="container-fluid">
		    <a class="navbar-brand fw-bold" href="home.jsp"> <span class="fa fa-asterisk"></span> Tech Blog</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="home.jsp"> <span class="fa fa-bell-o"></span> LearnCode with Sivaji</a>
		        </li>
		        <li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            <span class="fa fa-check-square-o"></span> Categories
		          </a>
		          <ul class="dropdown-menu">
		            <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#addCategory-modal">Add Category</a></li>
		            <li><hr class="dropdown-divider"></li>
		          <%
          		  	CategoryDao categoryDao2 = new CategoryDaoImpl();
          			List<Category> categories2 = categoryDao2.getAllCategories();
          			for (Category category : categories2) 
          			{%>
          		  		<li><a href="#" class="dropdown-item" onclick="getPosts(<%= category.getId()%>, this)"><%= category.getName()%></a></li>
          			<%}
          		   %>
		          </ul>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="#"> <span class="fa fa-address-card-0"></span> Contact</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" data-bs-toggle="modal" data-bs-target="#addPost-modal"> <span class="fa fa-asterisk"></span> Do Post</a>
		        </li>
		      </ul>
		      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
		         <li class="nav-item">
		          <a class="nav-link" data-bs-toggle="modal" data-bs-target="#profile-modal"> <span class="fa fa-user-circle"></span> <%= user.getName() %></a>
		        </li>
		         <li class="nav-item">
		          <a class="nav-link" href="./logout"> <span class="fa fa-user-plus"></span> Logout</a>
		        </li>
		      </ul>
		    </div>
		  </div>
		</nav>
		<!-- navbar ends -->
		
		<div class="modal fade" id="addCategory-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header primary-background text-light text-center">
		        <h5 class="modal-title" id="exampleModalLabel">Add Category</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <form id="add-category" action="saveCategory" method="POST">
		        	<div class="form-group mb-2">
		        		<input type="text" class="form-control" name="categoryName" placeholder="Enter category name">
		        	</div>
		        	<div class="form-group mb-2">
		        		<textarea rows="5" cols="" class="form-control" name="description" placeholder="Enter category description"></textarea>
		        	</div>
		        	<div class="form-group mb-2 text-center">
		        		<input type="submit" value="Save" class="btn btn-outline-primary">
		        	</div>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- session message starts -->
		<%
			String successMsg = (String) session.getAttribute("successMsg");
			if (successMsg!=null)
			{%>
				<div class="alert alert-success">
					<%= successMsg %>
				</div>	
			<% session.removeAttribute("successMsg");
			}
			String failMsg = (String) session.getAttribute("failMsg");
			if (failMsg!=null) 
			{%>
				<div class="alert alert-danger">
					<%= failMsg %>
				</div>	
			<% session.removeAttribute("failMsg");
			}
		%>
		<!-- session message ends -->
		
		         <!--main body of the page-->

        <main>
            <div class="container-fluid">
                <div class="row mt-4">
                    <!--first col-->
                    <div class="col-md-3">
                        <!--categories-->
                        <div class="list-group">
                            <a href="#" onclick="getPosts(0, this)"  class=" c-link list-group-item list-group-item-action active">
                                All Posts
                            </a>
                            <!--categories-->
                            <%                              
                           	 	CategoryDao categoryDao = new CategoryDaoImpl();
                            	List<Category> categories = categoryDao.getAllCategories();
								for (Category category : categories) {
                            %>
                            <a href="#" onclick="getPosts(<%= category.getId()%>, this)" class=" c-link list-group-item list-group-item-action"><%= category.getName()%></a>
                            <%}

                            %>
                        </div>

                    </div>

                    <!--second col-->
                    <div class="col-md-9" id="post-container">
                        <!--posts-->
                        <div class="container text-center" id="loader">
                            <i class="fa fa-refresh fa-4x fa-spin"></i>
                            <h3 class="mt-2">Loading...</h3>
                        </div>

                        <div class="container-fluid" id="post-container">

                        </div>
                    </div>

                </div>

            </div>

        </main>


        <!--end main body of the page-->
		
		
		
		<!-- show profile popup starts -->
		<div class="modal fade" id="profile-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header primary-background text-light text-center">
		        <h5 class="modal-title" id="exampleModalLabel">TechBlog</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		      	<div class="container text-center">
		      		<img alt="profle_image" src="./pics/<%= user.getProfile() %>" class="img-fluid img-thumbnail" style="height:200px; width:200px; border-radius: 50%">
		      		<br>
		      		<h5 class="mt-3"><%=user.getName() %></h5>	  		
		      		<div id="profile-details">
		      			<table class="table">
			      			<tr>
			      				<th> Id </th>
			      				<td><%= user.getId() %></td>
			      			</tr>
			      			<tr>
			      				<th> Email </th>
			      				<td><%= user.getEmail() %></td>
			      			</tr>
			      			<tr>
			      				<th> Gender </th>
			      				<td><%= user.getGender() %></td>
			      			</tr>
			      			<tr>
			      				<th> About </th>
			      				<td><%= user.getAbout() %></td>
			      			</tr>
			      			<tr>
			      				<th> Registerd On </th>
			      				<td><%= user.getRdate() %></td>
			      			</tr>
		      			</table>
		      	   </div>
		      	    <!-- edit profile -->
		      	   <div id="edit-profile" style="display: none;">
		      	   		<form action="EditServlet" method="post" enctype="multipart/form-data">
		      	   			<table class="table">
		      	   				<tr>
		      	   					<th> Id </th>
		      	   					<td><%= user.getId() %></td>
		      	   				</tr>
		      	   				<tr>
		      	   					<th> Email </th>
		      	   					<td><input type="text" name="email" value="<%= user.getEmail() %>" class="form-control"></td>
		      	   				</tr>
		      	   				<tr>
		      	   					<th> Name </th>
		      	   					<td><input type="text" name="name" value="<%= user.getName() %>" class="form-control"></td>
		      	   				</tr>
		      	   				<tr>
		      	   					<th> Password </th>
		      	   					<td><input type="password" name="password" value="<%= user.getPassword() %>" class="form-control"></td>
		      	   				</tr>
		      	   				<tr>
		      	   					<th> Gender </th>
		      	   					<td><%= user.getGender() %></td>
		      	   				</tr>
		      	   				<tr>
		      	   					<th> About </th>
		      	   					<td><textarea rows="3" class="form-control" name="about"><%= user.getAbout() %></textarea></td>
		      	   				</tr>
		      	   				<tr>
		      	   					<th>Profile </th>
		      	   					<td><input type="file" class="form-control" name="profile"></td>
		      	   				</tr>
		      	   			</table>
		      	   			<input type="submit" class="btn btn-outline-primary" value="Save">
		      	   		</form>
		      	   </div>
		      </div>      
		   	</div>  		
		      <div class="modal-footer d-flex justify-content-center">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="edit-btn">Edit</button>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- show profile popup ends -->
		
		<!-- add post model starts -->
		<div class="modal fade" id="addPost-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Provide the post details..</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <form id="add-post" action="addPost" method="POST" enctype="multipart/form-data">
		        	<div class="form-group mb-2">
		        		<select class="form-control" name="catid">
		        			<option selected disabled>-- select category --</option>
		        			<%
		        				CategoryDao categoryDao1 = new CategoryDaoImpl();
		        				List<Category> categories1 = categoryDao1.getAllCategories();
		        				for (Category category : categories1)
		        				{%>
		        					<option value="<%= category.getId() %>"><%= category.getName() %></option>	
		        				<%}
		        			%>
		        			
		        		</select>
		        	</div>
		        	<div class="form-group mb-2">
		        		<input type="text" class="form-control" name="ptitle" placeholder="Enter post Title">
		        	</div>
		        	<div class="form-group mb-2">
		        		<textarea rows="5" cols="" class="form-control" name="pcontent" placeholder="Enter your content"></textarea>
		        	</div>
		        	<div class="form-group mb-2">
		        		<textarea rows="5" cols="" class="form-control" name="pcode" placeholder="Enter your program (if any)"></textarea>
		        	</div>
		        	<div class="form-group mb-3">
		        		<label for="pic">Select your pic..</label><br>
		        		<input type="file" name="pic" id="pic" name="pic">
		        	</div>
		        	<div class="form-group mb-2 text-center">
		        		<input type="submit" value="Post" class="btn btn-outline-primary">
		        	</div>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
		<!--  add post model ends -->
		
		
  
  		
  		<!-- java scripts -->
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    	 <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    	<script src="./js/script.js"></script>
    	
    	<script>
	    	function doLike(uid, pid) {
	    	    const d = {
	    	        uid: uid,
	    	        pid: pid,
	    	        operation: 'like'
	    	    };
	    	    
	    	    console.log(d);
	    	    
	    	    $.ajax({
	    	        url: "likeServlet",
	    	        type: "GET", // Or "GET", depending on your backend implementation
	    	        data: d,
	    	        success: function(data, textStatus, jqXHR) {
	    	        	console.log(data);
	    	            if (data.trim() == 'true')
	    	            {
	    	                let c = $(".like-counter").html();
	    	                c++;
	    	                $('.like-counter').html(c);
	    	            }
	    	        },
	    	        error: function(jqXHR, textStatus, errorThrown) {
	    	            console.log("Error:", errorThrown);
	    	        }
	    	    });
	    	}
    	</script>
    	
    	
    	<script>
    		$(document).ready(function(){
    			let editStatus = false;
    			$('#edit-btn').click(function(){
    				if (editStatus==false) {
    					$('#profile-details').hide();
        				$('#edit-profile').show();
        				editStatus = true;
        				$(this).text("Back");
    				}
    				else {
    					$('#profile-details').show();
    					$('#edit-profile').hide();
    					editStatus = false;
    					$(this).text("Edit");
    				}
    			})
    		})
    	</script>
    <script>
        $(document).ready(function (e) {
            //
            $("#add-post").on("submit", function (event) {
                //this code gets called when form is submitted....
                event.preventDefault();
                console.log("you have clicked on submit..")
                let form = new FormData(this);

                //now requesting to server
                $.ajax({
                    url: "addPost",
                    type: 'POST',
                    data: form,
                    success: function (data, textStatus, jqXHR) {
                        //success ..
                        if (data.trim() == 'done')
                        {
                            swal("Good job!", "saved successfully", "success");
                        } else
                        {
                            swal("Error!!", "Something went wrong try again...", "error");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        //error..
                        swal("Error!!", "Something went wrong try again...", "error");
                    },
                    processData: false,
                    contentType: false
                })
            })
        })
    </script>
    
    <!--loading post using ajax-->
        <script>

            function getPosts(catId, temp) {
                $("#loader").show();
                $("#post-container").hide()

                $(".c-link").removeClass('active')


                $.ajax({
                    url: "load_posts.jsp",
                    data: {cid: catId},
                    success: function (data, textStatus, jqXHR) {
                        $("#loader").hide();
                        $("#post-container").show();
                        $('#post-container').html(data)
                        $(temp).addClass('active')

                    }
                })

            }

            $(document).ready(function (e) {

                let allPostRef = $('.c-link')[0]
                getPosts(0, allPostRef)


            })
        </script>


	</body>
</html>