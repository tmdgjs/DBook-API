package kr.hs.dgsw.dbook.dbookapi.Service;

import kr.hs.dgsw.dbook.dbookapi.Json.CategoryResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.DefaultResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.TokenResponse;
import kr.hs.dgsw.dbook.dbookapi.VO.Category;
import kr.hs.dgsw.dbook.dbookapi.VO.EBook;

import java.util.List;

public interface EBookService {

    List<EBook> getSharingBookList(TokenResponse objToken);

    DefaultResponse getFile(Long ebookId);

    List<EBook> getSharingBookListWithCategory(TokenResponse objToken, String category);

    String getCategoryName(Integer category);

    List<EBook> getCategoryList(Integer category);


    List<Category> getAllCategory();
}
