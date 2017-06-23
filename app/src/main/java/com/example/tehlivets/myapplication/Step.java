package com.example.tehlivets.myapplication;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

public class Step {

    private int number;
    private String description;
    private Bitmap img = null;

    public Step(int _number, String _description) {
        this.number = _number;
        this.description = _description;
    }

    public Step(int _number, String _description, Bitmap _img){
        this.number = _number;
        this.description = _description;
        this.img = _img;
    }

    public Step(int _number){
        this.number = _number;
    }

    public byte[] getBitmapAsByteArray() {
        if (img!=null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            return outputStream.toByteArray();
        }else return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public void update(int _number){
        this.number = _number;
    }

    public String toString(){
        return this.number + ". " + this.description;
    }

    public int getStepNumber() {
        return this.number;
    }

    public String getDescription() {
        return description;
    }

    public boolean isBitmap() {
        return img == null ? false : true;
    }

    public Bitmap getBitmap() {
        return img;
    }
}
