package controllers;

import dao.dao.impl.MySQLTweetDao;
import dao.dao.impl.MySQLUserDao;
import models.AppUser;
import models.Tweet;
import services.TweetAppService;
import services.impl.TweetAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddTweetServlet", value = "/addMessage")
public class AddTweetServlet extends HttpServlet {


    private TweetAppService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = new TweetAppServiceImpl(new MySQLUserDao(), new MySQLTweetDao());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AppUser author = service.getUser(ServletUtils.getUserLoginFromSession(req));
        String tweetValue = req.getParameter(ServletUtils.TWEET_MESSAGE_PARAM);
        Tweet tweet = new Tweet(author.getLogin(), tweetValue);
        service.save(tweet);

        req.setAttribute(ServletUtils.TWEET_MESSAGE_PARAM, tweet);

        req.getRequestDispatcher("messages").forward(req,resp);
    }


}
