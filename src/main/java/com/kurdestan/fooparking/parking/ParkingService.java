package com.kurdestan.fooparking.parking;

import com.kurdestan.fooparking.bill.Bill;
import com.kurdestan.fooparking.common.SearchCriteria;
import com.kurdestan.fooparking.common.SearchSpecification;
import com.kurdestan.fooparking.common.exception.NotFoundException;
import com.kurdestan.fooparking.vehicle.Vehicle;
import com.kurdestan.fooparking.vehicle.VehicleRepository;
import lombok.AllArgsConstructor;
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
    public Parking save(Parking parking) {
        return repository.save(parking);
    }

    @Override
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
        if (!optionalParking.isPresent()) {
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
        Vehicle vehicle = vehicleRepository.findByPlate(plate);
        return repository.findByVehicle(vehicle);
    }

    @Override
    public Page<Parking> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<Parking> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<Parking> parkingSpecification = new SearchSpecification<>();
        searchCriteria.forEach(parkingSpecification::add);
        return repository.findAll(parkingSpecification);
    }
}
