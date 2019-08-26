package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.Valuta;

import java.io.IOException;

import static controller.convertController.valutaliste;


public class addValutaController {



    @FXML
    private JFXTextField addValutaNavnField;

    @FXML
    private JFXTextField addValutaKursField;

    @FXML
    private JFXButton addValutaLeggTilButton;

    @FXML
    private JFXButton addValutaTilbakeButton;

    @FXML
    private JFXRadioButton addValuta1Radio;

    @FXML
    private JFXRadioButton addValuta100Radio;

    private ObservableSet<JFXRadioButton> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<JFXRadioButton> unselectedCheckBoxes = FXCollections.observableSet();

    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

    private final int maxNumSelected = 1;

    @FXML
    public void initialize(){

        configureCheckBox(addValuta1Radio);
        configureCheckBox(addValuta100Radio);

        addValutaLeggTilButton.setDisable(true);

        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
                addValutaLeggTilButton.setDisable(false);
            } else {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
                addValutaLeggTilButton.setDisable(true);
            }
        });

        addValutaTilbakeButton.setOnAction(event ->{
            addValutaTilbakeButton.getScene().getWindow().hide();
            changeScene("/view/convert.fxml");
        });


        addValutaLeggTilButton.setOnAction(actionEvent -> {

            double kurs = 1.0;
            String navn = addValutaNavnField.getText().trim();
            if (isStringNumber(addValutaKursField.getText())) {
                kurs = Double.parseDouble(addValutaKursField.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        return;
                    }
                });


            }

            int enhet = addValuta1Radio.isSelected() ? 1 : 100;
            Valuta ny = new Valuta(navn, kurs, enhet);
            valutaliste.add(new Valuta(navn, kurs, enhet));



            //Change back to main scene
            addValutaLeggTilButton.getScene().getWindow().hide();
            changeScene("/view/convert.fxml");
        });




    }



    /*
    https://stackoverflow.com/questions/30543460/multiple-but-limited-checkboxes-in-fxml-javafx
    */

    private void configureCheckBox(JFXRadioButton checkBox) {
        if(checkBox.isSelected()){
            selectedCheckBoxes.add(checkBox);
        } else {
            unselectedCheckBoxes.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkBox);
                selectedCheckBoxes.add(checkBox);
            } else {
                selectedCheckBoxes.remove(checkBox);
                unselectedCheckBoxes.add(checkBox);
            }
        });
    }

    private boolean isStringNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
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
        stage.show();
    }

}