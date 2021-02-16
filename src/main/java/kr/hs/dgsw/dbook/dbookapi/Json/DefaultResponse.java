package kr.hs.dgsw.dbook.dbookapi.Json;

import lombok.Data;

@Data
public class DefaultResponse {

    int code;

    String message;

    Object data;

    public DefaultResponse() {
        this.code = 0;
        this.message = "";
    }

    public DefaultResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public DefaultResponse(Object data) {
        this.data = data;
    }
}
