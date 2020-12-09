/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import modelo.Colegio;
import modelo.Profesores;

/**
 *
 * @author artur
 */
public class ControladorProfesores implements Serializable {
    
    private ArrayList<Profesores> array_profesores;

    public ControladorProfesores() {
        this.array_profesores = new ArrayList();
    }
    
    public ArrayList<Profesores> getArray_profesores() {
        return array_profesores;
    }
    
    public Profesores getProfesoresDeArray(int i){
        return array_profesores.get(i);
    }
    
    public ArrayList CaptarCod_profEnJComboBox(){
        ArrayList<String> cod_profesores = new ArrayList();
        for(int i = 0; i < getArray_profesores().size(); i++){
            cod_profesores.add(getProfesoresDeArray(i).getNombre());
        }
        return cod_profesores;
    }
    
    public void CaptarProfesoresFichero() throws IOException{
        Profesores profesores = new Profesores();
        this.array_profesores = profesores.LeerProfesoresFichero();
    }
    
    public void GuardarProfesoresFichero() throws IOException{
        Profesores profesor = new Profesores();
        profesor.GuardarProfesoresFichero(this.array_profesores.get(this.array_profesores.size() - 1));
    }
    
    public void deleteProfesoresFichero(int id){
        Profesores colegio = new Profesores();
        colegio.DeleteProfesorFichero(id);
    }
    
    public void updateProfesor(Profesores colegio){
        colegio.ActualizarProfesores(colegio);
    }
    
    public String[][] introducirProfesorEnMatriz(){
        
        String contenido_tabla[][] = new String[this.getArray_profesores().size()][5];
        
        for (int i = 0; i < this.getArray_profesores().size(); i++) {
            contenido_tabla[i][0] = String.valueOf(this.getProfesoresDeArray(i).getNombre());
            contenido_tabla[i][1] = String.valueOf(this.getProfesoresDeArray(i).getEstudios());
        }
        
        return contenido_tabla;
    }
    
    public void insertarProfesor(Profesores colegio){
        
        array_profesores.add(colegio);
        
    }
    
    
}
