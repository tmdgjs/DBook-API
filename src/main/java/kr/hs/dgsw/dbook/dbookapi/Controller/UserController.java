package kr.hs.dgsw.dbook.dbookapi.Controller;

import kr.hs.dgsw.dbook.dbookapi.Json.DefaultResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.LoginResponse;
import kr.hs.dgsw.dbook.dbookapi.Service.UserService;
import kr.hs.dgsw.dbook.dbookapi.VO.ProfileImg;
import kr.hs.dgsw.dbook.dbookapi.VO.User;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/uploads/images") // 이미지 업로드
    public ResponseEntity<DefaultResponse> fileUpload(@RequestParam("file") MultipartFile files){

        DefaultResponse defaultResponse = userService.fileUpload(files);

        return new ResponseEntity<>(defaultResponse,HttpStatus.OK);
    }

    @GetMapping("/images") // 이미지 가져오기
    public void fileView(HttpServletResponse res, @RequestParam("imageNo") Integer imageNo) {

        ProfileImg profileImg = userService.getProfileImageInfo(imageNo);
        try{
            res.setContentType("image/jpeg");
            byte[] imageFile = profileImg.getData();
            InputStream is = new ByteArrayInputStream(imageFile);
            IOUtils.copy(is, res.getOutputStream());
        } catch (Exception e){
            e.getMessage();
        }
    }

    @PostMapping("/signups")
    public ResponseEntity<DefaultResponse> signUp(@RequestBody User user){

        DefaultResponse defaultResponse = userService.signup(user);

        if(defaultResponse.getCode() != 0)
            return new ResponseEntity<>(defaultResponse, HttpStatus.UNAUTHORIZED);

        defaultResponse.setMessage("");
        return new ResponseEntity<>(defaultResponse,HttpStatus.OK);
    }

    @PostMapping("/logins")
    public ResponseEntity<LoginResponse> login(@RequestBody User user){
        LoginResponse loginResponse = userService.login(user);

        if(loginResponse.getCode() != 0)
            return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }



}
