package kr.hs.dgsw.dbook.dbookapi.Service;

import kr.hs.dgsw.dbook.dbookapi.Json.DefaultResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.TokenResponse;

public interface LibraryService {

    DefaultResponse getMyLibrary(TokenResponse objToken);

    DefaultResponse getMyLibraryName(TokenResponse objToken);
}
