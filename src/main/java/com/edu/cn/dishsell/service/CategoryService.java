package com.edu.cn.dishsell.service;

import com.edu.cn.dishsell.dataobject.ProductCategory;

import java.util.List;
/**
 * @Author: cyh
 * @Date: 2019-11-10 18:16
 * @Description: 类目类service接口
 **/
public interface CategoryService {

    /** 查询商品类目 */
    ProductCategory findOne(Integer categoryId);

    /** 查询所有商品类目列表 */
    List<ProductCategory> findAll();

    /** 通过类目类型查询商品类目列表 */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /** 保存或更新类目 */
    ProductCategory save(ProductCategory productCategory);

}
