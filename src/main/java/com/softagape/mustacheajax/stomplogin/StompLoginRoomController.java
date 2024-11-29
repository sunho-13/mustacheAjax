package com.softagape.mustacheajax.stomplogin;

import com.softagape.mustacheajax.commons.dto.CUDInfoDto;
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
@RequestMapping("/stomplogin")
public class StompLoginRoomController implements IResponseController {
    @Autowired
    private StompLoginRoomService stompLoginRoomService;

    @GetMapping("/list")
    public String stompRoomList(Model model) {
        try {
            CUDInfoDto cudInfoDto = makeResponseCheckLogin(model);
            List<StompLoginRoomDto> list = this.stompLoginRoomService.findAll();
            model.addAttribute("stompRoomList", list);
            return "stomplogin/stomploginroomlist";
        } catch (LoginAccessException lae) {
            log.error(lae.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/enter")
    public String enterStompRoom(Model model
            , HttpServletRequest request
            , @ModelAttribute StompLoginRoomDto stompLoginRoomDto
    ) {
        try {
            if (stompLoginRoomDto == null) {
                return "redirect:/stomplogin/list";
            }
            CUDInfoDto cudInfoDto = makeResponseCheckLogin(model);
            String writer = cudInfoDto.getLoginUser().getNickname();
            StompLoginRoomDto stompRoom = this.stompLoginRoomService.findByRoomId(stompLoginRoomDto.getId());
            model.addAttribute("stompLoginRoomDto", stompRoom);
            model.addAttribute("writerId", cudInfoDto.getLoginUser().getId());
            model.addAttribute("writer", writer);
            String url = String.format("%s:%d", request.getServerName(), request.getServerPort());
            model.addAttribute("hostUrl", url);
            return "stomplogin/stomploginroomdetail";
        } catch (LoginAccessException lae) {
            log.error(lae.getMessage());
            return "redirect:/";
        }
    }
    @GetMapping("/adminlist")
    public String stompRoomAdminList(Model model) {
        try {
            CUDInfoDto cudInfoDto = makeResponseCheckLoginAdmin(model);
            List<StompLoginRoomDto> list = this.stompLoginRoomService.findAdminAll();
            model.addAttribute("stompRoomList", list);
            return "stomplogin/stomploginroomadminlist";
        } catch (LoginAccessException lae) {
            log.error(lae.getMessage());
            return "redirect:/";
        }
    }
}
