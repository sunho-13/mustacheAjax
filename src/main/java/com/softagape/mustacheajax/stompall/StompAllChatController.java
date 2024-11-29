package com.softagape.mustacheajax.stompall;

import com.softagape.mustacheajax.stomp.StompMessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class StompAllChatController {
    @Autowired
    private SimpMessageSendingOperations msgTempate;

    @Autowired
    private StompAllRoomService stompAllRoomService;

    @Autowired
    private StompAllChatService stompAllChatService;

    @MessageMapping("/stompall/message")
    public void message(StompAllMessageDto messageDto) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messageDto.setMsgTime(simpleDateFormat.format(new Date()));
        log.info("/stompall/message => roomId:{}, msgType:{}, msgTime:{}, writer:{}, message:{}"
                , messageDto.getRoomId()
                , messageDto.getMsgType()
                , messageDto.getMsgTime()
                , messageDto.getWriter()
                , messageDto.getMessage()
        );
        StompAllRoomDto stompRoom = stompAllRoomService.findByRoomId(messageDto.getRoomId());
        if (stompRoom == null) {
            return;
        }
        StompAllChatDto chatDto = StompAllChatDto.builder()
                .id(0L).roomId(messageDto.getRoomId())
                .writer(messageDto.getWriter())
                .msgTime(messageDto.getMsgTime())
                .message(messageDto.getMessage())
                .build();
        if ( StompMessageType.ENTER == messageDto.getMsgType() ) {
//            stompRoom.getUserList().add(StompAllMessageDto.getWriter());
            stompRoom.setCount(stompRoom.getCount() + 1);
            stompAllRoomService.update(stompRoom);
        } else if ( StompMessageType.OUT == messageDto.getMsgType() ) {
//            stompRoom.getUserList().remove(StompAllMessageDto.getWriter());
            stompRoom.setCount(stompRoom.getCount() - 1);
            stompAllRoomService.update(stompRoom);
        }
        if( stompRoom.getCount() < 1 ) {
            stompAllRoomService.deleteByRoomId(messageDto.getRoomId(), chatDto);
        } else {
            msgTempate.convertAndSend("/sub/stompall/room/" + messageDto.getRoomId()
                    , messageDto);
            this.stompAllChatService.insert(chatDto);
        }
    }
}
