package com.ghtk.recycleview.object;

/**
 * Created by SonNM on 6/5/2022.
 */

public class ItemObject {
    private String mName = "";
    private int mColor ;
    private boolean isSelected = false ;



    public ItemObject(String mName, int mColor) {
        this.mName = mName;
        this.mColor = mColor;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
