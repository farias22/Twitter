package controllers;

import dao.dao.impl.MySQLTweetDao;
import dao.dao.impl.MySQLUserDao;
import models.AppUser;
import services.TweetAppService;
import services.impl.TweetAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FallowSerclet", value = "/follow")
public class FallowServlet extends HttpServlet {

    private TweetAppService service;

    @Override
    public void init() throws ServletException {
        service = new TweetAppServiceImpl(new MySQLUserDao(), new MySQLTweetDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppUser user1 = service.getUser(ServletUtils.getUserLoginFromSession(req));
        AppUser user2 = service.getUser(req.getParameter(ServletUtils.USER_LOGIN_TO_FOLLOW));
        service.fallow(user1,user2);
        req.getRequestDispatcher("users").forward(req,resp);
    }

}
