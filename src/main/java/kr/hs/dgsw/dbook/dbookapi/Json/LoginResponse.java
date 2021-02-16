package kr.hs.dgsw.dbook.dbookapi.Json;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends DefaultResponse{

    String token;

    String email;

    public LoginResponse(){
        this.message = "";
        this.code = 0;
    }

    public LoginResponse(String email, String token, Object object) {
        this.email = email;
        this.token = token;
        this.data = data;
    }

    public LoginResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
