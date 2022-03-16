package it.polito.td.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	// Il model contiene la logica applicativa
	
	// Attributi del modello
	private int segreto; // Variabile in cui il programma metterà il suo numero segreto
	private final int TMAX = 8;
	private final int NMAX = 100;
	private int tentativiFatti;
	private Set<Integer> tentativi; // Lo uso per controllare di non aver inserito due volte lo stesso numero
	private boolean inGioco = false; // La metto a true quando inizia una nuova partita
	
	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}

	public void nuovaPartita() {
		tentativi = new HashSet<Integer>();
		// Gestione di una nuova partita: tirare a random un numero e inizializzare i tentativi fatti a 0
    	this.segreto = (int)((Math.random() * NMAX) +1); // Math.random() estrae un numero a caso tra 0 e 0,99 ma
    													 // noi vogliamo che sia tra tra 1 e 100 e quindi lo
    													 // moltiplichiamo per 100 e ci sommiamo +1 per traslarlo di
    													 // di una posizione
    	this.tentativiFatti = 0;
    	this.inGioco = true;
	}
	
	public int tentativo(int tentativo) {
		/*
		 * 0: numero indovinato
		 * 1: numero troppo alto
		 * -1: numero troppo basso
		 */
		
		// Prima di dire se il numero inserito è troppo basso o troppo alto controllo che i tentativi non siano
    	// esauriti perchè altrimenti non ha senso dire qualcosa 
		
		if(!inGioco) // Prima devo controllare se si è ancora in gioco
			throw new IllegalStateException("HAI PERSO! La partita è terminata");
		
		if(!tentativoValido(tentativo)) // Lancio l'eccezione se il boolean mi ritorna false
			throw new InvalidParameterException("Devi inserire un numero tra 1 e " + NMAX + " che non hai ancora utilizzato");
		
		this.tentativiFatti++;
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti == TMAX) {
			this.inGioco = false; // La partita termina quando sono stati terminati i tentativi fatti
		}
		
		if(tentativo == segreto) {
			this.inGioco = false; // Partita finisce perchè ho indovinato il numero
			return 0;
		}
		else if(tentativo < segreto) {
			return -1;
		}
		else
			return 1;		
	}

	private boolean tentativoValido(int tentativo) {
		if(tentativo < 1 || tentativo > NMAX || tentativi.contains(tentativo)) // Se il numero è in un intervallo sbagliato o se è già presente
																			   // nell'insieme di tentativi fatti, cioè nel set --> Genero un'eccezione
			return false;
		return true;
	}
}
