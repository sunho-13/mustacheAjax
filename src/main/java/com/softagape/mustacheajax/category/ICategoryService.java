package com.softagape.mustacheajax.category;

import com.softagape.mustacheajax.ICommonService;
import com.softagape.mustacheajax.commons.dto.SearchAjaxDto;

import java.util.List;

public interface ICategoryService<T> extends ICommonService<T> {
    ICategory findByName(String name);
    List<ICategory> findAllByNameContains(SearchAjaxDto dto);
    int countAllByNameContains(SearchAjaxDto searchAjaxDto);
}
