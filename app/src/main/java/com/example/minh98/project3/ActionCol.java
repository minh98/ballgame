package com.example.minh98.project3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by minh98 on 18/03/2017.
 */

public class ActionCol {
    ArrayList<Col> arrayList;
    Core core;
    long sleepUpdata;

    Random random;
    int w, h;
    public ActionCol(Core s , int a, int b){
        core =s;
        w=a;
        h=b;
        arrayList=new ArrayList<>();


        random=new Random();

        sleepUpdata=now();


    }

    public void addCol(int x,int y,int h){
        arrayList.add(new Col(x,y,h));
    }


    private void checkSize() {
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).getY()>h){
                arrayList.remove(i);
            }
        }
    }
//////TODO ACTION COL/////////////////////////////////////////////////////////////
    public void updateCol() {
        if(arrayList.size()<1){
            addCol(random.nextInt(w/3*2),-h/12,h/12);
        }else if(arrayList.get(arrayList.size()-1).getY()>w/3){
            addCol(random.nextInt(w/3*2),-h/12,h/12);
        }

    }

    private long now() {
        return System.currentTimeMillis();
    }

    public void update(){
        for(Col aa: arrayList){
            aa.setY(aa.getY()+h/118/2);
        }
    }

    public void drawCol(Canvas canvas) {
        for(Col aa: arrayList){
            Paint p=new Paint();
            p.setColor(Color.BLACK);
            p.setStyle(Paint.Style.FILL);
            //thanh ben trai
            canvas.drawRect(0, aa.getY(), aa.getX(),aa.getY()+aa.getH(),p);
            for(int i=0;i<aa.getX()/20;i++){
                //gai to
                canvas.drawRect(i*20,aa.getY()+aa.getH(),i*20+h/118,aa.getY()+aa.getH()+h/118,p);
                //gai be
                canvas.drawRect(i*20+h/118/2-1,aa.getY()+aa.getH(),i*20+h/118/2+1,aa.getY()+aa.getH()+h/59,p);
            }
            //thanh ben phai
            canvas.drawRect(aa.getX()+w/3,aa.getY(),w,aa.getY()+aa.getH(),p);
            for(int i=0;i<(w-(aa.getX()+w/3))/20;i++){
                //gai to
                canvas.drawRect(aa.getX()+w/3+i*20,aa.getY()+aa.getH(),aa.getX()+w/3+i*20+h/118,aa.getY()+aa.getH()+h/118,p);
                //gai be
                canvas.drawRect(aa.getX()+w/3+i*20+h/118/2-1,aa.getY()+aa.getH(),aa.getX()+w/3+i*20+h/118/2+1,aa.getY()+aa.getH()+h/59,p);
            }
        }
    }

    public void actionCol() {
        updateCol();
        if(now()-sleepUpdata>35){
            update();
            sleepUpdata= now();
        }
        checkSize();

    }
}
