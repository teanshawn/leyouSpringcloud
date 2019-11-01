package com.leyou.item.mapper;


import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrandMapper {

    List<Brand> queryByPage(@Param("page") Integer page, @Param("rows") Integer rows, @Param("sortBy") String sortBy, @Param("desc") Boolean desc, @Param("key") String key);

    Integer queryForTotalCount(@Param("key") String key);
}
