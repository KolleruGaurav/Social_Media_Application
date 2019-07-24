package com.socialmedia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.socialmedia.repository.ApplicationRepository;

public class ApplicationService {

	ApplicationRepository repository = new ApplicationRepository();

	public HashMap<Integer, String> getUserPost(int userId) {

		return repository.getPostMap().get(userId);
	}

	public void addNewPost(int userId, int postId, String content) {

		HashMap<Integer, HashMap<Integer, String>> postMap = repository.getPostMap();
		HashMap<Integer, HashMap<Integer, Integer>> sequenceMap = repository.getSequenceMap();

		HashMap<Integer, String> userPost = postMap.get(userId);
		HashMap<Integer, Integer> postSequence = sequenceMap.get(userId);

		if (null == userPost) {

			HashMap<Integer, String> map1 = new HashMap<>();

			map1.put(postId, content);
			postMap.put(userId, map1);

		} else {
			userPost.put(postId, content);
			postMap.put(userId, userPost);

		}
		repository.setPostMap(postMap);

		if (null == postSequence) {
			HashMap<Integer, Integer> map1 = new HashMap<>();

			map1.put(postId, repository.sequence++);
			sequenceMap.put(userId, map1);

		} else {

			postSequence.put(postId, repository.sequence++);
			sequenceMap.put(userId, postSequence);

		}
		repository.setSequenceMap(sequenceMap);

	}

	public List<Integer> getFeeds(int userId) {

		HashMap<Integer, Integer> postSequence = repository.getSequenceMap().get(userId);
		List<Integer> postIdSet = new ArrayList();
		int count = 0;
		
		if (null != postSequence && !postSequence.isEmpty()) {
			Map<Integer, Integer> sortedPostSequence = new LinkedHashMap<>();
			postSequence.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
					.forEachOrdered(x -> sortedPostSequence.put(x.getKey(), x.getValue()));

			for (Map.Entry<Integer, Integer> entry : sortedPostSequence.entrySet()) {

				if (count < 20) {
					postIdSet.add(entry.getKey());
				}
				count++;

			}
		} else {
			return null;
		}
		return postIdSet;

	}

	public String follow(int followerId, int followeeId) {
		
		HashSet<Integer> followers = new HashSet<>();
		HashMap<Integer, HashSet<Integer>> userMap = repository.getUserMap();
		String status = "Failure";
		if(userMap.isEmpty() || userMap.get(followerId) == null ) {
			
			followers.add(followeeId);
			userMap.put(followerId, followers);
			status ="success";
			
		}else {
			HashSet<Integer> set = userMap.get(followerId);			
			set.add(followeeId);
			userMap.put(followerId, set);
			status ="success";
		}
		repository.setUserMap(userMap);
		return status;
		
	}

	public String unFollow(int followerId, int followeeId) {
		HashSet<Integer> set = repository.getUserMap().get(followerId);
		String status;
		
		if(null != set) {
		if (followerId == followeeId)
			status  = "success";
		
		if(set.contains(followeeId)) {
			set.remove(followeeId);
			status  = "success";
		}else {
			status = "Failure";
		}
		
		}else {
			status = "Failure";
		}
		return status;
		
	}
}
