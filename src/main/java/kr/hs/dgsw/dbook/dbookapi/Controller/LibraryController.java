package kr.hs.dgsw.dbook.dbookapi.Controller;

import kr.hs.dgsw.dbook.dbookapi.Json.DefaultResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.TokenResponse;
import kr.hs.dgsw.dbook.dbookapi.Service.LibraryService;
import kr.hs.dgsw.dbook.dbookapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/librarys")
public class LibraryController {

    @Autowired
    private UserService userService;

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public ResponseEntity<Object> getMyLibrary(@RequestHeader("Authorization") String token){

        // 토큰 검증
        TokenResponse objToken = userService.tokenCheck(token);

        if(!objToken.getMessage().equals("")){
            return new ResponseEntity<>(objToken, HttpStatus.SEE_OTHER);
        }

        DefaultResponse libraryRes = libraryService.getMyLibrary(objToken);

        return new ResponseEntity<>(libraryRes, HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<Object> getMyLibraryName(@RequestHeader("Authorization") String token){
        // 토큰 검증
        TokenResponse objToken = userService.tokenCheck(token);

        if(!objToken.getMessage().equals("")){
            return new ResponseEntity<>(objToken, HttpStatus.UNAUTHORIZED);
        }

        DefaultResponse defaultResponse = libraryService.getMyLibraryName(objToken);

        return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
    }



}
