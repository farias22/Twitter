package dao;

import models.AppUser;

import java.util.HashSet;

public interface AppUserDao {

    HashSet<AppUser> getAll();

    void saveUser(AppUser appUser);

    void delete(AppUser appUser);

    AppUser getUserById(Long id);

    AppUser getUserByEmail(String email);

    AppUser getUserByLogin(String login);

    HashSet<AppUser> getFallowedUsers (AppUser loggedUser);

    HashSet<AppUser> getNotFallowedUsers (AppUser loggedUser);

    HashSet<AppUser> getFallowers (AppUser loggedUser);

    void fallow(AppUser logged, AppUser appUser);

    void unfollow(AppUser logged, AppUser appUser);


}
