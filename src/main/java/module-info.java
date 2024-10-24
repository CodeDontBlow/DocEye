module org.codedontblow {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires tess4j;
    requires ollama4j;

    opens org.codedontblow.services to javafx.fxml;
    exports org.codedontblow.services;
    exports org.codedontblow.dao;
    exports org.codedontblow.model;
}