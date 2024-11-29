package com.softagape.mustacheajax.stompall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StompAllChatService {
    @Autowired
    private IStompAllChatMybatisMapper iStompAllChatMybatisMapper;

    public void insert(StompAllChatDto dto) {
        iStompAllChatMybatisMapper.insert(dto);
    }

    public List<StompAllChatDto> findAllByRoomId(Long roomId) {
        return iStompAllChatMybatisMapper.findAllByRoomId(roomId);
    }
}
