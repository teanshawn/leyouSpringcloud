package com.leyou.item.controller;

import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import com.leyou.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping("list")
    public PageResult<Brand> query(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key) {

        key = ("").equals(key) || key == null ? "" : "%" + key + "%";
        page = (page - 1) * rows;

        List<Brand> list = brandService.queryByPage(page, rows, sortBy, desc, key);
        Integer total = brandService.queryForTotalCount(key);
        int totalPages = (total - 1) / rows + 1;
        PageResult<Brand> result = new PageResult<Brand>(total, totalPages, list);
        return result;
    }
}
