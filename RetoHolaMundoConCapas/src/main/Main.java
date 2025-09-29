/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Aplicación JavaFX principal que carga la vista inicial `UsLog_Choice.fxml`.
 * Configura y muestra la ventana principal de la aplicación.
 * 
 * @author 2dami
 */
public class Main extends Application {
    
    @Override
    /**
     * Punto de entrada de JavaFX. Carga el FXML inicial y muestra la escena.
     * 
     * @param stage Escenario principal proporcionado por el sistema JavaFX
     * @throws Exception si ocurre un error al cargar el recurso FXML
     */
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/UsLog_Choice.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("User Log In Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método main de la aplicación.
     * 
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        
        
        launch(args);
    }
    
}
