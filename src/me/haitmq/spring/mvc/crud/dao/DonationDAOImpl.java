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
import org.springframework.data.repository.PagingAndSortingRepository;
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
		getSession().saveOrUpdate(donation);

	}
	
	

	@Override
	public Donation getDontaion(int theId) {
		return getSession().get(Donation.class, theId);
	}

	@Override
	public List<Donation> getDonationList() {
		Query<Donation> theQuery = getSession().createQuery("from Donation", Donation.class);

		return theQuery.getResultList();
	}



	@Override
	public void delete(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("delete from Donation where id=:theId");

		theQuery.setParameter("theId", theId);

		theQuery.executeUpdate();
		
	}

	@Override
	public List<Donation> findByPhoneNumber(String searchString) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery("from Donation d where d.phoneNumber like concat(:searchString, '%')", Donation.class);
		theQuery.setParameter("searchString", searchString);
		return theQuery.getResultList();
	}

	@Override
	public List<Donation> findByOrganization(String searchString) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery("from Donation d where d.organization like concat(:searchString, '%')", Donation.class);
		theQuery.setParameter("searchString", searchString);
		return theQuery.getResultList();
	}

	@Override
	public List<Donation> findByCode(String searchString) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery("from Donation d where d.code like concat(:searchString, '%')", Donation.class);
		theQuery.setParameter("searchString", searchString);
		return theQuery.getResultList();
	}
	
	@Override
	public List<Donation> findByStatus(String searchString) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery("from Donation d where d.status like concat(:searchString, '%')", Donation.class);
		theQuery.setParameter("searchString", searchString);
		return theQuery.getResultList();
	}

	@Override
	public List<Donation> findByPhoneNumberOrOrganizationOrCode(String searchString) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

	@Override
	public long countDonations() {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> countQuery = session.createQuery("select count(u) from User u", Long.class);
		return countQuery.uniqueResult();
	}

	@Override
	public Page<Donation> findAll(Pageable pageable) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Donation> theQuery = session.createQuery("from Donation", Donation.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		return new PageImpl<>(theQuery.getResultList(), pageable, countDonations());
	}
        
	

}
