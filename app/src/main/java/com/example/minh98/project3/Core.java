package com.example.minh98.project3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by minh98 on 18/03/2017.
 */

public class Core extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    SurfaceHolder surfaceHolder;
    Paint p;
    Thread t;
    boolean running=true;
    boolean logo=true,menu,main;
    int w,h;
    long time;
    int diem=0;
    Canvas canvas;
    boolean start;

     boolean chet;

    ActionBall acBall;
    ActionCol acCol;
    private boolean touch;
    boolean run=false ;
    private float rCircle=1;
    private float RCircle;
    private float vr=1;
    private float gr=0.3f;
    ArrayList<circle> circle;
    private int alphaMenu;
    private int valphaMenu=5;

    public Core(Context context) {
        super(context);
        setFocusable(true);
        getHolder().addCallback(this);
        circle=new ArrayList<>();
        p=new Paint();
        t=new Thread(this);

        //khoi tao thoi gian de xet xem da den giai doan nao roi (logo,menu,main)

        time=now();


    }
    public void checkTime(){
        //neu ma thoi gian >5s sau khi start app thi chuyen tu giai doan logo sang giai doan menu

        if(now()-time>5000){
            menu=true;
            logo=false;
        }
    }

    public void drawLogo(Canvas canvas){
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        canvas.drawPaint(p);
        p.setColor(Color.RED);
        p.setTextSize(h/40);
        canvas.drawText("logo",44,44,p);
    }
    public void drawMenu(Canvas canvas){
        int tamx=w/2;
        int tamy=h/3;
        int r=w/4;
        RCircle=r;
        Paint p=new Paint();
        p.setFlags(Paint.ANTI_ALIAS_FLAG);
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);
        canvas.drawPaint(p);
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        canvas.drawCircle(tamx,tamy,r,p);
        drawCircle(canvas,tamx,tamy,r);
