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

        File file = new File("FicheroAsignaturas.dat");
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(file));
        
        try {
            this.array_asignaturas  = ((ArrayList<Asignaturas>) dataIS.readObject());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Colegio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataIS.close();
        
        return this.array_asignaturas;

    }
    
    public void GuardarAsignaturasFichero(ArrayList<Asignaturas> array_asignaturas) throws IOException{
        
        File file = new File("FicheroAsignaturas.dat");
        FileOutputStream fileout = null;
        ObjectOutputStream dataOS = null;
        try {
            fileout = new FileOutputStream(file);
            dataOS = new ObjectOutputStream(fileout);
            dataOS.writeObject(array_asignaturas);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Colegio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Colegio.class.getName()).log(Level.SEVERE, null, ex);
        }

        dataOS.close();
    }
    
    public Asignaturas(int cod_asig, String tipo, int curso, String nombre, int convocatoria, int cod_prof) {
        this.cod_asig = cod_asig;
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
