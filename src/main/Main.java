package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.awt.Button;
import java.awt.Color;

public class Main extends JFrame {

    int windowWidth = 1000;
    int windowHeight = 600;
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

    // population for each CellGroup.
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

    int framerate = 60;

    Thread thread1, thread2;

    public Main() {
        setup();
        initialize();
        run();
    }

    private void run() {
        thread1.start();
        ;
        thread2.start();
        ;
        double deltaTime = System.currentTimeMillis();
        int frames = 0;

        while (true) {
            double timenow = System.currentTimeMillis();

            if (deltaTime + (1000 / framerate) <= timenow) {
                drawBoard.repaint();
                update();
                deltaTime = timenow;
                frames++;
            }

        }

    }

    private void update() {

        greenCellGroup.moveCells();
        whiteCellGroup.moveCells();
        redCellGroup.moveCells();
        blueCellGroup.moveCells();
    }

    public void rule(CellGroup cellGroupA, CellGroup cellGroupB, double attraction) {
            if(attraction != 0){
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
                        fy += F * dy;

                    }

                }
                cellA.setVx((cellA.getVx() + fx) * ease);
                cellA.setVy((cellA.getVy() + fy) * ease);

            }
            }
        

    }

    private void initialize() {

        initializeCells();
        randomizeRules();

        thread1 = new Thread() {
            public void run() {
                double deltaTime = System.currentTimeMillis();
                double firstFrameMillis = deltaTime;
                int frames = 0;
                while (true) {
                    double timenow = System.currentTimeMillis();
                    double timePassed = timenow - firstFrameMillis;
                    if (timePassed >= 1000) {

                        drawBoard.setFrames((int) (frames / (timePassed / 1000)));
                        frames = 0;
                        firstFrameMillis = timenow;
                    }

                    if (deltaTime + (1000 / 60) <= timenow) {
                        rule(redCellGroup, redCellGroup, redG);
                        rule(redCellGroup, blueCellGroup, redBlueG);
                        rule(redCellGroup, greenCellGroup, redGreenG);
                        rule(redCellGroup, whiteCellGroup, redWhiteG);
                        rule(whiteCellGroup, whiteCellGroup, whiteG);
                        rule(whiteCellGroup, redCellGroup, whiteRedD);
                        rule(whiteCellGroup, greenCellGroup, whiteGreenG);
                        rule(whiteCellGroup, blueCellGroup, whiteBlueG);
                        deltaTime = timenow;
                        frames++;
                    }
                }

            }
        };
        thread2 = new Thread() {
            public void run() {
                double deltaTime = System.currentTimeMillis();
                while (true) {
                    double timenow = System.currentTimeMillis();
                    if (deltaTime + (1000 / 60) <= timenow) {
                        rule(greenCellGroup, greenCellGroup, greenG);
                        rule(greenCellGroup, blueCellGroup, greenBlueG);
                        rule(greenCellGroup, redCellGroup, greenRedG);
                        rule(greenCellGroup, whiteCellGroup, greenWhiteG);
                        rule(blueCellGroup, blueCellGroup, blueeG);
                        rule(blueCellGroup, greenCellGroup, blueGreenG);
                        rule(blueCellGroup, redCellGroup, blueRedG);
                        rule(blueCellGroup, whiteCellGroup, blueWhiteG);
                        deltaTime = timenow;
                    }
                }
            }
        };
    }

    public void initializeCells() {

        greenCellGroup = new CellGroup(Color.green, population, windowWidth, windowHeight);
        blueCellGroup = new CellGroup(Color.blue, population, windowWidth, windowHeight);
        redCellGroup = new CellGroup(Color.red, population, windowWidth, windowHeight);
        whiteCellGroup = new CellGroup(Color.white, population, windowWidth, windowHeight);

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

        System.out.println(greenG);
        System.out.println(greenBlueG);
        System.out.println(greenRedG);
        System.out.println(greenWhiteG);

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
        Random random = new Random();
        Random random2 = new Random(random.nextLong());
        Random random3 = new Random(random2.nextLong());
        Random random4 = new Random(random3.nextLong());
        
        if (random.nextDouble() < 0.4) return 0;
        if (random2.nextDouble() > 0.5) {
            return random3.nextDouble();
        }
        return random4.nextDouble() * -1;
 
    }

    private void setup() {
        System.setProperty("sun.java2d.opengl", "true");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(windowWidth, windowHeight));
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
                initialize();

            }

        });
    }

    public static void main(String[] args) {
        new Main();
    }

}