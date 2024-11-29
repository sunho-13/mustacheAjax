package com.softagape.mustacheajax.stompall;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStompAllRoomMybatisMapper {
    void insert(StompAllRoomDto dto);
    void update(StompAllRoomDto dto);
    void deleteFlagById(Long id);
    StompAllRoomDto findById(Long id);
    List<StompAllRoomDto> findAll();
}
