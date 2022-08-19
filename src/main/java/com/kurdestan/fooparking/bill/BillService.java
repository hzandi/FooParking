package com.kurdestan.fooparking.bill;

import com.kurdestan.fooparking.common.SearchCriteria;
import com.kurdestan.fooparking.common.SearchSpecification;
import com.kurdestan.fooparking.common.exception.NotFoundException;
import com.kurdestan.fooparking.parking.Parking;
import com.kurdestan.fooparking.parking.ParkingRepository;
import com.kurdestan.fooparking.vehicle.Vehicle;
import com.kurdestan.fooparking.vehicle.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillService implements IBillService {

    private final BillRepository repository;
    private final VehicleRepository vehicleRepository;
    private final ParkingRepository parkingRepository;

    @Override
    public Bill save(Bill bill) {
        return repository.save(bill);
    }

    @Override
    public Bill update(Bill bill) {
        Bill lastSavedBill = getById(bill.getId());
        lastSavedBill.setFee(bill.getFee());
        lastSavedBill.setIsPayed(bill.getIsPayed());
        lastSavedBill.setParking(bill.getParking());

        return repository.save(lastSavedBill);
    }

    @Override
    public void delete(Bill bill) {
        delete(bill.getId());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Bill getById(Long id) {
        Optional<Bill> optionalBill = repository.findById(id);
        if(!optionalBill.isPresent()){
            throw new NotFoundException("Not Found");
        }
        return optionalBill.get();
    }

    @Override
    public Bill getByPlate(String plate) {
        Optional<Vehicle> optionalVehicle = Optional.ofNullable(vehicleRepository.findByPlate(plate));
        if(!optionalVehicle.isPresent()){
            throw new NotFoundException("Not Found");
        }
        Optional<Parking> optionalParking = Optional.ofNullable(parkingRepository.findByVehicle(optionalVehicle.get()).get(0));
        if(!optionalParking.isPresent() || optionalParking.get().getBill() == null){
            throw new NotFoundException("Not Found");
        }
        Optional<Bill> optionalBill = repository.findById(optionalParking.get().getBill().getId());
        if(!optionalBill.isPresent()){
            throw new NotFoundException("Not Found");
        }
        return optionalBill.get();
    }

    @Override
    public List<Bill> getAll() {
        return (List<Bill>) repository.findAll();
    }


    @Override
    public Page<Bill> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<Bill> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<Bill> billSpecification = new SearchSpecification<>();
        searchCriteria.forEach(billSpecification::add);
        return repository.findAll(billSpecification);
    }
}
