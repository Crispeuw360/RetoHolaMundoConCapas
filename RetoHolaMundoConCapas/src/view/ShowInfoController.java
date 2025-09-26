/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author 2dami
 */
public class ShowInfoController implements Initializable {

    @FXML
    private Button GoBack;
    @FXML
    private ScrollPane Spane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closeAccount(ActionEvent event) {
        Stage stage = new Stage();
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("UsLog_Choice.fxml"));
                        Scene scene;
                        scene = new Scene(root);
                        stage = new Stage(StageStyle.DECORATED);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(UsLog_ChoiceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
                    Stage stagen = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
                    stagen.close();
    }
    
}
