package dev.example;


import dev.example.convertert.BirthdayConverter;
import dev.example.entity.Birthday;
import dev.example.entity.Role;
import dev.example.entity.User;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRun {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
//        configuration.addAnnotatedClass(User.class);
        try (var sessionFactory = configuration.buildSessionFactory()) {
            var session = sessionFactory.openSession();
            session.beginTransaction();

            User user = User.builder()
                    .username("ivan@mail.ru")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(new Birthday(LocalDate.of(2000, 01, 01)))
                    .role(Role.ADMIN)
                    .build();

//            session.save(user);
//            session.update(user);
//            session.saveOrUpdate(user);
//            session.delete(user);
            User user1 = session.get(User.class, "ivan@mail.ru");
            user1.setFirstname("Ivan");
//            session.evict(user1);
            System.out.println(user1);
            session.getTransaction().commit();
        }
    }
}
