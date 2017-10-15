/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import models.ModelPersonne;

/**
 * FXML Controller class
 *
 * @author thonon
 */
public class MemoPersonneController implements Initializable {

    @FXML
    private BorderPane m_root;
    @FXML
    private TextArea m_textMemo;
    
    private ModelPersonne personne;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    public ModelPersonne getPersonne() {
        return personne;
    }

    public void setPersonne(ModelPersonne personne) {
        this.personne = personne;
        if(this.personne != null){
            m_textMemo.setText(this.personne.getMemo());
        }
    }
    
    @FXML
    public void OnSave(){
        personne.setMemo(m_textMemo.getText());
        personne.setDateUpdate(Timestamp.valueOf(LocalDateTime.now()));
        m_root.getScene().getWindow().setUserData("SAVE");
        m_root.getScene().getWindow().hide();
    }
    
    @FXML
    public void OnCancel(){
        m_root.getScene().getWindow().setUserData("CANCEL");
        m_root.getScene().getWindow().hide();
    }

}
