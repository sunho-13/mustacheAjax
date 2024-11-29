package com.softagape.mustacheajax.stompall;

import com.softagape.mustacheajax.commons.exeption.LoginAccessException;
import com.softagape.mustacheajax.commons.inif.IResponseController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/stompall")
public class StompAllRoomController implements IResponseController {
    @Autowired
    private StompAllRoomService stompAllRoomService;

    @GetMapping("/list")
    public String stompRoomList(Model model) {
        try {
//            CUDInfoDto cudInfoDto = makeResponseCheckLogin(model);
            List<StompAllRoomDto> list = this.stompAllRoomService.findAll();
            model.addAttribute("stompRoomList", list);
            return "stompall/stompallroomlist";
        } catch (LoginAccessException lae) {
            log.error(lae.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/enter")
    public String enterStompRoom(Model model
            , HttpServletRequest request
            , @ModelAttribute StompAllRoomDto stompAllRoomDto
    ) {
        try {
            if (stompAllRoomDto == null) {
                return "redirect:/stompall/list";
            }
//            CUDInfoDto cudInfoDto = makeResponseCheckLogin(model);
            String writer = stompAllRoomDto.getWriter();
            StompAllRoomDto stompRoom = this.stompAllRoomService.findByRoomId(stompAllRoomDto.getId());
//            StompAllRoomDto stompRoom = this.stompRoomAllService.findByRoomId(stompAllRoomDto.getRoomId());
//            if ( this.getIndexOfWriter(writer, stompRoom.getUserList()) >= 0 ) {
//                log.error("{} 이미 존재하는 대화명", writer);
//                return "redirect:/stomp/list";
//            }
            model.addAttribute("stompAllRoomDto", stompRoom);
            model.addAttribute("writer", writer);
            String url = String.format("%s:%d", request.getServerName(), request.getServerPort());
            model.addAttribute("hostUrl", url);
            return "stompall/stompallroomdetail";
        } catch (LoginAccessException lae) {
            log.error(lae.getMessage());
            return "redirect:/";
        }
    }
}
