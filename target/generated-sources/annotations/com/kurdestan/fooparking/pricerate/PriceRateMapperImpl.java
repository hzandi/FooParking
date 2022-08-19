package com.kurdestan.fooparking.pricerate;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-20T02:16:30+0430",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class PriceRateMapperImpl implements PriceRateMapper {

    @Override
    public PriceRate toPriceRate(PriceRateDTO priceRateDTO) {
        if ( priceRateDTO == null ) {
            return null;
        }

        PriceRate priceRate = new PriceRate();

        priceRate.setId( priceRateDTO.getId() );
        priceRate.setVersion( priceRateDTO.getVersion() );
        priceRate.setCreatedDate( priceRateDTO.getCreatedDate() );
        priceRate.setCreatedBy( priceRateDTO.getCreatedBy() );
        priceRate.setLastModifiedDate( priceRateDTO.getLastModifiedDate() );
        priceRate.setLastModifiedBy( priceRateDTO.getLastModifiedBy() );
        priceRate.setType( priceRateDTO.getType() );
        priceRate.setHourlyRate( priceRateDTO.getHourlyRate() );
        priceRate.setDailyRate( priceRateDTO.getDailyRate() );

        return priceRate;
    }

    @Override
    public PriceRateDTO toPriceRateDTO(PriceRate priceRate) {
        if ( priceRate == null ) {
            return null;
        }

        PriceRateDTO priceRateDTO = new PriceRateDTO();

        priceRateDTO.setId( priceRate.getId() );
        priceRateDTO.setVersion( priceRate.getVersion() );
        priceRateDTO.setCreatedDate( priceRate.getCreatedDate() );
        priceRateDTO.setCreatedBy( priceRate.getCreatedBy() );
        priceRateDTO.setLastModifiedDate( priceRate.getLastModifiedDate() );
        priceRateDTO.setLastModifiedBy( priceRate.getLastModifiedBy() );
        priceRateDTO.setType( priceRate.getType() );
        priceRateDTO.setHourlyRate( priceRate.getHourlyRate() );
        priceRateDTO.setDailyRate( priceRate.getDailyRate() );

        return priceRateDTO;
    }

    @Override
    public List<PriceRate> toPriceRateList(List<PriceRateDTO> priceRateDTOS) {
        if ( priceRateDTOS == null ) {
            return null;
        }

        List<PriceRate> list = new ArrayList<PriceRate>( priceRateDTOS.size() );
        for ( PriceRateDTO priceRateDTO : priceRateDTOS ) {
            list.add( toPriceRate( priceRateDTO ) );
        }

        return list;
    }

    @Override
    public List<PriceRateDTO> toPriceRateDTOList(List<PriceRate> priceRates) {
        if ( priceRates == null ) {
            return null;
        }

        List<PriceRateDTO> list = new ArrayList<PriceRateDTO>( priceRates.size() );
        for ( PriceRate priceRate : priceRates ) {
            list.add( toPriceRateDTO( priceRate ) );
        }

        return list;
    }
}
