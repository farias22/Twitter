package dao.dao.impl;

import dao.AbstractSQLDao;
import dao.AppUserDao;
import models.AppUser;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

public class MySQLUserDao extends AbstractSQLDao implements AppUserDao {


    @Override
    public HashSet<AppUser> getAll() {
        TypedQuery<AppUser> getAll = entityManager.createQuery("from AppUser u where u.isActive = true", AppUser.class);
        List<AppUser> resultList = getAll.getResultList();
        return new HashSet<>(resultList);
    }

    @Override
    public void saveUser(AppUser user) {
        hibernateUtil.save(user);
    }

    @Override
    public void deleteUser(AppUser user) {
        //unfollowBeforeDelete(user);
        //hibernateUtil.delete(AppUser.class, user.getId());
        user.setIsActive(false);
    }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        TypedQuery<AppUser> query = entityManager.createQuery("select u from AppUser u where u.id =:id", AppUser.class);
        query.setParameter("id", id);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AppUser> getUserByEmail(String email) {
        TypedQuery<AppUser> query = entityManager.createQuery("select u from AppUser u where u.email =:email", AppUser.class);
        query.setParameter("email", email);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AppUser> getUserByLogin(String login) {
        TypedQuery<AppUser> query = entityManager.createQuery("select u from AppUser u where u.login =:login", AppUser.class);
        query.setParameter("login", login);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public HashSet<AppUser> getFollowedUsers(AppUser loggedUser) {
        return new HashSet<>(loggedUser.getFollowing());
    }

    @Override
    public HashSet<AppUser> getNotFollowedUsers(AppUser loggedUser) {
        Query query = entityManager.createQuery("select u from AppUser u where u.login != :login and u.isActive=true");
        query.setParameter("login", loggedUser.getLogin());
        HashSet<AppUser> appUsers = new HashSet<AppUser>(query.getResultList());
        appUsers.removeAll(loggedUser.getFollowing());
        return appUsers;
    }

    @Override
    public HashSet<AppUser> getFollowers(AppUser loggedUser) {
        Query query = entityManager.createQuery("select followers from AppUser u where u.id = :userId");
        query.setParameter("userId", loggedUser.getId());
        List<AppUser> followers = new ArrayList<>(query.getResultList());
        return followers.stream().filter(x -> x.getIsActive()).collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public void follow(AppUser loggedUser, AppUser userToFollow) {
        loggedUser.getFollowing().add(userToFollow);
        saveUser(loggedUser);
    }

    @Override
    public void unfollow(AppUser loggedUser, AppUser userToStopFollow) {
        loggedUser.getFollowing().remove(userToStopFollow);
        saveUser(loggedUser);
    }

    private void unfollowBeforeDelete(AppUser user) {
        getFollowers(user).forEach(follower -> unfollow(follower, user));
    }
}
