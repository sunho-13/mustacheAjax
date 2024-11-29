package com.softagape.mustacheajax.stompall;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStompAllChatMybatisMapper {
    void insert(StompAllChatDto dto);
    List<StompAllChatDto> findAllByRoomId(Long roomId);
}
