<%@page import="org.siva.blog.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="org.siva.blog.dao.CategoryDaoImpl"%>
<%@page import="org.siva.blog.dao.CategoryDao"%>
<nav class="navbar navbar-expand-lg navbar-dark primary-background">
  <div class="container-fluid">
    <a class="navbar-brand fw-bold" href="index.jsp"> <span class="fa fa-asterisk"></span> Tech Blog</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp"> <span class="fa fa-bell-o"></span> LearnCode with Sivaji</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <span class="fa fa-check-square-o"></span> Categories
          </a>
          <ul class="dropdown-menu">
          	<%
          		CategoryDao categoryDao = new CategoryDaoImpl();
          		List<Category> categories = categoryDao.getAllCategories();
          		for (Category category : categories) 
          		{%>
          		  <li><a class="dropdown-item"><%= category.getName() %></a></li>
          		<%}
          	%>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"> <span class="fa fa-address-card-o"></span> Contact</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="login.jsp"> <span class="fa fa-user-circle"></span> Login</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="register.jsp"> <span class="fa fa-user-plus"></span> Sign up</a>
        </li>
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-light" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>