package ru.kpfu.itis.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import ru.kpfu.itis.Models.MarkerDTO;
import ru.kpfu.itis.Services.Connection;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

public class MarkersTable extends StackPane {
    ObservableList<MarkerDTO> markersList;
    private final ListView<MarkerDTO> listView;

    private Connection connection;

    public MarkersTable(Connection connection){
        this.connection = connection;
        listView = new ListView<>(markersList = FXCollections.observableArrayList());
        getChildren().add(listView);
        try{
            refresh();
        }catch(Exception e){
    //        App.AppInstance.alert;
            e.printStackTrace();
        }
    }

    public void refresh() throws IOException{
        List<MarkerDTO> dtoList = connection.getAllMarkers();
        markersList.clear();
        markersList.addAll(dtoList);
    }
}
