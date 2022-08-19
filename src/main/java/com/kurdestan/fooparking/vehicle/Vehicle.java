package com.kurdestan.fooparking.vehicle;

import com.kurdestan.fooparking.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_vehicle")
@Data
public class Vehicle extends BaseEntity {

    @NotNull
    @Column(name = "model")
    private String model;

    @NotNull
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private VehicleType type;

    @NotNull
    @Column(name = "plate")
    private String plate;
}
