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
public class Profesores implements Serializable {

    private ArrayList<Profesores> array_profesores;
    private int cod_prof;
    public static int id;
    private String nombre;
    private String estudios;
    private String rangos;
    private String genero;
    private int id_colegio;

    public Profesores() {
        array_profesores = new ArrayList<>();
    }

    public Profesores(int cod_prof, String nombre, String estudios, String rangos, String genero, int id_colegio) throws IOException {

        array_profesores = new ArrayList();

        this.array_profesores = LeerProfesoresFichero();

        if (this.array_profesores.size() - 1 >= 0) {
            this.id = this.array_profesores.get(this.array_profesores.size() - 1).id + 1;
            System.out.println(id);
        } else {
            this.id = id++;
        }

        this.cod_prof = id;
        this.nombre = nombre;
        this.estudios = estudios;
        this.rangos = rangos;
        this.genero = genero;
        this.id_colegio = id_colegio;
    }

    public ArrayList LeerProfesoresFichero() throws IOException {

        this.array_profesores.clear();

        String url = "jdbc:sqlite:D://sqlite/profesores.db";

        String sql = "SELECT * FROM profesores";

        try ( Connection conn = DriverManager.getConnection(url);  Statement stmt = conn.createStatement()) {
            // create a new table

            ResultSet resultado = stmt.executeQuery(sql);

            while (resultado.next()) {
                Profesores colegio = new Profesores();

                colegio.setCod_prof(resultado.getInt(1));
                colegio.setNombre(resultado.getString(2));
                colegio.setEstudios(resultado.getString(3));
                colegio.setRangos(resultado.getString(4));
                colegio.setGenero(resultado.getString(5));
                colegio.setCod_colegio(resultado.getInt(7));

                this.array_profesores.add(colegio);
            }

            resultado.close();
            stmt.close();

            System.out.println("Se leyó la instancia");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.array_profesores;

    }

    public void GuardarProfesoresFichero(Profesores colegio) throws IOException {

        String url = "jdbc:sqlite:D://sqlite/profesores.db";

        String sql = "INSERT INTO profesores(id,cod_prof,nombre, estudios, rango, genero, cod_colegio) VALUES(?,?,?,?,?,?,?)";

        try ( Connection conn = DriverManager.getConnection(url);  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, colegio.id);
            pstmt.setInt(2, colegio.getCod_prof());
            pstmt.setString(3, colegio.getNombre());
            pstmt.setString(4, colegio.getEstudios());
            pstmt.setString(5, colegio.getRangos());
            pstmt.setString(6, colegio.getEstudios());
            pstmt.setInt(7, colegio.getCod_colegio());

            pstmt.executeUpdate();

            System.out.println("Se inserto instancia");
            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void DeleteProfesorFichero(int cod_colegio){
        
        String url = "jdbc:sqlite:D://sqlite/profesores.db";
        
        String sql = "DELETE FROM profesores WHERE cod_prof = ?";
        
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, cod_colegio);
            // execute the delete statement
            pstmt.executeUpdate();
            
            System.out.println("SE ELIMINÓ EL PROFESORES CON ID = " + cod_colegio);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void setCod_colegio(int Cod_colegio) {
        this.id_colegio = Cod_colegio;
    }

    public int getCod_colegio() {
        return id_colegio;
    }

    public void setCod_prof(int cod_prof) {
        this.cod_prof = cod_prof;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public void setRangos(String rangos) {
        this.rangos = rangos;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCod_prof() {
        return cod_prof;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstudios() {
        return estudios;
    }

    public String getRangos() {
        return rangos;
    }

    public String getGenero() {
        return genero;
    }
}
