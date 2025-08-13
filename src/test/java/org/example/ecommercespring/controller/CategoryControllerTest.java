package org.example.ecommercespring.controller;

import org.example.ecommercespring.controllers.CategoryController;
import org.example.ecommercespring.exception.GlobalExceptionHandler;
import org.example.ecommercespring.services.ICategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
     private ICategoryService categoryService;

    @InjectMocks
     private CategoryController categoryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        // this mini requirement to test  controller
        mockMvc=MockMvcBuilders.standaloneSetup(categoryController).
                setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void simpleTest(){
       // need to learn how to do in controller layer
    }
}
/*
@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
     private ICategoryService categoryService;

    @InjectMocks
     private CategoryController categoryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        // this mini requirement to test  controller
        mockMvc=MockMvcBuilders.standaloneSetup(categoryController).
                setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }
}
1. @ExtendWith(MockitoExtension.class)
Purpose: Initializes @Mock and @InjectMocks before each test.

Without it, categoryService would stay null, and categoryController wouldn’t get the mock injected.

This is the JUnit 5 way of enabling Mockito for your test.

2. @Mock private ICategoryService categoryService;
This creates a mock object for your service layer.

You don’t want to hit the real database or real service in a controller test.

You can control its behavior using when(...).thenReturn(...) in your test.

3. @InjectMocks private CategoryController categoryController;
Tells Mockito:

"Create an instance of CategoryController and inject any fields/constructor args with matching mocks."

So here, categoryService mock gets injected into categoryController.

4. private MockMvc mockMvc;
MockMvc is Spring’s testing tool for simulating HTTP requests to your controller without starting the full server.

It lets you test routes, request mappings, status codes, and responses.

5. @BeforeEach void setUp()
This is where you prepare your test environment before each test method.

MockMvcBuilders.standaloneSetup(categoryController)
Builds a MockMvc instance just for CategoryController.

Standalone mode means you are not loading the full Spring context — faster and more isolated.

.setControllerAdvice(new GlobalExceptionHandler())
Registers your custom exception handler so that if the controller throws exceptions, the test will handle them exactly like in real runtime.

.build()
Creates the MockMvc instance.

Why all this is needed together
You’re doing a controller unit test, not an integration test.

You don’t load the full Spring Boot context (fast!).

You mock out dependencies (categoryService) so you control responses.

You set up MockMvc to simulate real HTTP calls to your controller methods.

You register GlobalExceptionHandler so exception mapping is tested.
 */