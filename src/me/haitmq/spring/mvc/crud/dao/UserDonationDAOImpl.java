package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.haitmq.spring.mvc.crud.entity.UserDonation;

@Repository
public class UserDonationDAOImpl implements UserDonationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(UserDonation userDonation) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userDonation);

	}

	@Override
	public UserDonation getUserDonation(int theId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(UserDonation.class, theId);
	}

	@Override
	public List<UserDonation> getUserDonations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("delete from UserDonation where id=:theId");

		theQuery.setParameter("theId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public List<UserDonation> getUserByDonationId(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query<UserDonation> theQuery = session.createQuery("from UserDonation ud where ud.donation=:theId", UserDonation.class);
		theQuery.setParameter("theId", theId);
		return theQuery.getResultList();
	}

	@Override
	public List<UserDonation> getDonationByUserId(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query<UserDonation> theQuery = session.createQuery("from UserDonation ud where ud.user=:theId", UserDonation.class);
		theQuery.setParameter("theId", theId);
		return theQuery.getResultList();
	}
	

}
