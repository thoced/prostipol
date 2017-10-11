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
public class ModelPays extends ModelBase{

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty pays = new SimpleStringProperty();

    @Override
    public String toString() {
        return pays.get();
    }

    public String getPays() {
        return pays.get();
    }

    public void setPays(String value) {
        pays.set(value);
    }

    public StringProperty paysProperty() {
        return pays;
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
