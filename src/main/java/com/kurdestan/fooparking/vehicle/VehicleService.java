package com.kurdestan.fooparking.vehicle;

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
public class VehicleService implements IVehicleService {

    private final VehicleRepository repository;

    @Override
    public Vehicle save(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        Vehicle lastSavedVehicle = getById(vehicle.getId());

        lastSavedVehicle.setModel(vehicle.getModel());
        lastSavedVehicle.setType(vehicle.getType());
        lastSavedVehicle.setPlate(vehicle.getPlate());

        return repository.save(lastSavedVehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        repository.deleteById(vehicle.getId());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Vehicle getById(Long id) {
        Optional<Vehicle> optionalVehicle = repository.findById(id);
        if(!optionalVehicle.isPresent()){
            throw new NotFoundException("Not Found");
        }
        return optionalVehicle.get();
    }

    @Override
    public List<Vehicle> getAll() {
        return (List<Vehicle>) repository.findAll();
    }

    @Override
    public List<Vehicle> filterByType(VehicleType type) {
        return repository.findAllByType(type);
    }

    @Override
    public Page<Vehicle> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<Vehicle> search(List<SearchCriteria> searchCriteria) {
        VehicleSpecification vehicleSpecification = new VehicleSpecification();
        searchCriteria.forEach(criteria -> vehicleSpecification.add(criteria));
        return repository.findAll(vehicleSpecification);
    }
}
