package dev.example.dao;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import dev.example.dto.PaymentFilter;
import dev.example.dto.QPredicate;
import dev.example.entity.Payment;
import dev.example.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static dev.example.entity.QCompany.company;
import static dev.example.entity.QPayment.payment;
import static dev.example.entity.QUser.user;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();


    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session).select(user).from(user).fetch();
    }

    public List<User> findAllByFirstName(Session session, String firstName) {
        return new JPAQuery<User>(session).select(user).from(user)
                .where(user.personalInfo().firstname.eq(firstName)).fetch();
    }

    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit) {
        return new JPAQuery<User>(session).select(user).from(user)
                .orderBy(new OrderSpecifier(Order.ASC, user.personalInfo().birthDate))
                .limit(limit).fetch();
    }

    public List<User> findAllByCompanyName(Session session, String companyName) {
        return new JPAQuery<User>(session).select(user).from(company)
                .join(company.users, user)
                .where(company.name.eq(companyName))
                .fetch();
    }

    public List<Payment> findAllPaymentsByCompanyName(Session session, String companyName) {
        return new JPAQuery<Payment>(session)
                .select(payment)
                .from(company)
                .join(company.users, user)
                .join(user.payments, payment)
                .where(company.name.eq(companyName))
                .orderBy(user.personalInfo().firstname.asc(), payment.amount.asc())
                .fetch();
    }

    public Double findAveragePaymentAmountByFirstAndLastNames(Session session, PaymentFilter filter) {

//        List<Predicate> predicates = new ArrayList<>();
//        if(filter.getFirstName() != null)
//            predicates.add(user.personalInfo().firstname.eq(filter.getFirstName()));
//        if(filter.getLastName() != null)
//            predicates.add(user.personalInfo().lastname.eq(filter.getLastName()));

        var predicate = QPredicate.builder()
                .add(filter.getFirstName(), user.personalInfo().firstname::eq)
                .add(filter.getLastName(), user.personalInfo().lastname::eq)
                .buildOr();


        return new JPAQuery<Double>(session)
                .select(payment.amount.avg())
                .from(payment)
                .join(payment.receiver(), user)
                .where(predicate)
                .fetchOne();
    }

    public List<Tuple> findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(Session session) {
        return new JPAQuery<Tuple>(session)
                .select(company.name, payment.amount.avg())
                .from(company)
                .join(company.users, user)
                .join(user.payments, payment)
                .groupBy(company.name)
                .orderBy(company.name.asc())
                .fetch();
    }

    public List<Tuple> isItPossible(Session session) {
        return new JPAQuery<Tuple>(session)
                .select(user, payment.amount.avg())
                .from(user)
                .join(user.payments, payment)
                .groupBy(user.id)
                .having(payment.amount.avg().gt(
                        new JPAQuery<Double>(session)
                                .select(payment.amount.avg())
                                .from(payment)
                ))
                .orderBy(user.personalInfo().firstname.asc())
                .fetch();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
