package com.stevenblythe;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.Random;

import static com.stevenblythe.Constants.*;

public class MainView extends VBox {
    private Colony colony;
    private Toolbar toolbar;
    private final Canvas canvas;
    private final Random random = new Random();
    private DrawMode drawMode = DrawMode.CREATE;

    public MainView() {
        this.toolbar = new Toolbar(this);

        this.setOnKeyPressed(this::onKeyPressed);

        this.canvas = new Canvas(CANVAS_SIZE_IN_X, CANVAS_SIZE_IN_Y);
        this.canvas.setOnMouseClicked(this::handleCellMouseClick);
        this.canvas.setOnMouseDragged(this::handleCellMouseClick);

        this.getChildren().addAll(this.toolbar, this.canvas);

        spawnRandomColony();

        drawColony();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.C) {
            this.setDrawMode(DrawMode.CREATE);
            this.toolbar.getDrawModeCreateRadioButton().setSelected(true);
        } else if (keyEvent.getCode() == KeyCode.R) {
            this.setDrawMode(DrawMode.REMOVE);
            this.toolbar.getDrawModeRemoveRadioButton().setSelected(true);
        }
    }

    private void handleCellMouseClick(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        int cellIndexX = (int) (mouseX / CELL_SIZE_IN_X);
        int cellIndexY = (int) (mouseY / CELL_SIZE_IN_Y);

        if (drawMode == DrawMode.CREATE) {
            if (!colony.cellIsAlive(cellIndexX, cellIndexY)) {
                colony.spawnCellAt(cellIndexX, cellIndexY);
            }
        } else {
            if (colony.cellIsAlive(cellIndexX, cellIndexY)) {
                colony.killCellAt(cellIndexX, cellIndexY);
            }
        }

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

    public void drawColony() {
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

    public Colony getColony() {
        return colony;
    }

    public void setDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }
}
