/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author thonon
 */
public class ModelSexe {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty sexe = new SimpleStringProperty();

    public String getSexe() {
        return sexe.get();
    }

    public void setSexe(String value) {
        sexe.set(value);
    }

    public StringProperty sexeProperty() {
        return sexe;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return sexe.get(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
