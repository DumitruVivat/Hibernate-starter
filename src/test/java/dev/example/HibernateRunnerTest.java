package dev.example;

import dev.example.entity.*;
import dev.example.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;


public class HibernateRunnerTest {

    @Test
    public void checkInheritance() throws SQLException {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();


        var company = Company.builder()
                .name("Yandex")
                .build();

        session.save(company);

        var programmer = Programmer.builder()
                .username("ivan@gmail.com")
                .language(Language.JAVA)
                .company(company)
                .build();

        session.save(programmer);

        var manager = Manager.builder()
                .username("petr@gmail.com")
                .project("Java Enterprise")
                .company(company)
                .build();

        session.save(manager);

        session.flush();
        session.clear();

        var programmer1 = session.get(Programmer.class, 1L);
        var manager1 = session.get(User.class, 2L);

        session.getTransaction().commit();
    }

    @Test
    public void checkH2(){
//        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
//        @Cleanup var session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        var company = Company.builder()
//                        .name("Amazon")
//                        .build();
//
//
//        session.save(company);
//        session.getTransaction().commit();

    }

    @Test
    public void checkManyToMany() throws SQLException {
//        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
//        @Cleanup var session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        Chat chat = session.get(Chat.class, 1L);
//        User user = session.get(User.class, 10L);
////        UserChat userChat = UserChat.builder()
////
////                .build();
//        UserChat userChat = new UserChat();
//        userChat.setCreatedAt(Instant.now());
//        userChat.setCreatedBy("Andrei");
//
//        userChat.setUser(user);
//        userChat.setChat(chat);
//
//        session.save(userChat);
//
//
//        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() throws SQLException {
//        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
//        @Cleanup var session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        User user = User.builder()
//                .username("Ivan5@mail.ru")
//                .build();
//        Profile profile = Profile.builder()
//                .language("RU")
//                .street("Stefan 1")
//                .build();
//
//        session.save(user);
//        profile.setUser(user);
//        session.save(profile);
//
//        session.getTransaction().commit();
//    }
//
//    @Test
//    public void checkOrRemove() throws SQLException {
//        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
//        @Cleanup var session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        Company company = session.get(Company.class, 8);
//        company.getUsers().removeIf(user -> user.getId().equals(5));
//
//        session.getTransaction().commit();
    }

    @Test
    public void addNewUserAndCompany() throws SQLException {
//        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
//        @Cleanup var session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        Company company = Company.builder()
//                .name("Amazon")
//                .build();
//        User user = User.builder()
//                .username("Ivan3@mail.ru")
//                .build();
//
//        company.addUser(user);
//
//        session.save(company);
//
//        session.getTransaction().commit();
    }

    @Test
    public void checkOneToMany() throws SQLException {
//        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
//        @Cleanup var session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        var company = session.get(Company.class, 8);
//        System.out.println(company.getUsers());
//
//        session.getTransaction().commit();
    }

    @Test
    public void testHibernateApi() throws SQLException,IllegalAccessException, NoSuchFieldException {
//        var user = User.builder()
//                .username("ivan2@mail.ru")
//                .firstname("Ivan")
//                .lastname("Ivanov")
//                .birthDate(
//                        new Birthday(LocalDate.of(2000, 01, 01)))
//                .role(Role.USER)
//                .build();
//
//        var sql = """
//                insert into
//                %s
//                (%s)
//                values
//                (%s)
//                """;
//
//        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
//                .map(table -> table.schema() + "." + table.name())
//                .orElse(user.getClass().getName());
//
//        Field[] fields = user.getClass().getDeclaredFields();
//
//        var columnNames = Arrays.stream(fields)
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name)
//                        .orElse(field.getName())
//                ).collect(Collectors.joining(", "));
//
//        var columnValues = Arrays.stream(fields)
//                .map(field -> "?")
//                .collect(Collectors.joining(", "));
//
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
//                "postgres","kaibaman178");
//        PreparedStatement preparedStatement = connection
//                .prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//
//
//        for(int i = 0; i < fields.length; i++) {
//            fields[i].setAccessible(true);
//            preparedStatement.setObject(i + 1, fields[i].get(user));
//        }
//
//        System.out.println(preparedStatement);
//        preparedStatement.executeUpdate();
//
//
//        preparedStatement.close();
//        connection.close();
    }
}
