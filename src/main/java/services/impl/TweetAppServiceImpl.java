package services.impl;

import dao.AppUserDao;
import dao.TweetDao;
import error.ValidationError;
import models.AppUser;
import services.TweetAppService;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static utils.ServletUtils.*;

public class TweetAppServiceImpl implements TweetAppService {

    private AppUserDao appUserDao;
    private TweetDao tweetDao;

    public TweetAppServiceImpl(AppUserDao appUserDao, TweetDao tweetDao) {
        this.appUserDao = appUserDao;
        this.tweetDao = tweetDao;
    }

    @Override
    public List<ValidationError> validateUser(AppUser appUser) {
        List<ValidationError> errors = new ArrayList<>();

        if (!isLoginAvailable(appUser.getLogin())) {
            errors.add(new ValidationError(LOGIN_ERROR_HEADER, LOGIN_IN_USE_ERROR_MESSAGE));
        }
        if (!isMailAvailable(appUser.getEmail())) {
            errors.add(new ValidationError(EMAIL_ERROR_HEADER, EMAIL_ERROR_MESSAGE));
        }
        return errors;
    }


    @Override
    public void registerUser(AppUser appUser) {
        appUserDao.saveUser(appUser);
    }

    private boolean isLoginAvailable(String appUserLogin) {
        try {
            appUserDao.getUserByLogin(appUserLogin);
            return false;
        } catch (NoResultException ex) {
            return true;
        }
    }

    private boolean isMailAvailable(String appUserMail) {
        try {
            appUserDao.getUserByEmail(appUserMail);
            return false;
        } catch (NoResultException ex) {
            return true;
        }
    }


}
