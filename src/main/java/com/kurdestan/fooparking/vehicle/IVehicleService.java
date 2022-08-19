package com.kurdestan.fooparking.vehicle;

import com.kurdestan.fooparking.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IVehicleService {

    Vehicle save(Vehicle vehicle);
    Vehicle update(Vehicle vehicle);
    void delete(Vehicle vehicle);
    void delete(Long id);
    Vehicle getById(Long id);
    List<Vehicle> getAll();
    List<Vehicle> filterByType(VehicleType type);
    Page<Vehicle> paging(Integer page, Integer size);
    List<Vehicle> search(List<SearchCriteria> searchCriteria);

}