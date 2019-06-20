/**
 * Sample Skeleton for 'Ufo.fxml' Controller Class
 */

package it.polito.tdp.ufo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.ufo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class UfoController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<String> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxStato"
    private ComboBox<String> boxStato; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleAnalizza(ActionEvent event) {
    	String state = boxStato.getValue();
    	txtResult.appendText("I precedenti di "+ state +" sono:\n");
    	List<String> precedenti = model.getPrecedenti(state);
    	for(String p : precedenti) {
    		txtResult.appendText(p.toString()+"\n");	
    	}
    	txtResult.appendText("\nI successori di "+ state +" sono:\n");
    	List<String> successori = model.getSuccessivi(state);
    	for(String s : successori) {
    		txtResult.appendText(s.toString()+"\n");	
    	}
    	txtResult.appendText("\nI raggiungibili di "+ state +" sono:\n");
    	List<String> raggiungibili = model.getRaggiungibili(state);

    }

    @FXML
    void handleAvvistamenti(ActionEvent event) {
    	String y = boxAnno.getValue();
    	String[] sp = y.split(" -");
    	
    	model.creaGrafo(Integer.parseInt(sp[0]));
    	boxStato.getItems().addAll(model.getVertici());
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	
    	/**/
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert boxStato != null : "fx:id=\"boxStato\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Ufo.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		boxAnno.getItems().addAll(model.getYears());
	}
}
