package com.leyou.item.service;

import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandService {

    @Autowired
    BrandMapper brandMapper;

    public List<Brand> queryByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        List<Brand> list = brandMapper.queryByPage(page, rows, sortBy, desc, key);
        return list;
    }

    public Integer queryForTotalCount(String key) {
        Integer integer = brandMapper.queryForTotalCount(key);
        return integer;
    }

    public void saveBrand(Brand brand, List<Long> cids) {
        brandMapper.insertSelective(brand);
        cids.forEach((cid) -> this.brandMapper.insertCategoryBrand(cid, brand.getId()));
    }

    public List<Category> queryByBrandId(Long bid) {
        return this.brandMapper.queryByBrandId(bid);
    }

    public void updateBrand(Brand brand, List<Long> cids) {
        brandMapper.updateByPrimaryKeySelective(brand);
        brandMapper.deleteCategoryBrand(brand.getId());
        cids.forEach((cid) -> brandMapper.insertCategoryBrand(cid, brand.getId()));
    }

    public void deleteBrand(Long bid) {
        brandMapper.deleteCategoryBrand(bid);
        Brand brand = new Brand();
        brand.setId(bid);
        brandMapper.deleteByPrimaryKey(bid);
    }

    public List<Brand> queryBrandByBrandIds(List<Long> ids) {
        return this.brandMapper.selectByIdList(ids);
    }
}
