package com.good.frame.listener;

import com.good.frame.annotation.MyConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

@Component
public class ConfigListener implements ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext applicationContext;

    private Properties configProperties = new Properties();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        applicationContext = contextRefreshedEvent.getApplicationContext();
        try {
            handleMyConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleMyConfiguration() throws Exception {
        /*在容器中搜索被@MyConfiguration修饰的所有类名*/
        String[] configNames = applicationContext.getBeanNamesForAnnotation(MyConfiguration.class);
        if (StringUtils.isEmpty(configNames)) {
            return;
        }
        Object[] configObjects = new Object[configNames.length];

        /*在spring容器中获取类名对应的对象*/
        for (int i = 0; i < configNames.length; i++) {
            configObjects[i] = applicationContext.getBean(configNames[i]);
        }
        for (Object object : configObjects) {
            Class<?> clazz = object.getClass();
            MyConfiguration myConfiguration = clazz.getAnnotation(MyConfiguration.class);
            /*获取注解的value值并获取输入流*/
            String path = "/" + myConfiguration.value();
            InputStream inputStream = clazz.getResourceAsStream(path.replaceAll("/+", "/"));
            if (null == inputStream) {
                System.out.println("未找到资源:" + path);
                return;
            }
            configProperties.load(inputStream);

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);//访问private字段
                field.set(object, configProperties.getProperty(field.getName()));//获取字段名并查询字段名在配置文件中对应的value，再设置到字段中去
            }
        }
    }
}
