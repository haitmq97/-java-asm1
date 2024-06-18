package me.haitmq.spring.mvc.crud.dao;

import java.util.Comparator;
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

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;
import me.haitmq.spring.mvc.crud.utils.FormatData;



@Repository
public class UserDonationDAOImpl implements UserDonationDAO {
	
	private static final Logger log = LoggerFactory.getLogger(UserDonationDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	

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
	
	//sdfohasdf
	@Override
	public Page<UserDonation> findByUserNameOrDonationCodeSortByStatusByCreatedDate(String searchingValue, Pageable pageable) {
		
		UserDonationStatus userDonationStatus = FormatData.userDonationStatusFormat(searchingValue);
		
		String theQueryString = "from UserDonation ud"
				 	+ " where ud.showing = 1"
				 	+ " and ( ud.status = :userDonationStatus"
					+ " or lower(ud.donation.code) like lower(:userDonationStatus)" 
					+ " or lower(ud.user.userName) like lower(concat(:searchingValue, '%')))"
					+ " order by ud.createdDate desc ,ud.status desc";

		//Session session = sessionFactory.getCurrentSession();
		Query<UserDonation> theQuery = getSession().createQuery(theQueryString, UserDonation.class);
		// số bảng ghi cần bỏ qua (kết quả đầu tiên)
		theQuery.setFirstResult((int) pageable.getOffset());
		// thiết lập số lượng tối đa bảng ghi sẽ được trả về
		theQuery.setMaxResults(pageable.getPageSize());
		theQuery.setParameter("searchingValue", searchingValue);
		theQuery.setParameter("userDonationStatus", userDonationStatus);
		// đếm và trả về tổng số bảng ghi
		Query<Long> theCountQuery = getSession().createQuery("select count(ud) " + theQueryString, Long.class);
		theCountQuery.setParameter("searchingValue", searchingValue);
		theCountQuery.setParameter("userDonationStatus", userDonationStatus);
		return new PageImpl<>(theQuery.getResultList(), pageable, theCountQuery.uniqueResult());
	}
	
	

	@Override
	public Page<UserDonation> findByUserNameSortByCreatedDate(String username, String searchingValue,
			Pageable pageable) {

		UserDonationStatus userDonationStatus = FormatData.userDonationStatusFormat(searchingValue);

		String theQueryString = "from UserDonation ud" 
				+ " where ud.showing = 1" 
				+ " and ud.user.userName = :userName"
				+ " and (ud.status = :userDonationStatus" 
				+ " or lower(ud.name) like lower(concat(:searchingValue, '%'))"
				+ " or lower(ud.user.userName) like lower(concat(:searchingValue, '%'))"
				+ " or lower(ud.user.email) like lower(concat(:searchingValue, '%')))"
				+ " order by ud.createdDate desc ,ud.status desc";
		Query<UserDonation> theQuery = getSession().createQuery(theQueryString, UserDonation.class);

		// Đặt giá trị tham số
		theQuery.setParameter("searchingValue", searchingValue);
		theQuery.setParameter("userName", username);
		theQuery.setParameter("userDonationStatus", userDonationStatus);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countQuery = getSession().createQuery("select count(ud) " + theQueryString, Long.class);

		countQuery.setParameter("searchingValue", searchingValue);
		countQuery.setParameter("userName", username);
		countQuery.setParameter("userDonationStatus", userDonationStatus);

		return new PageImpl<>(theQuery.getResultList(), pageable, countQuery.uniqueResult());

		
	}
	
	
	
	
	@Override
	public Page<UserDonation> findByDonationCodeSortByCreatedDate(String donationCode, String searchingValue,
			Pageable pageable) {

		UserDonationStatus userDonationStatus = FormatData.userDonationStatusFormat(searchingValue);
		
		String theQueryString = "from UserDonation ud" 
				+ " where ud.showing = 1" 
				+ " and ud.donation.code = :donationCode"
				+ " and ( ud.status = :userDonationStatus" 
				+ " or lower(ud.name) like lower(concat(:searchingValue, '%'))"
				+ " or lower(ud.user.userName) like lower(concat(:searchingValue, '%'))"
				+ " or lower(ud.user.email) like lower(concat(:searchingValue, '%')))"
				+ " order by ud.createdDate desc ,ud.status desc";

		Query<UserDonation> theQuery = getSession().createQuery(theQueryString, UserDonation.class);

		// Đặt giá trị tham số
		theQuery.setParameter("searchingValue", searchingValue);
		theQuery.setParameter("donationCode", donationCode);
		theQuery.setParameter("userDonationStatus", userDonationStatus);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countQuery = getSession().createQuery("select count(ud) " + theQueryString, Long.class);

		countQuery.setParameter("searchingValue", searchingValue);
		countQuery.setParameter("donationCode", donationCode);
		countQuery.setParameter("userDonationStatus", userDonationStatus);
		Long total = countQuery.uniqueResult();

		return new PageImpl<>(theQuery.getResultList(), pageable, total);

		
	}
	
	
	@Override
	public Page<UserDonation> findByDonationCodeAndStatusSortByCreatedDate(String donationCode, UserDonationStatus status ,String searchingValue,
			Pageable pageable) {
		
		
		String theQueryString = "from UserDonation ud where " 
				+ "ud.showing = 1 and " 
				+ " ud.donation.code =:donationCode"
				+ " and ud.status =:status and ("
				+ " lower(ud.status) like lower(concat(:searchingValue, '%')) or" 
				+ " lower(ud.name) like lower(concat(:searchingValue, '%')) or"
				+ " lower(ud.user.userName) like lower(concat(:searchingValue, '%')) or"
				+ " lower(ud.user.email) like lower(concat(:searchingValue, '%')))"
				+ " order by ud.createdDate desc ,ud.status desc";

		Query<UserDonation> theQuery = getSession().createQuery(theQueryString, UserDonation.class);

		// Đặt giá trị tham số
		theQuery.setParameter("searchingValue", searchingValue);
		theQuery.setParameter("donationCode", donationCode);
		theQuery.setParameter("status", status);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countQuery = getSession().createQuery("select count(ud) " + theQueryString, Long.class);

		countQuery.setParameter("searchingValue", searchingValue);
		countQuery.setParameter("donationCode", donationCode);
		countQuery.setParameter("status", status);
		Long total = countQuery.uniqueResult();

		return new PageImpl<>(theQuery.getResultList(), pageable, total);
	}
	

	public Page<UserDonation> findByDonationCodeGroupByUserSortByTotalMoney(String donationCode, String searchingValue, Pageable pageable) {
		
		UserDonationStatus userDonationStatus = FormatData.userDonationStatusFormat(searchingValue);
		
		String theQueryString = "from UserDonation ud where " 
				+ "ud.showing = 1 and " 
				+ " ud.donation.code =:donationCode and ("
				+ " ud.status = :userDonationStatus or" 
				+ " lower(ud.name) like lower(concat(:searchingValue, '%')) or"
				+ " lower(ud.user.userName) like lower(concat(:searchingValue, '%')) or"
				+ " lower(ud.user.email) like lower(concat(:searchingValue, '%')))"
				+ " order by ud.createdDate desc ,ud.status desc";

		Query<UserDonation> theQuery = getSession().createQuery("select ud.donation.code, ud.user.userName, ud.user.email, sum(money) as sum_money"+theQueryString, UserDonation.class);

		// Đặt giá trị tham số
		theQuery.setParameter("searchingValue", searchingValue);
		theQuery.setParameter("donationCode", donationCode);
		theQuery.setParameter("userDonationStatus", userDonationStatus);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countQuery = getSession().createQuery("select count(ud) " + theQueryString, Long.class);

		countQuery.setParameter("searchingValue", searchingValue);
		countQuery.setParameter("donationCode", donationCode);
		countQuery.setParameter("userDonationStatus", userDonationStatus);
		Long total = countQuery.uniqueResult();

		return new PageImpl<>(theQuery.getResultList(), pageable, total);
		
	}


	
	public List<UserDonation> getUserDonationListByDonationCode(String donationCode) {
		
		return null;
	}
	
}
