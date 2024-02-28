
package tarea5;


public class Calculadora {
     double calcularImpuestos(int ingresos){
        return ingresos + 0.15;
    }
    double calcularImpuestos(int ingresos, double porcentajeImpuestos){



        return ingresos * (porcentajeImpuestos/100);
    }

    double calcularImpuestos(double porcentajeImpuestos, double dividendos, double extencion){

        double impuestos = dividendos*(porcentajeImpuestos/100);
        if (impuestos > extencion) {
            return impuestos - extencion;
        }else{
            return 0; 
        }
        
    }
}
