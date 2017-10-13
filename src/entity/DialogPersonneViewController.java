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

    private ModelPersonne m_modelPersonne;
    
    private boolean m_isSauvegarde = false;
      
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

    public ModelPersonne getmModelPersonne() {
        return m_modelPersonne;
    }

    public boolean isSauvegarde() {
        return m_isSauvegarde;
    }
    
    

    public void setModelPersonne(ModelPersonne m_modelPersonne) {
        this.m_modelPersonne = m_modelPersonne;
        if(this.m_modelPersonne != null){
            m_textNom.setText(this.m_modelPersonne.getNom());
            m_textPrenom.setText(this.m_modelPersonne.getPrenom());
            m_dateNaissance.setValue(this.m_modelPersonne.getDateNaissance());
            m_comboSexe.setValue(this.m_modelPersonne.getSexe());
            m_textSurnom.setText(this.m_modelPersonne.getSurnom());
            m_comboNationalite.setValue(this.m_modelPersonne.getNationalite());
            m_comboOrigine.setValue(this.m_modelPersonne.getOrigine());
            m_comboStatut.setValue(this.m_modelPersonne.getStatut());
        }
    }

    @FXML
    public void OnSauvegarde(){
        
         if(m_modelPersonne == null)
             return;
         
         m_modelPersonne.setNom(m_textNom.getText());
         m_modelPersonne.setPrenom(m_textPrenom.getText());
         m_modelPersonne.setDateNaissance(m_dateNaissance.getValue());
         m_modelPersonne.setDateEncodage(Timestamp.valueOf(LocalDateTime.now()));
         m_modelPersonne.setDateUpdate(Timestamp.valueOf(LocalDateTime.now()));
         m_modelPersonne.setSexe((ModelSexe)m_comboSexe.getSelectionModel().getSelectedItem());
         m_modelPersonne.setSurnom(m_textSurnom.getText());
         m_modelPersonne.setNationalite((ModelPays)m_comboNationalite.getSelectionModel().getSelectedItem());
         m_modelPersonne.setOrigine((ModelPays)m_comboOrigine.getSelectionModel().getSelectedItem());
         m_modelPersonne.setStatut((ModelStatut)m_comboStatut.getSelectionModel().getSelectedItem());
         
         // verif par rapport à la date
         if(m_dateNaissance.getValue() == null){
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Date de naissance");
             alert.setContentText("Date de naissance manquante");
             alert.showAndWait();
             return;
         }
         
         if(m_textNom.getText() == null || m_textNom.getText().length() == 0){
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Nom");
             alert.setContentText("Un nom doit être indiqué");
             alert.showAndWait();
             return;
         }
         
         if(m_textPrenom.getText() == null || m_textPrenom.getText().length() == 0){
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Prenom");
             alert.setContentText("Un prénom doit être indiqué");
             alert.showAndWait();
             return;
         }
 
        this.m_isSauvegarde = true;
        // fermeture
        this.OnCancel();
    }
    
    
    
    @FXML
    public void OnCancel(){
       
        m_rootPane.getScene().getWindow().hide();
    }

}
