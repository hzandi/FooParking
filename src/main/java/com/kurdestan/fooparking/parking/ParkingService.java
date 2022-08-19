package com.kurdestan.fooparking.parking;

import com.kurdestan.fooparking.common.SearchCriteria;
import com.kurdestan.fooparking.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParkingService implements IParkingService {

    private final ParkingRepository repository;

    @Override
    public Parking save(Parking parking) {
        return repository.save(parking);
    }

    @Override
    public Parking update(Parking parking) {
        Parking lastSavedParking = getById(parking.getId());

        lastSavedParking.setEntranceDatetime(parking.getEntranceDatetime());
        lastSavedParking.setExitDatetime(parking.getExitDatetime());
        lastSavedParking.setFee(parking.getFee());
        lastSavedParking.setIsPayed(parking.getIsPayed());
        lastSavedParking.setVehicle(parking.getVehicle());
        lastSavedParking.setPriceRate(parking.getPriceRate());

        return repository.save(lastSavedParking);
    }

    @Override
    public void delete(Parking parking) {
        repository.deleteById(parking.getId());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Parking getById(Long id) {
        Optional<Parking> optionalParking = repository.findById(id);
        if(!optionalParking.isPresent()){
            throw new NotFoundException("Not Found");
        }
        return optionalParking.get();
    }

    @Override
    public List<Parking> getAll() {
        return (List<Parking>) repository.findAll();
    }

    @Override
    public List<Parking> getByPlate(String plate) {
        return repository.findByPlate(plate);
    }

    @Override
    public Page<Parking> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<Parking> search(List<SearchCriteria> searchCriteria) {
        ParkingSpecification parkingSpecification = new ParkingSpecification();
        searchCriteria.forEach(parkingSpecification::add);
        return repository.findAll(parkingSpecification);
    }
}
