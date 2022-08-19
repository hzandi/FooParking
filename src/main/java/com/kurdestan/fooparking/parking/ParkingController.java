package com.kurdestan.fooparking.parking;

import com.kurdestan.fooparking.bill.BillDTO;
import com.kurdestan.fooparking.common.PagingData;
import com.kurdestan.fooparking.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parking/")
@AllArgsConstructor
public class ParkingController {

    private final IParkingService service;
    private ParkingMapper mapper;

    @PostMapping("/v1/exit/{plate}/{exitDatetime}")
    public ResponseEntity<ParkingDTO> exitRequest(@PathVariable String plate, @PathVariable Long exitDatetime) {
        Parking parking = service.exitRequest(plate, exitDatetime);
        ParkingDTO parkingDTO = mapper.toParkingDTO(parking);
        return ResponseEntity.ok(parkingDTO);
    }

    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody ParkingDTO parkingDTO){
        Parking parking = mapper.toParking(parkingDTO);
        service.save(parking);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity update(@RequestBody ParkingDTO parkingDTO) {
        Parking parking = mapper.toParking(parkingDTO);
        service.update(parking);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<ParkingDTO> getParking(@PathVariable Long id) {
        Parking parking = service.getById(id);
        ParkingDTO parkingDTO = mapper.toParkingDTO(parking);
        return ResponseEntity.ok(parkingDTO);
    }

    @GetMapping("/v1")
    public ResponseEntity<List<ParkingDTO>> getAll() {
        List<Parking> parkingList = service.getAll();
        List<ParkingDTO> parkingDTOS = mapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(parkingDTOS);
    }

    @GetMapping("/v1/filter-plate/{plate}")
    public ResponseEntity<List<ParkingDTO>> filterByPlate(@PathVariable String plate) {
        List<Parking> parkingList = service.getByPlate(plate);
        List<ParkingDTO> parkingDTOS = mapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(parkingDTOS);
    }


    @DeleteMapping("/v1/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<ParkingDTO>> findAll(@PathVariable Integer page, @PathVariable Integer size) {

        Page<Parking> parkingPage = service.paging(page, size);

        int totalPage = parkingPage.getTotalPages();
        List<Parking> data = parkingPage.getContent();
        List<ParkingDTO> parkingDTOS = mapper.toParkingDTOList(data);

        PagingData<ParkingDTO> pagingData = new PagingData<>(totalPage, page, parkingDTOS);
        return ResponseEntity.ok(pagingData);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<ParkingDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Parking> parkingList = service.search(searchCriteria);
        List<ParkingDTO> parkingDTOS = mapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(parkingDTOS);
    }

}
