package ui.elements.extra;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AlertPane extends HBox {
    public final Label alertLabel;
    public AlertPane() {
        alertLabel = new Label();
        alertLabel.setStyle("-fx-text-fill:rgb(0,0,0);");

        this.getChildren().addAll(alertLabel);
        this.setStyle("-fx-background-color:rgb(255,0,0, 0.5);");
        this.setPrefHeight(30);
        this.setMaxHeight(30);
        this.setAlignment(Pos.CENTER);
    }

    public void setAlertLabelText(String text) {
        this.alertLabel.setText(text);
    }
}
