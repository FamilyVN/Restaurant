package com.tuananh.restaurant.manager.model.setting;

/**
 * Created by framgia on 28/10/2016.
 */
public class Setting {
    private int mId;
    private String mName;
    private int mIdImage;

    public Setting(int id, String name, int idImage) {
        mId = id;
        mName = name;
        mIdImage = idImage;
    }

    public Setting(String name, int idImage) {
        mName = name;
        mIdImage = idImage;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getIdImage() {
        return mIdImage;
    }
}
