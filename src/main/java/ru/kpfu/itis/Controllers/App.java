package ru.kpfu.itis.Controllers;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.kpfu.itis.Services.Connection;

import java.io.IOException;
import java.util.logging.Logger;

public class App extends Application {
    public static Application AppInstance;

    private Connection connection;
    private Logger log = Logger.getLogger(App.class.getName());

    private MarkersTable markersTable;
    private TextField field;

    public App(){
        AppInstance = this;
        connection = new Connection();
        markersTable = new MarkersTable(connection);
        field = new TextField();
    }

    @Override
    public void start(Stage stage){
        Button btnRefresh = new Button("Refresh");
        btnRefresh.setOnAction(e -> btnRefreshHandler());

        Button btnAddMarker = new Button("Add marker");
        btnAddMarker.setOnAction(e -> btnAddMarkerHandler());

        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(btnRefresh, btnAddMarker);
        Parent root = new VBox(field, markersTable, buttonPanel);
        Scene scene = new Scene(root);

        stage.setTitle("Markers List");
        stage.setScene(scene);
        stage.show();
    }

    void alert(String error){
        new Alert(Alert.AlertType.ERROR, error, ButtonType.OK).show();
    }

    public static void main(String[] args){
        launch(args);
    }

    private void btnRefreshHandler(){
        try{
            markersTable.refresh();
        }catch(IOException e){
            log.warning("IOException");
        }
        log.info("Data were refreshed");
    }

    private void btnAddMarkerHandler(){
        try {
            String marker = field.getText();
            connection.saveMarker(marker);
            markersTable.refresh();

            log.info(marker);
        } catch(Exception ex) {
            alert("Error");
        }
    }
}
