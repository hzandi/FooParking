package com.kurdestan.fooparking.vehicle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-19T23:43:16+0430",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public Vehicle toVehicle(VehicleDTO vehicleDTO) {
        if ( vehicleDTO == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( vehicleDTO.getId() );
        vehicle.setVersion( vehicleDTO.getVersion() );
        vehicle.setCreatedDate( vehicleDTO.getCreatedDate() );
        vehicle.setCreatedBy( vehicleDTO.getCreatedBy() );
        vehicle.setLastModifiedDate( vehicleDTO.getLastModifiedDate() );
        vehicle.setLastModifiedBy( vehicleDTO.getLastModifiedBy() );
        vehicle.setModel( vehicleDTO.getModel() );
        vehicle.setType( vehicleDTO.getType() );
        vehicle.setPlate( vehicleDTO.getPlate() );

        return vehicle;
    }

    @Override
    public VehicleDTO toVehicleDTO(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleDTO vehicleDTO = new VehicleDTO();

        vehicleDTO.setId( vehicle.getId() );
        vehicleDTO.setVersion( vehicle.getVersion() );
        vehicleDTO.setCreatedDate( vehicle.getCreatedDate() );
        vehicleDTO.setCreatedBy( vehicle.getCreatedBy() );
        vehicleDTO.setLastModifiedDate( vehicle.getLastModifiedDate() );
        vehicleDTO.setLastModifiedBy( vehicle.getLastModifiedBy() );
        vehicleDTO.setModel( vehicle.getModel() );
        vehicleDTO.setType( vehicle.getType() );
        vehicleDTO.setPlate( vehicle.getPlate() );

        return vehicleDTO;
    }

    @Override
    public List<Vehicle> toVehicleList(List<VehicleDTO> vehicleDTOs) {
        if ( vehicleDTOs == null ) {
            return null;
        }

        List<Vehicle> list = new ArrayList<Vehicle>( vehicleDTOs.size() );
        for ( VehicleDTO vehicleDTO : vehicleDTOs ) {
            list.add( toVehicle( vehicleDTO ) );
        }

        return list;
    }

    @Override
    public List<VehicleDTO> toVehicleDTOList(List<Vehicle> vehicles) {
        if ( vehicles == null ) {
            return null;
        }

        List<VehicleDTO> list = new ArrayList<VehicleDTO>( vehicles.size() );
        for ( Vehicle vehicle : vehicles ) {
            list.add( toVehicleDTO( vehicle ) );
        }

        return list;
    }
}
