package com.edu.cn.dishsell.utils;

import java.util.Random;
/**
 * @Author: cyh
 * @Date: 2019-11-17 17:11
 * @Description: 生成唯一的主键
 *               格式：时间+随机数
 **/
public class KeyUtil {

    //多线程可能会重复，添加synchronized关键字
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        //随机生成6位随机数
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);

    }

}
