package com.kurdestan.fooparking.pricerate;

import com.kurdestan.fooparking.common.SearchCriteria;
import org.springframework.data.domain.Page;
import java.util.List;


public interface IPriceRateService {

    PriceRate save(PriceRate priceRate);
    PriceRate update(PriceRate priceRate);
    void delete(PriceRate priceRate);
    void delete(Long id);
    PriceRate getById(Long id);
    List<PriceRate> getAll();
    List<PriceRate> filterByType(ParkingType type);
    Page<PriceRate> paging(Integer page, Integer size);
    List<PriceRate> search(List<SearchCriteria> searchCriteria);

}