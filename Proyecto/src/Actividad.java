package examen.u2;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class Actividad {
    private Empleado empleadoResponsable;
    private int idAnimal;
    private String procesoRealizado;
    private Date fechaProceso;
    private String observaciones;

    public Actividad(Empleado empleadoResponsable, int idAnimal, String procesoRealizado, Date fechaProceso, String observaciones) {
        this.empleadoResponsable = empleadoResponsable;
        this.idAnimal = idAnimal;
        this.procesoRealizado = procesoRealizado;
        this.fechaProceso = fechaProceso;
        this.observaciones = observaciones;
    }

    // Getters and setters

    public Empleado getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    public void setEmpleadoResponsable(Empleado empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }   

    public String getProcesoRealizado() {
        return procesoRealizado;
    }

    public void setProcesoRealizado(String procesoRealizado) {
        this.procesoRealizado = procesoRealizado;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return String.format("Actividad: %s\nFecha: %tF\nEmpleado responsable: %s\nID animal: %d\nObservaciones: %s",
                procesoRealizado, fechaProceso, empleadoResponsable.getNombre(), idAnimal, observaciones);
    }
}
