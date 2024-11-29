package com.softagape.mustacheajax.stomplogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StompLoginChatService {
    @Autowired
    private IStompLoginChatMybatisMapper iStompLoginChatMybatisMapper;

    public void insert(StompLoginChatDto dto) {
        iStompLoginChatMybatisMapper.insert(dto);
    }

    public List<StompLoginChatDto> findAllByRoomId(Long roomId) {
        return iStompLoginChatMybatisMapper.findAllByRoomId(roomId);
    }
}
