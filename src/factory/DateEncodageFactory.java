/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.TableCell;

/**
 *
 * @author thonon
 */
public class DateEncodageFactory extends TableCell{

    private  DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm:ss");
    
    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        
        if(item != null){
             java.sql.Timestamp date = (java.sql.Timestamp) item;
             this.setText( date.toLocalDateTime().format(df));
        }
    }
    
    
}
