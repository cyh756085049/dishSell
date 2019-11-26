package com.edu.cn.dishsell.service.impl;

import com.edu.cn.dishsell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        System.out.println(productCategory);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategories = categoryService.findAll();
        System.out.println(productCategories);
        System.out.println(productCategories.size());
        Assert.assertNotEquals(0, productCategories.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> categoryTypeIn = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
        System.out.println(categoryTypeIn);
        Assert.assertNotEquals(0, categoryTypeIn.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("凉菜");
        productCategory.setCategoryType(12);
        ProductCategory category = categoryService.save(productCategory);
        System.out.println(category);
        Assert.assertNotNull(category);
    }
}
