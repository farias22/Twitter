package services;

import error.ValidationError;
import models.AppUser;

import java.util.List;

public interface TweetAppService {

//    boolean isLoginAvailable(String appUserLogin);
//
//    boolean isMailAvailable(String appUserMail);

    List<ValidationError> validateUser(AppUser appUser);

    void registerUser(AppUser appUser);


}
