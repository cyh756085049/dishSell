package com.edu.cn.dishsell.controller;

import com.edu.cn.dishsell.dataobject.ProductCategory;
import com.edu.cn.dishsell.dataobject.ProductInfo;
import com.edu.cn.dishsell.service.CategoryService;
import com.edu.cn.dishsell.service.ProductService;
import com.edu.cn.dishsell.utils.ResultVOUtil;
import com.edu.cn.dishsell.vo.ProductInfoVO;
import com.edu.cn.dishsell.vo.ProductVO;
import com.edu.cn.dishsell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: cyh
 * @Date: 2019-11-10 18:00
 * @Description: 买家端商品
 **/
@RestController
@RequestMapping("buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 商品列表
     * @return
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123", condition = "#id.length() > 3", unless = "#result.getCode() != 0")
    public ResultVO list() {

        //一、查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //二、查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
//        //1、传统方法
//        for (ProductInfo productInfo : productInfoList) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }

        //2、精简方法（java8，lambda）
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //三、数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

}
