package com.itheima.mm.utils;

/**
 * @Author: wriprin
 * @Date: 2021/10/26/026 18:34:27
 * @Version 1.0
 */
public class IntegerUtils {
    /**
     * 判断前端传入的值类型，并转为 Integer
     * @param o
     * @return
     */
    public static Integer getInteger(Object o) {
        Integer result = null;
        if (o instanceof Integer) {
            result = (Integer) o;
        } else {
            result = Integer.valueOf((String) o);
        }
        return result;
    }

}
