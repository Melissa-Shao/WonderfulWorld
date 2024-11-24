module io.github.wonderfulworld.comp2522202430termprojectwonderfulworld {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires org.json;

    opens io.github.wonderfulworld.comp2522202430termprojectwonderfulworld to javafx.fxml;
    exports io.github.wonderfulworld.comp2522202430termprojectwonderfulworld;
}