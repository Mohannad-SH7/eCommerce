package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CategoryServiceImp implements CategoryService{


    //private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty() )
            throw new APIException("No category created until now");
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory !=null)
            throw   new APIException("Category with the name '" +category.getCategoryName()+"' already exists !!!");
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> optionalCategory=categoryRepository.findById(categoryId);
        Category category  =optionalCategory
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepository.delete(category);
        return "Category with category Id: "+ categoryId+" Deleted Successfully";

//        List<Category> categories = categoryRepository.findAll();
//
//        Category category = categories.stream()
//                .filter(c ->c.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
//
//        categoryRepository.delete(category);
//        return "Category with category Id: "+ categoryId+" Deleted Successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {// category id : 1
        Optional<Category> savedCategoryOptional=categoryRepository.findById(categoryId);
        Category savedCategory=savedCategoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryId(categoryId);
        savedCategory =categoryRepository.save(category);
        return savedCategory;

//        List<Category> categories = categoryRepository.findAll();
//        Optional<Category> categoryOptional = categories.stream()
//                .filter(c ->c.getCategoryId().equals(categoryId))
//                .findFirst();
//
//        if(categoryOptional.isPresent()){
//            Category existingCategory= categoryOptional.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category savedCategory = categoryRepository.save(existingCategory);
//            return savedCategory;
//        }
//        else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not Found");
//        }
    }
}
