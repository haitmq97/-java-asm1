package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus;



@Repository
public class UserDonationDAOImpl implements UserDonationDAO {
	
	private static final Logger log = LoggerFactory.getLogger(UserDonationDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	

	@Override
	public void save(UserDonation userDonation) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(userDonation);
		} catch (Exception e) {
			//log.error("UserDonationDAO ERROR - save(): ", e);
			//e.printStackTrace();
		}
	}

	@Override
	public void update(UserDonation userDonation) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(userDonation);
		} catch (Exception e) {
			//log.error("UserDonationDAO ERROR - update(): ", e);
			//e.printStackTrace();
		}
		
	}
	
	@Override
	public void saveOrUpdate(UserDonation userDonation) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(userDonation);
		} catch (Exception e) {
			//log.error("UserDonationDAO ERROR - saveOrUpdate(): ", e);
			//e.printStackTrace();
		}
		
	}

	@Override
	public UserDonation getUserDonation(int theId) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(UserDonation.class, theId);
			
		} catch (Exception e) {
			//log.error("UserDonationDAO ERROR - getUserDonation(): ", e);
			//e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserDonation> getAllUserDonations() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<UserDonation> theQuery = session.createQuery("from UserDonation", UserDonation.class);
			return theQuery.getResultList();
			
		} catch (Exception e) {
			//log.error("UserDonationDAO ERROR - getAllUserDonations(): ", e);
			//e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public void delete(int theId) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			Query theQuery = session.createQuery("delete from UserDonation where id=:theId");

			theQuery.setParameter("theId", theId);

			theQuery.executeUpdate();
			
		} catch (Exception e) {
			//log.error("UserDonationDAO ERROR - delete(): ", e);
			//e.printStackTrace();
			
		}

	}
	

	

	@Override
	public List<UserDonation> getUserDonationByDonationId(int theId) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<UserDonation> theQuery = session.createQuery("from UserDonation ud where ud.donation=:theId", UserDonation.class);
			theQuery.setParameter("theId", theId);
			return theQuery.getResultList();
			
		} catch (Exception e) {
			//log.error("UserDonationDAO ERROR - getUserDonationByDonationId(): ", e);
			//e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public List<UserDonation> getUserDonationByUserId(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query<UserDonation> theQuery = session.createQuery("from UserDonation ud where ud.user.id=:theId", UserDonation.class);
		theQuery.setParameter("theId", theId);
		return theQuery.getResultList();
	}

	@Override
	public Page<UserDonation> findAll(Pageable pageable) {
		String theQueryString = "from UserDonation ud";

		Session session = sessionFactory.getCurrentSession();
		Query<UserDonation> theQuery = session.createQuery(theQueryString, UserDonation.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> theCountQuery = session.createQuery("select count(ud) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}

	@Override
	public Page<UserDonation> findAllSortByStatus(Pageable pageable) {
		String theQueryString = "from UserDonation ud order by ud.status";

		Session session = sessionFactory.getCurrentSession();
		Query<UserDonation> theQuery = session.createQuery(theQueryString, UserDonation.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> theCountQuery = session.createQuery("select count(ud) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}

	@Override
	public Page<UserDonation> findAllSortByCreatedDate(Pageable pageable) {
		String theQueryString = "from UserDonation ud order by ud.createdDate";

		Session session = sessionFactory.getCurrentSession();
		Query<UserDonation> theQuery = session.createQuery(theQueryString, UserDonation.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> theCountQuery = session.createQuery("select count(ud) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}

	@Override
	public Page<UserDonation> findAllSortByStatusByCreatedDate(Pageable pageable) {
		String theQueryString ="from UserDonation ud order by ud.status, ud.createdDate";

		Session session = sessionFactory.getCurrentSession();
		Query<UserDonation> theQuery = session.createQuery(theQueryString, UserDonation.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> theCountQuery = session.createQuery("select count(ud) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}

	
	///////////////////////////
	@Override
	public Page<UserDonation> findByUserId(int userId, Pageable pageable) {
		 try {
		        String queryString = "from UserDonation ud where ud.user.id = :userId";

		        Session session = sessionFactory.getCurrentSession();
		        Query<UserDonation> query = session.createQuery(queryString, UserDonation.class);
		        query.setFirstResult((int) pageable.getOffset());
		        query.setMaxResults(pageable.getPageSize());
		        query.setParameter("userId", userId);

		        Query<Long> countQuery = session.createQuery("select count(ud) " + queryString, Long.class);
		        countQuery.setParameter("userId", userId);

		        return new PageImpl<>(query.getResultList(), pageable, countQuery.uniqueResult());
		    } catch (HibernateException e) {
		        // Handle HibernateException (or a more specific exception) appropriately
		        throw new RuntimeException("Error processing findByUserId", e);
		    }
	}

	//////////////////////////////////////////////
	@Override
	public Page<UserDonation> findByDonationId(int donationId, Pageable pageable) {
		 try {
		        String queryString = "from UserDonation ud where ud.donation.id = :donationId";

		        Session session = sessionFactory.getCurrentSession();
		        Query<UserDonation> query = session.createQuery(queryString, UserDonation.class);
		        query.setFirstResult((int) pageable.getOffset());
		        query.setMaxResults(pageable.getPageSize());
		        query.setParameter("donationId", donationId);

		        Query<Long> countQuery = session.createQuery("select count(ud) " + queryString, Long.class);
		        countQuery.setParameter("donationId", donationId);

		        return new PageImpl<>(query.getResultList(), pageable, countQuery.uniqueResult());
		    } catch (HibernateException e) {
		        // Handle HibernateException (or a more specific exception) appropriately
		        throw new RuntimeException("Error processing findByUserId", e);
		    }
	}
	
	
	////////////////////////////
	
	@Override
	public Long getTotalMoneyByDonationId(int theId) {
		 try {
			 	String queryString = "select COALESCE(sum(money), 0) from UserDonation ud where ud.donation.id = :donationId and ud.status = :status";
		        Session session = sessionFactory.getCurrentSession();
		        Query<Long> query = session.createQuery(queryString, Long.class);
		        
		        query.setParameter("donationId", theId);
		        
		        query.setParameter("status", UserDonationStatus.CONFIRMED);
		        

		        return query.uniqueResult();
		    } catch (HibernateException e) {
		        // Handle HibernateException (or a more specific exception) appropriately
		        throw new RuntimeException("Error processing findByUserId", e);
		    }
		 
		 // nếu nhiều thì cộng hiện tại với amount mới
	}
	
	@Override
	public List<UserDonation> getUserDonationListByDonationId(int theId) {
		 try {
		        String queryString = "from UserDonation ud where ud.donation.id = :donationId";

		        Session session = sessionFactory.getCurrentSession();
		        Query<UserDonation> query = session.createQuery(queryString, UserDonation.class);
		        
		        query.setParameter("donationId", theId);
		        

		        return query.getResultList();
		    } catch (HibernateException e) {
		        // Handle HibernateException (or a more specific exception) appropriately
		        throw new RuntimeException("Error processing findByUserId", e);
		    }
	}
	
	
	@Override
	public Page<UserDonation> findByUserNameOrDonationCodeSortByStatusByCreatedDate(String searchingValue, Pageable pageable) {
		
		String theQueryString = "from UserDonation ud where"
				 	+ " ud.showing = 1 and ("
					+ " ud.donation.code like concat(:searchingValue, '%') or" 
					+ " ud.user.userName like concat(:searchingValue, '%'))";

		Session session = sessionFactory.getCurrentSession();
		Query<UserDonation> theQuery = session.createQuery(theQueryString, UserDonation.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		theQuery.setParameter("searchingValue", searchingValue);
		
		Query<Long> theCountQuery = session.createQuery("select count(ud) " + theQueryString, Long.class);
		theCountQuery.setParameter("searchingValue", searchingValue);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}
	
}
