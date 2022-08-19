package com.kurdestan.fooparking.parking;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkingMapper {

    @Mappings({
            @Mapping(source = "entranceDateTime",target ="entranceDateTime", ignore = false, qualifiedByName = "parkingTimestampToDate"),
            @Mapping(source = "exitDateTime",target ="exitDateTime", ignore = false, qualifiedByName = "parkingTimestampToDate")
    })
    Parking toParking(ParkingDTO parkingDTO);


    @Mappings({
            @Mapping(source = "entranceDateTime",target ="entranceDateTime", ignore = false, qualifiedByName = "parkingDateToTimestamp"),
            @Mapping(source = "exitDateTime",target ="exitDateTime", ignore = false, qualifiedByName = "parkingDateToTimestamp"),
    })
    ParkingDTO toParkingDTO(Parking parking);

    List<Parking> toParkingList(List<ParkingDTO> parkingDTOS);
    List<ParkingDTO> toParkingDTOList(List<Parking> parkingList);


    @Named("parkingTimestampToDate")
    default Date toDate(Long timestamp){
        if (timestamp!=null)
            return new Date(timestamp);

        return null ;
    }

    @Named("parkingDateToTimestamp")
    default Long toTimestamp(Date date){
        if (date!=null)
            return date.getTime();

        return null ;
    }
}
