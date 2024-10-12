package dev.example;


import dev.example.entity.*;
import dev.example.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        Company company = Company.builder()
                .name("Mail")
                .build();
        User user = User.builder()
                .username("ivan2@mail.ru")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Ivan")
                        .lastname("Ivanov")
                        .birthDate(new Birthday(LocalDate.of(2000, 01, 01)))
                        .build())
                .role(Role.ADMIN)
                .company(company)
                .build();
        User user2 = null;
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try(Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

//                session1.saveOrUpdate(company);
//                session1.saveOrUpdate(user);
                user2 = session1.get(User.class, 5);
                System.out.println(user2);
                System.out.println(user2.getCompany().getName());


                session1.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
