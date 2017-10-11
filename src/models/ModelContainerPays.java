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
public class ModelContainerPays extends ModelContainerBase{

    private ObservableList<ModelPays> ob;

    public ModelContainerPays() {
        ob = FXCollections.observableArrayList();
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
            String sql = "select id,nom_en_gb from t_pays";
            Statement st = SingleModelDb.getInstance().getSql().getCon().createStatement();
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                ModelPays model = new ModelPays();
                model.setId(result.getInt("id"));
                model.setPays(result.getString("nom_en_gb"));
                ob.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelContainerPays.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<ModelPays> getOb() {
        return ob;
    }

}
