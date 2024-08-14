package com.softagape.mustacheajax.boardlike;

import com.softagape.mustacheajax.commons.inif.IServiceCRUD;

public interface IBoardLikeService extends IServiceCRUD<IBoardLike> {
    Boolean deleteByTableUserBoard(BoardLikeDto dto);
    Integer countByTableUserBoard(IBoardLike searchDto);
}