/////////////////////////////////////////////
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        Point a = new Point(tamx-r/3+r/10, tamy-r/3);
        Point b = new Point(tamx-r/3+r/10, tamy+r/3);
        Point c = new Point(tamx+r/3+r/10, tamy);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(tamx-r/3+r/10, tamy-r/3);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();

        canvas.drawPath(path, p);
        //////////////////////////////////
        //button
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);
        p.setTextSize(w/15);
        p.setTextAlign(Paint.Align.CENTER);
        canvas.drawCircle(w/2-w/4,h*2/3,w/15,p);
        canvas.drawText("A",w/2-w/4,h*2/3,p);
        canvas.drawCircle(w/2,h*2/3,w/15,p);
        canvas.drawText("B",w/2,h*2/3,p);
        canvas.drawCircle(w/2+w/4,h*2/3,w/15,p);
        canvas.drawText("C",w/2+w/4,h*2/3,p);

    }

    private void drawCircle(Canvas canvas,int tamx,int tamy,int r) {
        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setAlpha(alphaMenu);
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        canvas.drawCircle(tamx,tamy,rCircle,p);
        if(rCircle>=RCircle && circle.size()<=0){
            circle.add(new circle(tamx,tamy,r,255));
        }

        for(int i=0;i<circle.size();i++){
            p.setAlpha(circle.get(i).getAlpha());
            canvas.drawCircle(circle.get(i).getTamx(),circle.get(i).getTamy(),circle.get(i).getR(),p);
        }
    }

    public void drawMain(Canvas canvas){
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        canvas.drawPaint(p);
        p.setColor(Color.RED);
        p.setTextSize(44);
        if(touch){
            canvas.drawText("dang touch",66,66,p);
        }
        canvas.drawText("diem: "+diem,111,111,p);

        canvas.drawText("run is: " +(run?"true":"false"),111,111,p);
        canvas.drawText("width: "+w+" height: "+h,700,1000,p);
        // ve col,ball, gai
        acCol.drawCol(canvas);
        acBall.drawBall(canvas);
        drawGai(canvas);
    }
    public void drawGai(Canvas canvas){

        Paint p=new Paint();
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        for(int i=0;i<=w/17;i++){
            //top
            canvas.drawRect(i*w/17,0,i*w/17+w/77,h/118,p);
            canvas.drawRect(i*w/17+w/77/2-1,0,i*w/17+w/77/2+1,h/59,p);
            //bot
            canvas.drawRect(i*w/17,h-h/118,i*w/17+10,h,p);
            canvas.drawRect(i*w/17+w/77/2-1,h-h/59,i*w/17+w/77/2+1,h,p);
        }
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
       if(logo)
           drawLogo(canvas);
        if(menu)
            drawMenu(canvas);
        if(main)
            drawMain(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceHolder=holder;
        w=getWidth();
        h=getHeight();
        acBall=new ActionBall(w/2,h/2,h/79,w,h,this);
        acCol=new ActionCol(this,w,h);
        if(!start) {
            t.start();
            start=true;
        }else{
            t.run();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //Switch("logo");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:{
                if(main && !run){
                    run=true;
                }
                touch=true;
                diem++;
                acBall.ball.vy = h/118;
                acBall.timeSleep = now();
                if (menu) {
                    Switch("main");
                }
                if (acBall.left(event.getX()))
                    acBall.ball.vx = -w/153;
                else
                    acBall.ball.vx = w/153;

                    if (menu) start();


            }

            break;
        }
        return super.onTouchEvent(event);
    }

    public void Switch(String text){
        if(text.equals("logo")){
            menu=false;
            main=false;
            logo=true;
        }else{
            //neu menu
            if(text.equals("menu")){
                //do mo cua menu =255 giam dan ve 0(dam dan)
                alphaMenu=255;
                valphaMenu=-5;
                logo=false;
                main=false;
                menu=true;
                run=false;
            }else{
                //neu sai ( main)
                //khoi tao lai cai giai tri mac dinh cua ball
                acBall.ball.Ball(w/2,w/2,h/79,w/153,h/118,-0.6f);
                acBall.arrayList.clear();
                acCol.arrayList.clear();
                // do mo cua menu =0 va tang len 255(mo dan)
                alphaMenu=0;
                valphaMenu=5;
                logo=false;
                menu=false;
                main=true;
                run=false;
            }
        }


    }
    private void start() {

        //quay lai giao dien chinh
        chet=false;
        running=true;


        Switch("main");

        try{
            t.run();
        }catch (Exception e){
            t.run();
        }
    }

    @Override
    public void run() {
        while(running){

            try{
                canvas=surfaceHolder.lockCanvas();
                synchronized (canvas){
                    draw(canvas);
                    MainAction();
                }


            }catch (Exception e){
                Log.d("loi",e.toString());
            }
            try{
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(canvas!=null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

    }

    private void MainAction() {


        if(logo)
            checkTime();
        if(menu)
            actionMenu();
        if(main && run) {
            actionMain();
            check();

        }
    }

    private void check() {
        //neu toa do y cua ball >h thi chuyen sang trang thai chet
        if(acBall.ball.y>h){
            Switch("menu");
        }
    }

    private void actionMain() {
        actionCol();
        actionBall();

    }

    private void actionMenu() {
        updateCircle();
        updateAlpha();
    }

    private void updateAlpha() {
        alphaMenu+=valphaMenu;
        if(alphaMenu<=0)
            alphaMenu=0;
        if(alphaMenu>=255)
            alphaMenu=255;

    }

    private void updateCircle() {

        rCircle+=vr;
        vr+=gr;

        if(rCircle>=RCircle){
            vr=-1;

            gr=-gr;



        }else if(rCircle<=0){

            vr=1;
            gr=-gr;
        }
        if(rCircle>RCircle){
            rCircle=RCircle;
        }
        for(circle aa: circle){
            aa.setR(aa.getR()+3);
            if(aa.getAlpha()>3){
                aa.setAlpha(aa.getAlpha()-3);
            }
            if(aa.getAlpha()<=3){
                circle.remove(aa);
            }
        }
    }

/////TODO-actionCol and actionBall////////////////////////////////////////////////////////////////////////////


    private void actionBall() {
        acBall.actionBall();

    }
    private void actionCol() {
        acCol.actionCol();
    }
    public long now() {
        return System.currentTimeMillis();
    }
}
