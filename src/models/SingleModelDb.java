/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thonon
 */
public class SingleModelDb {
    
    protected static SingleModelDb _instance = null;
    
    protected ConnectionSQL _sql;
    
    // Model
    protected ModelListPersonne _modelListPersonne;

    public ConnectionSQL getSql() {
        return _sql;
    }

    public ModelListPersonne getModelListPersonne() {
        return _modelListPersonne;
    }

    protected SingleModelDb(){
        try {
            _sql = new ConnectionSQL();
            // modelListPersonne
            _modelListPersonne = new ModelListPersonne();
            
        } catch (SQLException ex) {
            Logger.getLogger(SingleModelDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SingleModelDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SingleModelDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SingleModelDb getInstance(){
        if(_instance != null)
            return _instance;
        else{
            _instance = new SingleModelDb();
            return _instance;
        }
    }
}
