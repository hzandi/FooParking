package com.kurdestan.fooparking.pricerate;

import com.kurdestan.fooparking.common.PagingData;
import com.kurdestan.fooparking.common.SearchCriteria;
import com.kurdestan.fooparking.vehicle.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/pricerate/")
@AllArgsConstructor
public class PriceRateController {

    private final IPriceRateService service;
    private PriceRateMapper mapper;


    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody PriceRateDTO priceRateDTO){
        PriceRate priceRate = mapper.toPriceRate(priceRateDTO);
        service.save(priceRate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity update(@RequestBody PriceRateDTO priceRateDTO) {
        PriceRate priceRate = mapper.toPriceRate(priceRateDTO);
        service.update(priceRate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<PriceRateDTO> getVehicle(@PathVariable Long id) {
        PriceRate priceRate = service.getById(id);
        PriceRateDTO priceRateDTO = mapper.toPriceRateDTO(priceRate);
        return ResponseEntity.ok(priceRateDTO);
    }

    @GetMapping("/v1")
    public ResponseEntity<List<PriceRateDTO>> getAll() {
        List<PriceRate> priceRates = service.getAll();
        List<PriceRateDTO> priceRateDTOS = mapper.toPriceRateDTOList(priceRates);
        return ResponseEntity.ok(priceRateDTOS);
    }

    @GetMapping("/v1/filter-parking-type/{type}")
    public ResponseEntity<List<PriceRateDTO>> filterByType(@PathVariable ParkingType type) {
        List<PriceRate> priceRates = service.filterByType(type);
        List<PriceRateDTO> priceRateDTOS = mapper.toPriceRateDTOList(priceRates);
        return ResponseEntity.ok(priceRateDTOS);
    }


    @DeleteMapping("/v1/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<PriceRateDTO>> filterByType(@PathVariable Integer page, Integer size) {

        Page<PriceRate> priceRatePage = service.paging(page, size);

        int totalPage = priceRatePage.getTotalPages();
        List<PriceRate> data = priceRatePage.getContent();
        List<PriceRateDTO> priceRateDTOS = mapper.toPriceRateDTOList(data);

        PagingData<PriceRateDTO> pagingData = new PagingData<>(totalPage, page, priceRateDTOS);
        return ResponseEntity.ok(pagingData);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<PriceRateDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<PriceRate> priceRates = service.search(searchCriteria);
        List<PriceRateDTO> priceRateDTOS = mapper.toPriceRateDTOList(priceRates);
        return ResponseEntity.ok(priceRateDTOS);
    }

}
