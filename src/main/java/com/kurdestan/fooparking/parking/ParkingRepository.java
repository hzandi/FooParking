package com.kurdestan.fooparking.parking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends
        PagingAndSortingRepository<Parking, Long>,
        JpaSpecificationExecutor<Parking> {

    List<Parking> findByPlate(String plate);

    Page<Parking> findAll(Pageable pageable);

    Page<Parking> findAll(Specification<Parking> specification, Pageable pageable);

    List<Parking> findAll(Specification<Parking> specification);
}
