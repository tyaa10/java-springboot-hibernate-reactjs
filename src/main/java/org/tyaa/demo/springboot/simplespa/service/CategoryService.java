package org.tyaa.demo.springboot.simplespa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.demo.springboot.simplespa.dao.CategoryHibernateDAO;
import org.tyaa.demo.springboot.simplespa.entity.Category;
import org.tyaa.demo.springboot.simplespa.model.ResponseModel;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryHibernateDAO dao;

    public ResponseModel create(Category category) {
        dao.save(category);
        return ResponseModel.builder()
                .status("success")
                .message(String.format("Category %s created", category.getName()))
                .build();
    }

    public ResponseModel getAll() {
        List<Category> categories = dao.findAll();
        return ResponseModel.builder()
                .status("success")
                .data(categories)
                .build();
    }
}
