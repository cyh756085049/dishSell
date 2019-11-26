package com.edu.cn.dishsell.repository;

import com.edu.cn.dishsell.dataobject.ProductInfo;
import com.edu.cn.dishsell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1001");
        productInfo.setProductName("蜂蜜柚子茶");
        productInfo.setProductPrice(new BigDecimal(7));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的饮料");
        productInfo.setProductIcon("http://xxxxx1.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(2);
        ProductInfo result = repository.save(productInfo);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        System.out.println(productInfoList);
        System.out.println(productInfoList.size());
        Assert.assertNotEquals(0, productInfoList.size());
    }

}
