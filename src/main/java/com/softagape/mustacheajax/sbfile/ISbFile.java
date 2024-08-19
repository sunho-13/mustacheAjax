package com.softagape.mustacheajax.sbfile;

public interface ISbFile {
    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    Integer getOrd();
    void setOrd(Integer ord);

    String getFileType();
    void setFileType(String fileType);

    String getUniqName();
    void setUniqName(String uniqName);

    Integer getLength();
    void setLength(Integer length);

    String getDescription();
    void setDescription(String description);

    String getTbl();
    void setTbl(String tbl);

    Long getBoardId();
    void setBoardId(Long boardId);

    default void copyFields(ISbFile from) {
        if (from == null) {
            return;
        }
        if (from.getId() != null) {
            this.setId(from.getId());
        }
        if (from.getName() != null && !from.getName().isEmpty()) {
            this.setName(from.getName());
        }
        if (from.getOrd() != null) {
            this.setOrd(from.getOrd());
        }
        if (from.getFileType() != null && !from.getFileType().isEmpty()) {
            this.setFileType(from.getFileType());
        }
        if (from.getUniqName() != null && !from.getUniqName().isEmpty()) {
            this.setUniqName(from.getUniqName());
        }
        if (from.getLength() != null) {
            this.setLength(from.getLength());
        }
        if (from.getDescription() != null && !from.getDescription().isEmpty()) {
            this.setDescription(from.getDescription());
        }
        if (from.getTbl() != null && !from.getTbl().isEmpty()) {
            this.setTbl(from.getTbl());
        }
        if (from.getBoardId() != null) {
            this.setBoardId(from.getBoardId());
        }
    }
}
