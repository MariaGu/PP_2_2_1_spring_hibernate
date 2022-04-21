package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @Transactional
    public User getUserByCar(String model, int series) {

//        String hql = "SELECT u FROM User u INNER JOIN Car c ON c.id = u.car.id WHERE c.model = :modelName AND c.series = :seriesName";
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery(hql);
//        query.setParameter("modelName", model);
//        query.setParameter("seriesName", series);
//        User user = (User) query.getSingleResult();

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                "from User u WHERE u.car.model = :modelName AND u.car.series = :seriesName");
        query.setParameter("modelName", model);
        query.setParameter("seriesName", series);

        return query.getSingleResult();
    }

}
