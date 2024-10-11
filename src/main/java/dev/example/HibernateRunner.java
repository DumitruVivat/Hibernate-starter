package dev.example;


import dev.example.entity.Birthday;
import dev.example.entity.Role;
import dev.example.entity.User;
import dev.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        // TRANSIENT
        User user = User.builder()
                .username("ivan@mail.ru")
                .firstname("Ivan")
                .lastname("Ivanov")
                .birthDate(new Birthday(LocalDate.of(2000, 01, 01)))
                .role(Role.ADMIN)
                .build();

        // TRANSIENT
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try(Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                //PERSISTENT x session1
                session1.saveOrUpdate(user);

                user.setFirstname("Ivan");
                System.out.println(session1.isDirty());
                session1.get(User.class, "ivan@mail.ru");
                System.out.println(user);

                session1.getTransaction().commit();
            }
//
//            try(Session session2 = sessionFactory.openSession()) {
//                session2.beginTransaction();
//
//                //GET after DELETE PERSISTENT x session2 DETACHED x session1
//                session2.delete(user);
//
//                //REMOVED x session2
//                session2.getTransaction().commit();
//            }
        }
    }
}
