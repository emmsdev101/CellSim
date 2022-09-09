package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.Button;
import java.awt.Color;

public class Main extends JFrame {

    Button randomize = new Button("Randomize");
    Button reset = new Button("Reset");
    Button save = new Button("Save");

    JPanel btnPanel = new JPanel();
    Dimension btnSize = new Dimension(100, 50);

    DrawBoard drawBoard;

    CellGroup greenCellGroup;
    CellGroup blueCellGroup;
    CellGroup redCellGroup;
    CellGroup whiteCellGroup;

    //  population for each CellGroup.
    // actual population is population*4 since there are 4 CellGroup.
    int population = 1000; 
    // This is used for rendering all cells
    CellGroup[] cellGroups = new CellGroup[4];

    double greenG;
    double greenBlueG;
    double greenRedG;
    double greenWhiteG;

    double blueeG;
    double blueGreenG;
    double blueRedG;
    double blueWhiteG;

    double redG;
    double redGreenG;
    double redBlueG;
    double redWhiteG;

    double whiteG;
    double whiteGreenG;
    double whiteRedD;
    double whiteBlueG;

    public Main() {
        initialize();
        setup();
        run();
    }

    private void run() {
        while (true) {
            try {
                update();
                drawBoard.repaint();
                Thread.sleep(1000 / 60);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

    }
    private void update() {
        rule(greenCellGroup, greenCellGroup, greenG);
        rule(greenCellGroup, blueCellGroup, greenBlueG);
        rule(greenCellGroup, redCellGroup, greenRedG);
        rule(greenCellGroup, whiteCellGroup, greenWhiteG);

        rule(blueCellGroup, blueCellGroup, blueeG);
        rule(blueCellGroup, greenCellGroup, blueGreenG);
        rule(blueCellGroup, redCellGroup, blueRedG);
        rule(blueCellGroup, whiteCellGroup, blueWhiteG);

        rule(redCellGroup, redCellGroup, redG);
        rule(redCellGroup, blueCellGroup, redBlueG);
        rule(redCellGroup, greenCellGroup, redGreenG);
        rule(redCellGroup, whiteCellGroup, redWhiteG);

        rule(whiteCellGroup, whiteCellGroup, whiteG);
        rule(whiteCellGroup, redCellGroup, whiteRedD);
        rule(whiteCellGroup, greenCellGroup, whiteGreenG);
        rule(whiteCellGroup, blueCellGroup, whiteBlueG);
    }

    public void rule(CellGroup cellGroupA, CellGroup cellGroupB, double attraction) {
        if (attraction > 0) {
            Cell[] cellsA = cellGroupA.getCells();
            Cell[] cellsB = cellGroupB.getCells();


            double ease = 0.5;


            for (int i = 0; i < cellsA.length; i++) {
                Cell cellA = cellsA[i];
                Cell cellB;
                double fx = 0; // Attractive force on x axis
                double fy = 0; // Attractive force on y axis


                for (int j = 0; j < cellsB.length; j++) {
                     
                     cellB = cellsB[j];

                    // Calculating distance of cellA and cellB
                    double dx = cellB.getX() - cellA.getX(); // dx = delta x
                    double dy = cellB.getY() - cellA.getY(); // dy = delta y
                    double d = Math.sqrt((dx * dx) + (dy * dy));

                    if (d > 0 && d < cellGroupA.getForceRadius()) {
                        double F = (attraction * 1) / d;
                        fx += F * dx;
                        fy +=  F * dy;


                    }
                }
                cellA.setVx((cellA.getVx() + fx) * ease);
                cellA.setVy((cellA.getVy() + fy) * ease);

                cellA.setX(cellA.getX() + cellA.getVx());
                cellA.setY(cellA.getY() + cellA.getVy());

                if (cellA.getX() >= 500 || cellA.getX() <= 0)
                    cellA.setVx(cellA.getVx() * -1);
                if (cellA.getY() >= 500 || cellA.getY() <= 0)
                    cellA.setVy(cellA.getVy() * -1);
            }
        }
    }

    private void initialize() {

        initializeCells();
        randomizeRules();
    }
    public void initializeCells() {
        greenCellGroup = new CellGroup(Color.green, population);
        blueCellGroup = new CellGroup(Color.blue, population);
        redCellGroup = new CellGroup(Color.red, population);
        whiteCellGroup = new CellGroup(Color.white, population);

        cellGroups[0] = greenCellGroup;
        cellGroups[1] = blueCellGroup;
        cellGroups[2] = redCellGroup;
        cellGroups[3] = whiteCellGroup;

        ;
    }

    public void randomizeRules() {
        greenG = randomizeG();
        greenBlueG = randomizeG();
        greenRedG = randomizeG();
        greenWhiteG = randomizeG();

        blueeG = randomizeG();
        blueGreenG = randomizeG();
        blueRedG = randomizeG();
        blueWhiteG = randomizeG();

        redG = randomizeG();
        redGreenG = randomizeG();
        redBlueG = randomizeG();
        redWhiteG = randomizeG();

        whiteG = randomizeG();
        whiteGreenG = randomizeG();
        whiteRedD = randomizeG();
        whiteBlueG = randomizeG();
    }

    public double randomizeG() {
        if (Math.random() < 0.5) {
            return ((Math.random() * 1) * -1);
        }
        return (Math.random() * 1);
    }

    private void setup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 600));
        setLocationRelativeTo(null);

        drawBoard = new DrawBoard(cellGroups);

        randomize.setSize(btnSize);
        reset.setSize(btnSize);

        btnPanel.add(randomize);
        btnPanel.add(reset);
        btnPanel.add(save);
        btnPanel.add(reset);
        btnPanel.setBackground(new Color(200, 200, 200));
        drawBoard.setBackground(Color.black);

        getContentPane().add(drawBoard, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);

        randomize.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                initializeCells();
                randomizeRules();

            }

        });
    }



    public static void main(String[] args) {
        new Main();
    }

}