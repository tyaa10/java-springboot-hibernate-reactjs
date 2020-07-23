package org.tyaa.demo.springboot.simplespa.junit.service;


import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tyaa.demo.springboot.simplespa.dao.CategoryHibernateDAO;
import org.tyaa.demo.springboot.simplespa.entity.Category;
import org.tyaa.demo.springboot.simplespa.model.CategoryModel;
import org.tyaa.demo.springboot.simplespa.model.ResponseModel;
import org.tyaa.demo.springboot.simplespa.service.CategoryService;
import org.tyaa.demo.springboot.simplespa.service.interfaces.ICategoryService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryHibernateDAO categoryDAO;

    @Mock
    private ICategoryService categoryServiceMock;

    @InjectMocks
    private CategoryService categoryService;

    ArgumentCaptor<Category> categoryArgument =
            ArgumentCaptor.forClass(Category.class);

    @BeforeAll
    static void setup() {
        System.out.println("CategoryService Unit Test Started");
    }

    @BeforeEach
    void init() {
        // System.out.println("Test Case Started");
    }

    @Test
    void demoCase() {
        assertTrue(2 * 2 == 4);
    }

    @Test
    void shouldCreatedCategorySuccessfully() {
        final CategoryModel categoryModel =
                CategoryModel.builder()
                .name("test category 1")
                .build();
        ResponseModel responseModel =
                categoryService.create(categoryModel);
        assertNotNull(responseModel);
        assertEquals(ResponseModel.SUCCESS_STATUS, responseModel.getStatus());
        verify(categoryDAO, atLeast(1))
                .save(categoryArgument.capture());
    }

    @Test
    void shouldReturnGetAll() {
        doReturn(
            ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .data(Arrays.asList(new CategoryModel[] {
                    new CategoryModel(1L, "c1"),
                    new CategoryModel(1L, "c2"),
                    new CategoryModel(1L, "c3")
                }))
                .build()
        ).when(categoryServiceMock)
        .getAll();
        ResponseModel responseModel =
            categoryServiceMock.getAll();
        assertNotNull(responseModel);
        assertNotNull(responseModel.getData());
        assertTrue(((List)responseModel.getData()).size() == 3);
    }

    @AfterEach
    void tearDown() {
        // System.out.println("Test Case Finished");
    }

    @Test
    void shouldThrowConstraintException() {
        final String tooLongCategoryName =
            "test category 1234567890 1234567890 1234567890";
        final Category tooLongNameCategory =
            Category.builder().name(tooLongCategoryName).build();
        given(categoryDAO.save(tooLongNameCategory))
            .willThrow(new IllegalArgumentException());
        try {
            final CategoryModel categoryModel =
                CategoryModel.builder()
                    .name("test category 1234567890 1234567890 1234567890")
                    .build();
            categoryService.create(categoryModel);
            fail("Should throw an IllegalArgumentException");
        } catch (IllegalArgumentException ex) { }
        then(categoryDAO)
            .should(atLeastOnce())
            .save(categoryArgument.capture());
    }

    @Test
    // @ExtendWith({SystemOutResource.class, SystemOutResourceParameterResolver.class})
    @ExtendWith(SystemOutResource.class)
    void checkSuccessLogging(/* SystemOutResource sysOut */) {
        final CategoryModel categoryModel =
            CategoryModel.builder()
                .name("test category 1")
                .build();
        categoryService.create(categoryModel);
        assertEquals(
            String.format("Category %s Created", categoryModel.getName().trim()),
                SystemOutResource.outContent.toString().trim()
        );
    }

    @AfterAll
    static void done() {
        System.out.println("CategoryService Unit Test Finished");
    }
}
