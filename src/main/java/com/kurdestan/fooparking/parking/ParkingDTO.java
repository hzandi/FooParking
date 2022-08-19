package com.kurdestan.fooparking.parking;

import com.kurdestan.fooparking.common.BaseDTO;
import com.kurdestan.fooparking.pricerate.PriceRateDTO;
import com.kurdestan.fooparking.vehicle.VehicleDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ParkingDTO extends BaseDTO {

    @ApiModelProperty(required = true, hidden = false)
    private Long entranceDateTime;

    @ApiModelProperty(required = true, hidden = false)
    private Long exitDateTime;

    @ApiModelProperty(required = true, hidden = false)
    private Double fee;

    @ApiModelProperty(required = true, hidden = false)
    private Boolean isPayed;

    @ApiModelProperty(required = true, hidden = false)
    private VehicleDTO vehicleDTO;

    @ApiModelProperty(required = true, hidden = false)
    private PriceRateDTO priceRateDTO;
}
