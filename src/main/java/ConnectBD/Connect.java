/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author arturo
 */
public class Connect {
    
    public static void connect() {
        
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:D:/sqlite/chinook.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
            conn.close();
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void createNewTableColegios() {
        // SQLite connection string
        String url = "jdbc:sqlite:D://sqlite/colegios.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS colegios (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	cod_colegio integer NOT NULL,\n"
                + "	nombre text NOT NULL,\n"
                + "     direccion text NOT NULL,\n"
                + "     espublico boolean NOT NULL,\n"
                + "     tienefp boolean NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            
            System.out.println("SE HA CREADO LA TABLA COLEGIOS");
            
            stmt.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
