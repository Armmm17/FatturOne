package com.armandoboaca17.fatturone;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class QueryResult extends VBox {
    private Main main;

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
        this.main = main;
        setBackground(new Background(new BackgroundFill(Paint.valueOf("#e3e3e3"), CornerRadii.EMPTY, Insets.EMPTY)));


        /*
        grid
            label
            textfield
         */


        this.gridCustomer = new GridPane(2,2);
        this.gridCustomer.setMinSize(V.WIDTH/2, 30);
        this.gridCustomer.setAlignment(Pos.CENTER_LEFT);
        this.gridCustomer.setHgap(15);
        this.gridCustomer.setVgap(5);
        this.gridCustomer.setPadding(new Insets(5, 0, 5, 15));
        this.lCustomerNumber = new Label("Customer Number");
        this.lcustomerName = new Label("Customer Name");
        this.tfCustomerNumber = new TextField("Num");
        this.tfcustomerName = new TextField("Name");
        this.tfCustomerNumber.setMaxWidth(200);
        this.tfCustomerNumber.setEditable(false);
        this.tfcustomerName.setMaxWidth(200);
        this.tfcustomerName.setEditable(false);
        this.gridCustomer.add(this.lCustomerNumber, 0, 1);
        this.gridCustomer.add(this.lcustomerName, 1, 1);
        this.gridCustomer.add(this.tfCustomerNumber, 0, 2);
        this.gridCustomer.add(this.tfcustomerName, 1, 2);



        this.gridShippingAddress = new GridPane(1,2);
        this.gridShippingAddress.setMinSize(V.WIDTH/2, 30);
        this.gridShippingAddress.setAlignment(Pos.CENTER_LEFT);
        this.gridShippingAddress.setHgap(15);
        this.gridShippingAddress.setVgap(5);
        this.gridShippingAddress.setPadding(new Insets(5, 0, 5, 15));
        this.lShippingAddress = new Label("Shipping Address");
        this.tfShippingAddress = new TextField("via/piazza, citta, regione, stato");
        this.tfShippingAddress.setMaxWidth(250);
        this.tfShippingAddress.setEditable(false);
        this.gridShippingAddress.add(this.lShippingAddress, 0, 1);
        this.gridShippingAddress.add(this.tfShippingAddress, 0, 2);



        this.gridPhone = new GridPane(1,2);
        this.gridPhone.setMinSize(V.WIDTH/2, 30);
        this.gridPhone.setAlignment(Pos.CENTER_LEFT);
        this.gridPhone.setHgap(15);
        this.gridPhone.setVgap(5);
        this.gridPhone.setPadding(new Insets(5, 0, 5, 15));
        this.lPhone = new Label("Phone");
        this.tfPhone = new TextField("num");
        this.tfPhone.setMaxWidth(250);
        this.tfPhone.setEditable(false);
        this.gridPhone.add(this.lPhone, 0, 1);
        this.gridPhone.add(this.tfPhone, 0, 2);



        this.gridEmployee = new GridPane(2,2);
        this.gridEmployee.setMinSize(V.WIDTH/2, 30);
        this.gridEmployee.setAlignment(Pos.CENTER_LEFT);
        this.gridEmployee.setHgap(15);
        this.gridEmployee.setVgap(5);
        this.gridEmployee.setPadding(new Insets(5, 0, 5, 15));
        this.lEmployeeNumber = new Label("Employee number");
        this.lEmployeeName = new Label("Employee name");
        this.tfEmployeeNumber = new TextField("num");
        this.tfEmployeeName = new TextField("name");
        this.tfEmployeeNumber.setMaxWidth(250);
        this.tfEmployeeNumber.setEditable(false);
        this.tfEmployeeName.setMaxWidth(250);
        this.tfEmployeeName.setEditable(false);
        this.gridEmployee.add(this.lEmployeeNumber, 0, 1);
        this.gridEmployee.add(this.lEmployeeName, 1, 1);
        this.gridEmployee.add(this.tfEmployeeNumber, 0, 2);
        this.gridEmployee.add(this.tfEmployeeName, 1, 2);



        this.gridEmployeeEmail = new GridPane(1,2);
        this.gridEmployeeEmail.setMinSize(V.WIDTH/2, 30);
        this.gridEmployeeEmail.setAlignment(Pos.CENTER_LEFT);
        this.gridEmployeeEmail.setHgap(15);
        this.gridEmployeeEmail.setVgap(5);
        this.gridEmployeeEmail.setPadding(new Insets(5, 0, 5, 15));
        this.lEmployeeEmail = new Label("Employee Email");
        this.tfEmployeeEmail = new TextField("email");
        this.tfEmployeeEmail.setMaxWidth(250);
        this.tfEmployeeEmail.setEditable(false);
        this.gridEmployeeEmail.add(this.lEmployeeEmail, 0, 1);
        this.gridEmployeeEmail.add(this.tfEmployeeEmail, 0, 2);


        this.gridOrder = new GridPane(2,2);
        this.gridOrder.setMinSize(V.WIDTH/2, 30);
        this.gridOrder.setAlignment(Pos.CENTER_LEFT);
        this.gridOrder.setHgap(15);
        this.gridOrder.setVgap(5);
        this.gridOrder.setPadding(new Insets(5, 0, 5, 15));
        this.lOrderNumber = new Label("Order Number");
        this.lOrderDate = new Label("Order date");
        this.tfOrderNumber = new TextField("num");
        this.tfOrderDate = new TextField("date");
        this.tfOrderNumber.setMaxWidth(250);
        this.tfOrderNumber.setEditable(false);
        this.tfOrderDate.setMaxWidth(250);
        this.tfOrderDate.setEditable(false);
        this.gridOrder.add(this.lOrderNumber, 0, 1);
        this.gridOrder.add(this.lOrderDate, 1, 1);
        this.gridOrder.add(this.tfOrderNumber, 0, 2);
        this.gridOrder.add(this.tfOrderDate, 1, 2);


        //INSERIRE DETTAGLIO ORDINE QUA


        this.gridTotOrder = new GridPane(1,2);
        this.gridTotOrder.setMinSize(V.WIDTH/2, 30);
        this.gridTotOrder.setAlignment(Pos.CENTER_RIGHT);
        this.gridTotOrder.setHgap(15);
        this.gridTotOrder.setVgap(5);
        this.gridTotOrder.setPadding(new Insets(5, 15, 5, 0));
        this.lOrderTotal = new Label("Totale ordine");
        this.tfOrderTotal = new TextField("$");
        this.tfOrderTotal.setMaxWidth(250);
        this.tfOrderTotal.setEditable(false);
        this.gridTotOrder.add(this.lOrderTotal, 0, 1);
        this.gridTotOrder.add(this.tfOrderTotal, 0, 2);







        /* NameField : TextField
        this.lName = new Label("House name :");
        this.tfName = new TextField("");
        this.tfName.setMaxWidth(250);
        this.tfName.setEditable(false);
        this.grid.add(this.lName, 0, 1);
        this.grid.add(this.tfName, 1, 1);


         */






        this.getChildren().add(gridCustomer);
        this.getChildren().add(gridShippingAddress);
        this.getChildren().add(gridPhone);
        this.getChildren().add(gridEmployee);
        this.getChildren().add(gridEmployeeEmail);
        this.getChildren().add(gridOrder);
        this.getChildren().add(gridTotOrder);


    }
}
