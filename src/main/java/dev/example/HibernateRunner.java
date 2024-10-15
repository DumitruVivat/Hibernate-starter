package dev.example;


import dev.example.entity.User;
import dev.example.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        User user = null;
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            user = session.find(User.class, 1L);
            var user1 = session.find(User.class, 1L);
            System.out.println(user.getCompany());
            session.getTransaction().commit();
        }
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var user2 = session.find(User.class, 1L);
            System.out.println(user2.getCompany());
            session.getTransaction().commit();
        }
    }
}
