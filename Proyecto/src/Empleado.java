package examen.u2;
    import java.time.LocalDate;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private String rfc;
    private String curp;
    private double salario;
    private String rol;

    public Empleado(int idEmpleado, String nombre, String apellidos, LocalDate fechaNacimiento, LocalDate fechaIngreso, String rfc, String curp, double salario, String rol) {
        this.idEmpleado=idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.rfc = rfc;
        this.curp = curp;
        this.salario = salario;
        this.rol = rol;
}

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
     @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaIngreso=" + fechaIngreso +
                ", rfc='" + rfc + '\'' +
                ", curp='" + curp + '\'' +
                ", salario=" + salario +
                ", rol='" + rol + '\'' +
                '}';
    }
    public String getFullName() {
    	return this.nombre+" "+this.apellidos;
    }
}
