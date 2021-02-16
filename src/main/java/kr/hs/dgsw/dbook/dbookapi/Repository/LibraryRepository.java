package kr.hs.dgsw.dbook.dbookapi.Repository;

import kr.hs.dgsw.dbook.dbookapi.VO.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    List<Library> findByEmail(String email);
}
