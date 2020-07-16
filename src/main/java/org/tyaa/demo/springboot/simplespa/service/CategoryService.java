package org.tyaa.demo.springboot.simplespa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.demo.springboot.simplespa.dao.CategoryHibernateDAO;
import org.tyaa.demo.springboot.simplespa.entity.Category;
import org.tyaa.demo.springboot.simplespa.model.ResponseModel;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryHibernateDAO dao;

    public ResponseModel create(Category category) {
        dao.save(category);
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .message(String.format("Category %s Created", category.getName()))
                .build();
    }

    public ResponseModel getAll() {
        List<Category> categories = dao.findAll();
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .data(categories)
                .build();
    }

    public ResponseModel delete(Long id) {
        Optional<Category> categoryOptional = dao.findById(id);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            dao.delete(category);
            return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .message(String.format("Category #%s Deleted", category.getName()))
                .build();
        } else {
            return ResponseModel.builder()
                .status(ResponseModel.FAIL_STATUS)
                .message(String.format("Category #%d Not Found", id))
                .build();
        }
    }
}
