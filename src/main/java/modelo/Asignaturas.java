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
public class Asignaturas implements Serializable{
    
    private ArrayList<Asignaturas> array_asignaturas;
    
    private int cod_asig;
    public static int id;
    private String tipo;
    private int curso;
    private String nombre;
    private int convocatoria;
    private int cod_prof;

    public Asignaturas() {
        array_asignaturas = new ArrayList<>();
        this.cod_prof = 0;
    }

    public ArrayList LeerAsignaturasFichero() throws IOException {

        this.array_asignaturas.clear();

        String url = "jdbc:sqlite:D://sqlite/asignaturas.db";
        
        String sql = "SELECT * FROM asignaturas";
        
        try (Connection conn = DriverManager.getConnection(url);
            
            Statement stmt = conn.createStatement()) {
            // create a new table
            
            ResultSet resultado = stmt.executeQuery(sql);
            
            while(resultado.next()){
                Asignaturas colegio = new Asignaturas();
                
                
                colegio.id = resultado.getInt(1);
                colegio.setCod_asig(resultado.getInt(2));
                colegio.setTipo(resultado.getString(3));
                colegio.setCurso(resultado.getInt(4));
                colegio.setNombre(resultado.getString(5));
                colegio.setConvocatoria(resultado.getInt(6));
                colegio.setCod_prof(resultado.getInt(7));
                
                
                this.array_asignaturas.add(colegio);
            }
            
            resultado.close();
            stmt.close();
            
            System.out.println("Se leyó la instancia");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return this.array_asignaturas;

    }
    
    public void GuardarAsignaturasFichero(Asignaturas colegio) throws IOException{
        
        String url = "jdbc:sqlite:D://sqlite/asignaturas.db";

        String sql = "INSERT INTO asignaturas(id,cod_asig,tipo, curso, nombre, convocatoria, cod_prof) VALUES(?,?,?,?,?,?,?)";

        try ( Connection conn = DriverManager.getConnection(url);  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, colegio.id);
            pstmt.setInt(2, colegio.getCod_asig());
            pstmt.setString(3, colegio.getTipo());
            pstmt.setInt(4, colegio.getCurso());
            pstmt.setString(5, colegio.getNombre());
            pstmt.setInt(6, colegio.getConvocatoria());
            pstmt.setInt(7, colegio.getCod_prof());

            pstmt.executeUpdate();

            System.out.println("Se inserto instancia");
            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void DeleteAsignaturaFichero(int cod_colegio){
        
        String url = "jdbc:sqlite:D://sqlite/asignaturas.db";
        
        String sql = "DELETE FROM asignaturas WHERE cod_asig = ?";
        
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, cod_colegio);
            // execute the delete statement
            pstmt.executeUpdate();
            
            System.out.println("SE ELIMINÓ EL ASIGNATURAS CON ID = " + cod_colegio);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public Asignaturas(int cod_asig, String tipo, int curso, String nombre, int convocatoria, int cod_prof) throws IOException {
        
        array_asignaturas = new ArrayList();
        
        this.array_asignaturas = LeerAsignaturasFichero();
        
        if(this.array_asignaturas.size() - 1 >= 0){
            this.id = this.array_asignaturas.get(this.array_asignaturas.size() - 1).id + 1;
            System.out.println(id);
        } else {
            this.id = id++;
        }
        
        this.cod_asig = id;
        this.tipo = tipo;
        this.curso = curso;
        this.nombre = nombre;
        this.convocatoria = convocatoria;
        this.cod_prof = cod_prof;
        
    }

    
    
    public void setCod_asig(int cod_asig) {
        this.cod_asig = cod_asig;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setConvocatoria(int convocatoria) {
        this.convocatoria = convocatoria;
    }

    public int getCod_asig() {
        return cod_asig;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCurso() {
        return curso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getConvocatoria() {
        return convocatoria;
    }

    public void setCod_prof(int cod_prof) {
        this.cod_prof = cod_prof;
    }

    public int getCod_prof() {
        return cod_prof;
    }
    
    
}
