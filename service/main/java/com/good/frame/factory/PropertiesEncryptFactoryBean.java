package com.good.frame.factory;

import com.good.frame.util.DESUtil;
import org.springframework.beans.factory.FactoryBean;

import java.util.Properties;

public class PropertiesEncryptFactoryBean implements FactoryBean {
    private Properties properties;
    private static String KEY = "river";

    public Object getObject() throws Exception {
        return getProperties();
    }

    public Class getObjectType() {
        return Properties.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties inProperties) {
        this.properties = inProperties;
        properties.put("user", decrypt(properties.getProperty("user")));
        properties.put("password", decrypt(properties.getProperty("password")));
    }

    /** 解密 */
    private static String decrypt(String encryptedData) {
        return DESUtil.getInstance().decode(encryptedData, KEY);
    }
}
