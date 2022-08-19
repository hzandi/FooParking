package com.kurdestan.fooparking.bill;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {

    Bill toBill(BillDTO billDTO);

    BillDTO toBillDTO(Bill bill);

    List<Bill> toBillList(List<BillDTO> billDTOS);

    List<BillDTO> toBillDTOS(List<Bill> billList);
}
