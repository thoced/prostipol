/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import entity.MemoPersonneController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ModelPersonne;
import models.SingleModelDb;

/**
 *
 * @author thonon
 */
public class MemoFactory extends TableCell implements EventHandler{

   
    
    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        if(!empty){
          Button button = new Button("Memo");
            button.setOnAction(this);
            this.setGraphic(button);
        }
       
    }
    
    

    @Override
    public void handle(Event event) {
        if(event.getSource().getClass() == Button.class){
            ModelPersonne personne = (ModelPersonne) this.getTableRow().getItem();
            if(personne != null){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/entity/MemoPersonneView.fxml"));
                    BorderPane pane = loader.load();
                    MemoPersonneController controller = loader.getController();
                    if(controller != null){
                        controller.setPersonne(personne);
                        Scene scene = new Scene(pane);
                        Stage stage = new Stage();
                        stage.setTitle("Mémo pour: " + personne.toString());
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        if(stage.getUserData() != null){
                            if(stage.getUserData().equals("SAVE")){
                                ModelPersonne model = controller.getPersonne();
                                if(model != null){
                                SingleModelDb.getInstance().getModelListPersonne().update(model);
                                this.getTableView().refresh();
                                }

                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MemoFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
 
    
}
