package com.example.minh98.project3;

/**
 * Created by minh98 on 18/03/2017.
 */

public class Ball {
    float x, y, r, vx, vy, g;

    public Ball(float x, float y, float r, float vx, float vy, float g) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
        this.g = g;
    }
    public void Ball(float x, float y, float r, float vx, float vy, float g) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;

        this.vy = vy;
        this.g = g;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }
}