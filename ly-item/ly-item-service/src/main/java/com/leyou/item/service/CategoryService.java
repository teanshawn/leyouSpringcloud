package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> queryListByParent(Long id){
        Category category = new Category();
        category.setId(id);
        List<Category> select = this.categoryMapper.select(category);
        return select;
    }
}
