package com.clouddo.commons.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zhongming
 * @since 3.0
 * 2018/4/27下午4:39
 */
public class ApplicationContextRegister implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextRegister.class);

    //spring上下文
    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("ApplicationContext registed-->{}", applicationContext);
        ApplicationContextRegister.applicationContext = applicationContext;
    }

    /**
     * 通过名字获取bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }
}
