package com.softagape.mustacheajax.commons.inif;

import com.softagape.mustacheajax.board.IBoard;
import com.softagape.mustacheajax.commons.dto.CUDInfoDto;

public interface IServiceCRUD<T> {
    IBoard insert(CUDInfoDto info, T dto);
    IBoard update(CUDInfoDto info, T dto);
    Boolean deleteFlag(CUDInfoDto info, T dto);
    Boolean deleteById(Long id);
    T findById(Long id);
}
