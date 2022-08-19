package com.kurdestan.fooparking.vehicle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleRepository extends
        PagingAndSortingRepository<Vehicle, Long>,
        JpaSpecificationExecutor<Vehicle> {

    Vehicle findByPlate(String plate);

    List<Vehicle> findAllByType(VehicleType vehicleType);

    Page<Vehicle> findAll(Pageable pageable);

    Page<Vehicle> findAll(Specification<Vehicle> specification, Pageable pageable);

    List<Vehicle> findAll(Specification<Vehicle> specification);
}
