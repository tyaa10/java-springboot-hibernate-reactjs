package org.tyaa.demo.springboot.simplespa.junit.service;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceTest {

    @BeforeAll
    static void setup() {
        System.out.println("CategoryService Unit Test Started");
    }

    @BeforeEach
    void init() {
        System.out.println("Test Case Started");
    }

    @Test
    void demoCase() {
        assertTrue(2 * 2 == 4);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Case Finished");
    }

    @AfterAll
    static void done() {
        System.out.println("CategoryService Unit Test Finished");
    }
}
