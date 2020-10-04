package dao.dao.impl;

import dao.AbstractSQLDao;
import dao.TweetDao;
import models.AppUser;
import models.Tweet;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class MySQLTweetDao  extends AbstractSQLDao implements TweetDao {


    @Override
    public void save(Tweet tweet) {

        hibernateUtil.save(tweet);

    }
    @Override
    public void deleteTweet(Long tweetId) {

        hibernateUtil.delete(Tweet.class, tweetId);
    }

    @Override
    public List<Tweet> getUserTweet(AppUser appUser) {
        TypedQuery<Tweet> query = entityManager.createQuery("select t from Tweet t where t.author= :login", Tweet.class);
        query.setParameter("login",appUser.getLogin());

        return new ArrayList<Tweet>(query.getResultList());
    }

    @Override
    public Tweet getTweet(Long tweetId) {
        Tweet tweet = entityManager.find(Tweet.class, tweetId);

        return tweet;
    }
}
