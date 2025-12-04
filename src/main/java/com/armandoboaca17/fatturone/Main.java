package com.armandoboaca17.fatturone;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Stage stage;

    Scene main;


    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        //SCENA MAIN
        VBox root = new VBox(20);

        GridPane grid;
        Label lInOrder;
        TextField tfInOrder;


        //Instazio Scene figlie



        // Instanzio Scene utilizaabili
        this.main = new Scene(root, V.WIDTH, V.HEIGHT);


        //SCENA MAIN
        root.setBackground(new Background(new BackgroundFill(Paint.valueOf("grey"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setAlignment(Pos.CENTER);

        grid = new GridPane(2,1);
        grid.setMinSize(V.WIDTH/2, V.HEIGHT/2);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        lInOrder = new Label("Numero ordine :");
        tfInOrder = new TextField();
        tfInOrder.setMaxWidth(250);
        grid.add(lInOrder, 0, 1);
        grid.add(tfInOrder, 1, 1);



        //Cambia scena / Naviga
        Button mostraFattura = new Button("Mostra Fattura");
        mostraFattura.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                V.LISTAORDINI.clear();
                V.setOrderNumber(Integer.parseInt(tfInOrder.getText()));
                DBConnection.eseguiQuery();
                QueryResult qr = new QueryResult();
                qr.show();
            }
        });



        root.getChildren().add(grid);
        root.getChildren().add(mostraFattura);




        stage.setResizable(false);
        stage.setTitle("FatturOne");
        stage.setScene(main);
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getMain() {
        return main;
    }

    public static void main(String[] args) {
        launch();
    }
}