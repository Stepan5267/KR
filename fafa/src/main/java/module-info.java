module com.example.fafa {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;
    requires org.postgresql.jdbc;

    opens com.example.fafa to javafx.fxml;
    opens com.example.fafa.model to org.hibernate.orm.core, javafx.base;
    opens com.example.fafa.util to org.hibernate.orm.core;
    opens com.example.fafa.controller.exercisegroup to javafx.fxml;
    opens com.example.fafa.controller.user to javafx.fxml;
    opens com.example.fafa.controller.records to javafx.fxml;

    exports com.example.fafa;
    exports com.example.fafa.controller.exercisegroup;
    exports com.example.fafa.controller.user;
    exports com.example.fafa.controller.records;
}