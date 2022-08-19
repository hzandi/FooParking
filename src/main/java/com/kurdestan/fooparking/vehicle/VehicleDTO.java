package com.kurdestan.fooparking.vehicle;

import com.kurdestan.fooparking.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VehicleDTO extends BaseDTO {

    @ApiModelProperty(required = true, hidden = false)
    private String model;

    @ApiModelProperty(required = true, hidden = false)
    private VehicleType type;

    @ApiModelProperty(required = true, hidden = false)
    private String plate;
}
