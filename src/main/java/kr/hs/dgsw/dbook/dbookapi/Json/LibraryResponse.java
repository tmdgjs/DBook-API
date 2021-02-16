package kr.hs.dgsw.dbook.dbookapi.Json;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.tomcat.jni.Library;

@EqualsAndHashCode(callSuper = true)
@Data
public class LibraryResponse extends DefaultResponse{

    String userid;

    public LibraryResponse(){
        this.message = "";
        this.code = 0;
    }



}
