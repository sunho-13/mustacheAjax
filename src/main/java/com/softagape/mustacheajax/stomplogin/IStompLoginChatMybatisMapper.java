package com.softagape.mustacheajax.stomplogin;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStompLoginChatMybatisMapper {
    void insert(StompLoginChatDto dto);
    List<StompLoginChatDto> findAllByRoomId(Long roomId);
}
