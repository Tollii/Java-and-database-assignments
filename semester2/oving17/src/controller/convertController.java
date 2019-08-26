package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import sample.Valuta;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static sample.Main.getHasRun;
import static sample.Valuta.konverterValuta;

public class convertController {

    protected static ArrayList<Valuta> valutaliste = new ArrayList<>();
    private Valuta fra, til;


    @FXML
    private JFXListView<String> listViewValuta1;

    @FXML
    private JFXListView<String> listViewValuta2;

    @FXML
    private JFXTextArea valutaText;

    @FXML
    private JFXButton valutaKonverter;

    @FXML
    private JFXTextField valutaTextField;

    @FXML
    private JFXButton valutaLeggTil;

    @FXML
    public void initialize(){
        if(!getHasRun()){
            addStandardValuta();
            System.out.println("init");
        }
        fillListViews();


        listViewValuta1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewValuta2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewValuta1.setOnMouseClicked(mouseEvent -> {
            this.fra =  getValuta(listViewValuta1.getSelectionModel().getSelectedItem());
            System.out.println(fra.getValutanavn());
        });

        listViewValuta2.setOnMouseClicked(mouseEvent -> {
             this.til = getValuta(listViewValuta2.getSelectionModel().getSelectedItem());
            System.out.println(til.getValutanavn());
        });

        valutaKonverter.setOnAction(actionEvent -> {
            double result = konverterValuta(fra, til, Double.parseDouble(valutaTextField.getText().trim()));
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            System.out.println(konverterValuta(fra, til, result));
            valutaText.setText(numberFormat.format(result) + "\n" + til.getValutanavn());
        });

        valutaLeggTil.setOnAction(actionEvent -> {

            valutaLeggTil.getScene().getWindow().hide();
            changeScene("/view/addValuta.fxml");
        });
    } // Initialize


    private void addStandardValuta(){
        valutaliste.add(new Valuta("Euro", 8.10, 1));
        valutaliste.add(new Valuta("US Dollar", 6.23, 1));
        valutaliste.add(new Valuta("Britiske pund", 12.27, 1));
        valutaliste.add(new Valuta("Svenske kroner", 88.96, 100));
        valutaliste.add(new Valuta("Danske kroner", 108.75, 100));
        valutaliste.add(new Valuta("Yen", 5.14, 100));
        valutaliste.add(new Valuta("Islandske kroner", 9.16, 100));
        valutaliste.add(new Valuta("Norske kroner", 100, 100));
    }

    private Valuta getValuta(String valutaNavn){
        for(Valuta val : valutaliste){
            if(val.getValutanavn().equals(valutaNavn)){
                return val;
            }
        }
        return null;
    }

    private void fillListViews(){


        for(Valuta liste : valutaliste){
            listViewValuta1.getItems().addAll(liste.getValutanavn());
            listViewValuta2.getItems().addAll(liste.getValutanavn());
        }
    }

    private void changeScene(String path){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


} // convertController

