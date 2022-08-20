package com.kurdestan.fooparking.parking;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-20T17:38:17+0430",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class ParkingMapperImpl implements ParkingMapper {

    @Override
    public Parking toParking(ParkingDTO parkingDTO) {
        if ( parkingDTO == null ) {
            return null;
        }

        Parking parking = new Parking();

        parking.setEntranceDatetime( toDate( parkingDTO.getEntranceDatetime() ) );
        parking.setExitDatetime( toDate( parkingDTO.getExitDatetime() ) );
        parking.setId( parkingDTO.getId() );
        parking.setVersion( parkingDTO.getVersion() );
        parking.setCreatedDate( parkingDTO.getCreatedDate() );
        parking.setCreatedBy( parkingDTO.getCreatedBy() );
        parking.setLastModifiedDate( parkingDTO.getLastModifiedDate() );
        parking.setLastModifiedBy( parkingDTO.getLastModifiedBy() );

        return parking;
    }

    @Override
    public ParkingDTO toParkingDTO(Parking parking) {
        if ( parking == null ) {
            return null;
        }

        ParkingDTO parkingDTO = new ParkingDTO();

        parkingDTO.setEntranceDatetime( toTimestamp( parking.getEntranceDatetime() ) );
        parkingDTO.setExitDatetime( toTimestamp( parking.getExitDatetime() ) );
        parkingDTO.setId( parking.getId() );
        parkingDTO.setVersion( parking.getVersion() );
        parkingDTO.setCreatedDate( parking.getCreatedDate() );
        parkingDTO.setCreatedBy( parking.getCreatedBy() );
        parkingDTO.setLastModifiedDate( parking.getLastModifiedDate() );
        parkingDTO.setLastModifiedBy( parking.getLastModifiedBy() );

        return parkingDTO;
    }

    @Override
    public List<Parking> toParkingList(List<ParkingDTO> parkingDTOS) {
        if ( parkingDTOS == null ) {
            return null;
        }

        List<Parking> list = new ArrayList<Parking>( parkingDTOS.size() );
        for ( ParkingDTO parkingDTO : parkingDTOS ) {
            list.add( toParking( parkingDTO ) );
        }

        return list;
    }

    @Override
    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        if ( parkingList == null ) {
            return null;
        }

        List<ParkingDTO> list = new ArrayList<ParkingDTO>( parkingList.size() );
        for ( Parking parking : parkingList ) {
            list.add( toParkingDTO( parking ) );
        }

        return list;
    }
}
