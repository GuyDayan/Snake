

import java.awt.*;


public class Snake implements Paintable {
    private int[] x;
    private int[] y;
    private int currentLength;
    private int width;
    private int height;
    private int direction;

    public Snake(){
        this.x = new int[Definition.GAME_UNIT];
        this.y = new int[Definition.GAME_UNIT];
        this.x[0] =Definition.UNIT_SIZE *3;
        this.y[0] =Definition.UNIT_SIZE *3;
        this.currentLength = Definition.SNAKE_START_LENGTH_BODY;
        this.width =Definition.UNIT_SIZE;
        this.height=Definition.UNIT_SIZE;
        this.direction = Definition.DIRECTION_DOWN;

    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


    public void move() {
        for (int i=this.currentLength ; i>0 ; i--) {
            this.x[i] = this.x[i - 1];
            this.y[i] = this.y[i - 1];
        }
            switch (direction) {
                case Definition.DIRECTION_UP:
                    this.y[0] -= Definition.UNIT_SIZE;
                    break;
                case Definition.DIRECTION_LEFT:
                    this.x[0] -= Definition.UNIT_SIZE;
                    break;
                case Definition.DIRECTION_DOWN:
                    this.y[0] += Definition.UNIT_SIZE;
                    break;
                case Definition.DIRECTION_RIGHT:
                    this.x[0] += Definition.UNIT_SIZE;
                    break;
            }

        }


    public void paint(Graphics graphics) {
        for (int i = 0; i < this.currentLength; i++) {
            if (i == 0){
                graphics.setColor(Color.ORANGE);
                graphics.fillRect(this.x[i], this.y[i], Definition.UNIT_SIZE, Definition.UNIT_SIZE);
            }else {
                graphics.setColor(Color.WHITE);
                graphics.fillRect(this.x[i], this.y[i], Definition.UNIT_SIZE, Definition.UNIT_SIZE);
            }

        }

        }

    }











