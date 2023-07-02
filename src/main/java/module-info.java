module ui.presentation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens ui.presentation to javafx.fxml;
    opens ui.elements.competition.calculationGame to javafx.fxml;
    opens ui.elements.gameField to javafx.fxml; // Add this line to open the package

    exports ui.presentation;
    exports ui.elements.fullGame;
    opens ui.elements.fullGame to javafx.fxml;
}
