package tarjetas;

import java.time.LocalDateTime;


public class Tarjetas {
    private String numTarjeta;
    private String clabeInterbancaria;
    protected double cantidad;
    private String CVV;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaDeVencimiento;
    private LocalDateTime ultimoMovimiento;

    public Tarjetas(String numTarjeta, String clabeInterbancaria, double cantidad, String CVV, LocalDateTime fechaCreacion, LocalDateTime fechaDeVencimiento, LocalDateTime ultimoMovimiento) {
        this.numTarjeta = numTarjeta;
        this.clabeInterbancaria = clabeInterbancaria;
        this.cantidad = cantidad;
        this.CVV = CVV;
        this.fechaCreacion = fechaCreacion;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.ultimoMovimiento = ultimoMovimiento;
    }

    // Métodos Getter para acceder a los campos privados
    public String getNumTarjeta() {
        return numTarjeta;
    }

    public String getClabeInterbancaria() {
        return clabeInterbancaria;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getCVV() {
        return CVV;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaDeVencimiento;
    }

    public LocalDateTime getUltimoMovimiento() {
        return ultimoMovimiento;
    }

    public void agregarDinero(double cantidad) {
        this.cantidad += cantidad;
        this.ultimoMovimiento = LocalDateTime.now();
    }

    public boolean retirarDinero(double cantidad) {
        if (this.cantidad >= cantidad) {
            this.cantidad -= cantidad;
            this.ultimoMovimiento = LocalDateTime.now();
            return true;
        } else {
            System.out.println("No hay suficiente saldo para realizar esta operación.");
            return false;
        }
    }

}
