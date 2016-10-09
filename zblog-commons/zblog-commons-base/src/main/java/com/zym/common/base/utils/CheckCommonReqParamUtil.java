package com.zym.common.base.utils;

import com.zym.common.base.model.base.CommonReqParam;
import com.zym.common.base.statuscode.GlobalResultStatus;
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
        if (bean == null) {
            return GlobalResultStatus.ERROR;
        }
        try {
            Class clazz = bean.getClass();
            /*
            * 得到类中的所有属性集合
            */
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true); //设置些属性是可以访问的
                String fieldName = field.getName();//得到此属性名字
                Object value = field.get(bean);//得到此属性的值
                String type = field.getGenericType().toString();//得到此属性的类型
                System.out.println("name:" + fieldName + ", value = " + value + ", type = " + type);

                if ("class java.lang.String".equals(type)) {
                    if (StringUtil.isEmpty(String.valueOf(value))) {
                        if (fieldName.equals("appKey")) {
                            return GlobalResultStatus.PARAM_APPKEY_MISSING;
                        }else if (fieldName.equals("accessToken")) {
                            return GlobalResultStatus.PARAM_ACCESSTOKEN_MISSING;
                        }
                    }
                } else if (type.endsWith("int")) {

                } else if (type.endsWith("Integer")) {
                    if (value == null) {
                        if (fieldName.equals("source")) {
                            return GlobalResultStatus.PARAM_SOURCE_MISSING;
                        }
                    }
                } else if (type.endsWith("Long")) {
                    if (value == null) {
                        if (fieldName.equals("timestamp")) {
                            return GlobalResultStatus.PARAM_TIMESTAMP_MISSING;
                        }
                    }
                } else if (type.endsWith("Double")) {

                } else {

                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return GlobalResultStatus.SUCCESS;
    }


    public static void main(String[] args) {
        CheckCommonReqParamUtil.getInstance().checkBean(new CommonReqParam());
    }
}
