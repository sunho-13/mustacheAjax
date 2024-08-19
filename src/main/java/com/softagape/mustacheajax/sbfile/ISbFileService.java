package com.softagape.mustacheajax.sbfile;

import com.softagape.mustacheajax.board.BoardDto;
import com.softagape.mustacheajax.commons.inif.IServiceCRUD;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISbFileService extends IServiceCRUD<ISbFile> {
    List<ISbFile> findAllByTblBoardId(ISbFile search);
    Boolean insertFiles(BoardDto boardDto, MultipartFile[] files);
    byte[] getBytesFromFile(ISbFile down);
}
