package com.kurdestan.fooparking.pricerate;

import com.kurdestan.fooparking.common.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_price_rate")
@Data
@Audited
public class PriceRate extends BaseEntity {

    @NotNull
    @Column(name = "ParkingType", unique = true)
    @Enumerated(value = EnumType.STRING)
    private ParkingType type;

    @NotNull
    @Column(name = "hourly_rate")
    private Double hourlyRate;

    @NotNull
    @Column(name = "daily_rate")
    private Double dailyRate;
}
