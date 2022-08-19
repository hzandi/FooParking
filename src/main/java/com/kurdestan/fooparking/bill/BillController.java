package com.kurdestan.fooparking.bill;

import com.kurdestan.fooparking.common.PagingData;
import com.kurdestan.fooparking.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bill/")
@AllArgsConstructor
public class BillController {

    private final IBillService service;
    private BillMapper mapper;


    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody BillDTO billDTO) {
        Bill bill = mapper.toBill(billDTO);
        service.save(bill);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity update(@RequestBody BillDTO billDTO) {
        Bill bill = mapper.toBill(billDTO);
        service.update(bill);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<BillDTO> getBill(@PathVariable Long id) {
        Bill bill = service.getById(id);
        BillDTO billDTO = mapper.toBillDTO(bill);
        return ResponseEntity.ok(billDTO);
    }

    @GetMapping("/v1")
    public ResponseEntity<List<BillDTO>> getAll() {
        List<Bill> billList = service.getAll();
        List<BillDTO> billDTOS = mapper.toBillDTOS(billList);
        return ResponseEntity.ok(billDTOS);
    }

    @GetMapping("/v1/filter-plate/{plate}")
    public ResponseEntity<BillDTO> filterByPlate(@PathVariable String plate) {
        Bill bill = service.getByPlate(plate);
        BillDTO billDTO = mapper.toBillDTO(bill);
        return ResponseEntity.ok(billDTO);
    }


    @DeleteMapping("/v1/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<BillDTO>> findAll(@PathVariable Integer page, @PathVariable Integer size) {

        Page<Bill> billPage = service.paging(page, size);

        int totalPage = billPage.getTotalPages();
        List<Bill> data = billPage.getContent();
        List<BillDTO> billDTOS = mapper.toBillDTOS(data);

        PagingData<BillDTO> pagingData = new PagingData<>(totalPage, page, billDTOS);
        return ResponseEntity.ok(pagingData);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<BillDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Bill> billList = service.search(searchCriteria);
        List<BillDTO> billDTOS = mapper.toBillDTOS(billList);
        return ResponseEntity.ok(billDTOS);
    }
}
