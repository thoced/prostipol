/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prostipol;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import models.ModelListPersonne;
import models.ModelPersonne;
import models.SingleModelDb;

/**
 *
 * @author thonon
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private BorderPane m_root;
    @FXML 
    public SplitPane m_splitVertical;
    
    @FXML
    public TreeView m_treeView;
    
    @FXML
    public Pane m_upperPane;
    
    @FXML
    public AnchorPane m_lowerPane;
    
    private BorderPane m_panePersonnes;
    
    private TreeItem<String> m_itemsPersonne;
    private TreeItem<String> m_itemsLieux;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

     ObservableList<String> items = FXCollections.observableArrayList();
     items.add("Personnes");
     items.add("Lieux");
     TreeItem<String> rootItem = new TreeItem<String>("ProstiPol");
     TreeItem<String> itemEntity = new TreeItem<String>("Entitées");
     TreeItem<String> itemBooking = new TreeItem<String>("Réservations");
     m_itemsPersonne = new TreeItem<String>("Personnes ()");
     m_itemsLieux = new TreeItem<String>("Lieux");
     
     rootItem.setExpanded(true);
     rootItem.getChildren().add(itemEntity);
     rootItem.getChildren().add(itemBooking);
     
     itemEntity.setExpanded(true);
     itemEntity.getChildren().add(m_itemsPersonne);
     itemEntity.getChildren().add(m_itemsLieux);
     
     itemBooking.setExpanded(true);
     
     // listener
       SingleModelDb.getInstance().getModelListPersonne().getOb().addListener(new ListChangeListener<ModelPersonne>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends ModelPersonne> c) {
              m_itemsPersonne.setValue("Personnes (" + c.getList().size() + ")");
            }
        });
     
     
     m_treeView.setRoot(rootItem);
     
        try {
            // upperpane
            // test
            m_panePersonnes = FXMLLoader.load(getClass().getResource("/entity/FXMLPersonne.fxml"));
           

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        try {
            SingleModelDb db = SingleModelDb.getInstance();
            db.getSql().Connect();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }    
    
    @FXML
    public void OnMouClickOnTreeView(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            TreeItem itemSelected = (TreeItem) m_treeView.getSelectionModel().getSelectedItem();
            if(itemSelected != null){               
                if(itemSelected == m_itemsPersonne){
                     m_splitVertical.getItems().clear();
                     m_splitVertical.getItems().add(m_panePersonnes);
                     m_splitVertical.getItems().add(new BorderPane());
                }else if(itemSelected == m_itemsLieux){
                     m_splitVertical.getItems().clear();
                    
                }
            }
        }
    }
    
    @FXML
    public void OnFermeture(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Fermeture");
        alert.setContentText("Etes-vous sûr de vouloir fermer l'application ?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
            m_root.getScene().getWindow().hide();
    }
    
}
