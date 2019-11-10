package com.leyou.item.controller;

import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.BrandService;
import com.leyou.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.saveBrand(brand, cids);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("bid/{bid}")
    public ResponseEntity queryByBrandId(@PathVariable("bid") Long bid) {
        List<Category> categories = brandService.queryByBrandId(bid);
        return ResponseEntity.ok(categories);
    }

    @PutMapping
    public ResponseEntity updateBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.updateBrand(brand,cids);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity deleteBrand(@RequestParam("bid") Long bid){
        System.out.println("bid in delete === " + bid);
        brandService.deleteBrand(bid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("delete2")
    public ResponseEntity deleteBrand2( Long bid){
        System.out.println("bid in delete2 === " + bid);
        brandService.deleteBrand(bid);
        return new ResponseEntity(HttpStatus.OK);
    }
}
