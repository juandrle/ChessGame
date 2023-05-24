module com.example.monsterschach {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.monsterschach to javafx.fxml;
    exports com.example.monsterschach;
}