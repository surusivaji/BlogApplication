package org.siva.blog.dao;

public interface LikeDao {
	
	boolean insertLike(int uid, int pid);
	
	int countLikesOnPost(int pid);
	
	boolean isLikedByUser(int uid, int pid);
	
	boolean deleteLike(int uid, int pid);

}
