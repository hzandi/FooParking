package com.kurdestan.fooparking.bill;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillRepository extends
        PagingAndSortingRepository<Bill, Long>,
        JpaSpecificationExecutor<Bill> {

    List<Bill> findAllById(Long id);

    Page<Bill> findAll(Pageable pageable);

    Page<Bill> findAll(Specification<Bill> specification, Pageable pageable);

    List<Bill> findAll(Specification<Bill> specification);
}
