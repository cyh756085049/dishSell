package com.edu.cn.dishsell.exception;

import com.edu.cn.dishsell.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author: cyh
 * @Date: 2019-11-10 18:38
 * @Description: 异常类
 **/
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }



}
