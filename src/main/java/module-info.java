module UI.Presentation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens UI.Presentation to javafx.fxml;
    opens UI.Elements.CalculationGame to javafx.fxml;
    opens UI.Elements.GameField to javafx.fxml;// Add this line to open the package

    exports UI.Presentation;
}