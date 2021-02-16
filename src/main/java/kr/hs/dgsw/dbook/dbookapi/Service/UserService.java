package kr.hs.dgsw.dbook.dbookapi.Service;

import kr.hs.dgsw.dbook.dbookapi.Json.DefaultResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.LoginResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.TokenResponse;
import kr.hs.dgsw.dbook.dbookapi.VO.ProfileImg;
import kr.hs.dgsw.dbook.dbookapi.VO.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    DefaultResponse signup(User user);

    LoginResponse login(User user);

    TokenResponse tokenCheck(String token);

    DefaultResponse fileUpload(MultipartFile files);

    ProfileImg getProfileImageInfo(Integer imageNo);
}
