package com.leyou.item.controller;

import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.BrandService;
import com.leyou.pojo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
@Api(tags = "品牌相关")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping("list")
    @ApiOperation("品牌分页查询")
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
    @ApiOperation("添加品牌")
    public ResponseEntity<Void> saveBrand(@ApiParam(name = "brand",value = "品牌实体类",required = true)Brand brand,
                                          @ApiParam(name = "cids",value = "品类id集合",required = true)@RequestParam("cids") List<Long> cids) {
        brandService.saveBrand(brand, cids);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("bid/{bid}")
    @ApiOperation("根据id查询类别")
    public ResponseEntity queryByBrandId(@ApiParam(name = "bid",value = "品牌id",required = true)@PathVariable("bid") Long bid) {
        List<Category> categories = brandService.queryByBrandId(bid);
        return ResponseEntity.ok(categories);
    }

    @PutMapping
    @ApiOperation("更新品牌")
    public ResponseEntity updateBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.updateBrand(brand,cids);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("delete")
    @ApiOperation("根据id删除品牌")
    public ResponseEntity deleteBrand(@ApiParam(name = "bid",value = "品牌id",required = true)@RequestParam("bid") Long bid){
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
