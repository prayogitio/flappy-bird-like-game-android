package com.example.flappypig;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Pig pig;
    private List<Lumber> lumbers = new ArrayList<>();
    private final int lumberCount = 3;
    private final int distanceBetweenLumber = 700;
    private int maxX;
    private boolean isLumberShown = false;
    AtomicInteger score = new AtomicInteger(0);

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        pig = new Pig(context, screenX, screenY);
        for (int i = 0; i < lumberCount; ++i) {
            lumbers.add(new Lumber(context, screenX, screenY));
        }
        maxX = screenX;
        this.setOnClickListener(v -> pig.setTapping());
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        boolean addScore = false;
        pig.update();
        if (!isLumberShown) {
            lumbers.get(0).update();
            isLumberShown = true;
        } else {
            for (int i = 0; i < lumberCount; ++i) {
                int prevLumberIdx = (i == 0) ? lumberCount-1 : i-1;
                if (lumbers.get(i).getDetectCollision1().left >= maxX) {
                    if (maxX - lumbers.get(prevLumberIdx).getDetectCollision1().right >= distanceBetweenLumber) {
                        lumbers.get(i).update();
                        detectCollision(lumbers.get(i), pig);
                        addScore(lumbers.get(i), pig);
                    }
                } else {
                    lumbers.get(i).update();
                    detectCollision(lumbers.get(i), pig);
                    addScore(lumbers.get(i), pig);
                }
            }
        }
    }

    private void addScore(Lumber lumber, Pig pig) {
        if (lumber.getDetectCollision1().right <= pig.getDetectCollision().left) {
            if (!lumber.isScoreIsSet()) {
                score.incrementAndGet();
                MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.preview);
                mp.start();
                lumber.setScoreIsSet(true);
            }
        }
    }

    private void detectCollision(Lumber lumber, Pig pig) {
        if (Rect.intersects(lumber.getDetectCollision1(), pig.getDetectCollision())
                || Rect.intersects(lumber.getDetectCollision2(), pig.getDetectCollision())) {
            Intent intent = new Intent(this.getContext(), MainActivity.class);
            this.getContext().startActivity(intent);
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);
            canvas.drawRect(pig.getDetectCollision(), paint);
            for (int i = 0; i < lumberCount; ++i) {
                canvas.drawRect(lumbers.get(i).getDetectCollision1(), paint);
                canvas.drawRect(lumbers.get(i).getDetectCollision2(), paint);
            }
            paint.setTextSize(70);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("Score: " + score, 0, paint.getTextSize(), paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException ignored) {}
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
