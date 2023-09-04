package com.test.design_pattern_test.designpattern.singleton;

public class Settings {

    private Settings() {}

    private static class SettingsHolder{
        private static final Settings INSTANCE = new Settings();
    }

    public static Settings getInstance() {
       return SettingsHolder.INSTANCE;
    }
}
