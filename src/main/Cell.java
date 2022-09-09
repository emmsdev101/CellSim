package main;

public class Cell {
    private double x;
    private double y;
    private int size;
    
    private double vx=0;
    private double vy=0;

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell(int size, int windowWidth, int windowHeight){
        this.size = size;
        this.x = Math.floor(Math.random()*(windowWidth+1));
        this.y = Math.floor(Math.random()*(windowHeight+1));
    }
}
