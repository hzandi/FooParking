package com.kurdestan.fooparking.parking;

import com.kurdestan.fooparking.common.SearchCriteria;
import com.kurdestan.fooparking.pricerate.PriceRate;
import com.kurdestan.fooparking.vehicle.Vehicle;
import com.kurdestan.fooparking.vehicle.VehicleType;
import org.springframework.data.domain.Page;

import javax.print.attribute.standard.PageRanges;
import java.util.List;


public interface IParkingService {

    Parking save(Parking parking);
    Parking update(Parking parking);
    void delete(Parking parking);
    void delete(Long id);
    Parking getById(Long id);
    List<Parking> getByPlate(String plate);
    List<Parking> getAll();
    Page<Parking> paging(Integer page, Integer size);
    List<Parking> search(List<SearchCriteria> searchCriteria);

}