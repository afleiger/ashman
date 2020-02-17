/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Villegard
 */
public class SettingsLayoutController extends Dialog<Void> implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TextField ghostText;
    @FXML TextField addText;
    @FXML ChoiceBox colorBox;
    
    ButtonType mOkButton;
    
    public SettingsLayoutController() throws IOException
    {
        super();
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("SettingsLayout.fxml"));
        load.setController(this);
        Parent rootNode = (Parent)load.load();
        getDialogPane().setContent(rootNode);
        mOkButton = new ButtonType("Apply", ButtonBar.ButtonData.APPLY);
        ButtonType cancelButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        
        getDialogPane().getButtonTypes().addAll(mOkButton, cancelButton);
        
        ((Button)getDialogPane().lookupButton(mOkButton)).addEventFilter(ActionEvent.ACTION, actionEvent -> onOkValidation(actionEvent));
        
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        GameSettings.readPreferences(getClass());
        
        
        colorBox.setItems(FXCollections.observableArrayList(
                "Standard",
                "Vomit Rainbow",
                "Heavenly",
                "Grieving",
                "Eye Damage",
                "Neon",
                "Grayscale"));
        colorBox.getSelectionModel().selectFirst();
        ghostText.textProperty().setValue(""+GameSettings.initialGhosts);
        addText.textProperty().setValue(""+GameSettings.addedGhosts);
        
    }
    
    
    
    private void onOkValidation(ActionEvent event)
    {
        try
        {
            int num = Integer.parseInt(ghostText.getText());
            int num2 = Integer.parseInt(addText.getText());
            if(num < 1 || num > 9 || num2 < 1 || num2 > 9)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Please enter a valid number from 1 to 9");
                alert.showAndWait();
            }
            else
            {
                GameSettings.initialGhosts = num;
                GameSettings.addedGhosts = num2;
                GameSettings.palette = GameSettings.PaletteFactory((String)colorBox.getValue());
                GameSettings.storePreferences(getClass());
            }
            event.consume();
            
        }
        catch(NumberFormatException e)
        {
            event.consume();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a valid number from 1 to 9");
            alert.showAndWait();
        }
    }
    
    
}
