package com.example.minh98.project3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;


/**
 * Created by minh98 on 18/03/2017.
 */

public class ActionBall {

    Core core;
    int w,h;
    int count;
    boolean isCollision=false;
     ArrayList<Balls> arrayList;
    Ball ball;
     long timeSleep;
    private boolean lr;

    public ActionBall(float a, float b, int e, int c, int d, Core s){

        w=c;
        h=d;
         arrayList=new ArrayList<>();
        count=0;
        this.core =s;
        ball=new Ball(a,b,e,w/153,h/118,-0.6f);
    }
        public void check(){
        for(Col aa: core.acCol.arrayList){
            int xdau=0;
            int xcuoi=aa.getX();
            int ydau=aa.getY();
            int ycuoi=ydau+aa.getH();
            int xdau2=aa.getX()+w/3;
            int xcuoi2=w;
            int ydau2=aa.getY();
            int ycuoi2=ycuoi;

            boolean b1 =checkCollision(xdau,xcuoi,ydau,ycuoi);
            boolean b2 =checkCollision(xdau2,xcuoi2,ydau2,ycuoi2);
            if(b1 || b2){ //neu bong cham vao cot
                isCollision=true;

                    if (ball.x>xcuoi && ball.x<xdau2){  //nam trong khoang left right
                        if(ball.y>ydau-ball.r && ball.y< ycuoi+ball.r) {
                            lr=true;
                            if(Math.abs(ball.vx)==w/153)//neu bong dap vao thanh (left right) thi tang gap doi vx
                                ball.vx*=2;
                            ball.vx = -ball.vx;
                            ball.x += ball.vx;
                            ball.vx/=2;
                        }
                    } else{// nam o hai ben
                        lr=false;
                        //if(Math.abs(ball.vx)==10)//neu ra khoi vung left right thi vx lai ve trang thai binh thuong

                        if(ball.vy>0){//neu qua bong dang di len
                            if(ball.y<ydau){//neu qua bong nam ben tren thanh hcn

                            }else{
                                if(ball.y>ycuoi){//qua bong nam ben duoi thanh hcn
                                    core.Switch("menu");
                                }
                            }

                        }
                    }


                ball.vy=0;
                return;
            }else{
                lr=false;
                isCollision=false;
            }
        }
    }
    public boolean checkCollision(int xdau,int xcuoi,int ytren,int yduoi){

        int tamx=(int)ball.x;
        int tamy=(int)ball.y;
        if(tamx<xdau)tamx=xdau;
        if(tamx>xcuoi)tamx=xcuoi;
        if(tamy<ytren)tamy=ytren;
        if(tamy>yduoi)tamy=yduoi;
        float dx=ball.x-tamx;
        float dy=ball.y-tamy;


        return dx*dx+dy*dy<=ball.r*ball.r;
    }

    private void addBalls(float x, float y) {
        arrayList.add(new Balls((int)x,(int)y));
    }

    private long now() {
        return System.currentTimeMillis();
    }

    public boolean left(float x){
        if(x<ball.x){
            return true;
        }
        return false;
    }
    public void drawBall(Canvas canvas){
        Paint p=new Paint();
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle( ball.x, ball.y, ball.r,p);


        drawBalls(canvas);

    }



    public void checkBalls(){
        if(arrayList.size()>ball.r-2){
            arrayList.remove(0);
        }
    }
    public void drawBalls(Canvas canvas){
        Paint p=new Paint();

        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setAlpha(200);
        for(int i=0;i<arrayList.size();i++){
            canvas.drawCircle(arrayList.get(i).getX(),arrayList.get(i).getY(),i,p);
        }
    }
//////TODO/////////////////////////////////////////////////////////////////////////************************
    public void updateBall() {

        if (ball.y < h) {
            if (ball.y < ball.r) {
                core.Switch("menu");
            }
            if (true) {


                if (!isCollision) {
                    ball.vy += ball.g;
                    ball.y -= ball.vy;
                }
                timeSleep = now();
                count++;
                if (count > 1) {

                    addBalls(ball.x, ball.y);
                    count = 0;
                }
                if(!lr)
                ball.x += ball.vx;
            }
        }


        if(ball.y>h-ball.r){
            core.Switch("menu");
        }
        if (ball.x < ball.r) {
            ball.vx = -ball.vx;
            ball.x = ball.r;
        }
        if (ball.x > w - ball.r){
            ball.vx = -ball.vx;
            ball.x = w - ball.r;
        }
        if(ball.y>h-ball.r)
            ball.y=h-ball.r;

    }

    public void checkBall() {
        check();
        checkBalls();
    }

    public void actionBall() {
        updateBall();
        checkBall();
    }
}
