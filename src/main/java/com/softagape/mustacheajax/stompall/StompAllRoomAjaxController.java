package com.softagape.mustacheajax.stompall;

import com.softagape.mustacheajax.commons.exeption.LoginAccessException;
import com.softagape.mustacheajax.commons.inif.IResponseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/stompall")
public class StompAllRoomAjaxController implements IResponseController {
    @Autowired
    private StompAllRoomService stompAllRoomService;

    @PostMapping("/create")
    public ResponseEntity<StompAllRoomDto> createStompRoom(Model model, @RequestBody StompAllRoomDto StompAllRoomDto) {
        try {
            if (StompAllRoomDto == null) {
                return ResponseEntity.badRequest().build();
            }
//            CUDInfoDto cudInfoDto = makeResponseCheckLogin(model);
            StompAllRoomDto newRoom = this.stompAllRoomService.insert(StompAllRoomDto.getRoomName());
            return ResponseEntity.ok(newRoom);
        } catch (LoginAccessException lae) {
            log.error(lae.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
