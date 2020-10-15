package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> queryListByParent(Long id){
        Category category = new Category();
        category.setParentId(id);
        List<Category> select = this.categoryMapper.select(category);
        return select;
    }

    public List<String> queryNameByIds(List<Long> ids){
        return categoryMapper.selectByIdList(ids).stream().map(Category::getName).collect(Collectors.toList());
    }
}
