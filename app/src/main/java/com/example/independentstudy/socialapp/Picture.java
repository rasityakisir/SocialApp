package com.example.independentstudy.socialapp;

import android.media.Image;
import android.provider.MediaStore;


public class Picture {
    // private variables
    int _id;
    String _name;
    byte[] _image;

    // Empty constructor
    public Picture() {

    }

    // constructor
    public Picture(int keyId, String name, byte[] image) {
        this._id = keyId;
        this._name = name;
        this._image = image;

    }

    // constructor
    public Picture(String contactID, String name, byte[] image) {
        this._name = name;
        this._image = image;

    }

    // constructor
    public Picture(String name, byte[] image) {
        this._name = name;
        this._image = image;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int keyId) {
        this._id = keyId;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting phone number
    public byte[] getImage() {
        return this._image;
    }

    // setting phone number
    public void setImage(byte[] image) {
        this._image = image;
    }
}
