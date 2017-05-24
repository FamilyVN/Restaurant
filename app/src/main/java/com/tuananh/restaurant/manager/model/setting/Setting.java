package com.tuananh.restaurant.manager.model.setting;

/**
 * Created by framgia on 28/10/2016.
 */
public class Setting {
    private int mIdSettings;
    private String mNameSettings;
    private int mIdImageSettings;

    public Setting(int idSettings, String nameSettings, int idImageSettings) {
        mIdSettings = idSettings;
        mNameSettings = nameSettings;
        mIdImageSettings = idImageSettings;
    }

    public Setting(String nameSettings, int idImageSettings) {
        mNameSettings = nameSettings;
        mIdImageSettings = idImageSettings;
    }

    public int getIdSettings() {
        return mIdSettings;
    }

    public String getNameSettings() {
        return mNameSettings;
    }

    public int getIdImageSettings() {
        return mIdImageSettings;
    }
}
