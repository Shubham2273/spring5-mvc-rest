package guru.springframework.service;

import guru.springframework.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
    CategoryDTO createNewCategory(CategoryDTO categoryDTO);
    CategoryDTO saveCategoryByDTO(String name, CategoryDTO categoryDTO);
   Void  deleteCategoryDTO(Long id);

}
