package com.kurdestan.fooparking.parking;

import com.kurdestan.fooparking.bill.Bill;
import com.kurdestan.fooparking.common.BaseEntity;
import com.kurdestan.fooparking.pricerate.PriceRate;
import com.kurdestan.fooparking.vehicle.Vehicle;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

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

    @OneToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @NotNull
    @Audited(targetAuditMode = NOT_AUDITED)
    @ManyToOne
    @JoinColumn(name = "price_rate_id")
    private PriceRate priceRate;

}
