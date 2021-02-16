package kr.hs.dgsw.dbook.dbookapi.Json;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
public class EBookListResponse extends DefaultResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder
    public EBookListResponse(int code, String msg, Object data){
        super(code, msg);
        this.data = data;

    }

}
