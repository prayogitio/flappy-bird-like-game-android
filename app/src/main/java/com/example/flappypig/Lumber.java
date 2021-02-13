package com.example.flappypig;

import android.content.Context;
import android.graphics.Rect;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Lumber {

    private int gap = 250;
    private Rect detectCollision1;
    private Rect detectCollision2;
    private int x;
    private int y;
    private int minX;
    private int maxX;
    private int maxY;
    private int minY;
    private int speed;
    private int lumberWidth = 75;

    public boolean isScoreIsSet() {
        return scoreIsSet;
    }

    public void setScoreIsSet(boolean scoreIsSet) {
        this.scoreIsSet = scoreIsSet;
    }

    private boolean scoreIsSet;

    public Lumber(Context context, int screenX, int screenY) {
        speed = 10;
        maxX = screenX;
        x = maxX;
        minX = 0;
        minY = 0;
        maxY = screenY - gap;
        Random generator = new Random();
        y = generator.nextInt(maxY);
        detectCollision1 = new Rect(x, 0, x+lumberWidth, y);
        detectCollision2 = new Rect(x, y+gap, x+lumberWidth, screenY);
        scoreIsSet = false;
    }

    public void update() {
        x -= speed;
        if (x < minX - lumberWidth) {
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            detectCollision1.bottom = y;
            detectCollision2.top = y+gap;
            this.scoreIsSet = false;
        }
        detectCollision1.left = x;
        detectCollision1.right = x+lumberWidth;
        detectCollision2.left = x;
        detectCollision2.right = x+lumberWidth;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public Rect getDetectCollision1() {
        return detectCollision1;
    }

    public void setDetectCollision1(Rect detectCollision1) {
        this.detectCollision1 = detectCollision1;
    }

    public Rect getDetectCollision2() {
        return detectCollision2;
    }

    public void setDetectCollision2(Rect detectCollision2) {
        this.detectCollision2 = detectCollision2;
    }

    public int getX() {
        return x;
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

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLumberWidth() {
        return lumberWidth;
    }

    public void setLumberWidth(int lumberWidth) {
        this.lumberWidth = lumberWidth;
    }
}
