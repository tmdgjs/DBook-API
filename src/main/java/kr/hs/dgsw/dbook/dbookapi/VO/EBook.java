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
public class EBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String description;

    Integer category;

    String author;

    String uploaderEmail;

    @CreationTimestamp
    LocalDateTime published;

    Boolean isShared;

    String bookFile;

    String coverImage;

    String publisher;

}