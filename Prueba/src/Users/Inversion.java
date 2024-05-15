package Users;

import java.time.LocalDate;

public class Inversion {
    String fecha;
    String cantInversion;
    String nombreInversionista;
    String formated;

    Inversion(String nombreInversionista, String fecha, String cantInversion) {
        this.nombreInversionista = nombreInversionista;
        this.fecha = fecha;
        this.cantInversion = cantInversion;
    }
    public String infoInversion(){
        return "Nombre: " + nombreInversionista + ", Fecha: " + fecha + ", Cantidad de Inversión: " + cantInversion;
    }
}
