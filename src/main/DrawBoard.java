package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

public class DrawBoard extends JPanel {
    CellGroup[] cellgroup;
    public DrawBoard(CellGroup[] cellgroup) {
        this.cellgroup = cellgroup;

    }

    public void paintComponent( Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.green);
        for (int i = 0; i < cellgroup.length; i++) {
            CellGroup groupCells = cellgroup[i];
            Cell[] cells = groupCells.getCells();

            g.setColor(groupCells.getColor());       

            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[j];     
                g.fillRect((int)cell.getX(), (int)cell.getY(), cell.getSize(), cell.getSize());
            }

        }

    }
}
