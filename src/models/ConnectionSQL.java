/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Thonon
 */
public class ConnectionSQL 
{
    private Connection con = null;
    
    private static String url = "jdbc:mysql://localhost:3306/db_prostipol";
    private static String user = "prostipol";
    private static String password = "prostipol";
    
    public ConnectionSQL() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
    {
      // chargement du fichier de configuration
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "prosticonfig" + System.getProperty("file.separator") + "prostipol.ini");
        properties.load(input);
        //url = properties.getProperty("url_database");
        this.Connect();
 
    }
    
    public void Connect() throws SQLException,ClassNotFoundException
    {
        try
        {
      // For Mysql
       Class.forName("com.mysql.jdbc.Driver");
        // For hsqldb
        //Class.forName("org.hsqldb.jdbcDriver");
        // For SqlLite
        //Class.forName("org.sqlite.JDBC");
       con = DriverManager.getConnection(url, user, password);
        }catch(SQLException sqe)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Connection failure");
            alert.setContentText("Probleme de connection : " + sqe.getMessage());
            alert.showAndWait();
            throw sqe;
        } 
        
    }
    
    public  Connection getCon() {
        if(con == null){
            try {
                this.Connect();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionSQL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        ConnectionSQL.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ConnectionSQL.password = password;
    }

   
    
    
    
}
