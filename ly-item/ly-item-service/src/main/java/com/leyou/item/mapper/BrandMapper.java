package com.leyou.item.mapper;


import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BrandMapper extends tk.mybatis.mapper.common.Mapper<Brand> {

    List<Brand> queryByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);
//    List<Brand> queryByPage(@Param("page") Integer page, @Param("rows") Integer rows, @Param("sortBy") String sortBy, @Param("desc") Boolean desc, @Param("key") String key);

    Integer queryForTotalCount(@Param("key") String key);

    @Insert("insert into tb_category_brand values(#{cid},#{bid})")
    int insertCategoryBrand(Long cid,Long bid);

    @Delete("delete from tb_category_brand where brand_id = #{bid}")
    int deleteCategoryBrand(Long bid);

    @Select("SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id = #{bid})")
    List<Category> queryByBrandId(Long bid);
}
