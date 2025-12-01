package com.armandoboaca17.fatturone;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QueryResult extends VBox {
    private HBox hBox;

    private GridPane
            gridCustomer,
            gridShippingAddress,
            gridPhone,
            gridEmployee,
            gridEmployeeEmail,
            gridOrder,
            gridTotOrder;


    private Label
            lCustomerNumber,
            lcustomerName,
            lShippingAddress,
            lPhone,
            lEmployeeNumber,
            lEmployeeName,
            lEmployeeEmail,
            lOrderNumber,
            lOrderDate,
            lProductsTable,
            lOrderTotal;

    private TextField
            tfCustomerNumber,
            tfcustomerName,
            tfShippingAddress,
            tfPhone,
            tfEmployeeNumber,
            tfEmployeeName,
            tfEmployeeEmail,
            tfOrderNumber,
            tfOrderDate,
            tfProductsTable,
            tfOrderTotal;






    public QueryResult(Main main) {
        TextField readOnlyField = new TextField("http://www.example.com/config");

        // 2. Set the field to non-editable (read-only)
        readOnlyField.setEditable(false);

        // 3. Optional: Apply styling to make it look more "display-only"
        readOnlyField.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc;");

        // Create a label for instructions
        Label instruction = new Label("The following configuration link is copyable but not editable:");
        this.getChildren().add(instruction);
        this.getChildren().add(readOnlyField);
    }
}
