package com.edu.cn.dishsell.service.impl;

import com.edu.cn.dishsell.dataobject.ProductInfo;
import com.edu.cn.dishsell.enums.ProductStatusEnum;
import com.edu.cn.dishsell.repository.ProductInfoRepository;
//import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("1001");
        System.out.println(productInfo);
        Assert.assertEquals("1001", productInfo.getProductId());
    }

    /** 查询上架的所有商品 */
    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
        System.out.println(productInfoList);
        System.out.println(productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<ProductInfo> productInfos = productService.findAll(pageRequest);
        System.out.println(productInfos.getTotalElements());
        System.out.println(productInfos.getTotalPages());
        Assert.assertNotEquals(0, productInfos.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1004");
        productInfo.setProductName("德芙巧克力");
        productInfo.setProductPrice(new BigDecimal(6));
        productInfo.setProductStock(50);
        productInfo.setProductDescription("很甜的");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(5);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);

    }

    @Test
    public void onSale() {
        ProductInfo productInfo = productService.onSale("1004");
        Assert.assertEquals(ProductStatusEnum.UP, productInfo.getProductStatusEnum());
    }

    @Test
    public void offSale() {
        ProductInfo productInfo = productService.offSale("1004");
        Assert.assertEquals(ProductStatusEnum.DOWN, productInfo.getProductStatusEnum());
    }
}
