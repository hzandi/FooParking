package com.kurdestan.fooparking.pricerate;

import org.mapstruct.Mapper;
import java.util.List;


@Mapper(componentModel = "spring")
public interface PriceRateMapper {

    PriceRate toPriceRate(PriceRateDTO priceRateDTO);

    PriceRateDTO toPriceRateDTO(PriceRate priceRate);

    List<PriceRate> toPriceRateList(List<PriceRateDTO> priceRateDTOS);

    List<PriceRateDTO> toPriceRateDTOList(List<PriceRate> priceRates);
}
