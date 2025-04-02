package org.siva.blog.dao;

import org.siva.blog.entities.User;

public interface UserDao {
	
	int saveUser(User user);

	User findUserByEmailAndPassword(String email, String password);
	
	int updateUser(User user);
	
	User getUserById(int id);

}
