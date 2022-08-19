package com.kurdestan.fooparking.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PagingData<T> {

    private Integer totalPage;
    private Integer currentPage;
    private List<T> data;

}
