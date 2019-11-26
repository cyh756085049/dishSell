package com.edu.cn.dishsell.utils;

import com.edu.cn.dishsell.enums.CodeEnum;

/**
 * @Author: cyh
 * @Date: 2019-11-20 17:04
 * @Description: 枚举工具类
 **/
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
