/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.td.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model; // Creo il riferimento al modello
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader
    
    @FXML // fx:id="hboxTentativi"
    private HBox hboxTentativi; // Value injected by FXMLLoader


    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {   	
    	this.model.nuovaPartita(); // Faccio partire una nuova partita
    	//gestione interfaccia
    	txtTentativi.setText(Integer.toString(this.model.getTMAX())); // All'inizio imposto che il numero di tentativi massimi 
    												  // possibili è TMAX
    	// Abilito l'area di gioco
    	hboxTentativi.setDisable(false); // La riga prova in cui posso giocare viene disabilitata fin dall'inizio
    									 // in modo che prima l'utente debba cliccare sul bottone Nuova Partita per
    									 // poter abilitare l'area di gioco
    	txtRisultato.clear(); // Pulisce l'area di testo 	
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	String ts = txtTentativo.getText();
    	int tentativo;
    	
    	//controllo 1 -> input numerico, cioè devo controllare che l'utente abbia inserito un numero nella casella
    	try {
    		tentativo = Integer.parseInt(ts); // Provo a convertire la stringa contenuta nella textbox in un intero
    	} catch (NumberFormatException e) { // Catturo l'eccezione se la stringa non contiene un numero intero
    		txtRisultato.setText("Devi inserire un tentativo numerico tra 1 e 100!");
    		return; // Si ferma l'esecuzione del singolo tentativo di gioco, non tutto il gioco
    	}
    	
    	int risultato; 
    	
    	try {
    		risultato = this.model.tentativo(tentativo); // Chiedo al modello di fare il tentativo e mi restituisce 0, 1 o -1;
    	} catch(InvalidParameterException ip) {
    		txtRisultato.setText(ip.getMessage()); // getMessage() restituisce il messaggio riportato all'interno dell'eccezione
    		return; // Esco dal metodo
    	} catch(IllegalStateException is) { // Abbiamo esaurito i tentativi
    		txtRisultato.setText(is.getMessage());
    		hboxTentativi.setDisable(true); // Disabilito la parte di interfaccia grafica perchè l'utente ha perso
    		return;
    	}
    	
    	if(risultato == 0) {
    		// HAI VINTO
    		txtRisultato.setText("HAI INDOVINATO CON " + this.model.getTentativiFatti() + " TENTATIVI");
    		hboxTentativi.setDisable(true); // Se la partita è terminata disabilito la riga dove si può giocare
    	}
    	else if(risultato == 1) {
    		txtRisultato.setText("Tentativo troppo alto!");
    	}
    	else if(risultato == -1) {
    		txtRisultato.setText("Tentativo troppo basso!");
    	}

    	
    	// setText -> sostituisce il testo
    	// appendText -> appende il testo in coda
    	
    	// Aggiorno i tentativi nell'interfaccia grafica
    	txtTentativi.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

}
