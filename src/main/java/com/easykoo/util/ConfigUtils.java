package com.easykoo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.Properties;


/**
 * @author Jesse
 * @version 03/12/2014
 */
public class ConfigUtils {
    private Log logger = LogFactory.getLog(getClass());

    private static String _configPath = "config.properties";
    private static ConfigUtils _instance = null;
    private Properties properties = new Properties();

    private ConfigUtils() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(_configPath);
            properties.load(inputStream);
            if (inputStream != null)
                inputStream.close();
        } catch (Exception e) {
            logger.error(_configPath + " not found or wrong format!");
        }
    }

    public static ConfigUtils getInstance() {
        if (_instance == null) {
            _instance = new ConfigUtils();
        }
        return _instance;
    }

    public ConfigUtils clone() {
        return getInstance();
    }

    public String getConfig(String key) {
        return properties.getProperty(key);
    }

    public void setValue(String key, String value) {
        try {
            OutputStream fos = new FileOutputStream(new File(_configPath));
            properties.setProperty(key, value);
            properties.store(fos, "Update '" + key + "' to '" + value + "'");
            logger.debug("Update '" + key + "' to '" + value + "' successfully!");
            fos.close();
        } catch (IOException e) {
            logger.error("Update '" + key + "' to '" + value + "' failed!");
        }
    }

    public String[] getNoNeedFilterUrl() {
        String arrayStr = properties.getProperty("no.need.filter");
        String[] array = arrayStr.split(",");
        return array;
    }

    public String[] getNoNeedLoginUrl() {
        String arrayStr = properties.getProperty("no.need.login");
        String[] array = arrayStr.split(",");
        return array;
    }

    public String getProductDirectory() {
        return properties.getProperty("product.directory");
    }

    public String getProductViewDirectory() {
        return properties.getProperty("product.view.directory");
    }

    public String getProductPreviewDirectory() {
        return properties.getProperty("product.preview.directory");
    }

    public String getPictureSizeLimit() {
        return properties.getProperty("picture.limit.size");
    }

    public int getViewPictureWidth() {
        return Integer.parseInt(properties.getProperty("view.picture.width"));
    }

    public Integer getPreviewPictureWidth() {
        return Integer.parseInt(properties.getProperty("preview.picture.width"));
    }
}