package com.javiagd.nexmo.simplesms.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsError;
import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by javigd on 15/04/15.
 */
public class ConfigManager {

    private static final String PROPERTIES_FILENAME = "config.properties";
    private static ConfigManager config;
    private final Properties configProperties = new Properties();

    private ConfigManager(Context context) throws SimpleSmsException {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream in = assetManager.open(PROPERTIES_FILENAME);
            configProperties.load(in);
        } catch (IOException e) {
            throw new SimpleSmsException(SimpleSmsError.CLIENT_CONFIG);
        }
    }

    public static ConfigManager getInstance(Context context) throws SimpleSmsException {
        if(config == null) {
            config = new ConfigManager(context);
        }
        return config;
    }

    public String getProperty(String key) {
        return configProperties.getProperty(key);
    }
}
