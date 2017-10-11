/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author thonon
 */
public class ModelContainerStatut extends ModelContainerBase{

    private ObservableList<ModelStatut> ob;

    public ModelContainerStatut() {
        ob = FXCollections.observableArrayList();
    }
    
    public ObservableList<ModelStatut> getOb() {
        return ob;
    }

    @Override
    public void insert(ModelBase model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ModelBase model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ModelBase model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selectAll() {
        try {
            ob.clear();
            String sql = "select * from t_statut";
            Statement st = SingleModelDb.getInstance().getSql().getCon().createStatement();
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                ModelStatut statut = new ModelStatut();
                statut.setId(result.getInt("id"));
                statut.setStatut(result.getString("statut"));
                ob.add(statut);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelContainerStatut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
