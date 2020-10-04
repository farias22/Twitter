import dao.AppUserDao;
import dao.dao.impl.MySQLUserDao;
import models.AppUser;

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


        dao.fallow(user1, user3);
        dao.fallow(user2,user3);
        dao.fallow(user1,user3);
        dao.fallow(user1,user1);
        System.out.println(dao.getUserById(1L).getLogin());
        dao.delete(user1);



    }
}
