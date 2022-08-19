package com.kurdestan.fooparking.vehicle;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toVehicle(VehicleDTO vehicleDTO);

    VehicleDTO toVehicleDTO(Vehicle vehicle);

    List<Vehicle> toVehicleList(List<VehicleDTO> vehicleDTOs);

    List<VehicleDTO> toVehicleDTOList(List<Vehicle> vehicles);

}
