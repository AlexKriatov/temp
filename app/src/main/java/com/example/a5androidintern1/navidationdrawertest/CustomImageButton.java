package com.example.a5androidintern1.navidationdrawertest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by A5 Android Intern 1 on 13.12.2016.
 */

public class CustomImageButton extends ImageButton {
    public CustomImageButton(Context context) {
        super(context);
    }

    public CustomImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Boolean isClicked() {
        return isClicked;
    }
    public void setClicked(Boolean state){
        isClicked=state;
    }

    private Boolean isClicked = false;

}
