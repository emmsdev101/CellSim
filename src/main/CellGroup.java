package main;

import java.awt.Color;
import java.io.Console;

public class CellGroup {
    private Color color;
    private Cell[] cells;

    private int forceRadius;

    public CellGroup(Color color, int quantity, int windowWidth, int windowHeight) {
        this.cells = new Cell[quantity];
        this.color = color;
        this.forceRadius = (int) Math.floor(Math.random()*(100-10+1)+10);
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(2,windowWidth,windowHeight);
        }
    }

    public Cell[] getCells() {
        return cells;
    }
    public Color getColor(){
        return this.color;
    }
    public int getForceRadius(){
        return this.forceRadius;
    }
    public void setForceRadius(int f){
        this.forceRadius = f;
    }
    public void moveCells(){
        double friction = 0.5;
        for (int i = 0; i < this.cells.length; i++) {
            Cell cellA = this.cells[i];

            double vx = cellA.getVx();
            double vy = cellA.getVy();

            cellA.setX(cellA.getX() + cellA.getVx());
            cellA.setY(cellA.getY() + cellA.getVy());

            if (cellA.getX() >= 500 || cellA.getX() <= 0)
                cellA.setVx(cellA.getVx() * -1);
            if (cellA.getY() >= 500 || cellA.getY() <= 0)
                cellA.setVy(cellA.getVy() * -1);
        }
    }
}
