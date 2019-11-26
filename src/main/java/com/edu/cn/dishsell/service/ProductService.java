package com.edu.cn.dishsell.service;

import com.edu.cn.dishsell.dataobject.ProductInfo;
import com.edu.cn.dishsell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
/**
 * @Author: cyh
 * @Date: 2019-11-10 18:50
 * @Description: 商品类service接口
 **/
public interface ProductService {

    /** 查询商品 */
    ProductInfo findOne(String productId);

    /** 查询所有在架商品列表 */
    List<ProductInfo> findUpAll();

    /** 查询所有商品列表带分页 */
    Page<ProductInfo> findAll(Pageable pageable);

    /** 添加商品 */
    ProductInfo save(ProductInfo productInfo);

    /** 加库存 */
    void increaseStock(List<CartDTO> cartDTOList);

    /** 减库存 */
    void decreaseStock(List<CartDTO> cartDTOList);

    /** 上架 */
    ProductInfo onSale(String productId);

    /** 下架 */
    ProductInfo offSale(String productId);
}
