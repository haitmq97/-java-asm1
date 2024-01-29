package me.haitmq.spring.mvc.crud.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import me.haitmq.spring.mvc.crud.entity.Donation;

public interface PagingAndSortingDonation extends PagingAndSortingRepository<Donation, Long> {

    Iterable<Donation> findAll(Sort sort);

    @Override
    Page<Donation> findAll(Pageable pageable);
}
