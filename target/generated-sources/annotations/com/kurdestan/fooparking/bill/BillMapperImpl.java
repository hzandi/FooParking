package com.kurdestan.fooparking.bill;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-20T01:00:38+0430",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class BillMapperImpl implements BillMapper {

    @Override
    public Bill toBill(BillDTO billDTO) {
        if ( billDTO == null ) {
            return null;
        }

        Bill bill = new Bill();

        bill.setId( billDTO.getId() );
        bill.setVersion( billDTO.getVersion() );
        bill.setCreatedDate( billDTO.getCreatedDate() );
        bill.setCreatedBy( billDTO.getCreatedBy() );
        bill.setLastModifiedDate( billDTO.getLastModifiedDate() );
        bill.setLastModifiedBy( billDTO.getLastModifiedBy() );
        bill.setFee( billDTO.getFee() );
        bill.setIsPayed( billDTO.getIsPayed() );

        return bill;
    }

    @Override
    public BillDTO toBillDTO(Bill bill) {
        if ( bill == null ) {
            return null;
        }

        BillDTO billDTO = new BillDTO();

        billDTO.setId( bill.getId() );
        billDTO.setVersion( bill.getVersion() );
        billDTO.setCreatedDate( bill.getCreatedDate() );
        billDTO.setCreatedBy( bill.getCreatedBy() );
        billDTO.setLastModifiedDate( bill.getLastModifiedDate() );
        billDTO.setLastModifiedBy( bill.getLastModifiedBy() );
        billDTO.setFee( bill.getFee() );
        billDTO.setIsPayed( bill.getIsPayed() );

        return billDTO;
    }

    @Override
    public List<Bill> toBillList(List<BillDTO> billDTOS) {
        if ( billDTOS == null ) {
            return null;
        }

        List<Bill> list = new ArrayList<Bill>( billDTOS.size() );
        for ( BillDTO billDTO : billDTOS ) {
            list.add( toBill( billDTO ) );
        }

        return list;
    }

    @Override
    public List<BillDTO> toBillDTOS(List<Bill> billList) {
        if ( billList == null ) {
            return null;
        }

        List<BillDTO> list = new ArrayList<BillDTO>( billList.size() );
        for ( Bill bill : billList ) {
            list.add( toBillDTO( bill ) );
        }

        return list;
    }
}
