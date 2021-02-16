package kr.hs.dgsw.dbook.dbookapi.Service;

import kr.hs.dgsw.dbook.dbookapi.Exception.DBookException;
import kr.hs.dgsw.dbook.dbookapi.Json.CategoryResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.DefaultResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.TokenResponse;
import kr.hs.dgsw.dbook.dbookapi.Repository.CategoryRepository;
import kr.hs.dgsw.dbook.dbookapi.Repository.EBookRepository;
import kr.hs.dgsw.dbook.dbookapi.VO.Category;
import kr.hs.dgsw.dbook.dbookapi.VO.EBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EBookServiceImpl implements EBookService{

    @Autowired
    private EBookRepository eBookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<EBook> getSharingBookList(TokenResponse objToken) {
        String userId = objToken.getTokenOwnerId();

        //return this.eBookRepository.findByIsSharedAndEbookUploaderNot(true, userId);
        return null;
    }

    @Override
    public DefaultResponse getFile(Long ebookId) {

        DefaultResponse defaultResponse = new DefaultResponse();
        try{

            EBook ebook = eBookRepository.findById(ebookId).orElseThrow(() -> new DBookException("책 정보가 없습니다."));
            defaultResponse.setData(ebook.getBookFile());

            return defaultResponse;

        } catch (DBookException e){

            defaultResponse.setMessage(e.getMessage());
            return defaultResponse;
        }
    }

    @Override
    public List<EBook> getSharingBookListWithCategory(TokenResponse objToken, String category) {

        return null;
    }

    @Override
    public String getCategoryName(Integer category) {

        Category objCategory = this.categoryRepository.findById(category).orElse(new Category(0, "No content"));
        return objCategory.getCategoryName();
    }

    @Override
    public List<EBook> getCategoryList(Integer category) {
        return this.eBookRepository.findByCategory(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return this.categoryRepository.findAll();
    }


}
