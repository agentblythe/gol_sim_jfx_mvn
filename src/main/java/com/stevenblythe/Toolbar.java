package com.stevenblythe;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private Button stepForwardButton;
    private RadioButton drawModeCreateRadioButton;
    private RadioButton drawModeRemoveRadioButton;
    private ToggleGroup toggleGroup;
    private MainView theMainView;

    public RadioButton getDrawModeCreateRadioButton() {
        return drawModeCreateRadioButton;
    }

    public RadioButton getDrawModeRemoveRadioButton() {
        return drawModeRemoveRadioButton;
    }

    public Toolbar(MainView mainView) {
        this.theMainView = mainView;

        this.toggleGroup = new ToggleGroup();

        this.drawModeCreateRadioButton = new RadioButton("Create");
        this.drawModeRemoveRadioButton = new RadioButton("Remove");
        this.drawModeCreateRadioButton.setSelected(true);
        this.drawModeRemoveRadioButton.setSelected(false);
        this.drawModeCreateRadioButton.setOnAction(this::createRadioButtonSelected);
        this.drawModeRemoveRadioButton.setOnAction(this::removeRadioButtonSelected);
        this.drawModeCreateRadioButton.setToggleGroup(this.toggleGroup);
        this.drawModeRemoveRadioButton.setToggleGroup(this.toggleGroup);

        this.stepForwardButton = new Button("Step Forward");
        this.stepForwardButton.setOnAction(this::stepForwardButtonPressed);

        this.getItems().addAll(
                drawModeCreateRadioButton,
                drawModeRemoveRadioButton,
                stepForwardButton);
    }

    private void stepForwardButtonPressed(ActionEvent actionEvent) {
        this.theMainView.getColony().evolve();
        this.theMainView.drawColony();
    }

    private void removeRadioButtonSelected(ActionEvent actionEvent) {
        this.theMainView.setDrawMode(DrawMode.REMOVE);
    }

    private void createRadioButtonSelected(ActionEvent actionEvent) {
        this.theMainView.setDrawMode(DrawMode.CREATE);
    }
}
