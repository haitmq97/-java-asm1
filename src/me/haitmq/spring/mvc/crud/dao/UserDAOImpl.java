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

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.Role;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;
import me.haitmq.spring.mvc.crud.utils.FormatData;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(User user) {

		try {
			// ? không thể lưu trực tiếp ? phải tách rời trước
			getSession().evict(getSession().get(User.class, user.getId()));

		} catch (Exception e) {

		} finally {
			getSession().saveOrUpdate(user);
		}
	}

	@Override
	public User getUser(int theId) {
		return getSession().get(User.class, theId);
	}

	@Override
	public List<User> getUserList() {

		Query<User> theQuery = getSession().createQuery("from User", User.class);
		return theQuery.getResultList();
	}

	
	
	@Override
	public Page<User> findAll(Pageable pageable) {
		String theQueryString = "from User u where u.showing = 1";

		Query<User> theQuery = getSession().createQuery(theQueryString, User.class);


		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countQuery = getSession().createQuery("select count(u) " + theQueryString, Long.class);

		return new PageImpl<>(theQuery.getResultList(), pageable, countQuery.uniqueResult());
	}

	// tìm theo username, email và số điện thoại (những user có showing =1)
	@Override
	public Page<User> findByEmailOrUserNameOrPhoneNumber(Pageable pageable, String searchingValue) {

		String theQueryString = "from User u where " 
				+ "u.showing = 1 and ("
				+ " u.userName like concat(:searchingValue, '%') or" 
				+ " u.email like concat(:searchingValue, '%') or"
				+ " u.phoneNumber like concat(:searchingValue, '%'))";

		Query<User> theQuery = getSession().createQuery(theQueryString, User.class);

		theQuery.setParameter("searchingValue", searchingValue);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countQuery = getSession().createQuery("select count(u) " + theQueryString, Long.class);

		countQuery.setParameter("searchingValue", searchingValue);

		return new PageImpl<>(theQuery.getResultList(), pageable, countQuery.uniqueResult());
	}

	@Override
	public Page<User> findByEmailOrUserNameOrPhoneNumberOrStatus(String searchingValue, Pageable pageable) {
		
		UserStatus userStatus = FormatData.userStatusFormat(searchingValue);
		
		String theQueryString = 
				"from User u where " 
				+ "u.showing = 1 and ("
				+ " u.status = :userStatus or"
				+ " lower(u.userName) like lower(concat(:searchingValue, '%')) or" 
				+ " lower(u.email) like lower(concat(:searchingValue, '%') ) or"
				+ " lower(u.role.roleName) like lower(concat(:searchingValue, '%')) or"
				+ " lower(u.phoneNumber) like lower(concat(:searchingValue, '%')))";


		Query<User> theQuery = getSession().createQuery(theQueryString, User.class);

		theQuery.setParameter("searchingValue", searchingValue);
		theQuery.setParameter("userStatus", userStatus);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countQuery = getSession().createQuery("select count(u) " + theQueryString, Long.class);

		countQuery.setParameter("searchingValue", searchingValue);
		countQuery.setParameter("userStatus", userStatus);

		return new PageImpl<>(theQuery.getResultList(), pageable, countQuery.uniqueResult());

	}

	// delete
	@Override
	public void delete(int theId) {

		Query theQuery = getSession().createQuery("delete from User where id=:userId");

		theQuery.setParameter("userId", theId);

		theQuery.executeUpdate();

	}

	// for login

	@Override
	public User getUserByUserName(String userName) {
		Query<User> theQuery = getSession().createQuery("from User where userName=:userName", User.class);
		User result = theQuery.setParameter("userName", userName).uniqueResult();
		return result;
	}

	@Override
	public User getUserByEmail(String email) {

		User result = null;
		Query<User> theQuery = getSession().createQuery("from User where email=:email", User.class);
		result = theQuery.setParameter("email", email).uniqueResult();

		return result;
	}

	@Override
	public User getUserByUserNameOrEmail(String userNameOrEmail) {
		String theQueryString = "from User u where " 
				+ " u.userName =:userNameOrEmail or"
				+ " u.email =:userNameOrEmail";

		Query<User> theQuery = getSession().createQuery(theQueryString, User.class);

		theQuery.setParameter("userNameOrEmail", userNameOrEmail);

		return theQuery.uniqueResult();
	}

}
