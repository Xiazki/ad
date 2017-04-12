package com.ad.dao.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by xiang on 2017/4/7.
 */
public class BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(T t) {
        getSession().save(t);
    }

    public void update(T t) {
        getSession().saveOrUpdate(t);
    }

    public T get(long id) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

        T t = (T) getSession().get(clazz, id);
        return t;
    }

    public void delete(long id) {
        T t = get(id);
        if (t != null) {
            getSession().delete(t);
        }
    }

    public void batchSave(List<T> entities) {
        for (T t : entities) {
            save(t);
        }
    }

    public void batchUpdate(List<T> entities) {
        for (T t : entities) {
            update(t);
        }
    }

    public void batchDelete(List<Long> ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}