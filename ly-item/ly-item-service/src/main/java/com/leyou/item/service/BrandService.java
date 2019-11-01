package com.leyou.item.service;

import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    BrandMapper brandMapper;

    public List<Brand> queryByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        List<Brand> list = brandMapper.queryByPage(page, rows, sortBy, desc, key);
        return list;
    }

    public Integer queryForTotalCount(String key){
        Integer integer = brandMapper.queryForTotalCount(key);
        return integer;
    }
}
