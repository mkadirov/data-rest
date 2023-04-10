package pdp.datarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.datarest.entity.Category;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.CategoryDto;
import pdp.datarest.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public ApiResponse addCategory(CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
        Category category = new Category();
        optionalCategory.ifPresent(category::setParentCategory);
        if(categoryRepository.existsByName(categoryDto.getName())){
            return new ApiResponse("Category exists", false);
        }
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse getCategoryList(){
        return new ApiResponse("Successfully retrieved", true, categoryRepository.findAll());
    }

    public ApiResponse getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.map(category ->
                new ApiResponse("Successfully retrieved", true, category)).orElseGet(() ->
                new ApiResponse("Category not found", false));
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto){

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            return new ApiResponse("Category not found", false);
        }
        if(categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id)){
            return new ApiResponse("Category exists", false);
        }
        Category category = optionalCategory.get();

        Optional<Category> opParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
        opParentCategory.ifPresent(category::setParentCategory);
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse deleteCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
            return new ApiResponse("Successfully deleted", true);
        }
        return new ApiResponse("Category not found", false);
    }
}
