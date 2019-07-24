package com.socialmedia.repository;

import java.util.HashMap;
import java.util.HashSet;


public class ApplicationRepository {

	private HashMap<Integer, HashSet<Integer>> userMap = new HashMap<>();
	private HashMap<Integer, HashMap<Integer, String>> postMap = new HashMap<>();
	private HashMap<Integer, HashMap<Integer, Integer>> sequenceMap = new HashMap<>();
	{	
		postMap.put(1, null);
	}
	
	

	public void setPostMap(HashMap<Integer, HashMap<Integer, String>> postMap) {
		this.postMap = postMap;
	}

	public static int sequence;

	public HashMap<Integer, HashSet<Integer>> getUserMap() {
		
		
		return userMap;
	}
	
	public HashMap<Integer, HashMap<Integer, String>> getPostMap() {
		return postMap;
	}

	public HashMap<Integer, HashMap<Integer, Integer>> getSequenceMap() {
		return sequenceMap;
	}

	public void setUserMap(HashMap<Integer, HashSet<Integer>> userMap) {
		this.userMap = userMap;
	}

	public void setSequenceMap(HashMap<Integer, HashMap<Integer, Integer>> sequenceMap) {
		this.sequenceMap = sequenceMap;
	}
	
	
	
}
