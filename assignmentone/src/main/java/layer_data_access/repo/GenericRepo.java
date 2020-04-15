package layer_data_access.repo;

import config.HibernateConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Admin;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;

public class GenericRepo {

    private final static Session session = HibernateConfig.Instance();

    public static <T> T findById(Class<T> clasz, int id) {

        Transaction transaction = session.beginTransaction();
        T toReturn = null;
        toReturn = session.find(clasz, Integer.valueOf(id));
        transaction.commit();

        return toReturn;
    }

    /**
     * Save returns the object identifier not the saved object.
     */
    public static <T> int save(T toSave) {
        Transaction transaction = session.beginTransaction();
        int savedId = (int) session.save(toSave);
        transaction.commit();
        return savedId;
    }

    public static <T> void update(T toUpdate){
        Transaction transaction = session.beginTransaction();
        session.update(toUpdate);
        transaction.commit();
    }

    public static <T> void delete(T toDelete){
        Transaction transaction = session.beginTransaction();
        session.delete(toDelete);
        transaction.commit();
     }

    public static ObservableList<User> users;
    public static <T> ObservableList<User> findUsers() {

        users  =  FXCollections.observableArrayList();

        Query qee = session.createQuery("from model.User");
        Iterator ite  =qee.iterate();
        while(ite.hasNext())
        {
            User obj = (User)ite.next();
            users.add(obj);
        }

        return users;
    }

    public static ObservableList<Admin> admins;
    public static <T> ObservableList<Admin> findAdmins() {

        admins  =  FXCollections.observableArrayList();
        Query qee = session.createQuery("from model.Admin");
        Iterator ite  =qee.iterate();
        while(ite.hasNext())
        {
            Admin obj = (Admin)ite.next();
            admins.add(obj);
        }
        return admins;
    }

    public static <T> ObservableList<T> findEverything(String querry) {
        ObservableList<T> list;
        list  =  FXCollections.observableArrayList();
        Query qee = session.createQuery(querry);
        Iterator ite  =qee.iterate();
        while(ite.hasNext())
        {
            list.add((T)ite.next());
        }
        return list;
    }



}
