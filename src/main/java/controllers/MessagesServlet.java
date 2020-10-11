package controllers;

import dao.dao.impl.MySQLTweetDao;
import dao.dao.impl.MySQLUserDao;
import models.AppUser;
import models.Tweet;
import services.TweetAppService;
import services.impl.TweetAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "MessagesServlet", value = "/messages")
public class MessagesServlet extends HttpServlet {

    private TweetAppService service;


    @Override
    public void init() throws ServletException {
        service = new TweetAppServiceImpl(new MySQLUserDao(), new MySQLTweetDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppUser user = service.getUser(ServletUtils.getUserLoginFromSession(req));
        HashSet<AppUser> followedUsers = service.getFollowedUsers(user);
        List<Tweet> tweets = followedUsers
                .stream()
                .flatMap(p -> service.getUserTweets(p).stream())
                .collect(Collectors.toList());

        tweets.addAll(service.getUserTweets(user));
        Collections.sort(tweets, new Comparator<Tweet>() {
            public int compare(Tweet o1, Tweet o2) {
                return o2.getPublishedAt().compareTo(o1.getPublishedAt());
            }
        });

        req.setAttribute(ServletUtils.FOLLOWED_TWEETS, tweets);
        req.getRequestDispatcher("/messages.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
