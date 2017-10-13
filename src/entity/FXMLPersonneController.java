/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.ModelListPersonne;
import models.ModelPersonne;
import models.ModelSexe;
import models.SingleModelDb;

/**
 * FXML Controller class
 *
 * @author thonon
 */
public class FXMLPersonneController implements Initializable,EventHandler<ActionEvent> {

    @FXML
    public TableView m_tablePersonne;
    @FXML
    public TableColumn<ModelPersonne,String> m_columnNom;
    @FXML
    public TableColumn<ModelPersonne,String> m_columnPrenom;
    @FXML
    public TableColumn<ModelPersonne,LocalDate> m_columnDateNaissance;
    @FXML
    public TableColumn<ModelPersonne,String> m_columnSexe;
    @FXML
    public TableColumn<ModelPersonne,String> m_columnSurnom;
    @FXML
    public TableColumn<ModelPersonne,String> m_columnNationalite;
    @FXML
    public TableColumn<ModelPersonne,String> m_columnOrigine;
    @FXML
    public TableColumn<ModelPersonne,LocalDate> m_columnDateEncodage;
    @FXML
    public TableColumn<ModelPersonne,LocalDate> m_columnDateUpdate;
    @FXML
    public TableColumn<ModelPersonne,String> m_columnStatut;
    
    @FXML
    public Button m_buttonAdd;
    @FXML
    public Button m_buttonDel;
    
    
    private ModelListPersonne model; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            model = SingleModelDb.getInstance().getModelListPersonne();
            model.selectAll();
            m_tablePersonne.setItems(model.getOb());
            m_columnNom.setCellValueFactory(cellData->cellData.getValue().nomProperty());
            m_columnPrenom.setCellValueFactory(cellData->cellData.getValue().prenomProperty());
            m_columnDateNaissance.setCellValueFactory(cellData->cellData.getValue().dateNaissanceProperty());
            m_columnSexe.setCellValueFactory(cellData->cellData.getValue().sexeProperty());
            m_columnSurnom.setCellValueFactory(cellData->cellData.getValue().surnomProperty());
            m_columnNationalite.setCellValueFactory(cellData->cellData.getValue().nationaliteProperty());
            m_columnOrigine.setCellValueFactory(cellData->cellData.getValue().origineProperty());
            m_columnDateEncodage.setCellValueFactory(cellData->cellData.getValue().dateEncodageProperty());
            m_columnDateUpdate.setCellValueFactory(cellData->cellData.getValue().dateUpdateProperty());
            m_columnStatut.setCellValueFactory(cellData->cellData.getValue().statutProperty());
            m_buttonAdd.setOnAction(this);
            m_buttonDel.setOnAction(this);

    }    

    @FXML
    public void OnMouseClick(MouseEvent event){
      
        if(event.getButton() == MouseButton.SECONDARY){
             System.out.println("click");
            ModelPersonne personne = (ModelPersonne) m_tablePersonne.getSelectionModel().getSelectedItem();
            if(personne != null){
                try {
                    // ouverture de la fenetre de mofication
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/entity/DialogPersonneView.fxml"));
                    BorderPane pane = loader.load();
                    DialogPersonneViewController controller = loader.getController();
                    if(controller != null){
                        controller.setModelPersonne(personne);
                    }
                    Scene scene = new Scene(pane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    if(controller.isSauvegarde())
                        model.update(personne);
                     // refresh
                    model.selectAll();
                    m_tablePersonne.refresh();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLPersonneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public void handle(ActionEvent event) {
       
        if(event.getEventType() == ActionEvent.ACTION){
            if(event.getSource() == m_buttonAdd){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/entity/DialogPersonneView.fxml"));
                    BorderPane pane = loader.load();
                    DialogPersonneViewController controller = loader.getController();
                    ModelPersonne modelPersonne = new ModelPersonne();
                    if(controller != null){
                        controller.setModelPersonne(modelPersonne);
                    }
                    Scene scene = new Scene(pane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    // ajout du modelPersonne dans la Db
                    if(controller.isSauvegarde())
                        model.insert(modelPersonne);
                    // refresh
                    model.selectAll();
                    m_tablePersonne.refresh();
                   
                } catch (IOException ex) {
                    Logger.getLogger(FXMLPersonneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(event.getSource() == m_buttonDel){
                
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Suppression");
                    alert.setContentText("Etes-vous sûr de vouloir supprimer cette personne ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.CANCEL)
                        return;
            
                
                if(m_tablePersonne.getSelectionModel().getSelectedItem() != null){
                   ModelPersonne personne = (ModelPersonne) m_tablePersonne.getSelectionModel().getSelectedItem();
                   if(personne != null){
                       model.delete(personne);
                       model.selectAll();
                       m_tablePersonne.refresh();
                   }
                }
            }
        }
    }

    
    
}
