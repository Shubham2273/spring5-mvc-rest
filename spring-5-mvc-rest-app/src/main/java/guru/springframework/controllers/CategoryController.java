package guru.springframework.controllers;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.api.v1.model.CategoryListDTO;
import guru.springframework.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String  BASE_URL= "/api/v1/categories/";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getallCatetories(){

        return new ResponseEntity<CategoryListDTO>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<CategoryDTO>(
                categoryService.getCategoryByName(name), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createNewCustomer(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<CategoryDTO>(categoryService.createNewCategory(categoryDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("{name}")
    public ResponseEntity<CategoryDTO>updateCustomer(@PathVariable String name ,@RequestBody CategoryDTO categoryDTO){
        return  new ResponseEntity<CategoryDTO>(categoryService.saveCategoryByDTO(name,categoryDTO),HttpStatus.OK);
    }

     @DeleteMapping("{id}")
    public ResponseEntity<Void>deletecustomer(@PathVariable Long id){
        categoryService.deleteCategoryDTO(id);
        return  new ResponseEntity<Void>(HttpStatus.OK);
     }

}
