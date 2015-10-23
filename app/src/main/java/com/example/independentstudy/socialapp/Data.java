package com.example.independentstudy.socialapp;

import android.media.Image;
import android.provider.MediaStore;

public class Data {

    private int _id;
    private String _text;

    public Data(String text){
        this._text = text;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_text (String text){
        this._text = text;
    }

    public int get_id() {
        return _id;
    }

    public String get_text() {
        return _text;
    }


}
