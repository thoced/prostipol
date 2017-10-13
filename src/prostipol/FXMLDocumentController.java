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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import models.ModelListPersonne;
import models.SingleModelDb;

/**
 *
 * @author thonon
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML 
    public SplitPane m_splitVertical;
    
    @FXML
    public TreeView m_treeView;
    
    @FXML
    public Pane m_upperPane;
    
    @FXML
    public AnchorPane m_lowerPane;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
        
     // reception nombre total de personne
     ModelListPersonne model = SingleModelDb.getInstance().getModelListPersonne();
     int totalPersonne = model.getTotalPersonnes();
     
     ObservableList<String> items = FXCollections.observableArrayList();
     items.add("Personnes");
     items.add("Lieux");

     TreeItem<String> rootItem = new TreeItem<String>("ProstiPol");
     TreeItem<String> itemEntity = new TreeItem<String>("Entitées");
     TreeItem<String> itemBooking = new TreeItem<String>("Réservations");
     
     TreeItem<String> item1 = new TreeItem<String>("Personnes (" + totalPersonne + ")");
     TreeItem<String> item2 = new TreeItem<String>("Lieux");
     
     rootItem.setExpanded(true);
     rootItem.getChildren().add(itemEntity);
     rootItem.getChildren().add(itemBooking);
     
     itemEntity.setExpanded(true);
     itemEntity.getChildren().add(item1);
     itemEntity.getChildren().add(item2);
     
     itemBooking.setExpanded(true);
     
     m_treeView.setRoot(rootItem);
     
        try {
            // upperpane
            // test
            BorderPane border = FXMLLoader.load(getClass().getResource("/entity/FXMLPersonne.fxml"));
            m_splitVertical.getItems().set(0,border);

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
    
    
    
}
