/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author thonon
 */
public class ModelPersonne extends ModelBase{

    private final IntegerProperty id = new SimpleIntegerProperty();

   
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> dateNaissance = new SimpleObjectProperty<>();
    private final ObjectProperty<ModelSexe> sexe = new SimpleObjectProperty<>();
    private final StringProperty surnom = new SimpleStringProperty();
    private final ObjectProperty<ModelPays> nationalite = new SimpleObjectProperty<>();
    private final ObjectProperty<ModelPays> origine = new SimpleObjectProperty<>();
    private final ObjectProperty<Object> photo = new SimpleObjectProperty<>();
    private final ObjectProperty<Timestamp> dateEncodage = new SimpleObjectProperty<>();
    private final ObjectProperty<Timestamp> dateUpdate = new SimpleObjectProperty<>();
    private final ObjectProperty<ModelStatut> statut = new SimpleObjectProperty<>();

    public ModelStatut getStatut() {
        return statut.get();
    }

    public void setStatut(ModelStatut value) {
        statut.set(value);
    }

    public ObjectProperty statutProperty() {
        return statut;
    }
    

    public Timestamp getDateEncodage() {
        return dateEncodage.get();
    }

    public void setDateEncodage(Timestamp value) {
        dateEncodage.set(value);
    }

    public ObjectProperty dateEncodageProperty() {
        return dateEncodage;
    }
    
      public Timestamp getDateUpdate() {
        return dateUpdate.get();
    }

    public void setDateUpdate(Timestamp value) {
        dateUpdate.set(value);
    }

    public ObjectProperty dateUpdateProperty() {
        return dateUpdate;
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

    public Object getPhoto() {
        return photo.get();
    }

    public void setPhoto(Object value) {
        photo.set(value);
    }

    public ObjectProperty photoProperty() {
        return photo;
    }
    
    public String getSurnom() {
        return surnom.get();
    }

    public void setSurnom(String value) {
        surnom.set(value);
    }

    public StringProperty surnomProperty() {
        return surnom;
    }
    
    public LocalDate getDateNaissance() {
        return dateNaissance.get();
    }

    public void setDateNaissance(LocalDate value) {
        dateNaissance.set(value);
    }

    public ObjectProperty dateNaissanceProperty() {
        return dateNaissance;
    }
    

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String value) {
        prenom.set(value);
    }

    public StringProperty prenomProperty() {
        return prenom;
    }
    

    public String getNom() {
        return nom.get();
    }

    public void setNom(String value) {
        nom.set(value);
    }

    public StringProperty nomProperty() {
        return nom;
    }
    
    public ModelSexe getSexe() {
        return sexe.get();
    }

    public void setSexe(ModelSexe value) {
        sexe.set(value);
    }

    public ObjectProperty sexeProperty() {
        return sexe;
    }
    
    public ModelPays getOrigine() {
        return origine.get();
    }

    public void setOrigine(ModelPays value) {
        origine.set(value);
    }

    public ObjectProperty origineProperty() {
        return origine;
    }
    
    public ModelPays getNationalite() {
        return nationalite.get();
    }

    public void setNationalite(ModelPays value) {
        nationalite.set(value);
    }

    public ObjectProperty nationaliteProperty() {
        return nationalite;
    }
 
}
