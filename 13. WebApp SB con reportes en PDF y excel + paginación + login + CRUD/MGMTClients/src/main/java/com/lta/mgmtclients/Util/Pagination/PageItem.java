package com.lta.mgmtclients.Util.Pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageItem {
    private int number;
    private boolean current;
}
