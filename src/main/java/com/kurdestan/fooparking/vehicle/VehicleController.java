package com.kurdestan.fooparking.vehicle;

import com.kurdestan.fooparking.common.PagingData;
import com.kurdestan.fooparking.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicle/")
@AllArgsConstructor
public class VehicleController {

    private final IVehicleService service;
    private VehicleMapper mapper;


    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody VehicleDTO vehicleDTO){
        Vehicle vehicle = mapper.toVehicle(vehicleDTO);
        service.save(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity update(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = mapper.toVehicle(vehicleDTO);
        service.update(vehicle);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long id) {
        Vehicle vehicle = service.getById(id);
        VehicleDTO vehicleDTO = mapper.toVehicleDTO(vehicle);
        return ResponseEntity.ok(vehicleDTO);
    }

    @GetMapping("/v1")
    public ResponseEntity<List<VehicleDTO>> getAll() {
        List<Vehicle> vehicles = service.getAll();
        List<VehicleDTO> vehicleDTOs = mapper.toVehicleDTOList(vehicles);
        return ResponseEntity.ok(vehicleDTOs);
    }

    @GetMapping("/v1/filter-type/{type}")
    public ResponseEntity<List<VehicleDTO>> filterByType(@PathVariable VehicleType type) {
        List<Vehicle> vehicles = service.filterByType(type);
        List<VehicleDTO> vehicleDTOs = mapper.toVehicleDTOList(vehicles);
        return ResponseEntity.ok(vehicleDTOs);
    }


    @DeleteMapping("/v1/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<VehicleDTO>> filterByType(@PathVariable Integer page, Integer size) {

        Page<Vehicle> vehiclePage = service.paging(page, size);

        int totalPage = vehiclePage.getTotalPages();
        List<Vehicle> data = vehiclePage.getContent();
        List<VehicleDTO> vehicleDTOS = mapper.toVehicleDTOList(data);

        PagingData<VehicleDTO> pagingData = new PagingData<>(totalPage, page, vehicleDTOS);
        return ResponseEntity.ok(pagingData);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<VehicleDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Vehicle> vehicles = service.search(searchCriteria);
        List<VehicleDTO> vehicleDTOS = mapper.toVehicleDTOList(vehicles);
        return ResponseEntity.ok(vehicleDTOS);
    }

}
