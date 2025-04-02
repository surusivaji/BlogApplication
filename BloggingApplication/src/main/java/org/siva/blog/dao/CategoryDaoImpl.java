package org.siva.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.siva.blog.entities.Category;

public class CategoryDaoImpl implements CategoryDao {
	
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String username = "root";
	private final String dbpassword = "Siva@2805";
	private final String url = "jdbc:mysql://localhost:3306/techblog";
	
	private final String insertCategory = "insert into categories (name, description) values(?, ?)";
	private final String selectAllCategories = "select * from categories";

	@Override
	public int addCategory(String name, String description) {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement preparedStatement = connection.prepareStatement(insertCategory);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			int executeUpdate = preparedStatement.executeUpdate();
			return executeUpdate;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@Override
	public List<Category> getAllCategories() {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, dbpassword);
			PreparedStatement preparedStatement = connection.prepareStatement(selectAllCategories);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Category> list = new ArrayList<Category>();
			while(resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt(1));
				category.setName(resultSet.getString(2));
				category.setDescription(resultSet.getString(3));
				list.add(category);
			}
			System.out.println(list);
			return list;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
