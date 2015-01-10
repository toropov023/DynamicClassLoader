package com.copperboard.DynamicClassLoader;

/**
 * Author: toropov023
 * Date: 09/01/2015
 */
public abstract class DynamicClass {
    protected String path;
    protected String mainClass;

    public abstract void onEnable();
    public abstract void onDisable();
}
