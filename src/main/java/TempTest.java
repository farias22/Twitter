import dao.AppUserDao;
import dao.TweetDao;
import dao.dao.impl.MySQLTweetDao;
import dao.dao.impl.MySQLUserDao;
import models.AppUser;
import models.Tweet;

public class TempTest {

    public static void main(String[] args) {
        AppUserDao dao = new MySQLUserDao();
        AppUser user1 = AppUser.UserBuilder.getBuilder()
                .name("Paweł")
                .email("asd")
                .lastName("Jabłonowski")
                .password("aaa")
                .login("pawelJ")
                .build();

        AppUser user2 = AppUser.UserBuilder.getBuilder()
                .name("Tomasz")
                .email("sxxx")
                .lastName("Kowalski")
                .password("xxxx")
                .login("tomekK")
                .build();

        AppUser user3 = AppUser.UserBuilder.getBuilder()
                .name("Adam")
                .email("xxxxxxx")
                .lastName("Małysz")
                .password("bulkaBanan666")
                .login("adamM")
                .build();


        dao.saveUser(user1);
        dao.saveUser(user2);
        dao.saveUser(user3);
        dao.delete(user2);


        dao.fallow(user1, user3);
        dao.fallow(user2,user3);
        dao.fallow(user1,user3);


        TweetDao tweetDao = new MySQLTweetDao();
        tweetDao.save(new Tweet(user1.getLogin(), "asdasdasdasdasdasdasdasdas"));
        tweetDao.save(new Tweet(user2.getLogin(), "asdasd"));
        tweetDao.save(new Tweet(user1.getLogin(), "Dupa dupa dupa"));
        tweetDao.save(new Tweet(user3.getLogin(), "aaa"));
        tweetDao.save(new Tweet(user1.getLogin(), "QQQQQQQQQQQQQQQ"));


        tweetDao.deleteTweet(2L);
        System.out.println(tweetDao.getTweet(1L));

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        for (Tweet tweet : tweetDao.getUserTweet(user1)) {
            System.out.println(tweet);
            System.out.println("#######");
        }

    }
}
