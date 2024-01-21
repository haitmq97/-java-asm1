package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import me.haitmq.spring.mvc.crud.entity.Role;
import me.haitmq.spring.mvc.crud.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/*
	
	@Override
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		Role role = user.getRole();
		session.save(role);
		session.saveOrUpdate(user);

	}
	
	*/
	
	@Override
	public void saveOrUpdate(User user) {
		Session session = sessionFactory.getCurrentSession();
		Role role = user.getRole();
		
		System.out.println("=====>>>>>>============================");
		System.out.println("=====>>>>>>In DAO method: ");
		System.out.println("=====>>>>>>BEFORE add role ");
		System.out.println("=====>>>>>>the User: " + user);
		System.out.println("=====>>>>>>the User role: " + user.getRole());
		System.out.println("=====>>>>>>============================");
		//session.saveOrUpdate(role);
		session.saveOrUpdate(user);
		
		System.out.println("=====>>>>>>============================");
		System.out.println("=====>>>>>>In DAO method: ");
		System.out.println("=====>>>>>>AFTER add role ");
		System.out.println("=====>>>>>>the User: " + user);
		System.out.println("=====>>>>>>the User role: " + user.getRole());
		System.out.println("=====>>>>>>============================");

	}

	@Override
	public User getUser(int theId) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(User.class, theId);
	}

	@Override
	public List<User> getUserList() {
		Session session = sessionFactory.getCurrentSession();
		Query<User> theQuery = session.createQuery("from User", User.class);

		// execute the query and return user list

		return theQuery.getResultList();
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = session.createQuery("from User", User.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		return new PageImpl<>(theQuery.getResultList(), pageable, countUsers());
	}

	private long countUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> countQuery = session.createQuery("select count(u) from User u", Long.class);
		return countQuery.uniqueResult();
	}

	@Override
	public Iterable<User> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAllByEmailOrPhoneNumber(Pageable pageable, String searchingValue) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> theQuery = session.createQuery(
				"from User u where u.email like concat(:searchingValue, '%') or u.phoneNumber like concat(:searchingValue, '%')",
				User.class);

		theQuery.setParameter("searchingValue", searchingValue); // Đặt giá trị cho tham số

		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countForSearchQuery = session.createQuery(
				"select count(u) from User u where u.email like concat(:searchingValue, '%') or u.phoneNumber like concat(:searchingValue, '%')",
				Long.class);
		
		/*
		Query<Long> countForSearchQuery = getSession().createQuery(
				"select count(u) from User u where u.email like concat(:searchingValue, '%') or u.phoneNumber like concat(:searchingValue, '%')",
				Long.class);

		*/
		countForSearchQuery.setParameter("searchingValue", searchingValue); // Đặt giá trị cho tham số

		return new PageImpl<>(theQuery.getResultList(), pageable, countForSearchQuery.uniqueResult());
	}

	@Override
	public void deleteUser(int theId) {
		
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("delete from User where id=:userId");

		theQuery.setParameter("userId", theId);

		theQuery.executeUpdate();

	}

}
