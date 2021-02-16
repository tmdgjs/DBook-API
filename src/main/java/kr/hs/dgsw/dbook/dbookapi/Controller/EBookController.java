package kr.hs.dgsw.dbook.dbookapi.Controller;

import kr.hs.dgsw.dbook.dbookapi.Json.CategoryResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.DefaultResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.TokenResponse;
import kr.hs.dgsw.dbook.dbookapi.Service.EBookService;
import kr.hs.dgsw.dbook.dbookapi.Service.UserService;
import kr.hs.dgsw.dbook.dbookapi.VO.Category;
import kr.hs.dgsw.dbook.dbookapi.VO.EBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ebooks")
public class EBookController {
    @Autowired
    private EBookService eBookService;

    @Autowired
    private UserService userService;

    @GetMapping("/shares")
    public ResponseEntity<Object> getSharingBookList(@RequestHeader("Authorization") String token){

        // 토큰 검증
        TokenResponse objToken = userService.tokenCheck(token);

        if(!objToken.getMessage().equals("")){
            return new ResponseEntity<>(objToken, HttpStatus.SEE_OTHER);
        }

        List<EBook> eBookList = eBookService.getSharingBookList(objToken);

        return new ResponseEntity<>(eBookList,HttpStatus.OK);
    }

    @GetMapping("/files/{ebookId}")
    public ResponseEntity<Object> getFile(@PathVariable Long ebookId){

        DefaultResponse fileRes = eBookService.getFile(ebookId);
        return new ResponseEntity<>(fileRes, HttpStatus.OK);
    }

    @GetMapping("/categorys")
    public ResponseEntity<Object> getCategoryList(@RequestParam(value = "category", required = false) Integer category){

        if(category == null){
            List<Map<String, Object>> objMapList = new ArrayList<>();
            List<Category> categories = eBookService.getAllCategory();

            for(Category objCategory : categories){

                Map<String, Object> objectMap = new HashMap<>();
                List<EBook> eBookList = eBookService.getCategoryList(objCategory.getCategoryId());
                objectMap.put("categoryId", objCategory.getCategoryId());
                objectMap.put("categoryName", objCategory.getCategoryName());
                objectMap.put("data", eBookList);
                objMapList.add(objectMap);

            }

            return new ResponseEntity<>(objMapList, HttpStatus.OK);
        } else {
            String strCategoryName = eBookService.getCategoryName(category);

            if(strCategoryName.equals("no content"))
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            List<EBook> eBookList = eBookService.getCategoryList(category);
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("categoryName", strCategoryName);
            objectMap.put("data", eBookList);


            return new ResponseEntity<>(objectMap, HttpStatus.OK);
        }


    }

    /*@GetMapping("/categorys")
    public ResponseEntity<Object> getSharingBookListWithCategory(@RequestHeader("Authorization") String token, @RequestParam("category") Integer category){
        // 토큰 검증
        TokenResponse objToken = userService.tokenCheck(token);

        if(!objToken.getMessage().equals("")){
            return new ResponseEntity<>(objToken, HttpStatus.UNAUTHORIZED);
        }

        String strCategoryName = eBookService.getCategoryName(category);

        List<EBook> eBookList = eBookService.getSharingBookListWithCategory(objToken, category);


        return new ResponseEntity<>(eBookList, HttpStatus.OK);

    }*/




}
