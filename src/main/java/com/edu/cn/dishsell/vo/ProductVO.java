package com.edu.cn.dishsell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: cyh
 * @Date: 2019-11-10 18:00
 * @Description: 前端商品展示类(包含类目)
 **/
@Data
public class ProductVO {

    /** 类目名称 */
    @JsonProperty("name")
    private String categoryName;

    /** 类目编号 */
    @JsonProperty("type")
    private Integer categoryType;

    /** 商品数据 */
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
