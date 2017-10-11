/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author thonon
 */
public class ModelListPersonne extends ModelContainerBase{
    private ObservableList<ModelPersonne> ob;

    public ModelListPersonne() {
         ob = FXCollections.observableArrayList();
    }

    public ObservableList<ModelPersonne> getOb() {
        return ob;
    }

    @Override
    public void insert(ModelBase model) {
        ModelPersonne mp = (ModelPersonne) model;
         try {
            Connection con = SingleModelDb.getInstance().getSql().getCon();
            String sql = "INSERT INTO t_personnes (nom,prenom,ref_sexe,surnom,ref_nationalite,ref_origine,date_naissance,date_encodage,date_update,ref_statut) VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);        
            ps.setString(1, mp.getNom());
            ps.setString(2, mp.getPrenom());
            ps.setInt(3, mp.getSexe().getId());
            ps.setString(4, mp.getSurnom());
            ps.setInt(5, mp.getNationalite().getId());
            ps.setInt(6, mp.getOrigine().getId());
            ps.setDate(7,java.sql.Date.valueOf(mp.getDateNaissance()));
            ps.setTimestamp(8, mp.getDateEncodage());
            ps.setTimestamp(9, mp.getDateUpdate());
            ps.setInt(10,mp.getStatut().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModelListPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        ob.clear();
         try {
            Connection con = SingleModelDb.getInstance().getSql().getCon();
            String sql = "select * from t_personnes INNER JOIN t_statut ON t_personnes.ref_statut = t_statut.id INNER JOIN t_sexe ON t_personnes.ref_sexe = t_sexe.id INNER JOIN t_pays ON t_personnes.ref_nationalite = t_pays.id AND t_personnes.ref_origine = t_pays.id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                ModelPersonne mp = new ModelPersonne();
                mp.setId(result.getInt("id"));
                mp.setNom(result.getString("nom"));
                mp.setPrenom(result.getString("prenom"));
                mp.setDateNaissance(result.getDate("date_naissance").toLocalDate());
                ModelSexe m = new ModelSexe();
                m.setId(result.getInt("t_personnes.ref_sexe"));
                m.setSexe(result.getString("t_sexe.sexe"));
                mp.setSexe(m);
                mp.setSurnom(result.getString("surnom"));
                ModelPays pn = new ModelPays();
                pn.setId(result.getInt("t_personnes.ref_nationalite"));
                pn.setPays(result.getString("t_pays.nom_en_gb"));
                mp.setNationalite(pn);
                ModelPays po = new ModelPays();
                po.setId(result.getInt("t_personnes.ref_origine"));
                po.setPays(result.getString("t_pays.nom_en_gb"));
                mp.setOrigine(po);
                mp.setDateEncodage(result.getTimestamp("date_encodage"));
                mp.setDateUpdate(result.getTimestamp("date_update"));
                ModelStatut st = new ModelStatut();
                st.setId(result.getInt("t_personnes.ref_statut"));
                st.setStatut(result.getString("t_statut.statut"));
                mp.setStatut(st);
                ob.add(mp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelListPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
