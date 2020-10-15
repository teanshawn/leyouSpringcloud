package com.leyou.item.service;

import com.leyou.item.mapper.SpecificationMapper;
import com.leyou.item.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    SpecificationMapper specificationMapper;

    public Specification queryById(Long id){
        Specification specification = specificationMapper.selectByPrimaryKey(id);
//        Example example = new Example(Specification.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andlike
        return specification;
    }

}
