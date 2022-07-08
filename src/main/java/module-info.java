module com.example.plms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires spring.context;

    opens com.plms.controller to javafx.fxml;
    exports com.plms.controller;
    exports com.plms.modules;
    opens com.plms.modules to javafx.fxml;
}