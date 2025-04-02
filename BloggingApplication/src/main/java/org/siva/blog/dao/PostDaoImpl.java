package org.siva.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.siva.blog.entities.Post;

public class PostDaoImpl implements PostDao	 {
	
	private final String url = "jdbc:mysql://localhost:3306/techblog";
	private final String username = "root";
	private final String dbpassword = "Siva@2805";
	private final String driver = "com.mysql.cj.jdbc.Driver";

	private final String insertPost = "insert into posts (ptitle, pcontent, pcode, ppic, pdate, catid, userid) values(?, ?, ?, ?, ?, ?, ?)";
	private final String selectAllPosts = "select * from posts";
	private final String selectPostsByCategoryId = "select * from posts where catid=?";
	private final String selectPostById = "select * from posts where pid=?";
	private final String deletePostById = "delete from posts where pid=?";
	
	@Override
	public boolean savePost(Post post) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement preparedStatement = connection.prepareStatement(insertPost);
			preparedStatement.setString(1, post.getPtitle());
			preparedStatement.setString(2, post.getPcontent());
			preparedStatement.setString(3, post.getPcode());
			preparedStatement.setString(4, post.getPpic());
			preparedStatement.setDate(5, post.getDate());
			preparedStatement.setInt(6, post.getCid());
			preparedStatement.setInt(7, post.getUserid());
			int update = preparedStatement.executeUpdate();
			if (update!=0) {
				return true;
			}
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Post> getAllPosts() {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement ps = connection.prepareStatement(selectAllPosts);
			ResultSet resultSet = ps.executeQuery();
			List<Post> posts = new ArrayList<Post>();
			while (resultSet.next()) {
				Post post = new Post();
				post.setPid(resultSet.getInt(1));
				post.setPtitle(resultSet.getString(2));
				post.setPcontent(resultSet.getString(3));
				post.setPcode(resultSet.getString(4));
				post.setPpic(resultSet.getString(5));
				post.setDate(resultSet.getDate(6));
				post.setCid(resultSet.getInt(7));
				post.setUserid(resultSet.getInt(8));
				posts.add(post);
			}
			return posts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Post> getPostByCategory(int cid) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement ps = connection.prepareStatement(selectPostsByCategoryId);
			ps.setInt(1, cid);
			ResultSet resultSet = ps.executeQuery();
			List<Post> posts = new ArrayList<Post>();
			while (resultSet.next()) {
				Post post = new Post();
				post.setPid(resultSet.getInt(1));
				post.setPtitle(resultSet.getString(2));
				post.setPcontent(resultSet.getString(3));
				post.setPcode(resultSet.getString(4));
				post.setPpic(resultSet.getString(5));
				post.setDate(resultSet.getDate(6));
				post.setCid(resultSet.getInt(7));
				post.setUserid(resultSet.getInt(8));
				posts.add(post);
			}
			return posts;
		} catch(Exception e) {
			return null;
		}
	} 
	
	@Override
	public Post getPostById(int postId) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement ps = connection.prepareStatement(selectPostById);
			ps.setInt(1, postId);
			ResultSet resultSet = ps.executeQuery();
			Post post = null;
			if (resultSet.next()) {
				post = new Post();
				post.setPid(resultSet.getInt(1));
				post.setPtitle(resultSet.getString(2));
				post.setPcontent(resultSet.getString(3));
				post.setPcode(resultSet.getString(4));
				post.setPpic(resultSet.getString(5));
				post.setDate(resultSet.getDate(6));
				post.setCid(resultSet.getInt(7));
				post.setUserid(resultSet.getInt(8));
				return post;
			}
			else {
				return post;
			}
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public int deletePost(int postId) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement ps = connection.prepareStatement(deletePostById);
			ps.setInt(1, postId);
			int result = ps.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
