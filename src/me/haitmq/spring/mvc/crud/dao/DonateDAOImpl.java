package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.haitmq.spring.mvc.crud.entity.Donate;

@Repository
public class DonateDAOImpl implements DonateDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Donate donate) {
		Session session = sessionFactory.getCurrentSession();
		session.save(donate);
	}

	@Override
	public void update(Donate donate) {
		Session session = sessionFactory.getCurrentSession();
		session.update(donate);
		
	}

	@Override
	public Donate getDonate(int theId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Donate.class, theId);
	}

	@Override
	public List<Donate> getDonates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("delete from Donate where id=:theId");

		theQuery.setParameter("theId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public List<Donate> getDonateByDonationId(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donate> theQuery = session.createQuery("from Donate ud where ud.donation=:theId", Donate.class);
		theQuery.setParameter("theId", theId);
		return theQuery.getResultList();
	}

	@Override
	public List<Donate> getDonateByUserId(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donate> theQuery = session.createQuery("from Donate ud where ud.user=:theId", Donate.class);
		theQuery.setParameter("theId", theId);
		return theQuery.getResultList();
	}
	

}
