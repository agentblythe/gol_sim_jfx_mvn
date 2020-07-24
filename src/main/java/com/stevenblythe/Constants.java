package com.stevenblythe;

import javafx.scene.paint.Color;

public class Constants {
    public static final int CANVAS_SIZE_IN_X = 400;
    public static final int CANVAS_SIZE_IN_Y = 400;

    public static final int CELLS_IN_X = 50;
    public static final int CELLS_IN_Y = 50;

    public static final int CELL_SIZE_IN_X = CANVAS_SIZE_IN_X / CELLS_IN_X;
    public static final int CELL_SIZE_IN_Y = CANVAS_SIZE_IN_Y / CELLS_IN_Y;

    public static final int INITIAL_SPARSENESS = 20;

    public static final Color BACKGROUND_COLOUR = Color.LIGHTBLUE;
    public static final Color ALIVE_COLOUR = Color.BLACK;
    public static final Color LINE_COLOUR = Color.GRAY;
}
