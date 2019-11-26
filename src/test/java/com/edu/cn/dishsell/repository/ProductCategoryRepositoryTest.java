package com.edu.cn.dishsell.repository;

import com.edu.cn.dishsell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
/**
 * @Author: cyh
 * @Date: 2019-11-10 18:00
 * @Description: 类目类测试
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    /**
     * 通过ID查询类目信息
     */
    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findById(1).get();
        System.out.println(productCategory);
    }

    /**
     * 添加一条类目信息
     */
    @Test
    @Transactional //此处事务的作用是不把测试的数据加入到数据库
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("最新");
        productCategory.setCategoryType(6);
        ProductCategory save = repository.save(productCategory);
        System.out.println(save);
        Assert.assertNotEquals(null, save);
    }

    /**
     * 通过ID编辑类目信息
     */
    @Test
    public void editTest() {
        ProductCategory productCategory = repository.findById(2).orElse(null);
        productCategory.setCategoryType(5);
        ProductCategory save = repository.save(productCategory);
        System.out.println(save);
        Assert.assertNotEquals(null, save);
    }

    @Test
    public void findAllTest() {
        List<ProductCategory> repositoryAll = repository.findAll();
        System.out.println(repositoryAll);
    }

    /**
     *
     */
    @Test
    public void findByCategoryTypeTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<ProductCategory> results = repository.findByCategoryTypeIn(list);
        System.out.println(results);
        System.out.println(results.size());
        Assert.assertNotEquals(0, results.size());
    }

}
