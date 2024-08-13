package com.softagape.mustacheajax.commons.inif;

import com.softagape.mustacheajax.commons.dto.CUDInfoDto;

public interface IServiceCRUD<T> {
    T insert(CUDInfoDto info, T dto);
    T update(CUDInfoDto info, T dto);
    Boolean deleteFlag(CUDInfoDto info, T dto);
    Boolean deleteById(Long id);
    T findById(Long id);
}
