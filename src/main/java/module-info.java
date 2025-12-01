module com.armandoboaca17.fatturone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.armandoboaca17.fatturone to javafx.fxml;
    exports com.armandoboaca17.fatturone;
}