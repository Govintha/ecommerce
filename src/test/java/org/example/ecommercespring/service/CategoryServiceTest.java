package org.example.ecommercespring.service;

import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.entity.Category;
import org.example.ecommercespring.repository.CategoryRepository;
import org.example.ecommercespring.services.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
This is a JUnit 5 extension that tells JUnit:
“When running this test class, let Mockito handle initialization of mocks and injection of @InjectMocks.”
Without it:
Your @Mock fields will remain null unless you manually initialize them.
@InjectMocks will also fail to work, because there’s nothing telling JUnit to let Mockito create the instance with dependencies injected.
Mockito automatically creates a mock for CategoryRepository.
Mockito automatically creates an instance of CategoryService and injects the mock repository into it.
If you can’t or don’t want to use @ExtendWith(MockitoExtension.class), you have two main options:
Option A: Use MockitoAnnotations.openMocks(this) in @BeforeEach
 */
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    //what are dependency there in primary class that has to be @Mock
      @Mock
      private CategoryRepository categoryRepository;

      //inject mock should on class like primary test class
      @InjectMocks
      private CategoryService categoryService;

       @Test
       void getAllCategories_Should_Return_AllCategory(){
           //Arrange
           List<Category> listOfCategory=new ArrayList<>();
           Category firstCategory= new Category();
           firstCategory.setName("Electronic");
           firstCategory.setId(1L);
           Category secondCategory= new Category();
           secondCategory.setName("Books");
           secondCategory.setId(2L);
           listOfCategory.add(firstCategory);
           listOfCategory.add(secondCategory);
            when(categoryRepository.findAll()).thenReturn(listOfCategory);

           //Act
           List<CategoryDTO> allCategories = categoryService.getAllCategories();

           //Assert
           assertAll("Test getAll Category",
                   ()->assertEquals(allCategories.size(),2));

           //is a Mockito method that checks whether a specific method was called on a mock, and how many times it was called.
           /*
           Hey Mockito, after running the getAllCategories() method,
           please confirm that categoryRepository.findAll() was called exactly one time."
           is not counting how many times getAllCategories() from your service is called in general — it is counting how many times, during this test execution,
           the categoryRepository.findAll() method was invoked.
            */
           verify(categoryRepository,times(1)).findAll();
       }


       @Test
       @DisplayName("Should return empty list when no category exist")
       void getAllCategory_ShouldReturnEmptyListWhenNoCategoryExist(){

           //Arrange
           List<Category> listOfCategory=new ArrayList<>();
           when(categoryRepository.findAll()).thenReturn(listOfCategory);

           //Act
           List<CategoryDTO> allCategories = categoryService.getAllCategories();

           //assert
           assertEquals(listOfCategory.size(),0);
           verify(categoryRepository,times(1)).findAll();

       }

       @Test
       @DisplayName("Create Category")
       void createCategory_shouldCreateCategorySuccessFully(){

           //Arrange
           CategoryDTO categoryDTO=CategoryDTO.builder().id(1L).name("test category").build();
           Category category= new Category();
           category.setName("test category");
           category.setId(1L);
           when(categoryRepository.save(any(Category.class))).thenReturn(category);

           //act
           CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);

           //assert
           assertAll("Saved Category",
                   ()-> assertEquals(1L,savedCategoryDTO.getId()),
                   ()-> assertEquals("test category",savedCategoryDTO.getName()));


       }
}
