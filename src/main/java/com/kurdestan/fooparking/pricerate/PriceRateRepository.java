package com.kurdestan.fooparking.pricerate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PriceRateRepository extends
        PagingAndSortingRepository<PriceRate, Long>,
        JpaSpecificationExecutor<PriceRate> {

    List<PriceRate> findAllByType(ParkingType parkingType);

    Page<PriceRate> findAll(Pageable pageable);

    Page<PriceRate> findAll(Specification<PriceRate> specification, Pageable pageable);

    List<PriceRate> findAll(Specification<PriceRate> specification);
}
