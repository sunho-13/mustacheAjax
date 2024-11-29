package com.softagape.mustacheajax.stomplogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StompLoginRoomService {
    @Autowired
    private IStompLoginRoomMybatisMapper iStompLoginRoomMybatisMapper;

    @Autowired
    private IStompLoginChatMybatisMapper iStompLoginChatMybatisMapper;

    public StompLoginRoomDto insert(String roomName) {
        StompLoginRoomDto newRoom = StompLoginRoomDto.builder()
                .id(-1L)
                .roomName(roomName)
                .build();
        iStompLoginRoomMybatisMapper.insert(newRoom);
        return newRoom;
    }

    public StompLoginRoomDto findByRoomId(Long id) {
        return iStompLoginRoomMybatisMapper.findById(id);
    }

    public List<StompLoginRoomDto> findAll() {
        return iStompLoginRoomMybatisMapper.findAll();
    }

    public void update(StompLoginRoomDto dto) {
        iStompLoginRoomMybatisMapper.update(dto);
    }

    @Transactional
    public void deleteByRoomId(Long id, StompLoginChatDto chatDto) {
        iStompLoginRoomMybatisMapper.deleteFlagById(id);
        iStompLoginChatMybatisMapper.insert(chatDto);
    }

    public List<StompLoginRoomDto> findAdminAll() {
        return iStompLoginRoomMybatisMapper.findAdminAll();
    }
}
