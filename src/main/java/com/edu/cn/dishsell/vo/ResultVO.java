package com.edu.cn.dishsell.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cyh
 * @Date: 2019-11-10 18:00
 * @Description: http请求返回的最外层对象
 **/
@Data
public class ResultVO<T> implements Serializable {

    // 使用快捷键control + command + i
    private static final long serialVersionUID = 5404115822959335572L;

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 */
    private T data;
}
