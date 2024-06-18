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
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.utils.FormatData;

@Repository
public class DonationDAOImpl implements DonationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
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
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchingValue, Pageable pageable) {
		
		DonationStatus donationStatus = FormatData.donationStatusFormat(searchingValue);
		
		
		String theQueryString =
				"from Donation d"
				+ " where d.showing = 1 "
				+ " and ( d.status = :donationStatus" 
				+ " or lower(d.phoneNumber) like lower(concat(:searchingValue, '%'))"
				+ " or lower(d.name) like lower(concat(:searchingValue, '%'))"
				+ " or lower(d.organization) like lower(concat(:searchingValue, '%'))" 
				+ " or lower(d.code) like lower(concat(:searchingValue, '%')))";

		Query<Donation> theQuery = getSession().createQuery(theQueryString, Donation.class);

		// Đặt giá trị tham số
		theQuery.setParameter("searchingValue", searchingValue);
		theQuery.setParameter("donationStatus", donationStatus);
		theQuery.setFirstResult((int) pageable.getOffset());
		theQuery.setMaxResults(pageable.getPageSize());

		Query<Long> countQuery = getSession().createQuery("select count(d) " + theQueryString, Long.class);

		countQuery.setParameter("searchingValue", searchingValue);
		countQuery.setParameter("donationStatus", donationStatus);

		return new PageImpl<>(theQuery.getResultList(), pageable, countQuery.uniqueResult());
	}
	
	
	
	@Override
	public Page<Donation> findAll(Pageable pageable) {
		String theQueryString = "from Donation d where d.showing = 1";
		// tạo 1 query truy vấn từ chuối query tham số để truy vấn các đối tượng
		// Donation
		Query<Donation> theQuery = getSession().createQuery(theQueryString, Donation.class);

		// xác định vị trí bắt đầu kết quả dựa trên offset của page
		theQuery.setFirstResult((int) pageable.getOffset());

		// xác định số phần tử của trang dựa trên size
		theQuery.setMaxResults(pageable.getPageSize());

		// đếm số kết quả trả về theo truy vấn để tính tổng số phần tử => phân trang
		Query<Long> countQuery = getSession().createQuery("select count(d) " + theQueryString, Long.class);

		// trả về đối tượng page chứa danh sách kết quả truy vấn
		return new PageImpl<>(theQuery.getResultList(), pageable, countQuery.uniqueResult());
	}
	
	@Override
	public void delete(int theId) {
		Query theQuery = getSession().createQuery("delete from Donation where id=:theId");

		theQuery.setParameter("theId", theId);

		theQuery.executeUpdate();

	}

}
