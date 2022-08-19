package com.kurdestan.fooparking.pricerate;

import com.kurdestan.fooparking.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PriceRateDTO extends BaseDTO {

    @ApiModelProperty(required = true, hidden = false)
    private ParkingType type;

    @ApiModelProperty(required = true, hidden = false)
    private Double hourlyRate;

    @ApiModelProperty(required = true, hidden = false)
    private Double dailyRate;
}
