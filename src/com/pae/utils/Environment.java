package com.pae.utils;

import java.io.InputStream;
import java.util.Properties;

public class Environment {
    private Properties prop;
    final String propFileName = "config.properties";

    public Environment() {
        initProperties();
    }

    private void initProperties() {
        this.prop = new Properties();

        InputStream inputStream = getClass().getClassLoader().
                getResourceAsStream(this.propFileName);

        if (inputStream!=null) {
            try {
            prop.load(inputStream);
            } catch (Exception ex) {
                System.out.println(ex.getStackTrace());
            }
        }
    }

    public String getEnv(String key) {
        return this.prop.getProperty(key);

    }
}
