package kr.hs.dgsw.dbook.dbookapi.Service;

import kr.hs.dgsw.dbook.dbookapi.Json.DefaultResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.LibraryResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.TokenResponse;
import kr.hs.dgsw.dbook.dbookapi.Repository.EBookRepository;
import kr.hs.dgsw.dbook.dbookapi.Repository.LibraryRepository;
import kr.hs.dgsw.dbook.dbookapi.Repository.UserRepository;
import kr.hs.dgsw.dbook.dbookapi.VO.EBook;
import kr.hs.dgsw.dbook.dbookapi.VO.Library;
import kr.hs.dgsw.dbook.dbookapi.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService{

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private EBookRepository eBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DefaultResponse getMyLibrary(TokenResponse objToken) {

        LibraryResponse libraryResponse = new LibraryResponse();
        String userId = objToken.getTokenOwnerId();
        libraryResponse.setUserid(userId);

        List<EBook> myLibrary = this.eBookRepository.findByUploaderEmail(userId);
        List<Library> mySharedList = this.libraryRepository.findByEmail(userId);

        for (Library library : mySharedList) {
            Long ebookId = library.getEbookId();
            EBook eBook = this.eBookRepository.findById(ebookId).orElseThrow(null);
            myLibrary.add(eBook);
        }

        libraryResponse.setData(myLibrary);
        return libraryResponse;
    }

    @Override
    public DefaultResponse getMyLibraryName(TokenResponse objToken) {

        DefaultResponse defaultResponse = new DefaultResponse();
        String userId = objToken.getTokenOwnerId();


        User user = this.userRepository.findByEmail(userId).orElse(null);

        defaultResponse.setData(user.getLibraryName());

        return defaultResponse;
    }
}
