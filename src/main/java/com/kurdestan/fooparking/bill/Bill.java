package com.kurdestan.fooparking.bill;

import com.kurdestan.fooparking.common.BaseEntity;
import com.kurdestan.fooparking.parking.Parking;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "tbl_bill")
@Data
public class Bill extends BaseEntity {

    @Column(name = "fee")
    private Double fee;

    @Column(name = "is_payed")
    private Boolean isPayed;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bill")
    private Parking parking;
}
