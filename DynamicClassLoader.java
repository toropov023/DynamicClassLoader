package com.copperboard.DynamicClassLoader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Author: toropov023
 * Date: 09/01/2015
 */
public class DynamicClassLoader {

    /**
     * Load the specified class into the runtime
     * @param path Path to the .jar <i>(i.e: "./resources/Gyro.jar")</i>
     * @param mainClass Main class path; <b>must</b> implement {@link com.copperboard.DynamicClassLoader.DynamicClass} <i>(i.e.: "org.raiderrobotics.Plugin")</i>
     * @return An instance of {@link com.copperboard.DynamicClassLoader.DynamicClass}
     * @throws Throwable
     */
    public static DynamicClass load(String path, String mainClass) throws Throwable{
        File authorizedJarFile = new File(path);
        ClassLoader authorizedLoader = URLClassLoader.newInstance(new URL[] { authorizedJarFile.toURL() });
        DynamicClass authorizedPlugin = (DynamicClass) authorizedLoader.loadClass(mainClass).newInstance();
        authorizedPlugin.onEnable();
        authorizedPlugin.path = path;
        authorizedPlugin.mainClass = mainClass;

        return authorizedPlugin;
    }

    /**
     * Reload the DynamicClass instance
     * @param dynamicClass DynamicClass instance to reload
     * @return A new instance of {@link com.copperboard.DynamicClassLoader.DynamicClass} (see: {@link #load(String, String)})
     * @throws Throwable
     */
    public static DynamicClass reload(DynamicClass dynamicClass) throws Throwable{
        dynamicClass.onDisable();

        return load(dynamicClass.path, dynamicClass.mainClass);
    }
}
