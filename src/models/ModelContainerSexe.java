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
public class ModelContainerSexe extends ModelContainerBase{

    private ObservableList<ModelSexe> ob;

    public ModelContainerSexe() {
        ob = FXCollections.observableArrayList();
    }

    public ObservableList<ModelSexe> getOb() {
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
            String sql = "select * from t_sexe";
            Statement st = SingleModelDb.getInstance().getSql().getCon().createStatement();
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                ModelSexe sexe = new ModelSexe();
                sexe.setId(result.getInt("id"));
                sexe.setSexe(result.getString("sexe"));
                ob.add(sexe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelContainerSexe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
