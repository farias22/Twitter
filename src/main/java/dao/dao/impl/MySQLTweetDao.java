package dao.dao.impl;

import dao.AbstractSQLDao;
import dao.TweetDao;
import models.AppUser;
import models.Tweet;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLTweetDao  extends AbstractSQLDao implements TweetDao {


    @Override
    public void save(Tweet tweet) {

        hibernateUtil.save(tweet);

    }
    @Override
    public void delete(Long tweetId) {

        hibernateUtil.delete(Tweet.class, tweetId);
    }

    @Override
    public List<Tweet> getUserTweets(AppUser appUser) {
        TypedQuery<Tweet> query = entityManager.createQuery("select t from Tweet t where t.author= :login", Tweet.class);
        query.setParameter("login",appUser.getLogin());

        return new ArrayList<Tweet>(query.getResultList());
    }

    @Override
    public Optional<Tweet> getTweet(Long tweetId) {
        Tweet tweet = entityManager.find(Tweet.class, tweetId);
        return Optional.ofNullable(tweet);
    }
}
