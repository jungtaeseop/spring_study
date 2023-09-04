package com.test.design_pattern_test.designpattern.singleton;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Settings settings = Settings.getInstance();

        Constructor<Settings> declaredConstructor = Settings.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Settings settings1 = declaredConstructor.newInstance();

        System.out.println(settings == settings1);


        SettingsEnum settingsEnum = SettingsEnum.INSTANCE;

        Constructor<SettingsEnum> declaredConstructor2 = SettingsEnum.class.getDeclaredConstructor();
        declaredConstructor2.setAccessible(true);
        SettingsEnum settingsEnum1 = declaredConstructor2.newInstance();

        System.out.println(settingsEnum == settingsEnum1);

    }
}
