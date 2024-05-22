package tarjetas;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class TarjetasDebito extends Tarjetas {
    
    public TarjetasDebito(String numTarjeta, String clabeInterbancaria, double cantidad, String CVV, LocalDateTime fechaCreacion, LocalDateTime fechaDeVencimiento, LocalDateTime ultimoMovimiento) {
        super(numTarjeta, clabeInterbancaria, cantidad, CVV, fechaCreacion, fechaDeVencimiento, ultimoMovimiento);
    }

    public static TarjetasDebito crearTarjetaDeDebito() {
        Scanner scanner = new Scanner(System.in);
        long numeroAleatorio = (long) (Math.random() * 10000000000000000L);
        String numeroTarjeta = String.format("%016d", numeroAleatorio);
        System.out.println("El numero de su tarjeta va a ser: " + numeroTarjeta);

        numeroAleatorio = (long) (Math.random() * 10000000000000000L);
        String clabeInterbancaria = String.format("%016d", numeroAleatorio);
        System.out.println("Su Clabe Interbancaria es: " + clabeInterbancaria);

        int numAleatorio = (int) (Math.random() * 100);
        String CVV = String.format("%03d", numAleatorio);
        System.out.println("Su CVV es: " + CVV);

        System.out.println("Ingrese el saldo de la tarjeta:");
        double cantidad = scanner.nextDouble();

        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaDeVencimiento = fechaCreacion.plusYears(5);
        LocalDateTime ultimoMovimiento = LocalDateTime.now();
        TarjetasDebito tarjetaDebito = new TarjetasDebito(numeroTarjeta, clabeInterbancaria, cantidad, CVV, fechaCreacion, fechaDeVencimiento, ultimoMovimiento);
        return tarjetaDebito;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Tarjeta de Débito:\n" +
                "Número de tarjeta: " + this.getNumTarjeta() + "\n" +
                "Clabe Interbancaria: " + super.getClabeInterbancaria() + "\n" +
                "Saldo: " + this.getCantidad() + "\n" +
                "CVV: " + this.getCVV() + "\n" +
                "Fecha de Creación: " + this.getFechaCreacion().format(formatter) + "\n" +
                "Fecha de Vencimiento: " + this.getFechaVencimiento().format(formatter) + "\n" +
                "Último Movimiento: " + this.getUltimoMovimiento().format(formatter) + "\n";
    }

    @Override
    public void agregarDinero(double cantidad) {
        super.agregarDinero(cantidad);
    }

    @Override
    public boolean retirarDinero(double cantidad) {
        return super.retirarDinero(cantidad);
    }

}