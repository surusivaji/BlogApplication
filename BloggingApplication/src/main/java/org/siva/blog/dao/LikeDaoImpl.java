package org.siva.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDaoImpl implements LikeDao {
	
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String username = "root";
	private final String dbpassword = "Siva@2805";
	private final String url = "jdbc:mysql://localhost:3306/techblog";
	
	private final String insertLikes = "insert into likes (uid, pid) values (?, ?)";
	private final String countLikes = "select count(*) from likes where pid=?";
	private final String checkUserLikedOrNot = "select * from likes where uid=? and pid=?";
	private final String deleteLike = "delete from likes where uid=? and pid=?";
	
	@Override
	public boolean insertLike(int uid, int pid) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement prepareStatement = connection.prepareStatement(insertLikes);
			prepareStatement.setInt(1, uid);
			prepareStatement.setInt(2, pid);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate!=0) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public int countLikesOnPost(int pid) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement prepareStatement = connection.prepareStatement(countLikes);
			prepareStatement.setInt(1, pid);
			ResultSet resultSet = prepareStatement.executeQuery();
			int count = 0;
			if (resultSet.next()) {
				count = resultSet.getInt("count(*)");
			}
			return count;
		}
		catch (Exception e) {
			return -1;
		}
	}
	
	@Override
	public boolean isLikedByUser(int uid, int pid) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement prepareStatement = connection.prepareStatement(checkUserLikedOrNot);
			prepareStatement.setInt(1, uid);
			prepareStatement.setInt(2, pid);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean deleteLike(int uid, int pid) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement prepareStatement = connection.prepareStatement(deleteLike);
			prepareStatement.setInt(1, uid);
			prepareStatement.setInt(2, pid);
			int result = prepareStatement.executeUpdate();
			if (result!=0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
