package com.softagape.mustacheajax.stomplogin;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStompLoginRoomMybatisMapper {
    void insert(StompLoginRoomDto dto);
    void update(StompLoginRoomDto dto);
    void deleteFlagById(Long id);
    StompLoginRoomDto findById(Long id);
    List<StompLoginRoomDto> findAll();
    List<StompLoginRoomDto> findAdminAll();
}
