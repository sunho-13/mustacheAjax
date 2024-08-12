package com.softagape.mustacheajax.board;

import com.softagape.mustacheajax.commons.dto.CUDInfoDto;
import com.softagape.mustacheajax.member.IMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardWebController {

    @GetMapping("/board_ajx_list")
    private String boardAjxList(){
        return "board/board_ajx_list";
    }


}
