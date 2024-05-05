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

	
	public Page<User> findAll2(Pageable pageable) {
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
	public Page<User> findAllByEmailOrPhoneNumber(Pageable pageable, String searchingValue) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> theQuery = session.createQuery("from User u where u.email like concat(:searchingValue, '%')"
				+ " or u.phoneNumber like concat(:searchingValue, '%')", User.class);

		theQuery.setParameter("searchingValue", searchingValue);

		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countForSearchQuery = session
				.createQuery("select count(u) from User u where u.email like concat(:searchingValue, '%')"
						+ " or u.phoneNumber like concat(:searchingValue, '%')", Long.class);

		countForSearchQuery.setParameter("searchingValue", searchingValue);

		return new PageImpl<>(theQuery.getResultList(), pageable, countForSearchQuery.uniqueResult());
	}

	/// for login
	

	//////////////////////////////

	@Override
	public List<User> getUserList() {
		Session session = sessionFactory.getCurrentSession();

		Query<User> theQuery = session.createQuery("from User", User.class);
		return theQuery.getResultList();
	}

	@Override
	public void saveOrUpdate(User user) {
		Session session = sessionFactory.getCurrentSession();
		Role role = user.getRole();
		System.out.println("==================================>>>>>>>>dao role: " + role);
		System.out.println("==================================>>>>>>>> test here in dao");
		System.out.println("==================================>>>>>>>>dao: " + user);
		try {
			
			session.evict(session.get(User.class, user.getId()));
		} catch (Exception e) {
			// 
		}
		
		session.saveOrUpdate(user);

//		session.merge(user);


	}

	@Override
	public User getUser(int theId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(User.class, theId);
	}

	@Override
	public void deleteUser(int theId) {

		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("delete from User where id=:userId");

		theQuery.setParameter("userId", theId);

		theQuery.executeUpdate();

	}

	//////////////////////////////

	@Override
	public Page<User> findAll(Pageable pageable) {
		String theQueryString = "from User u";

		Session session = sessionFactory.getCurrentSession();
		Query<User> theQuery = session.createQuery(theQueryString, User.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> theCountQuery = session.createQuery("select count(u) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}

	@Override
	public Page<User> findByEmailOrPhoneNumberOrStatus(String searchingValue, Pageable pageable) {

		String theQueryString = "from User u where "
				+ " u.status like concat(:searchingValue, '%') or" 
				+ " u.phoneNumber like concat(:searchingValue, '%') or"
				+ " u.email like concat(:searchingValue, '%')";

		Session session = sessionFactory.getCurrentSession();
		Query<User> theQuery = session.createQuery(theQueryString, User.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		theQuery.setParameter("searchingValue", searchingValue);
		
		Query<Long> theCountQuery = session.createQuery("select count(u) " + theQueryString, Long.class);
		theCountQuery.setParameter("searchingValue", searchingValue);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}


	
	
	// for login
	@Override
	public User getUserByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		User result = null;
		Query<User> theQuery = session.createQuery("from User where userName=:userName", User.class);
		result = theQuery.setParameter("userName", userName).uniqueResult();
		return result;
		/*
		 * try(Session session = sessionFactory.getCurrentSession()) { Query<User>
		 * theQuery = session.createQuery("from User where userName=:userName",
		 * User.class); return theQuery.setParameter("userName",
		 * userName).uniqueResult(); } catch(Exception e) { e.printStackTrace(); return
		 * null; }
		 */
	}

	@Override
	public User getUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		User result = null;
		Query<User> theQuery = session.createQuery("from User where email=:email", User.class);
		result = theQuery.setParameter("email", email).uniqueResult();

		return result;
	}
	
	
	@Override
	public User getUserByUserNameOrEmail(String userName) {
		String theQueryString = "from User u where "
				+ " u.userName =:userName or" 
				+ " u.email =:userName";

		Session session = sessionFactory.getCurrentSession();
		Query<User> theQuery = session.createQuery(theQueryString, User.class);
		
		theQuery.setParameter("userName", userName);
		
		
		return theQuery.uniqueResult();
	}

}




