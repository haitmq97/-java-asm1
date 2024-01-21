package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;

@Repository
public class DonationDAOImpl implements DonationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(Donation donation) {
		getSession().save(donation);

	}
	
	

	@Override
	public Donation getDontaion(int theId) {
		return getSession().get(Donation.class, theId);
	}

	@Override
	public List<Donation> getDonationList() {
		Query<Donation> theQuery = getSession().createQuery("from Donation", Donation.class);

		// execute the query and return user list

		return theQuery.getResultList();
	}

	@Override
	public Page<Donation> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Donation> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int theId) {
		// TODO Auto-generated method stub
		
	}
	
	

}
