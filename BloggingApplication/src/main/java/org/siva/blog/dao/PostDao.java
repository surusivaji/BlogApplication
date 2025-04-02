package org.siva.blog.dao;

import java.util.List;

import org.siva.blog.entities.Post;

public interface PostDao {
		
	boolean savePost(Post post);
	
	List<Post> getAllPosts();
	
	List<Post> getPostByCategory(int cid);
	
	Post getPostById(int postId);
	
	int deletePost(int postId);

}
