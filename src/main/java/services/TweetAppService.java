package services;

import error.ValidationError;
import models.AppUser;
import models.Tweet;

import java.util.HashSet;
import java.util.List;

public interface TweetAppService {

    List<ValidationError> validateUser(String login, String email);

    void registerUser(AppUser user);

    boolean isLoginAndPasswordValid(String login, String password);

    HashSet<AppUser> getFollowedUsers(AppUser user);

    AppUser getUser(String userLogin);

    HashSet<AppUser> getNotFollowedUsers(AppUser user);

    HashSet<AppUser> getFollowers(AppUser user);

    void fallow(AppUser currentUser, AppUser userToFallow);

    void unfallow(AppUser currentUser, AppUser userToFallow);

    List<Tweet> getUserTweets(AppUser appUser);

    void save(Tweet tweet);

    void delete(Long tweetId);


}

