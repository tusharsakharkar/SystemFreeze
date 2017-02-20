package com.example.tusharking.systemfreeze;

import android.graphics.drawable.Drawable;

public class AppList {

    private String name;
    Drawable icon;

    public AppList(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }
    public String toString() {
        return (this.getName());
    }
}
