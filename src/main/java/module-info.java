module ca.quickdo.intermediatejava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ca.quickdo.intermediatejava to javafx.fxml;
    exports ca.quickdo.intermediatejava;
}