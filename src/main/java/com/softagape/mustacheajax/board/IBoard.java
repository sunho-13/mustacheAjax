package com.softagape.mustacheajax.board;

import com.softagape.mustacheajax.commons.dto.IBase;


public interface IBoard extends IBase {
    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    String getContent();
    void setContent(String content);

    Integer getViewQty();
    void setViewQty(Integer viewQty);

    Integer getLikeQty();
    void setLikeQty(Integer likeQty);

    String getDelFlag();
    void setDelFlag(String delFlag);

    default void copyFields(IBoard from){

        if (from == null) {
            return;
        }
        if (from.getId() != null) {
            this.setId(from.getId());
        }
        if (from.getName() != null && !from.getName().isEmpty()) {
            this.setName(from.getName());
        }

        if(getContent() != null){
            this.setContent(from.getContent());
        }

        if(getViewQty() != null){
            this.setViewQty(from.getViewQty());
        }

        if(getLikeQty() != null){
            this.setLikeQty(from.getLikeQty());
        }

        if(getDelFlag() != null){
            this.setDelFlag(from.getDelFlag());
        }
    IBase.super.copyFields(from);

    }
}
