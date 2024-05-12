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
		Session session = sessionFactory.getCurrentSession();
		System.out.println("================>> donation dao iml");
		
		session.saveOrUpdate(donation);
		System.out.println("================>> donation dao iml: finish");

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

	/*
	@Override
	public Page<Donation> findByPhoneNumber(String searchString, Pageable pageable) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session
				.createQuery("from Donation d where d.phoneNumber like concat(:searchString, '%')", Donation.class);
		theQuery.setParameter("searchString", searchString);
		return theQuery.getResultList();
	}

	@Override
	public Page<Donation> findByOrganization(String searchString, Pageable pageable) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session
				.createQuery("from Donation d where d.organization like concat(:searchString, '%')", Donation.class);
		theQuery.setParameter("searchString", searchString);
		return theQuery.getResultList();
	}

	@Override
	public Page<Donation> findByCode(String searchString, Pageable pageable) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery("from Donation d where d.code like concat(:searchString, '%')",
				Donation.class);
		theQuery.setParameter("searchString", searchString);
		return theQuery.getResultList();
	}

	@Override
	public Page<Donation> findByStatus(String searchString, Pageable pageable) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery("from Donation d where d.status like concat(:searchString, '%')",
				Donation.class);
		theQuery.setParameter("searchString", searchString);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		return new PageImpl<>(theQuery.getResultList(), pageable, countDonations());
	}

	@Override
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchString, Pageable pageable) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery("from Donation d where "
				+ "d.status like concat(:searchString, '%') or" + " d.phoneNumber like concat(:searchString, '%') or"
				+ " d.organization like concat(:searchString, '%') or" + " d.code like concat(:searchString, '%')",
				Donation.class);
		theQuery.setParameter("searchString", searchString);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		return new PageImpl<>(theQuery.getResultList(), pageable, countDonations());
	}

	@Override
	public long countDonations() {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> countQuery = session.createQuery("select count(d) from Donation d", Long.class);
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
	
	*/

	////////////////////////////////////////////



	@Override
	public Page<Donation> findByQuery(String theQueryString, Pageable pageable) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery(theQueryString, Donation.class);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> countQuery = session.createQuery("select count(d) " + theQueryString, Long.class);
		return new PageImpl<>(theQuery.getResultList(), pageable, countQuery.uniqueResult());
	}
	
	
	@Override
	public Page<Donation> findByQuery(String theQueryString, String searchingValue, Pageable pageable) {
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery(theQueryString, Donation.class);
		theQuery.setParameter("searchingValue", searchingValue);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		
		Query<Long> countQuery = session.createQuery("select count(d) " + theQueryString, Long.class);
		countQuery.setParameter("searchingValue", searchingValue);
		return new PageImpl<>(theQuery.getResultList(), pageable, countQuery.uniqueResult());
	}

	@Override
	public Page<Donation> findByPhoneNumber(String phoneNumber, Pageable pageable) {
		String theQueryString ="from Donation d where d.phoneNumber like concat(:searchingValue, '%')";
		return findByQuery(theQueryString, phoneNumber, pageable);
	}

	@Override
	public Page<Donation> findByOrganization(String organization, Pageable pageable) {
		String theQueryString ="from Donation d where d.organization like concat(:searchingValue, '%')";
		return findByQuery(theQueryString, organization, pageable);
	}

	@Override
	public Page<Donation> findByCode(String code, Pageable pageable) {
		String theQueryString ="from Donation d where d.code like concat(:searchingValue, '%')";
		return findByQuery(theQueryString, code, pageable);
	}

	@Override
	public Page<Donation> findByStatus(String status, Pageable pageable) {
		String theQueryString ="from Donation d where d.status like concat(:searchingValue, '%')";
		return findByQuery(theQueryString, status, pageable);
	}

	@Override
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchingValue, Pageable pageable) {
		
		String theQueryString =
				"from Donation d where "
				+ "d.showing = 1 and ("
				+ " d.status like concat(:searchingValue, '%') or" 
				+ " d.phoneNumber like concat(:searchingValue, '%') or"
				+ " d.organization like concat(:searchingValue, '%') or" 
				+ " d.code like concat(:searchingValue, '%'))";
		
		return findByQuery(theQueryString, searchingValue, pageable);
	}
	
	
	//////////////////////////
	@Override
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus2(String searchingValue, Pageable pageable) {
		String queryString = "from Donation d where "
				+ " d.status like concat(:searchingValue, '%') or" 
				+ " d.phoneNumber like concat(:searchingValue, '%') or"
				+ " d.organization like concat(:searchingValue, '%') or" 
				+ " d.code like concat(:searchingValue, '%')";
		Session session = sessionFactory.getCurrentSession();
		Query<Donation> theQuery = session.createQuery(queryString,Donation.class);

		theQuery.setParameter("searchingValue", searchingValue);

		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());
		Query<Long> countForSearchQuery = session.createQuery("select count(d) " + queryString,Long.class);
		countForSearchQuery.setParameter("searchingValue", searchingValue);

		return new PageImpl<>(theQuery.getResultList(), pageable, countForSearchQuery.uniqueResult());
		
	}
	

	///////////////////////////

	@Override
	public Page<Donation> findAll(Pageable pageable) {
		String theQueryString ="from Donation d";
		return findByQuery(theQueryString, pageable);
	}
	
	

}
