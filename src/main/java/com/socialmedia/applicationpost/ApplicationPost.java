package com.socialmedia.applicationpost;

import java.util.HashMap;
import java.util.List;

import com.socialmedia.service.ApplicationService;


public class ApplicationPost {

	ApplicationService service = new ApplicationService();

	public String createPost(int userId, int postId, String content) {

		HashMap<Integer, String> userPost = service.getUserPost(userId);
		String result;
		if (userPost == null) {
			service.addNewPost(userId, postId, content);
			service.follow(userId, userId);
			result = "success";
		} else {
			if (userPost.containsKey(postId)) {
				result = "Failure";
			}else {
				service.addNewPost(userId, postId, content);
				service.follow(userId, userId);
				result = "success";
			}
		}
		
		return result;

	}

	public List<Integer> getNewsFeed(int userId) {

		List<Integer> list = service.getFeeds(userId);
		return list;
	}

	public String follow(int followerId, int followeeId) {

		return service.follow(followerId, followeeId);
	}

	public String unFollow(int followerId, int followeeId) {

		return service.unFollow(followerId, followeeId);

	}

}
