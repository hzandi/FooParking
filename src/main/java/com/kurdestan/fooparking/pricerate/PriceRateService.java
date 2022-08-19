package com.kurdestan.fooparking.pricerate;

import com.kurdestan.fooparking.common.SearchCriteria;
import com.kurdestan.fooparking.common.SearchSpecification;
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
public class PriceRateService implements IPriceRateService {

    private final PriceRateRepository repository;

    @Override
    public PriceRate save(PriceRate priceRate) {
        return repository.save(priceRate);
    }

    @Override
    public PriceRate update(PriceRate priceRate) {
        PriceRate lastSavedPriceRate = getById(priceRate.getId());

        lastSavedPriceRate.setType(priceRate.getType());
        lastSavedPriceRate.setHourlyRate(priceRate.getHourlyRate());
        lastSavedPriceRate.setDailyRate(priceRate.getDailyRate());

        return repository.save(lastSavedPriceRate);
    }

    @Override
    public void delete(PriceRate priceRate) {
        repository.deleteById(priceRate.getId());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PriceRate getById(Long id) {
        Optional<PriceRate> optionalPriceRate = repository.findById(id);
        if (!optionalPriceRate.isPresent()) {
            throw new NotFoundException("Not Found");
        }
        return optionalPriceRate.get();
    }

    @Override
    public List<PriceRate> getAll() {
        return (List<PriceRate>) repository.findAll();
    }

    @Override
    public List<PriceRate> filterByType(ParkingType type) {
        return repository.findAllByType(type);
    }

    @Override
    public Page<PriceRate> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<PriceRate> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<PriceRate> priceRateSpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> priceRateSpecification.add(criteria));
        return repository.findAll(priceRateSpecification);
    }
}
