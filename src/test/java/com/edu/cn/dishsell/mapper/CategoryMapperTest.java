package com.edu.cn.dishsell.mapper;

import com.edu.cn.dishsell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "最好吃的");
        map.put("category_type", 101);
        int result = categoryMapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory  = new ProductCategory();
        productCategory.setCategoryName("奶茶系列");
        productCategory.setCategoryType(102);
        int result = categoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = categoryMapper.findByCategoryType(102);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> productCategoryList = categoryMapper.findByCategoryName("甜品");
        Assert.assertEquals(2, productCategoryList.size());
    }

    @Test
    public void updateByCategoryType() {
        int result = categoryMapper.updateByCategoryType("奶茶系列", 101);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("奶茶");
        productCategory.setCategoryType(102);
        int result = categoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() {
        int result = categoryMapper.deleteByCategoryType(102);
        Assert.assertEquals(1, result);
    }
}
