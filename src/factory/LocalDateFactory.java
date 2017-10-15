/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import models.ModelPersonne;

/**
 *
 * @author thonon
 */
public class LocalDateFactory extends TableCell{

    private  DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        if(item != null){
        LocalDate date = (LocalDate) item;
        this.setText( date.format(df));
        }
    }
    
    
    
}