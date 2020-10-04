package dao.dao.impl;

import dao.AbstractAppUser;
import models.AppUser;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;

public class MySQLUserDao extends AbstractAppUser implements dao.AppUserDao {


    @Override
    public HashSet<AppUser> getAll() {

        TypedQuery<AppUser> allUsers = entityManager.createQuery("select u from AppUser u", AppUser.class);
        List<AppUser> resultList = allUsers.getResultList();

        return new HashSet<>(resultList);
    }

    @Override
    public void saveUser(AppUser appUser) {
        hibernateUtil.save(appUser);
    }

    @Override
    public void delete(AppUser appUser) {

        unfollowBeforeDelete(appUser);
        hibernateUtil.delete(AppUser.class, appUser.getId());
    }


    @Override
    public AppUser getUserById(Long id) {
        TypedQuery<AppUser> allUsers = entityManager.createQuery("select u from AppUser u where u.id= :id", AppUser.class);
        allUsers.setParameter("id", id);
        AppUser result = allUsers.getSingleResult();

        return result;
    }

    @Override
    public AppUser getUserByEmail(String email) {

        TypedQuery<AppUser> allUsers = entityManager.createQuery("select u from AppUser u where u.email= :email", AppUser.class);
        allUsers.setParameter("email", email);
        AppUser result = allUsers.getSingleResult();

        return result;
    }

    @Override
    public AppUser getUserByLogin(String login) {

        TypedQuery<AppUser> allUsers = entityManager.createQuery("select u from AppUser u where u.login= :login", AppUser.class);
        allUsers.setParameter("login", login);
        AppUser result = allUsers.getSingleResult();

        return result;

    }

    @Override
    public HashSet<AppUser> getFallowedUsers(AppUser loggedUser) {


        return new HashSet<>(loggedUser.getFollowing());

    }

    @Override
    public HashSet<AppUser> getNotFallowedUsers(AppUser loggedUser) {

        Query query = entityManager.createQuery("select u from AppUser u where u not in :followed");
        query.setParameter("followed", new HashSet<>(loggedUser.getFollowing()));
        return new HashSet<>(query.getResultList());

    }

    @Override
    public HashSet<AppUser> getFallowers(AppUser loggedUser) {


        Query query = entityManager.createQuery("select followers from AppUser u where u.id= :userId");
        query.setParameter("userId", loggedUser.getId());
        return new HashSet<>(query.getResultList());

    }

    @Override
    public void fallow(AppUser loggedUser, AppUser userToFallow) {
        loggedUser.getFollowing().add(userToFallow);
        saveUser(loggedUser);
    }

    @Override
    public void unfollow(AppUser loggedUser, AppUser userToUnfallow) {
        loggedUser.getFollowing().remove(userToUnfallow);
        saveUser(loggedUser);
    }

    public void unfollowBeforeDelete(final AppUser appUser) {

        getFallowers(appUser).
                stream().
                forEach(follower -> unfollow(follower, appUser));

    }
}
