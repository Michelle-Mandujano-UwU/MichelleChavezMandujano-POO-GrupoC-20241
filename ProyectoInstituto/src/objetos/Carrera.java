package objetos;

import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author
 */
public class Carrera {
    private int id;
    private String nombreCarrera;
    private int cantidadGrupos;
    private int cantidadAlumnos;
    private int cantidadMaterias;
    private String fechaCreacion;
    
    private int idCoordinador;
       
    public Carrera(int id, String nombreCarrera,int cantidadAlumnos, 
            int cantidadMaterias, int cantidadGrupos, 
            String fechaCreacion, int idCoordinador) {
        
        this.id = id;
        this.nombreCarrera = nombreCarrera;
        this.cantidadGrupos = cantidadGrupos;
        this.cantidadAlumnos = cantidadAlumnos;
        this.cantidadMaterias = cantidadMaterias;
        this.fechaCreacion = fechaCreacion;
        this.idCoordinador = idCoordinador;
    }

    public Carrera(JSONObject jsonCarrera) {
        
        this.id = Integer.parseInt(jsonCarrera.get("id").toString());
        this.nombreCarrera = jsonCarrera.get("nombreCarrera").toString();
        this.fechaCreacion = jsonCarrera.get("fechaCreacion").toString();
        this.cantidadGrupos = Integer.parseInt(jsonCarrera.get("cantidadGrupos").toString());
        this.cantidadMaterias = Integer.parseInt(jsonCarrera.get("cantidadMaterias").toString());
        this.cantidadAlumnos = Integer.parseInt(jsonCarrera.get("cantidadAlumnos").toString());
        if(!jsonCarrera.get("idCoordinador").toString().isEmpty()){
            this.idCoordinador = Integer.parseInt(jsonCarrera.get("idCoordinador").toString()); 
        }else{
            this.idCoordinador = -1;
        }
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

}
