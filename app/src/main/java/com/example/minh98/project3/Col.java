package com.example.minh98.project3;

/**
 * Created by minh98 on 18/03/2017.
 */

public class Col {
    int x,y,h;

    public Col(int x, int y,int h) {
        this.x = x;
        this.y = y;
        this.h=h;
    }

    public int getX() {
        return x;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
