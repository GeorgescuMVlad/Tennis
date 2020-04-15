package layer_data_access.repo;

import config.HibernateConfig;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserRepo {

    private final static Session session = HibernateConfig.Instance();

    public static User findUserByMail(String mail) {

        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.mail=:mail", User.class);
        query.setParameter("mail", mail);
        User user = query.uniqueResult();
        transaction.commit();

        return user;
    }
}
