/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.User;

/**
 * Controlador de la vista UsLog_Choice.fxml.
 * Gestiona el inicio de sesión del usuario permitiendo elegir entre archivo o base de datos
 * como origen de datos.
 * 
 * @author 2dami
 */
public class UsLog_ChoiceController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button btnLogIn;
    @FXML
    private PasswordField pswrPass;
    @FXML
    private TextField txtUsName;
    @FXML
    private RadioButton rbFile;
    @FXML
    private ToggleGroup Storage;
    @FXML
    private RadioButton rdDB;
    @FXML
    private Label lblMSG;

    private Controller cont = new Controller();

    @Override
    /**
     * Inicializa el controlador.
     * Actualmente no realiza ninguna acción.
     * 
     * @param url no utilizado
     * @param rb no utilizado
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    /**
     * Gestiona el flujo de inicio de sesión cuando el usuario pulsa el botón.
     * Valida entradas, comprueba la fuente de datos seleccionada y, si las
     * credenciales son correctas, abre la vista de información del usuario.
     * 
     * @param event Evento de acción del botón de login
     */
    private void LogIn(ActionEvent event) {
        String usname = txtUsName.getText();

        String pass = pswrPass.getText();
        if (usname.equals("") || pass.equals("")) {
            lblMSG.setText("Input your Username or/and Password before proceeding.");
        } else {
            if (!rdDB.isSelected() && !rbFile.isSelected()) {
                lblMSG.setText("Select the location of your account before proceeding.");
            } else {
                if (rdDB.isSelected()) {
                    Stage stage = new Stage();

                    User usuario = cont.showUserBD(usname);

                    if (usuario != null && usuario.getPassword().equals(pass)) {
                        Parent root;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShowInfo.fxml"));
                            root = loader.load();

                            Scene scene;
                            scene = new Scene(root);
                            stage = new Stage(StageStyle.DECORATED);
                            stage.setTitle("User "+usuario.getUsername()+"'s info from Database");

                            stage.setScene(scene);

                            ShowInfoController shcont = loader.getController();

                            shcont.setAll(usuario);

                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(UsLog_ChoiceController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
                        Stage stagen = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
                        stagen.close();
                    } else {
                        lblMSG.setText("The username or the password is incorrect.");
                    }
                } else if (rbFile.isSelected()) { 
                    
                        Stage stage = new Stage();

                        User usuario = cont.showUserFile(usname);
                        System.out.println(usuario);
                        if (usuario != null && usuario.getPassword().equals(pass)) {
                            Parent root;
                            try {
                                
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShowInfo.fxml"));
                                root = loader.load();
                                Scene scene;
                                scene = new Scene(root);
                                stage = new Stage(StageStyle.DECORATED);
                                stage.setTitle("User "+usuario.getUsername()+"'s info from Local File");
                                stage.setScene(scene);

                                ShowInfoController shcont = loader.getController();

                                shcont.setAll(usuario);

                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(UsLog_ChoiceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
                            Stage stagen = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
                            stagen.close();
                        } else {
                            lblMSG.setText("The username or the password is incorrect.");
                        }
                    }
                }
            }
        }

    }


