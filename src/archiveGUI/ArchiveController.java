/**
 * Sample Skeleton for 'ArchiveView.fxml' Controller Class
 */

package archiveGUI;

import java.net.URL;
import java.util.ResourceBundle;

import archiveModule.Auto;
import archiveModule.ManageAuto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArchiveController {
	private ManageAuto model;
	boolean inserting=false;

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtTarga"
    private TextField txtTarga; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="btnNew"
    private Button btnNew; // Value injected by FXMLLoader

    @FXML // fx:id="txtDescrizione"
    private TextArea txtDescrizione; // Value injected by FXMLLoader

    @FXML // fx:id="txtKM"
    private TextField txtKM; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtPrezzo"
    private TextField txtPrezzo; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtStatus"
    private TextField txtStatus; // Value injected by FXMLLoader

    @FXML // fx:id="btnSearch"
    private Button btnSearch; // Value injected by FXMLLoader

    @FXML // fx:id="btnModify"
    private Button btnModify; // Value injected by FXMLLoader

    @FXML // fx:id="btnDelete"
    private Button btnDelete; // Value injected by FXMLLoader

    @FXML // fx:id="btnList"
    private Button btnList; // Value injected by FXMLLoader

    @FXML
    void onDelete(ActionEvent event) {

    }

    @FXML
    void onList(ActionEvent event) {

    }

    @FXML
    void onModify(ActionEvent event) {

    }

    
    @FXML
    void onNew(ActionEvent event) {
    	setInserting(btnNew.getText().equalsIgnoreCase("New"));
    	clearFields();
    }

    
    @FXML
    void onSave(ActionEvent event) {
    	Auto a=new Auto(txtTarga.getText(), txtDescrizione.getText(), Integer.parseInt(txtKM.getText()),
    			        Integer.parseInt(txtAnno.getText()), Double.parseDouble(txtPrezzo.getText()));
    	if ( 0 != model.addAuto(a) ) {
    		txtStatus.setText("Targa già presente");
    	}
    	setInserting(false);
    }

    @FXML
    void onSearch(ActionEvent event) {
    	

    }

    void fieldRW(boolean set) {
    	txtDescrizione.setEditable(set);
    	txtKM.setEditable(set);
    	txtPrezzo.setEditable(set);
    	txtAnno.setEditable(set);
    }
    
    private void setInserting(boolean set) {
    	if (set) {
    		btnNew.setText("Cancel");
    	} else {
    		btnNew.setText("New");
    	}
		btnSave.setVisible(set);
		inserting=set; 
		fieldRW(inserting);
    }
    
    private void clearFields() {
    	txtTarga.clear();
    	txtDescrizione.clear();
    	txtAnno.clear();
    	txtPrezzo.clear();
    	txtKM.clear();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtTarga != null : "fx:id=\"txtTarga\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert btnNew != null : "fx:id=\"btnNew\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert txtDescrizione != null : "fx:id=\"txtDescrizione\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert txtKM != null : "fx:id=\"txtKM\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert txtPrezzo != null : "fx:id=\"txtPrezzo\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert btnModify != null : "fx:id=\"btnModify\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert btnList != null : "fx:id=\"btnList\" was not injected: check your FXML file 'ArchiveView.fxml'.";	
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        btnSave.disableProperty().bind(txtTarga.textProperty().isEmpty().or(
        		                       txtDescrizione.textProperty().isEmpty()).or(
        		                       txtKM.textProperty().isEmpty()).or(
        		                       txtPrezzo.textProperty().isEmpty()).or( 
        		                       txtAnno.textProperty().isEmpty()));
        txtTarga.textProperty().addListener((ov, oldValue, newValue) -> {
        	if (newValue.length()>7) txtTarga.setText(oldValue);
        	else txtTarga.setText(newValue.toUpperCase());
        	if (txtTarga.getText().isEmpty()||inserting) {
        		btnSearch.setDisable(true); 
        		txtStatus.clear();
        	} else {
        		btnSearch.setDisable(false); 
        	}       	
        });
        txtDescrizione.textProperty().addListener((ov, oldValue, newValue) -> {
        	if (newValue.length()>255) txtDescrizione.setText(oldValue);
        }); 
        txtKM.textProperty().addListener((ov, oldValue, newValue) -> {
        	 try {
                 // force numeric value by resetting to old value if exception is thrown
               	 Integer.parseInt(newValue);
                	 // force correct length by resetting to old value if longer than maxLength
               	 if( newValue.length() > 4)
               		 txtKM.setText(oldValue);
                 
             } catch (Exception e) {
            	 if (! newValue.isEmpty()) 
            		 txtKM.setText(oldValue);
            	 else txtKM.clear();
             }
        }); 
        txtAnno.textProperty().addListener((ov, oldValue, newValue) -> {
       	 try {
                // force numeric value by resetting to old value if exception is thrown
              	 Integer.parseInt(newValue);
               	 // force correct length by resetting to old value if longer than maxLength
              	 if( newValue.length() > 4)
              		txtAnno.setText(oldValue);
                
            } catch (Exception e) {
           	 if (! newValue.isEmpty()) 
           		txtAnno.setText(oldValue);
           	 else txtAnno.clear();
            }
        }); 
        txtPrezzo.textProperty().addListener((ov, oldValue, newValue) -> {
          	 try {
                   // force numeric value by resetting to old value if exception is thrown
                 	 Integer.parseInt(newValue);
                  	 // force correct length by resetting to old value if longer than maxLength
                 	 if( newValue.length() > 7)
                 		txtPrezzo.setText(oldValue);
               } catch (Exception e) {
              	 if (! newValue.isEmpty()) 
              		txtPrezzo.setText(oldValue);
              	 else txtPrezzo.clear();
               }
        }); 
    }

	 
	public void setModel(ManageAuto model) {
		this.model = model;
	}
    
}
