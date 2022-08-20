package com.kurdestan.fooparking.parking;

import com.kurdestan.fooparking.bill.Bill;
import com.kurdestan.fooparking.common.SearchCriteria;
import com.kurdestan.fooparking.common.SearchSpecification;
import com.kurdestan.fooparking.common.exception.NotFoundException;
import com.kurdestan.fooparking.vehicle.Vehicle;
import com.kurdestan.fooparking.vehicle.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ParkingService implements IParkingService {

    private final ParkingRepository repository;
    private final VehicleRepository vehicleRepository;

    @Override
    public Parking exitRequest(String plate, Long exitTimestamp) {
        // find parking with plate
        Vehicle vehicle = vehicleRepository.findByPlate(plate);
        List<Parking> parkingList = repository.findByVehicle(vehicle);
        if (parkingList.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
        Parking parking = parkingList.get(0);

        // update parking
        Date exitDateTime = new Date(exitTimestamp);
        Date entranceDateTime = parking.getEntranceDatetime();

        long diffInMillis = Math.abs(exitDateTime.getTime() - entranceDateTime.getTime());
        long hourDiff = TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        Bill bill = new Bill();
        bill.setParking(parking);
        if (hourDiff > 24) {
            Double dailyFee = parking.getPriceRate().getDailyRate() * (hourDiff / 24);
            Double hourlyFee = parking.getPriceRate().getHourlyRate() * (hourDiff % 24);
            bill.setFee(dailyFee + hourlyFee);
        } else {
            bill.setFee(parking.getPriceRate().getHourlyRate() * hourDiff);
        }
        parking.setExitDatetime(exitDateTime);
        parking.setBill(bill);
        return update(parking);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "parkingOne", allEntries = true),
            @CacheEvict(value = "parkingTwo", allEntries = true)
    })
    public Parking save(Parking parking) {
        return repository.save(parking);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "parkingOne", allEntries = true),
            @CacheEvict(value = "parkingTwo", allEntries = true)
    })
    public Parking update(Parking parking) {
        Parking lastSavedParking = getById(parking.getId());

        lastSavedParking.setEntranceDatetime(parking.getEntranceDatetime());
        lastSavedParking.setExitDatetime(parking.getExitDatetime());
        lastSavedParking.setBill(parking.getBill());
        lastSavedParking.setVehicle(parking.getVehicle());
        lastSavedParking.setPriceRate(parking.getPriceRate());

        return repository.save(lastSavedParking);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "parkingOne", allEntries = true),
            @CacheEvict(value = "parkingTwo", allEntries = true)
    })
    public void delete(Parking parking) {
        repository.deleteById(parking.getId());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "parkingOne", allEntries = true),
            @CacheEvict(value = "parkingTwo", allEntries = true)
    })
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "parkingTwo",key = "#id")
    public Parking getById(Long id) {
        Optional<Parking> optionalParking = repository.findById(id);
        if (!optionalParking.isPresent()) {
            throw new NotFoundException("Not Found");
        }
        return optionalParking.get();
    }

    @Override
    @Cacheable(value = "parkingOne")
    public List<Parking> getAll() {
        return (List<Parking>) repository.findAll();
    }

    @Override
    @Cacheable(value = "parkingTwo",key = "#plate")
    public List<Parking> getByPlate(String plate) {
        Vehicle vehicle = vehicleRepository.findByPlate(plate);
        return repository.findByVehicle(vehicle);
    }

    @Override
    @Cacheable(value = "parkingOne")
    public Page<Parking> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    @Cacheable(value = "parkingOne")
    public List<Parking> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<Parking> parkingSpecification = new SearchSpecification<>();
        searchCriteria.forEach(parkingSpecification::add);
        return repository.findAll(parkingSpecification);
    }
}
