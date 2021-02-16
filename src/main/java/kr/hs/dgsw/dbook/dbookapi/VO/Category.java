package kr.hs.dgsw.dbook.dbookapi.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer categoryId;

    String categoryName;

    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId; this.categoryName = categoryName;
    }

}
