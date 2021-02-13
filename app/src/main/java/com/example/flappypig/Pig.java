package com.example.flappypig;

import android.content.Context;
import android.graphics.Rect;

public class Pig {

    private int x;
    private int y;
    private int minY;
    private int maxY;
    private Rect detectCollision;
    private int speed;
    private int tapping;
    private int gravity;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 40;
    private int pigHeight = 30;
    private int pigWidth = 30;

    public void setTapping() {
        tapping = 5;
    }

    public Pig(Context context, int screenX, int screenY) {
        x = 75;
        y = 75;
        speed = 0;
        minY = 0;
        maxY = screenY - pigWidth;
        detectCollision = new Rect(x, y, pigWidth, pigHeight);
        gravity = -10;
        tapping = 0;
    }

    public void update() {
        if (tapping > 0) {
            speed += 30;
            tapping--;
        } else {
            speed = 0;
        }
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        y -= speed + gravity;
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + pigWidth;
        detectCollision.bottom = y + pigWidth;
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

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void setDetectCollision(Rect detectCollision) {
        this.detectCollision = detectCollision;
    }

    public float getPigWidth() {
        return pigWidth;
    }
}
