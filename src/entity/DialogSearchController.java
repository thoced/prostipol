/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import models.SingleModelDb;

/**
 *
 * @author thonon
 */
public class DialogSearchController implements Initializable {

    @FXML
    private BorderPane m_rootPane;
    @FXML
    private ComboBox m_comboType;
    @FXML
    private TextField m_textSearch;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        m_comboType.getItems().addAll("nom","prenom","surnom");
        
    }
  
    @FXML
    public void OnCancel(){
       
        m_rootPane.getScene().getWindow().setUserData("CANCEL");
        m_rootPane.getScene().getWindow().hide();
    }
    
    @FXML
    public void OnSearch(){
        
        SingleModelDb.getInstance().getModelListPersonne().searchWith(m_textSearch.getText(), (String)m_comboType.getSelectionModel().getSelectedItem());
        m_rootPane.getScene().getWindow().setUserData("SEARCH");
        m_rootPane.getScene().getWindow().hide();
    }
    
}
