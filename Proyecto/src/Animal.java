package examen.u2;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Random;

public class Animal {
    private String nombreAnimal;
    private String tipoAnimal;
    private LocalDate fechaNacimiento;
    private LocalDate fechaLlegadaZoologico;
    private double peso;
    private ArrayList<String> enfermedades;
    private String frecuenciaAlimentacion;
    private String tipoAlimentacion;
    private Boolean cuentaConVacunas;
    private int idAnimal;
    
    //Constructor
    public Animal(String nombreAnimal,String tipoAnimal, int idAnimal, LocalDate fechaNacimiento, LocalDate fechaLlegadaZoologico, double peso, ArrayList<String> enfermedades, String frecuenciaAlimentacion, String tipoAlimentacion, Boolean cuentaConVacunas) {
        this.nombreAnimal = nombreAnimal;
        this.tipoAnimal = tipoAnimal;
        this.idAnimal= idAnimal;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaLlegadaZoologico = fechaLlegadaZoologico;
        this.peso = peso;
        this.enfermedades = enfermedades;
        this.frecuenciaAlimentacion = frecuenciaAlimentacion;
        this.tipoAlimentacion = tipoAlimentacion;
        this.cuentaConVacunas = cuentaConVacunas;
    }

    public Animal() {}
    
    public String getTipoAnimal() {    
        return tipoAnimal;
    }
    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;    
    }

    public String getNombreAnimal() {
        return nombreAnimal;
    }
    public void setNombreAnimal(String nombreAnimal) {    
        this.nombreAnimal = nombreAnimal;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaLlegadaZoologico() {
        return fechaLlegadaZoologico;
    }
    public void setFechaLlegadaZoologico(LocalDate fechaLlegadaZoologico) {
        this.fechaLlegadaZoologico = fechaLlegadaZoologico;
    }

    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

    public ArrayList<String> getEnfermedades() {
        return enfermedades;
    }
    public void setEnfermedades(ArrayList<String> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public String getFrecuenciaAlimentacion() {
        return frecuenciaAlimentacion;
    }
    public void setFrecuenciaAlimentacion(String frecuenciaAlimentacion) {
        this.frecuenciaAlimentacion = frecuenciaAlimentacion;
    }

    public String getTipoAlimentacion() {
        return tipoAlimentacion;
    }
    public void setTipoAlimentacion(String tipoAlimentacion) {
        this.tipoAlimentacion = tipoAlimentacion;
    }

    public Boolean getCuentaConVacunas() {
        return cuentaConVacunas;
    }

    public void setCuentaConVacunas(Boolean cuentaConVacunas) {
        this.cuentaConVacunas = cuentaConVacunas;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }
    /*private int generarId() {
        Random random = new Random();
        return random.nextInt(10000) + 1;
    }*/
    
    public String getInformacion(){
    return String.format("Información del animal:\n" +
            "Tipo de animal: %s\n" +
            "Fecha de nacimiento: %tF\n" +
            "Fecha de llegada al zoológico: %tF\n" +
            "Peso: %.2f kg\n" +
            "Enfermedades: %s\n" +
            "Frecuencia de alimentación: %s\n" +
            "Tipo de alimentación: %s\n" +
            "¿Cuenta con vacunas?: %b",
            tipoAnimal, fechaNacimiento, fechaLlegadaZoologico, peso, enfermedades,
            frecuenciaAlimentacion, tipoAlimentacion, cuentaConVacunas);
}
    

}
