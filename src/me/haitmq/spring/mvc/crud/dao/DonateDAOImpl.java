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

import me.haitmq.spring.mvc.crud.entity.Donate;
import me.haitmq.spring.mvc.crud.service.DonateService;


@Repository
public class DonateDAOImpl implements DonateDAO {
	
	private static final Logger log = LoggerFactory.getLogger(DonateDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	

	@Override
	public void save(Donate donate) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(donate);
		} catch (Exception e) {
			//log.error("DonateDAO ERROR - save(): ", e);
			//e.printStackTrace();
		}
	}

	@Override
	public void update(Donate donate) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(donate);
		} catch (Exception e) {
			//log.error("DonateDAO ERROR - update(): ", e);
			//e.printStackTrace();
		}
		
	}
	
	@Override
	public void saveOrUpdate(Donate donate) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(donate);
		} catch (Exception e) {
			//log.error("DonateDAO ERROR - saveOrUpdate(): ", e);
			//e.printStackTrace();
		}
		
	}

	@Override
	public Donate getDonate(int theId) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Donate.class, theId);
			
		} catch (Exception e) {
			//log.error("DonateDAO ERROR - getDonate(): ", e);
			//e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Donate> getAllDonates() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Donate> theQuery = session.createQuery("from Donate", Donate.class);
			return theQuery.getResultList();
			
		} catch (Exception e) {
			//log.error("DonateDAO ERROR - getAllDonates(): ", e);
			//e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public void delete(int theId) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			Query theQuery = session.createQuery("delete from Donate where id=:theId");

			theQuery.setParameter("theId", theId);

			theQuery.executeUpdate();
			
		} catch (Exception e) {
			//log.error("DonateDAO ERROR - delete(): ", e);
			//e.printStackTrace();
			
		}

	}
	

	

	@Override
	public List<Donate> getDonateByDonationId(int theId) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Donate> theQuery = session.createQuery("from Donate ud where ud.donation=:theId", Donate.class);
			theQuery.setParameter("theId", theId);
			return theQuery.getResultList();
			
		} catch (Exception e) {
			//log.error("DonateDAO ERROR - getDonateByDonationId(): ", e);
			//e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public List<Donate> getDonateByUserId(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donate> theQuery = session.createQuery("from Donate ud where ud.user=:theId", Donate.class);
		theQuery.setParameter("theId", theId);
		return theQuery.getResultList();
	}

	@Override
	public Page<Donate> findAll(Pageable pageable) {
		String theQueryString = "from Donate ud";

		Session session = sessionFactory.getCurrentSession();
		Query<Donate> theQuery = session.createQuery(theQueryString, Donate.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> theCountQuery = session.createQuery("select count(ud) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}

	@Override
	public Page<Donate> findAllSortByStatus(Pageable pageable) {
		String theQueryString = "from Donate ud order by ud.status";

		Session session = sessionFactory.getCurrentSession();
		Query<Donate> theQuery = session.createQuery(theQueryString, Donate.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> theCountQuery = session.createQuery("select count(ud) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}

	@Override
	public Page<Donate> findAllSortByCreatedDate(Pageable pageable) {
		String theQueryString = "from Donate ud order by ud.createdDate";

		Session session = sessionFactory.getCurrentSession();
		Query<Donate> theQuery = session.createQuery(theQueryString, Donate.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> theCountQuery = session.createQuery("select count(ud) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}

	@Override
	public Page<Donate> findAllSortByStatusByCreatedDate(Pageable pageable) {
		String theQueryString ="from Donate ud order by ud.status, ud.createdDate";

		Session session = sessionFactory.getCurrentSession();
		Query<Donate> theQuery = session.createQuery(theQueryString, Donate.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> theCountQuery = session.createQuery("select count(ud) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}

	
	///////////////////////////
	@Override
	public Page<Donate> findByUserId(int userId, Pageable pageable) {
		 try {
		        String queryString = "from Donate ud where ud.user.id = :userId";

		        Session session = sessionFactory.getCurrentSession();
		        Query<Donate> query = session.createQuery(queryString, Donate.class);
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
	public Page<Donate> findByDonationId(int donationId, Pageable pageable) {
		 try {
		        String queryString = "from Donate ud where ud.donation.id = :donationId";

		        Session session = sessionFactory.getCurrentSession();
		        Query<Donate> query = session.createQuery(queryString, Donate.class);
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
			 	String queryString = "select COALESCE(sum(money), 0) from Donate ud where ud.donation.id = :donationId and ud.status = 1";
		        Session session = sessionFactory.getCurrentSession();
		        Query<Long> query = session.createQuery(queryString, Long.class);
		        
		        query.setParameter("donationId", theId);
		        

		        return query.uniqueResult();
		    } catch (HibernateException e) {
		        // Handle HibernateException (or a more specific exception) appropriately
		        throw new RuntimeException("Error processing findByUserId", e);
		    }
	}
	
	@Override
	public List<Donate> getDonteListByDonationId(int theId) {
		 try {
		        String queryString = "from Donate ud where ud.donation.id = :donationId";

		        Session session = sessionFactory.getCurrentSession();
		        Query<Donate> query = session.createQuery(queryString, Donate.class);
		        
		        query.setParameter("donationId", theId);
		        

		        return query.getResultList();
		    } catch (HibernateException e) {
		        // Handle HibernateException (or a more specific exception) appropriately
		        throw new RuntimeException("Error processing findByUserId", e);
		    }
	}
	
}
