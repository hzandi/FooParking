package com.kurdestan.fooparking.bill;

import com.kurdestan.fooparking.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IBillService {

    Bill save(Bill bill);
    Bill update(Bill bill);
    void delete(Bill bill);
    void delete(Long id);
    Bill getById(Long id);
    Bill getByPlate(String plate);
    List<Bill> getAll();
    Page<Bill> paging(Integer page, Integer size);
    List<Bill> search(List<SearchCriteria> searchCriteria);

}