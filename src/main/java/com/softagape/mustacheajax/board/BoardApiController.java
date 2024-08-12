package com.softagape.mustacheajax.board;

import com.softagape.mustacheajax.board.BoardDto;
import com.softagape.mustacheajax.board.IBoard;
import com.softagape.mustacheajax.commons.dto.CUDInfoDto;
import com.softagape.mustacheajax.commons.dto.SearchAjaxDto;
import com.softagape.mustacheajax.member.IMember;
import com.softagape.mustacheajax.member.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/board")
public class BoardApiController {
    @Autowired
    private IBoardService boardService;

    @PostMapping
    public ResponseEntity<IBoard> insert(Model model, @RequestBody BoardDto dto) {

        try {
            if ( dto == null ) {
                return ResponseEntity.badRequest().build(); // error 응답
            }
            IMember loginUser = (IMember)model.getAttribute("loginUser");
            if(loginUser==null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            CUDInfoDto cudInfoDto = new CUDInfoDto(loginUser);
            IBoard result = this.boardService.insert(cudInfoDto, dto);
            if ( result == null ) {
                return ResponseEntity.badRequest().build(); // error 응답
            }
            return ResponseEntity.ok(result);
            // 200 OK 와 result 데이터를 응답한다.
        } catch ( Exception ex ) {
            log.error(ex.toString());
            return ResponseEntity.badRequest().build(); // error 응답
        }
    }

    @PatchMapping("/")
    public ResponseEntity<IBoard> update(Model model, @RequestBody BoardDto dto) {
        try {
            if ( dto == null || dto.getId() == null || dto.getId() <=0) {
                return ResponseEntity.badRequest().build(); // error 응답
            }

            IMember loginUser = (IMember)model.getAttribute("loginUser");
            if(loginUser==null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            CUDInfoDto cudInfoDto = new CUDInfoDto(loginUser);
            IBoard result = this.boardService.update(cudInfoDto, dto);
            if ( result == null ) {
                return ResponseEntity.notFound().build(); // error 응답
            }

            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            log.error(ex.toString());
            return ResponseEntity.badRequest().build(); // error 응답
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(Model model, @PathVariable Long id) {
        try {
            if ( id == null ) {
                return ResponseEntity.badRequest().build();// error 응답
            }

            IMember loginUser = (IMember)model.getAttribute("loginUser");
            if(loginUser==null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            if(loginUser.getRole().equals(MemberRole.ADMIN.toString())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            Boolean result = this.boardService.deleteById(id);
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            log.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delflag")
    public ResponseEntity<Boolean> deleteFlag(Model model, @RequestBody BoardDto dto) {
        try {
            if ( dto == null || dto.getId() == null || dto.getId() <=0 ) {
                return ResponseEntity.badRequest().build();// error 응답
            }

            IMember loginUser = (IMember)model.getAttribute("loginUser");
            if(loginUser==null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            CUDInfoDto cudInfoDto = new CUDInfoDto(loginUser);
            Boolean result = this.boardService.deleteFlag(cudInfoDto, dto);
            // 최종 목적지인 Mybatis 쿼리를 DB 에 실행하고 결과를 리턴 받는다.
            return ResponseEntity.ok(result);
            // 200 OK 와 result 데이터를 응답한다.
        } catch ( Exception ex ) {
            log.error(ex.toString());
            return ResponseEntity.badRequest().build(); // error 응답
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<IBoard> findById(@PathVariable Long id) {
        try {
            if ( id == null || id <= 0 ) {
                return ResponseEntity.badRequest().build();
            }
            IBoard result = this.boardService.findById(id);
            if ( result == null ) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch ( Exception ex ) {
            log.error(ex.toString());
            return ResponseEntity.badRequest().build();
        }
    }

/*    @GetMapping("/nm/{searchName}") // POST method : /ct/nm/문자열{searchName}
    public ResponseEntity<List<IBoard>> findAllByNameContains(@PathVariable String searchName) {
        // ResponseEntity<데이터형> : http 응답을 http 응답코드와 리턴데이터형으로 묶어서 응답한다.
        // List<IBoard> 데이터형 리턴은 배열 데이터를 JSON 문자열로 표현하여 리턴한다.
        // @PathVariable String searchName : URL 주소의 /ct/nm/문자열 => {searchName} searchName 변수의 값으로 요청된다.
        try {
            if ( searchName == null || searchName.isEmpty() ) {
                return ResponseEntity.badRequest().build(); // error 응답
            }
            SearchAjaxDto searchAjaxDto = SearchAjaxDto.builder()
                    .searchName(searchName).page(1).build();
            List<IBoard> result = this.boardService.findAllByNameContains(searchAjaxDto);
            // 최종 목적지인 Mybatis 쿼리를 DB 에 실행하고 결과를 리턴 받는다.
            // findAllByNameContains 쿼리 문장을 만들때 orderByWord, searchName, rowsOnePage, firstIndex 값을
            // 활용하여 쿼리 문장을 만들고 실행한다.
            if ( result == null || result.size() <= 0 ) {
                return ResponseEntity.notFound().build(); // error 응답
            }
            return ResponseEntity.ok(result);
            // 200 OK 와 result 데이터를 응답한다.
        } catch ( Exception ex ) {
            log.error(ex.toString());
            return ResponseEntity.badRequest().build(); // error 응답
        }
    }*/

    @PostMapping("/searchName") // POST method : /ct/searchName
    public ResponseEntity<SearchAjaxDto> findAllByNameContains(Model model, @RequestBody SearchAjaxDto searchAjaxDto) {
        // ResponseEntity<데이터형> : http 응답을 http 응답코드와 리턴데이터형으로 묶어서 응답한다.
        // SearchAjaxDto 데이터형를 JSON 문자열로 표현하여 리턴한다.
        // @RequestBody SearchAjaxDto searchAjaxDto : JSON 문자열로 요청을 받는다.
        //      다만 JSON 문자열의 데이터가 SearchAjaxDto 데이터형이어야 한다.
        //      {"searchName":"값", "sortColumn":"값", "sortAscDsc":"값", "page":값}
        try {
            IMember loginUser = (IMember)model.getAttribute("loginUser");
            // POSTMAN 으로 테스트 안되지만, WEB 화면에서는 로그인한 사용자만 가능하다.
            if ( loginUser == null ) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            if ( searchAjaxDto == null ) {
                return ResponseEntity.badRequest().build();
            }
            int total = this.boardService.countAllByNameContains(searchAjaxDto);
            // 최종 목적지인 Mybatis 쿼리를 DB 에 실행하고 결과를 리턴 받는다.
            // 검색식의 searchName 으로 찾은 데이터 행수를 리턴받는다. 화면의 페이지 계산에 사용된다.
            List<BoardDto> list = this.boardService.findAllByNameContains(searchAjaxDto);
            // 최종 목적지인 Mybatis 쿼리를 DB 에 실행하고 결과를 리턴 받는다.
            // findAllByNameContains 쿼리 문장을 만들때 orderByWord, searchName, rowsOnePage, firstIndex 값을
            // 활용하여 쿼리 문장을 만들고 실행한다.
            if ( list == null ) {
                return ResponseEntity.notFound().build(); // error 응답
            }
            searchAjaxDto.setTotal(total);
            // SearchAjaxDto 응답결과에 total 을 추가한다.
            searchAjaxDto.setDataList(list);
            // SearchAjaxDto 응답결과에 List<IBoard> 을 추가한다.
            return ResponseEntity.ok(searchAjaxDto);
            // 200 OK 와 result 데이터를 응답한다.
        } catch ( Exception ex ) {
            log.error(ex.toString());
            return ResponseEntity.badRequest().build(); // error 응답
        }
    }

    @PostMapping("/countName")  // POST method : /ct/countName
    public ResponseEntity<Integer> countAllByNameContains(Model model, @RequestBody SearchAjaxDto searchAjaxDto) {
        // ResponseEntity<데이터형> : http 응답을 http 응답코드와 리턴데이터형으로 묶어서 응답한다.
        // @RequestBody SearchAjaxDto searchAjaxDto : JSON 문자열로 요청을 받는다.
        //      다만 JSON 문자열의 데이터가 SearchAjaxDto 데이터형이어야 한다.
        //      {"searchName":"값"}
        try {
            IMember loginUser = (IMember)model.getAttribute("loginUser");
            // POSTMAN 으로 테스트 안되지만, WEB 화면에서는 로그인한 사용자만 가능하다.
            if ( loginUser == null ) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            if ( searchAjaxDto == null ) {
                return ResponseEntity.badRequest().build(); // error 응답
            }
            int total = this.boardService.countAllByNameContains(searchAjaxDto);
            // 최종 목적지인 Mybatis 쿼리를 DB 에 실행하고 결과를 리턴 받는다.
            // countAllByNameContains 쿼리 문장을 만들때 searchName 값을 활용하여 쿼리 문장을 만들고 실행한다.
            // 데이터 행수를 리턴한다.
            return ResponseEntity.ok(total);
            // 200 OK 와 result 데이터를 응답한다.
        } catch ( Exception ex ) {
            log.error(ex.toString());
            return ResponseEntity.badRequest().build(); // error 응답
        }
    }
}
