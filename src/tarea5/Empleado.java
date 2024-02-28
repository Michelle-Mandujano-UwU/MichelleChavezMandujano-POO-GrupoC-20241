
package tarea5;


public class Empleado {
    double calcularSalario(double sueldoBase, double bonificacion, double horasExtras){
        return  sueldoBase + bonificacion + horasExtras*20;
    }
    double calcularSalario(double sueldoBase, double bonificacion){
        return  sueldoBase + bonificacion;
    }
    double calcularSalario(double sueldoBase){
        return  sueldoBase;
    }
}
