package kr.hs.dgsw.dbook.dbookapi.Repository;

import kr.hs.dgsw.dbook.dbookapi.VO.EBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EBookRepository  extends JpaRepository<EBook, Long> {

    List<EBook> findByUploaderEmail(String email);

    //List<EBook> findByIsSharedAndEbookUploaderNot(Boolean isShared, String ebookUploader);

    //List<EBook> findByIsSharedAndEbookUploaderNotAndEbookGenre(Boolean isShared, String ebookUploader, String category);

    List<EBook> findByCategory(Integer category);
}
