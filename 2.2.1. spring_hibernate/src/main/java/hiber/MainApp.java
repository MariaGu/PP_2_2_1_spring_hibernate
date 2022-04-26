package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("bmv", 111);
        User user1 = new User("Masha", "Gubina", "guaan@mail.ru");
        user1.setCar(car1);
        Car car2 = new Car("honda", 555);
        User user2 = new User("Glasha", "Petrova", "gumpapaan@mail.ru", car2);
        userService.add(user1);
        userService.add(user2);

        System.out.println("==============");
        System.out.println("Users with cars:");
        System.out.println(user1);
        System.out.println(user2);

        System.out.println("==============");
        System.out.println("List of all users:");
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        User user3 = userService.getUserByCar("honda", 555);
        System.out.println(user3);

        context.close();
    }
}
