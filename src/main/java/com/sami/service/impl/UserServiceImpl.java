package com.sami.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sami.config.HibernateUtil;
import com.sami.entity.User;
import com.sami.service.UserService;

@Named
@ApplicationScoped
public class UserServiceImpl implements UserService {

	@Override
	public void save(User user) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		List<User> users = session.createQuery("from User").getResultList();
		transaction.commit();
		return users;
	}

	@Override
	public void update(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
	}

	public void delete(Long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		User user = session.get(User.class, id);
		session.delete(user);
		transaction.commit();

	}

	@Override
	public User getUser(Long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		User user = session.get(User.class, id);
		transaction.commit();
		return user;
	}
}
