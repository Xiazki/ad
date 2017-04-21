package com.ad.dao.base;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by xiang on 2017/4/7.
 */
public class BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public BaseDao() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public void save(T t) {
        getSession().save(t);
    }

    public void update(T t) {
        getSession().saveOrUpdate(t);
    }

    public T get(long id) {
        T t = (T) getSession().get(clazz, id);
        return t;
    }

    public void delete(long id) {
        T t = get(id);
        if (t != null) {
            getSession().delete(t);
        }
    }

    public Integer count() {
        String hql = "from " + getClassName(clazz);
        Query query = getSession().createQuery(hql);
        return query.list().size();
    }

//    public List<T> list(Integer start, Integer length) {
//        return list(start, length, null);
//    }
//
//    public List<T> list(Integer start, Integer length, String search) {
//        String hql = "from "+getClassName(clazz) + " o where ";
//        return null;
//    }

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

    public String getClassName(Class<T> var1) {
        String entityName = var1.getSimpleName();
        Entity entity = var1.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name())) {
            entityName = entity.name();
        }
        return entityName;
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