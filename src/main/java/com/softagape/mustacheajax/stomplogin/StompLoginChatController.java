package com.softagape.mustacheajax.stomplogin;

import com.softagape.mustacheajax.commons.dto.CUDInfoDto;
import com.softagape.mustacheajax.commons.exeption.LoginAccessException;
import com.softagape.mustacheajax.commons.inif.IResponseController;
import com.softagape.mustacheajax.stomp.StompMessageType;
import com.softagape.mustacheajax.stompevery.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/stomplogin")
public class StompLoginChatController implements IResponseController {
    @Autowired
    private SimpMessageSendingOperations msgTempate;

    @Autowired
    private StompLoginRoomService stompLoginRoomService;

    @Autowired
    private StompLoginChatService stompLoginChatService;

    @MessageMapping("/message")
    public void message(StompLoginMessageDto messageDto) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messageDto.setMsgTime(simpleDateFormat.format(new Date()));
        log.info("/stomplogin/message => roomId:{}, msgType:{}, msgTime:{}, writerId:{}, writer:{}, message:{}"
                , messageDto.getRoomId()
                , messageDto.getMsgType()
                , messageDto.getMsgTime()
                , messageDto.getWriterId()
                , messageDto.getWriter()
                , messageDto.getMessage()
        );
        StompLoginRoomDto stompRoom = stompLoginRoomService.findByRoomId(messageDto.getRoomId());
        if (stompRoom == null) {
            return;
        }
        StompLoginChatDto chatDto = StompLoginChatDto.builder()
                .id(0L).roomId(messageDto.getRoomId())
                .writerId(messageDto.getWriterId())
                .writer(messageDto.getWriter())
                .msgTime(messageDto.getMsgTime())
                .message(messageDto.getMessage())
                .build();
        if ( StompMessageType.ENTER == messageDto.getMsgType() ) {
//            stompRoom.getUserList().add(StompLoginMessageDto.getWriter());
            stompRoom.setCount(stompRoom.getCount() + 1);
            stompLoginRoomService.update(stompRoom);
        } else if ( StompMessageType.OUT == messageDto.getMsgType() ) {
//            stompRoom.getUserList().remove(StompLoginMessageDto.getWriter());
            stompRoom.setCount(stompRoom.getCount() - 1);
            stompLoginRoomService.update(stompRoom);
        }
        if( stompRoom.getCount() < 1 ) {
            stompLoginRoomService.deleteByRoomId(messageDto.getRoomId(), chatDto);
        } else {
            msgTempate.convertAndSend("/sub/stomplogin/room/" + messageDto.getRoomId()
                    , messageDto);
            this.stompLoginChatService.insert(chatDto);
        }
    }

    @GetMapping("/adminenter/{id}")
    public String adminDetail(Model model, @PathVariable Long id) {
        try {
            CUDInfoDto cudInfoDto = makeResponseCheckLoginAdmin(model);
            StompLoginRoomDto stompRoom = this.stompLoginRoomService.findByRoomId(id);
            List<StompLoginChatDto> list = this.stompLoginChatService.findAllByRoomId(id);
            model.addAttribute("stompLoginRoomDto", stompRoom);
            model.addAttribute("stompChatList", list);
            return "stomplogin/stomploginroomadmindetail";
        } catch (LoginAccessException lae) {
            log.error(lae.getMessage());
            return "redirect:/";
        }
    }
}
