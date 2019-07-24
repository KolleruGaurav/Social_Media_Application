package com.socialmedia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.socialmedia.applicationpost.ApplicationPost;

@SpringBootApplication
public class SocialMediaApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub

		ApplicationPost ap = new ApplicationPost();

		System.out.println("Creating Post");
		
		ap.createPost(1, 01, "Feed 1");
		ap.createPost(2, 02, "Feed 2");
		ap.createPost(3, 03, "Feed 3");
		ap.createPost(2, 04, "Feed 4");
		ap.createPost(2, 05, "Feed 5");
		ap.createPost(2, 06, "Feed 6");
		
		System.out.println("Post created Successfully");

		System.out.println("Getting User NewsFeeds ID");
		
		List<Integer> postFeeds = ap.getNewsFeed(2);

		if (null == postFeeds) {
			System.out.println("No news feed is present");
		} else {
			postFeeds.forEach(p -> System.out.println(p));
		}

		
		ap.follow(1, 4);
		ap.follow(1, 5);
		
		ap.unFollow(1, 4);

	}

}
