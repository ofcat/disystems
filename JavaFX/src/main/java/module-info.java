module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
}