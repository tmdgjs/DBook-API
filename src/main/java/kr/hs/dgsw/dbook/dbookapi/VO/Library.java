package kr.hs.dgsw.dbook.dbookapi.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long libraryId;

    Long ebookId;

    String email;

    @CreationTimestamp
    LocalDateTime mylibraryCreated;

    public Library(Long ebookId, String email) {
        this.ebookId = ebookId;
        this.email = email;
    }
}
