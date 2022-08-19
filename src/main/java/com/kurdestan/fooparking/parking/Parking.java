package com.kurdestan.fooparking.parking;

import com.kurdestan.fooparking.common.BaseEntity;
import com.kurdestan.fooparking.pricerate.PriceRate;
import com.kurdestan.fooparking.vehicle.Vehicle;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tbl_parking")
@Data
public class Parking extends BaseEntity {

    @NotNull
    @Column(name = "entrance_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date entranceDatetime;

    @Column(name = "exit_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date exitDatetime;

    @Column(name = "fee")
    private Double fee;

    @Column(name = "is_payed")
    private Boolean isPayed;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "price_rate_id")
    private PriceRate priceRate;

}