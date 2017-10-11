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
public class ModelStatut extends ModelBase{

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty statut = new SimpleStringProperty();

    @Override
    public String toString() {
        return this.statut.getValue();
    }

    public String getStatut() {
        return statut.get();
    }

    public void setStatut(String value) {
        statut.set(value);
    }

    public StringProperty statutProperty() {
        return statut;
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
    
    
}
