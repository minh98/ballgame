package com.example.minh98.project3;

/**
 * Created by minh98 on 22/03/2017.
 */

public class circle {
    int tamx,tamy,r,alpha;

    public circle(int tamx, int tamy, int r,int alpha) {
        this.tamx = tamx;
        this.tamy = tamy;
        this.r = r;
        this.alpha=alpha;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getTamx() {
        return tamx;
    }

    public void setTamx(int tamx) {
        this.tamx = tamx;
    }

    public int getTamy() {
        return tamy;
    }

    public void setTamy(int tamy) {
        this.tamy = tamy;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
