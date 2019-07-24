package com.socialmedia.applicationpost;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.socialmedia.repository.ApplicationRepository;

public class ApplicationPostTest {

	static ApplicationPost appPost;
	static ApplicationRepository appRepo;
	static List<Integer> testListOrdered;
	static List<Integer> testListUnordered;

	@BeforeClass
	public static void beforeClass() {
		appPost = new ApplicationPost();

		appPost.createPost(1, 1, "Test Feed 1");
		appPost.createPost(1, 2, "Test Feed 2");
		appPost.createPost(1, 3, "Test Feed 3");
		appPost.createPost(2, 5, "Test Feed 3");
		appPost.createPost(3, 6, "Test Feed 3");

		testListOrdered = new ArrayList<>();
		testListOrdered.add(3);
		testListOrdered.add(2);
		testListOrdered.add(1);
		
		testListUnordered = new ArrayList<>();
		testListUnordered.add(2);
		testListUnordered.add(3);
		testListUnordered.add(1);

	}

	@Test
	public void test_createPost() {
		assertEquals("success", appPost.createPost(1, 4, "Test Feed 2"));
	}

	@Test
	public void test_createPostPassingExistingPostId() {
		assertEquals("Failure", appPost.createPost(1, 1, "Test Feed 3"));
	}

	@Test
	public void test_getNewsFeedsAsLatesttoOldest() {
		assertThat(testListOrdered, is(appPost.getNewsFeed(1)));
	}
	
	@Test
	public void test_getNewsFeedsInUnorderedWay() {
		assertThat(testListUnordered, not(appPost.getNewsFeed(1)));
	}
	
	@Test
	public void test_getNewsFeedReturningNull() {
		assertEquals(null, appPost.getNewsFeed(5));
	}
	
	@Test
	public void test_followAddingNewFollower() {
		assertEquals("success", appPost.follow(1, 4));
	}
	
	@Test
	public void test_UnfollowRemoveExistingFollower() {
		appPost.follow(1, 4);
		assertEquals("success", appPost.unFollow(1, 4));
	}
	
	@Test
	public void test_UnfollowRemoveNonExistingFollower() {
		assertEquals("Failure", appPost.unFollow(1, 5));
	}
	
	@Test
	public void test_UnfollowRemoveNonExistingUser() {
		assertEquals("Failure", appPost.unFollow(4, 1));
	}
	
	@Test
	public void test_UnfollowSelf() {
		assertEquals("success", appPost.unFollow(1, 1));
	}

}
