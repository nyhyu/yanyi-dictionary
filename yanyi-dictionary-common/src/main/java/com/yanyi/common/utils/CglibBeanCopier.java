package com.yanyi.common.utils;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import java.util.HashMap;
import java.util.Map;

public class CglibBeanCopier {

    public static Map<String, BeanCopier> beanCopierMap = new HashMap();

    public CglibBeanCopier() {
    }

    public static void copyProperties(Object source, Object target) {
        String key = generateKey(source.getClass(), target.getClass());
        BeanCopier beanCopier = null;
        if(beanCopierMap.containsKey(key)) {
            beanCopier = (BeanCopier)beanCopierMap.get(key);
        } else {
            beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(key, beanCopier);
        }

        beanCopier.copy(source, target, (Converter)null);
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
}
