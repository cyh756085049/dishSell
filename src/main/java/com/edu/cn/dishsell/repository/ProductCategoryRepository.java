package com.edu.cn.dishsell.repository;

import com.edu.cn.dishsell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @Author: cyh
 * @Date: 2019-11-10 18:00
 * @Description: 类目类jpa接口
 **/
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
