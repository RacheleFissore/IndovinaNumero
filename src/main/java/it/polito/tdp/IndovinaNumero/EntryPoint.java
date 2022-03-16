package it.polito.tdp.IndovinaNumero;

import javafx.application.Application;
import static javafx.application.Application.launch;

import it.polito.td.IndovinaNumero.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	// E' necessario fare una modifica al codice per poter avere il collegamento con l'FXML Controller
    	Model model = new Model();
    	FXMLController controller;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
    	Parent root = loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        controller = loader.getController(); // Grazie al loader posso recuperare il controller
        controller.setModel(model);
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
