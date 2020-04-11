package com.app;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Config {
    private static HashMap<String, String> configData = new HashMap<String, String>();

    // Initialize the configurations and set the values
    public static void initialize() {
        configData.put("title", "سیستم مدیریت بانک");
        configData.put("width", "1000");
        configData.put("height", "700");
    }

    // Get the values of the configurations
    public static String getConfig(String key) {
        return configData.get(key);
    }
}
