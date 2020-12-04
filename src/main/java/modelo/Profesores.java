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
import static modelo.Colegio.id;

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
    private String Cod_colegio;

    public Profesores() {
        array_profesores = new ArrayList<>();
    }

    
    
    public Profesores(int cod_prof, String nombre, String estudios, String rangos, String genero, String Cod_colegio) throws IOException {
        
        array_profesores = new ArrayList();
        
        this.array_profesores = LeerProfesoresFichero();
        
        if(this.array_profesores.size() - 1 >= 0){
            this.id = this.array_profesores.get(this.array_profesores.size() - 1).id + 1;
            System.out.println(id);
        } else {
            this.id = id++;
        }
        
        this.cod_prof = cod_prof;
        this.nombre = nombre;
        this.estudios = estudios;
        this.rangos = rangos;
        this.genero = genero;
        this.Cod_colegio = Cod_colegio;
    }

    
    public ArrayList LeerProfesoresFichero() throws IOException {

        File file = new File("FicheroProfesores.dat");
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(file));
        
        try {
            this.array_profesores  = ((ArrayList<Profesores>) dataIS.readObject());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Colegio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataIS.close();
        
        return this.array_profesores;

    }
    
    public void GuardarProfesoresFichero(ArrayList<Profesores> array_profesores) throws IOException{
        
        File file = new File("FicheroProfesores.dat");
        FileOutputStream fileout = null;
        ObjectOutputStream dataOS = null;
        try {
            fileout = new FileOutputStream(file);
            dataOS = new ObjectOutputStream(fileout);
            dataOS.writeObject(array_profesores);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Colegio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Colegio.class.getName()).log(Level.SEVERE, null, ex);
        }

        dataOS.close();
    }

    public void setCod_colegio(String Cod_colegio) {
        this.Cod_colegio = Cod_colegio;
    }

    public String getCod_colegio() {
        return Cod_colegio;
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
