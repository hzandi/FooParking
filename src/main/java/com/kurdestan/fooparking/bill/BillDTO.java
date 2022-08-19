package com.kurdestan.fooparking.bill;

import com.kurdestan.fooparking.common.BaseDTO;
import com.kurdestan.fooparking.parking.ParkingDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BillDTO extends BaseDTO {

    @ApiModelProperty(required = true, hidden = false)
    private Double fee;

    @ApiModelProperty(required = true, hidden = false)
    private Boolean isPayed;

    @ApiModelProperty(required = true, hidden = false)
    private ParkingDTO parkingDTO;
}
