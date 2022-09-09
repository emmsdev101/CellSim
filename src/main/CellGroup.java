package main;

import java.awt.Color;
import java.io.Console;

public class CellGroup {
    private Color color;
    private Cell[] cells;

    private int forceRadius;

    public CellGroup(Color color, int quantity) {
        this.cells = new Cell[quantity];
        this.color = color;
        this.forceRadius = (int) Math.floor(Math.random()*(100-10+1)+10);
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(3);
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
}
