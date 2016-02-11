/*
 *  Copyright (C) 2011 egorov
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.apertum.qsystem.server;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import ru.apertum.qsystem.common.exceptions.ServerException;

/**
 *
 * @author egorov
 */
public class Spring {

    private final BeanFactory factory;
    private final String driverClassName;
    private final String url;
    private final String username;
    private final String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public BeanFactory getFactory() {
        return factory;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    /**
     * For transactions through the transmission to perform a class with a method which implements the database
     *
     * @return
     */
    public TransactionTemplate getTt() {
        return new TransactionTemplate(getTxManager());
    }

    /**
     * For transactions in the usual way with the opening transaction named
     *
     * @return
     */
    public HibernateTransactionManager getTxManager() {
        return (HibernateTransactionManager) factory.getBean("transactionManager");
    }

    private Spring() {
        try {
            factory = new ClassPathXmlApplicationContext("/ru/apertum/qsystem/spring/qsContext.xml");
        } catch (BeanCreationException ex) {
            throw new ServerException("Failed to create class-bin application context: \"" + ex.getCause().getMessage() + "\"\n"
                    + "bin error \"" + ex.getBeanName() + "\""
                    + "Error message: \"" + ex.getCause().getMessage() + "\"\n" + ex);
        } catch (BeansException ex) {
            throw new ServerException("Error class bean application context: \"" + ex.getCause().getMessage() + "\"\n"
                    + "Error message: \"" + ex.getCause().getMessage() + "\"\n" + ex);
        } catch (Exception ex) {
            throw new ServerException("Failed to create application context: " + ex);
        }

        final ComboPooledDataSource bds = (ComboPooledDataSource) factory.getBean("c3p0DataSource");
        driverClassName = bds.getDriverClass();
        url = bds.getJdbcUrl();
        username = bds.getUser();
        password = bds.getPassword();
    }

    public static Spring getInstance() {
        return SpringHolder.INSTANCE;
    }

    private static class SpringHolder {

        private static final Spring INSTANCE = new Spring();
    }

    public Spring getHt() {
        return this;
    }

    public void saveAll(Collection list) {
        final Session ses = getTxManager().getSessionFactory().getCurrentSession();
        list.stream().forEach((object) -> {
            ses.save(object);
        });
        ses.flush();
    }

    public void saveOrUpdateAll(Collection list) {
        final Session ses = getTxManager().getSessionFactory().getCurrentSession();
        list.stream().forEach((object) -> {
            ses.saveOrUpdate(object);
        });
        ses.flush();
    }

    public void saveOrUpdate(Object obj) {
        final Session ses = getTxManager().getSessionFactory().getCurrentSession();
        ses.saveOrUpdate(obj);
        ses.flush();
    }

    public void deleteAll(Collection list) {
        final Session ses = getTxManager().getSessionFactory().getCurrentSession();
        list.stream().forEach((object) -> {
            ses.delete(object);
        });
        ses.flush();
    }

    public void delete(Object obj) {
        final Session ses = getTxManager().getSessionFactory().getCurrentSession();
        ses.delete(obj);
        ses.flush();
    }

    public List loadAll(Class clazz) {
        final Session ses = getTxManager().getSessionFactory().openSession();
        try {
            return ses.createCriteria(clazz).list();
        } finally {
            ses.close();
        }
    }

    public void load(Object obj, Serializable srlzbl) {
        final Session ses = getTxManager().getSessionFactory().openSession();
        try {
            ses.load(obj, srlzbl);
        } finally {
            ses.close();
        }
    }

    public List findByCriteria(DetachedCriteria dCriteria) {
        List list;
        final Session ses = getTxManager().getSessionFactory().openSession();
        try {
            list = dCriteria.getExecutableCriteria(ses).list();
        } finally {
            ses.close();
        }
        return list;
    }

    public <T> T get(Class<T> clazz, Serializable srlzbl) {
        final Session ses = getTxManager().getSessionFactory().openSession();
        try {
            return (T) ses.get(clazz, srlzbl);
        } finally {
            ses.close();
        }
    }

    public List find(String hql) {
        final Session ses = getTxManager().getSessionFactory().openSession();
        try {
            return ses.createQuery(hql).list();
        } finally {
            ses.close();
        }
    }

    public SessionFactory getSessionFactory() {
        return getTxManager().getSessionFactory();
    }
}
