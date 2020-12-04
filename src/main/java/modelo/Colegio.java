/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author artur
 */
public class Colegio implements Serializable {

    private ArrayList<Colegio> array_colegio;
    public static int id;
    private int cod_colegio;
    private String nombre;
    private String direccion;
    private boolean esPublico;
    private boolean tieneFP;

    public Colegio() {
        array_colegio = new ArrayList();

    }

    public Colegio(int cod_colegio, String nombre, String direccion, boolean esPublico, boolean tieneFP) throws IOException {
        array_colegio = new ArrayList();
        
        this.array_colegio = LeerColegiosFichero();
        
        if(this.array_colegio.size() - 1 >= 0){
            this.id = this.array_colegio.get(this.array_colegio.size() - 1).id + 1;
            System.out.println(id);
        } else {
            this.id = id++;
        }
        
        this.setCod_colegio(id);
        this.setNombre(nombre);
        this.setDireccion(direccion);
        this.setEsPublico(esPublico);
        this.setTieneFP(tieneFP);
    }

    public ArrayList LeerColegiosFichero() throws IOException {
        
        this.array_colegio.clear();

        String url = "jdbc:sqlite:D://sqlite/colegios.db";
        
        String sql = "SELECT * FROM colegios";
        
        try (Connection conn = DriverManager.getConnection(url);
            
            Statement stmt = conn.createStatement()) {
            // create a new table
            
            ResultSet resultado = stmt.executeQuery(sql);
            
            while(resultado.next()){
                Colegio colegio = new Colegio();
                System.out.println("cod_colegio: " + resultado.getInt(1) + " Nombre: " + resultado.getString(2) + " Dirección: " + resultado.getString(3));
                
                colegio.id = resultado.getInt(1);
                colegio.setCod_colegio(resultado.getInt(2));
                colegio.setNombre(resultado.getString(3));
                colegio.setDireccion(resultado.getString(4));
                colegio.setEsPublico(resultado.getBoolean(5));
                colegio.setTieneFP(resultado.getBoolean(6));
                
                
                this.array_colegio.add(colegio);
            }
            
            resultado.close();
            stmt.close();
            
            System.out.println("Se leyó la instancia");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.array_colegio;

    }
    
    public void GuardarColegioFichero(Colegio colegio) throws IOException {

        String url = "jdbc:sqlite:D://sqlite/colegios.db";
        
        String sql = "INSERT INTO colegios(id,cod_colegio,nombre, direccion, espublico, tienefp) VALUES(?,?,?,?,?,?)";
        
        
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, colegio.id);
            pstmt.setInt(2, colegio.getCod_colegio());
            pstmt.setString(3, colegio.getNombre());
            pstmt.setString(4, colegio.getDireccion());
            pstmt.setBoolean(5, colegio.isEsPublico());
            pstmt.setBoolean(6, colegio.isTieneFP());
            
            pstmt.executeUpdate();
            
            System.out.println("Se inserto instancia");
            pstmt.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void DeleteColegioFichero(int cod_colegio){
        
        String url = "jdbc:sqlite:D://sqlite/colegios.db";
        
        String sql = "DELETE FROM colegios WHERE cod_colegio = ?";
        
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, cod_colegio);
            // execute the delete statement
            pstmt.executeUpdate();
            
            System.out.println("SE ELIMINÓ EL COLEGIO CON ID = " + cod_colegio);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void ActualizarColegios(Colegio colegio, int id){
        
        String url = "jdbc:sqlite:D://sqlite/colegios.db";
        
        String sql = "UPDATE colegios SET cod_colegio = " + colegio.getCod_colegio();
        
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, cod_colegio);
            // execute the delete statement
            pstmt.executeUpdate();
            
            System.out.println("SE ELIMINÓ EL COLEGIO CON ID = " + cod_colegio);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setCod_colegio(int cod_colegio) {

        this.cod_colegio = cod_colegio;

    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    public void setEsPublico(boolean EsPublico) {
        this.esPublico = EsPublico;
    }

    public void setTieneFP(boolean TieneFP) {
        this.tieneFP = TieneFP;
    }

    public int getCod_colegio() {
        return this.cod_colegio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public boolean isEsPublico() {
        return this.esPublico;
    }

    public boolean isTieneFP() {
        return this.tieneFP;
    }
}
