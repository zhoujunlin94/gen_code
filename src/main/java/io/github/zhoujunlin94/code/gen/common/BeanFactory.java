package io.github.zhoujunlin94.code.gen.common;

import cn.hutool.core.lang.Singleton;

/**
 * @author zhoujunlin
 * @date 2024/9/17 21:46
 */
public class BeanFactory {

    public static <T> T getBean(Class<T> clazz) {
        return Singleton.get(clazz);
    }

}
