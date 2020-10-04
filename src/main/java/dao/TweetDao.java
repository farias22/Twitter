package dao;

import models.AppUser;
import models.Tweet;

import java.util.List;

public interface TweetDao {


    public void save(Tweet tweet);

    void deleteTweet(Long tweetId);

    List<Tweet> getUserTweet(AppUser appUser);

    Tweet getTweet(Long tweetId);

}
