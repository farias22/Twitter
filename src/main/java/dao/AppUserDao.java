package dao;

import models.AppUser;

import java.util.HashSet;
import java.util.Optional;

public interface AppUserDao {

    HashSet<AppUser> getAll();

    void saveUser(AppUser appUser);

    void delete(AppUser appUser);

    Optional<AppUser> getUserById(Long id);

    Optional <AppUser> getUserByEmail(String email);

    Optional <AppUser> getUserByLogin(String login);

    HashSet<AppUser> getFallowedUsers (AppUser loggedUser);

    HashSet<AppUser> getNotFallowedUsers (AppUser loggedUser);

    HashSet<AppUser> getFallowers (AppUser loggedUser);

    void fallow(AppUser logged, AppUser appUser);

    void unfollow(AppUser logged, AppUser appUser);


}
