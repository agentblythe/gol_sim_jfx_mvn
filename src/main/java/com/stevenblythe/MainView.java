package com.stevenblythe;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Random;

import static com.stevenblythe.Constants.*;

public class MainView extends VBox {
    private Colony colony;
    private Button stepForwardButton;
    private final Canvas canvas;
    private final Random random = new Random();

    public MainView() {
        this.stepForwardButton = new Button("Step Forward");
        this.stepForwardButton.setOnAction(a -> {
            colony.evolve();
            drawColony();
        });

        this.canvas = new Canvas(CANVAS_SIZE_IN_X, CANVAS_SIZE_IN_Y);
        this.getChildren().addAll(this.stepForwardButton, this.canvas);

        spawnRandomColony();

        drawColony();
    }

    private void spawnRandomColony() {
        this.colony = new Colony(CELLS_IN_X, CELLS_IN_Y);

        for(int x = 0; x < CELLS_IN_X; x++) {
            for (int y = 0; y < CELLS_IN_Y; y++) {
                if (random.nextInt(100) < INITIAL_SPARSENESS) {
                    colony.spawnCellAt(x, y);
                }
            }
        }
    }

    private void drawColony() {
        GraphicsContext g2d = canvas.getGraphicsContext2D();
        g2d.setFill(BACKGROUND_COLOUR);
        g2d.fillRect(0, 0, CANVAS_SIZE_IN_X, CANVAS_SIZE_IN_Y);

        g2d.setFill(ALIVE_COLOUR);
        for (int x = 0; x < CELLS_IN_X; x++) {
            for (int y = 0; y < CELLS_IN_Y; y++) {
                int xCoordTL = x * CELL_SIZE_IN_X;
                int yCoordTL = y * CELL_SIZE_IN_Y;
                if (colony.cellIsAlive(x, y)) {
                    g2d.fillRect(xCoordTL, yCoordTL, CELL_SIZE_IN_X, CELL_SIZE_IN_Y);
                }
            }
        }

        g2d.setStroke(LINE_COLOUR);
        g2d.setLineWidth(0.1f);
        for (int x = 0; x <= CANVAS_SIZE_IN_X; x += CELL_SIZE_IN_X) {
            g2d.strokeLine(x, 0, x, CANVAS_SIZE_IN_Y);
        }
        for (int y = 0; y <= CANVAS_SIZE_IN_Y; y += CELL_SIZE_IN_Y) {
            g2d.strokeLine(0, y, CANVAS_SIZE_IN_X, y);
        }
    }
}
