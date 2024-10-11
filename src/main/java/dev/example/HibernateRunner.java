package dev.example;


import dev.example.entity.Birthday;
import dev.example.entity.PersonalInfo;
import dev.example.entity.Role;
import dev.example.entity.User;
import dev.example.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        // TRANSIENT
        User user = User.builder()
                .username("ivan99@mail.ru")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Ivan")
                        .lastname("Ivanov")
                        .birthDate(new Birthday(LocalDate.of(2000, 01, 01)))
                        .build())
                .role(Role.ADMIN)
                .build();
        log.info("User object in transient state: {}", user);
        // TRANSIENT
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try(Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                //PERSISTENT x session1
                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
