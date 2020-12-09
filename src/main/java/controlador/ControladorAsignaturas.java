/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import modelo.Asignaturas;

/**
 *
 * @author artur
 */
public class ControladorAsignaturas implements Serializable{
    
    private ArrayList<Asignaturas> array_asignatuas;

    public ControladorAsignaturas() {
        array_asignatuas = new ArrayList();
    }

    public ArrayList<Asignaturas> getArray_asignatuas() {
        return array_asignatuas;
    }
    
    public Asignaturas getAsignaturaArrayAsignaturas(int i){
        return this.array_asignatuas.get(i);
    }
    
    public void CaptarAsignaturasFichero() throws IOException{
        Asignaturas asignatura = new Asignaturas();
        this.array_asignatuas = asignatura.LeerAsignaturasFichero();
    }
    
    public void GuardarAsignaturasFichero() throws IOException{
        Asignaturas asignaturas = new Asignaturas();
        asignaturas.GuardarAsignaturasFichero(this.array_asignatuas.get(this.array_asignatuas.size() - 1));
    }
    
    public void deleteAsignaturasFichero(int id){
        Asignaturas colegio = new Asignaturas();
        colegio.DeleteAsignaturaFichero(id);
    }
    
    public void updateAsignatura(Asignaturas colegio){
        colegio.ActualizarAsignaturas(colegio);
    }
    
    public String[][] introducirAsignaturasEnMatriz(){
        
        String contenido_tabla[][] = new String[this.getArray_asignatuas().size()][2];
        
        for (int i = 0; i < this.getArray_asignatuas().size(); i++) {

            contenido_tabla[i][0] = String.valueOf(this.getAsignaturaArrayAsignaturas(i).getNombre());
            contenido_tabla[i][1] = String.valueOf(this.getAsignaturaArrayAsignaturas(i).getTipo());
        }
        
        return contenido_tabla;
    }
    
    public void insertarAsignatura(Asignaturas colegio){
        
        array_asignatuas.add(colegio);
        
    }
    
    
}
