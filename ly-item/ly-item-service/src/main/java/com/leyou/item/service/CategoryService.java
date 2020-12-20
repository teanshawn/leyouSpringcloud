package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    /**
     * 根据分类id集合查询分类信息
     * @param ids
     * @return
     */
    public List<Category> queryCategoryByIds(List<Long> ids) {
        return this.categoryMapper.selectByIdList(ids);
    }

    /**
     * 根据cid3查询其所有层级分类
     * @param id
     * @return
     */
    public List<Category> queryAllCategoryLevelByCid3(Long id) {
        List<Category> categoryList = new ArrayList<>();
        Category category = this.categoryMapper.selectByPrimaryKey(id);
        while (category.getParentId() != 0){
            categoryList.add(category);
            category = this.categoryMapper.selectByPrimaryKey(category.getParentId());
        }
        categoryList.add(category);
        return categoryList;
    }
}
