/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.ModelContainerPays;
import models.ModelContainerSexe;
import models.ModelContainerStatut;
import models.ModelListPersonne;
import models.ModelPays;
import models.ModelPersonne;
import models.ModelSexe;
import models.ModelStatut;

/**
 * FXML Controller class
 *
 * @author thonon
 */
public class DialogPersonneViewController implements Initializable {

    private ModelListPersonne m_mlp;
    
    public enum MODE {INSERT,UPDATE};
    
    private MODE m_mode = MODE.INSERT;
    
    @FXML
    private BorderPane m_rootPane;
    @FXML
    private TextField m_textNom;
    @FXML
    private TextField m_textPrenom; 
    @FXML
    private DatePicker m_dateNaissance; 
    @FXML
    private ComboBox m_comboSexe;
   @FXML
    private TextField m_textSurnom; 
    @FXML
    private ComboBox m_comboNationalite;
    @FXML
    private ComboBox m_comboOrigine;
    @FXML
    private ComboBox m_comboStatut;
    @FXML
    private Button m_buttonOk; 
    @FXML
    private Button m_buttonCancel; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ModelContainerSexe model = new ModelContainerSexe();
        model.selectAll();
        m_comboSexe.setItems(model.getOb());
        m_comboSexe.getSelectionModel().selectFirst();
        ModelContainerPays pays = new ModelContainerPays();
        pays.selectAll();
        m_comboNationalite.setItems(pays.getOb());
        m_comboOrigine.setItems(pays.getOb());
        m_comboNationalite.getSelectionModel().selectFirst();
        m_comboOrigine.getSelectionModel().selectFirst();
        ModelContainerStatut statuts = new ModelContainerStatut();
        statuts.selectAll();
        m_comboStatut.setItems(statuts.getOb());
        m_comboStatut.getSelectionModel().selectFirst();
    }

    public void setModel(ModelListPersonne mlp){
        this.m_mlp = mlp;
    }

    @FXML
    public void OnSauvegarde(){
        
         ModelListPersonne model = new ModelListPersonne();
         ModelPersonne personne = new ModelPersonne();
         personne.setNom(m_textNom.getText());
         personne.setPrenom(m_textPrenom.getText());
         personne.setDateNaissance(m_dateNaissance.getValue());
         personne.setDateEncodage(Timestamp.valueOf(LocalDateTime.now()));
         personne.setDateUpdate(Timestamp.valueOf(LocalDateTime.now()));
         personne.setSexe((ModelSexe)m_comboSexe.getSelectionModel().getSelectedItem());
         personne.setSurnom(m_textSurnom.getText());
         personne.setNationalite((ModelPays)m_comboNationalite.getSelectionModel().getSelectedItem());
         personne.setOrigine((ModelPays)m_comboOrigine.getSelectionModel().getSelectedItem());
         personne.setStatut((ModelStatut)m_comboStatut.getSelectionModel().getSelectedItem());
         
         // verif par rapport à la date
         if(m_dateNaissance.getValue() == null){
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Date de naissance");
             alert.setContentText("Date de naissance manquante");
             alert.showAndWait();
             return;
         }
             
        if(m_mode == MODE.INSERT)
            model.insert(personne);
        else if(m_mode == MODE.UPDATE)
            model.update(personne);
       
        // fermeture
        this.OnCancel();
    }
    
    
    
    @FXML
    public void OnCancel(){
        m_rootPane.getScene().getWindow().hide();
    }

    public MODE getM_mode() {
        return m_mode;
    }

    public void setM_mode(MODE m_mode) {
        this.m_mode = m_mode;
    }
   
    
  
    
}
