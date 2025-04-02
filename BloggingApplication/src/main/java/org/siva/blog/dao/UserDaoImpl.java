package org.siva.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.siva.blog.entities.User;

public class UserDaoImpl implements UserDao {
	
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/techblog";
	private final String username = "root";
	private final String dbpassword = "Siva@2805";
	
	private final String register = "insert into user(name, email, password, gender, about, rdate, profile) values (?, ?, ?, ?, ?, ?, ?)";
	private final String findUserByEmailAndPassword = "select * from user where email=? and password=?";
	private final String updateUser = "update user set name=?, email=?, password=?, gender=?, about=?, profile=? where id=?";
	private final String findUserById = "select * from user where id=?";
	
	
	@Override
	public int saveUser(User user) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement preparedStatement = connection.prepareStatement(register);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getGender());
			preparedStatement.setString(5, user.getAbout());
			preparedStatement.setDate(6, user.getRdate());
			preparedStatement.setString(7, user.getProfile());
			int executeUpdate = preparedStatement.executeUpdate();
			if (executeUpdate!=0) {
				return executeUpdate;
			}
			else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement preparedStatement = connection.prepareStatement(findUserByEmailAndPassword);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setGender(resultSet.getString(5));
				user.setAbout(resultSet.getString(6));
				user.setRdate(resultSet.getDate(7));
				user.setProfile(resultSet.getString(8));
				return user;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int updateUser(User user) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement preparedStatement = connection.prepareStatement(updateUser);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getGender());
			preparedStatement.setString(5, user.getAbout());
			preparedStatement.setString(6, user.getProfile());
			preparedStatement.setInt(7, user.getId());
			int result = preparedStatement.executeUpdate();
			if (result!=0) {
				return result;
			}
			else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}
	
	@Override
	public User getUserById(int id) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement ps = connection.prepareStatement(findUserById);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setGender(resultSet.getString(5));
				user.setAbout(resultSet.getString(6));
				user.setRdate(resultSet.getDate(7));
				user.setProfile(resultSet.getString(8));
				return user;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
