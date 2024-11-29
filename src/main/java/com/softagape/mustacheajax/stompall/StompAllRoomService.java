package com.softagape.mustacheajax.stompall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StompAllRoomService {
    @Autowired
    private IStompAllRoomMybatisMapper iStompAllRoomMybatisMapper;

    @Autowired
    private IStompAllChatMybatisMapper iStompAllChatMybatisMapper;

    public StompAllRoomDto insert(String roomName) {
        StompAllRoomDto newRoom = StompAllRoomDto.builder()
                .id(-1L)
                .roomName(roomName)
                .build();
        iStompAllRoomMybatisMapper.insert(newRoom);
        return newRoom;
    }

    public StompAllRoomDto findByRoomId(Long id) {
        return iStompAllRoomMybatisMapper.findById(id);
    }

    public List<StompAllRoomDto> findAll() {
        return iStompAllRoomMybatisMapper.findAll();
    }

    public void update(StompAllRoomDto dto) {
        iStompAllRoomMybatisMapper.update(dto);
    }

    @Transactional
    public void deleteByRoomId(Long id, StompAllChatDto chatDto) {
        iStompAllRoomMybatisMapper.deleteFlagById(id);
        iStompAllChatMybatisMapper.insert(chatDto);
    }
}
