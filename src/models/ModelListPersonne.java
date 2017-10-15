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
import java.sql.Statement;
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

    public void searchWith(String search,String type){
        try {
            ob.clear();
            Connection con = SingleModelDb.getInstance().getSql().getCon();
            String sql = null;
            switch(type){
                case "nom": sql = "select * from t_personnes INNER JOIN t_sexe ON t_personnes.ref_sexe = t_sexe.id "
                        +                              "INNER JOIN t_statut ON t_personnes.ref_statut = t_statut.id "
                        +                              "INNER JOIN t_pays A1 ON t_personnes.ref_nationalite = A1.id "
                        +                              "INNER JOIN t_pays A2 ON t_personnes.ref_origine = A2.id WHERE t_personnes.nom LIKE ?";
                break;
                
                case "prenom": sql = "select * from t_personnes INNER JOIN t_sexe ON t_personnes.ref_sexe = t_sexe.id "
                        +                              "INNER JOIN t_statut ON t_personnes.ref_statut = t_statut.id "
                        +                              "INNER JOIN t_pays A1 ON t_personnes.ref_nationalite = A1.id "
                        +                              "INNER JOIN t_pays A2 ON t_personnes.ref_origine = A2.id WHERE t_personnes.prenom LIKE ?";
                break;
                
                case "surnom": sql = "select * from t_personnes INNER JOIN t_sexe ON t_personnes.ref_sexe = t_sexe.id "
                        +                              "INNER JOIN t_statut ON t_personnes.ref_statut = t_statut.id "
                        +                              "INNER JOIN t_pays A1 ON t_personnes.ref_nationalite = A1.id "
                        +                              "INNER JOIN t_pays A2 ON t_personnes.ref_origine = A2.id WHERE t_personnes.surnom LIKE ?";
                break;
            }
            
            PreparedStatement ps = con.prepareStatement(sql);
            search = "%" + search + "%";
            ps.setString(1, search);
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
                pn.setPays(result.getString("A1.nom_en_gb"));
                mp.setNationalite(pn);
                ModelPays po = new ModelPays();
                po.setId(result.getInt("t_personnes.ref_origine"));
                po.setPays(result.getString("A2.nom_en_gb"));
                mp.setOrigine(po);
                mp.setDateEncodage(result.getTimestamp("date_encodage"));
                mp.setDateUpdate(result.getTimestamp("date_update"));
                ModelStatut st = new ModelStatut();
                st.setId(result.getInt("t_personnes.ref_statut"));
                st.setStatut(result.getString("t_statut.statut"));
                mp.setStatut(st);
                mp.setMemo(result.getString("t_personnes.memo"));
                ob.add(mp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelListPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
       
  
    }
    
    public int getTotalPersonnes()
    {
        int total = 0;
        try {
            Connection con = SingleModelDb.getInstance().getSql().getCon();
            String sql = "select COUNT(id) AS total from t_personnes";
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(sql);
            if(result != null){
                result.first();
                total = result.getInt("total");
            }
  
        } catch (SQLException ex) {
            Logger.getLogger(ModelListPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
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
       if(model != null){
          ModelPersonne personne = (ModelPersonne)model;
           try {
               Connection con = SingleModelDb.getInstance().getSql().getCon();
               String sql = "delete from t_personnes where id = ?";
               PreparedStatement ps = con.prepareStatement(sql);
               ps.setInt(1,personne.getId());
               ps.executeUpdate();
           } catch (SQLException ex) {
               Logger.getLogger(ModelListPersonne.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
    }

    @Override
    public void update(ModelBase model) {
        ModelPersonne personne = (ModelPersonne) model;
        try {
            Connection con = SingleModelDb.getInstance().getSql().getCon();
            String sql = "update t_personnes set nom = ?,"
                    + "prenom = ?,"
                    + "ref_sexe = ?,"
                    + "surnom = ?,"
                    + "ref_nationalite = ?,"
                    + "ref_origine = ?,"
                    + "date_naissance = ?,"
                    + "date_update = ?,"
                    + "ref_statut = ?, "
                    + "memo = ? "
                    + "WHERE id = ?";
                    
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, personne.getNom());
            ps.setString(2, personne.getPrenom());
            ps.setInt(3, personne.getSexe().getId());
            ps.setString(4, personne.getSurnom());
            ps.setInt(5, personne.getNationalite().getId());
            ps.setInt(6, personne.getOrigine().getId());
            ps.setDate(7, java.sql.Date.valueOf(personne.getDateNaissance()));
            ps.setTimestamp(8, personne.getDateUpdate());
            ps.setInt(9, personne.getStatut().getId());
            ps.setString(10, personne.getMemo());
            ps.setInt(11, personne.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModelListPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void selectAll() {
        ob.clear();
         try {
            Connection con = SingleModelDb.getInstance().getSql().getCon();
            String sql = "select * from t_personnes INNER JOIN t_sexe ON t_personnes.ref_sexe = t_sexe.id "
                    +                              "INNER JOIN t_statut ON t_personnes.ref_statut = t_statut.id "
                    +                              "INNER JOIN t_pays A1 ON t_personnes.ref_nationalite = A1.id "
                    +                              "INNER JOIN t_pays A2 ON t_personnes.ref_origine = A2.id";
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
                pn.setPays(result.getString("A1.nom_en_gb"));
                mp.setNationalite(pn);
                ModelPays po = new ModelPays();
                po.setId(result.getInt("t_personnes.ref_origine"));
                po.setPays(result.getString("A2.nom_en_gb"));
                mp.setOrigine(po);
                mp.setDateEncodage(result.getTimestamp("date_encodage"));
                mp.setDateUpdate(result.getTimestamp("date_update"));
                ModelStatut st = new ModelStatut();
                st.setId(result.getInt("t_personnes.ref_statut"));
                st.setStatut(result.getString("t_statut.statut"));
                mp.setStatut(st);
                mp.setMemo(result.getString("t_personnes.memo"));
                ob.add(mp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelListPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         System.out.println(ob.size());
    }
    
}
