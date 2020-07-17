package org.tyaa.demo.springboot.simplespa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tyaa.demo.springboot.simplespa.entity.Category;
import org.tyaa.demo.springboot.simplespa.model.CategoryModel;
import org.tyaa.demo.springboot.simplespa.model.ResponseModel;
import org.tyaa.demo.springboot.simplespa.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public ResponseEntity<ResponseModel> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<ResponseModel> create(@RequestBody CategoryModel category) {
        return new ResponseEntity<>(service.create(category), HttpStatus.CREATED);
    }
}