package com.example.independentstudy.socialapp;

import android.media.Image;
import android.provider.MediaStore;


public class Video {

    private int _id;
    private String _video;

    public Video (String video){
        this._video = video;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_video (String video){
        this._video = video;
    }

    public int get_id() {
        return _id;
    }

    public String get_video(){
        return _video;
    }

}
