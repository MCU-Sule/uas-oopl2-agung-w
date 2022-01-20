module com.uas.agung {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;


    opens com.uas.agung to javafx.fxml;
    exports com.uas.agung;
    exports com.uas.agung.entity;
}