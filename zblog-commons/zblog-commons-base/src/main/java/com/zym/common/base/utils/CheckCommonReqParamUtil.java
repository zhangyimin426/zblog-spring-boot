package com.zym.common.base.utils;

import com.zym.common.base.model.base.CommonReqParam;
import com.zym.common.base.statuscode.ResultStatus;

import java.lang.reflect.Field;

/**
 * 检测公共参数工具
 * @author Gavin
 * @date 2016-09-30
 */
public class CheckCommonReqParamUtil<T> {

    private static CheckCommonReqParamUtil instance = null;

    public static CheckCommonReqParamUtil getInstance() {
        if (instance == null) {
            instance = new CheckCommonReqParamUtil();
        }
        return instance;
    }

    private CheckCommonReqParamUtil() {

    }

    public ResultStatus checkBean(T bean) {

        try {
            Class clazz = bean.getClass();
            /*
            * 得到类中的所有属性集合
            */
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true); //设置些属性是可以访问的
                Object value = field.get(bean);//得到此属性的值
                String type = field.getGenericType().toString();//得到此属性的类型
                System.out.println("name:" + field.getName() + ", value = " + value + ", type = " + type);
                if ("class java.lang.String".equals(type)) {
                    if (StringUtil.isEmpty(String.valueOf(value))) {

                    }
                } else if (type.endsWith("int") || type.endsWith("Integer")) {
                    System.out.println(field.getType() + "\t是int");
                    field.set(bean, 12);       //给属性设值
                } else {
                    System.out.println(field.getType() + "\t");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void main(String[] args) {
        CheckCommonReqParamUtil.getInstance().checkBean(new CommonReqParam());
    }
}
