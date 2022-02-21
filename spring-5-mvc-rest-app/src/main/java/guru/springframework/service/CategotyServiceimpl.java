package guru.springframework.service;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.controllers.CategoryController;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategotyServiceimpl implements CategoryService {

    private final CategoryRepository categoryRepository;
   private final CategoryMapper categoryMapper;

    public CategotyServiceimpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
      return categoryRepository.findAll().stream().map(category ->{
                  CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
                  categoryDTO.setCategoryUrl(CategoryController.BASE_URL +category.getName());
                  return  categoryDTO;
              }).collect(Collectors.toList());

    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if(category==null)
            throw new ResourceNotFoundException();
       return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }

    @Override
    public CategoryDTO createNewCategory(CategoryDTO categoryDTO) {
       Category category = categoryMapper.CategoryDTOtoCategory(categoryDTO);
       Category saved = categoryRepository.save(category);
       CategoryDTO categoryDTO1 =categoryMapper.categoryToCategoryDTO(saved);
       categoryDTO1.setCategoryUrl(CategoryController.BASE_URL+saved.getName());
       return  categoryDTO1;
    }


    @Override
    public CategoryDTO saveCategoryByDTO(String name, CategoryDTO categoryDTO) {
          Category category = categoryMapper.CategoryDTOtoCategory(categoryDTO);
          category.setName(name);
        Category savedcategory =  categoryRepository.save(category);
        CategoryDTO categoryDTO1 = categoryMapper.categoryToCategoryDTO(savedcategory);
        categoryDTO1.setCategoryUrl(CategoryController.BASE_URL+savedcategory.getName());
        return categoryDTO1;
    }

    @Override
    public Void deleteCategoryDTO(Long id) {
        categoryRepository.deleteById(id);
      return  null;
    }
}
