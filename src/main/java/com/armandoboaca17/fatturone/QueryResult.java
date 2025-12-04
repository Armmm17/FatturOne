package com.armandoboaca17.fatturone;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class QueryResult extends Stage {
    private Scene scene;
    private VBox root;

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

    private StackPane stackPane;
    private TableView tvOrderDetail;




    public QueryResult() {
        this.root = new VBox(20);
        this.scene = new Scene(this.root, V.WIDTH, (V.HEIGHT*2));
        this.root.setBackground(new Background(new BackgroundFill(Paint.valueOf("#e3e3e3"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setTitle("fattura/bolla nr. ordine: "+ V.getOrderNumber() +" - FatturOne");


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
        this.tfCustomerNumber = new TextField(V.LISTAORDINI.get(0).getCustomerNumber() + "");
        this.tfcustomerName = new TextField(V.LISTAORDINI.get(0).getCustomerName());
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
        this.tfShippingAddress = new TextField(V.LISTAORDINI.get(0).getIndirizzoSpedizione());
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
        this.tfPhone = new TextField(V.LISTAORDINI.get(0).getPhone());
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
        this.tfEmployeeNumber = new TextField(V.LISTAORDINI.get(0).getSalesRepEmployeeNumber()+"");
        this.tfEmployeeName = new TextField(V.LISTAORDINI.get(0).getResponsabile());
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
        this.tfEmployeeEmail = new TextField(V.LISTAORDINI.get(0).getEmail());
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
        this.tfOrderNumber = new TextField(V.LISTAORDINI.get(0).getOrderNumber());
        this.tfOrderDate = new TextField(V.LISTAORDINI.get(0).getOrderDate() + "");
        this.tfOrderNumber.setMaxWidth(250);
        this.tfOrderNumber.setEditable(false);
        this.tfOrderDate.setMaxWidth(250);
        this.tfOrderDate.setEditable(false);
        this.gridOrder.add(this.lOrderNumber, 0, 1);
        this.gridOrder.add(this.lOrderDate, 1, 1);
        this.gridOrder.add(this.tfOrderNumber, 0, 2);
        this.gridOrder.add(this.tfOrderDate, 1, 2);


        this.tvOrderDetail = new TableView<Map<String, String>>();

        this.tvOrderDetail.setEditable(true);

        TableColumn<Map<String, String>, String> colProductCode = new TableColumn<>("Product Code");
        TableColumn<Map<String, String>, String> colProductName = new TableColumn<>("Product Name");
        TableColumn<Map<String, String>, String> colQuantity = new TableColumn<>("quantity");
        TableColumn<Map<String, String>, String> colPriceEach = new TableColumn<>("Price each");
        TableColumn<Map<String, String>, String> colTotalXItem = new TableColumn<>("total");
        TableColumn<Map<String, String>, String> colItemDiscount = new TableColumn<>("discount");

        this.tvOrderDetail.getColumns().addAll(colProductCode, colProductName, colQuantity, colPriceEach, colTotalXItem, colItemDiscount);
        this.tvOrderDetail.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);




// Set cell value factories
        colProductCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("productCode")));
        colProductName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("productName")));
        colQuantity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("quantity")));
        colPriceEach.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("priceEach")));
        colTotalXItem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("totalXItem")));
        colItemDiscount.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("itemDiscount")));

// Create sample data
        ObservableList<Map<String, String>> sampleData = FXCollections.observableArrayList();

        for (int i = 0; i < V.LISTAORDINI.size(); i++) {
            Map<String, String> row = new HashMap<>();
            row.put("productCode", V.LISTAORDINI.get(i).getProductCode());
            row.put("productName", V.LISTAORDINI.get(i).getProductName());
            row.put("quantity", V.LISTAORDINI.get(i).getQuantityOrdered() + "");
            row.put("priceEach", V.LISTAORDINI.get(i).getPriceEach() + "");
            row.put("totalXItem", V.LISTAORDINI.get(i).getTotaleProdotto() + "");
            row.put("itemDiscount", V.LISTAORDINI.get(i).getScontoPercentuale() + "");
            sampleData.add(row);
        }

        this.tvOrderDetail.setItems(sampleData);



        this.stackPane = new StackPane(this.tvOrderDetail);
        this.stackPane.setAlignment(Pos.CENTER);
        this.stackPane.setPadding(new Insets(20, 20, 20, 20));


        this.gridTotOrder = new GridPane(1,2);
        this.gridTotOrder.setMinSize(V.WIDTH/2, 30);
        this.gridTotOrder.setAlignment(Pos.CENTER_RIGHT);
        this.gridTotOrder.setHgap(15);
        this.gridTotOrder.setVgap(5);
        this.gridTotOrder.setPadding(new Insets(5, 15, 5, 0));
        this.lOrderTotal = new Label("Totale ordine");
        this.tfOrderTotal = new TextField(V.LISTAORDINI.get(0).getTotaleOrdine()+ " €");
        this.tfOrderTotal.setMaxWidth(250);
        this.tfOrderTotal.setEditable(false);
        this.gridTotOrder.add(this.lOrderTotal, 0, 1);
        this.gridTotOrder.add(this.tfOrderTotal, 0, 2);









        this.root.getChildren().add(gridCustomer);
        this.root.getChildren().add(gridShippingAddress);
        this.root.getChildren().add(gridPhone);
        this.root.getChildren().add(gridEmployee);
        this.root.getChildren().add(gridEmployeeEmail);
        this.root.getChildren().add(gridOrder);
        this.root.getChildren().add(this.tvOrderDetail);
        this.root.getChildren().add(gridTotOrder);

        this.setScene(scene);

    }
}
