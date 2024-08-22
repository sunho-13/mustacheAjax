package com.softagape.mustacheajax.commentlike;

import com.softagape.mustacheajax.commons.inif.IServiceCRUD;

public interface ICommentLikeService extends IServiceCRUD<ICommentLike> {
    Boolean deleteByCommentTableUserBoard(CommentLikeDto dto);
    Integer countByCommentTableUserBoard(ICommentLike searchDto);
}
