package com.kurdestan.fooparking.vehicle;

import com.kurdestan.fooparking.common.SearchCriteria;
import com.kurdestan.fooparking.common.SearchSpecification;
import com.kurdestan.fooparking.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Caching(evict = {
            @CacheEvict(value = "vehicleCache", allEntries = true),
    })
    public Vehicle save(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "vehicleCache", allEntries = true),
    })
    public Vehicle update(Vehicle vehicle) {
        Vehicle lastSavedVehicle = getById(vehicle.getId());

        lastSavedVehicle.setModel(vehicle.getModel());
        lastSavedVehicle.setType(vehicle.getType());
        lastSavedVehicle.setPlate(vehicle.getPlate());

        return repository.save(lastSavedVehicle);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "vehicleCache", allEntries = true),
    })
    public void delete(Vehicle vehicle) {
        repository.deleteById(vehicle.getId());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "vehicleCache", allEntries = true),
    })
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "billCache", key = "#id")
    public Vehicle getById(Long id) {
        Optional<Vehicle> optionalVehicle = repository.findById(id);
        if(!optionalVehicle.isPresent()){
            throw new NotFoundException("Not Found");
        }
        return optionalVehicle.get();
    }

    @Override
    @Cacheable(value = "billCache")
    public List<Vehicle> getAll() {
        return (List<Vehicle>) repository.findAll();
    }

    @Override
    @Cacheable(value = "billCache")
    public List<Vehicle> filterByType(VehicleType type) {
        return repository.findAllByType(type);
    }

    @Override
    @Cacheable(value = "billCache")
    public Page<Vehicle> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    @Cacheable(value = "billCache")
    public List<Vehicle> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<Vehicle> vehicleSpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> vehicleSpecification.add(criteria));
        return repository.findAll(vehicleSpecification);
    }
}
