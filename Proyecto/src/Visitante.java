package examen.u2;
import java.time.LocalDate;

public class Visitante {
    private int idVisitante;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String curp;
    private int edad;
    private LocalDate fechaRegistro;
    private int numeroVisitas;

    public Visitante(int idVisitante, String nombre, String apellidos, LocalDate fechaNacimiento, String curp,int edad, LocalDate fechaRegistro) {
    	this.idVisitante = idVisitante;
    	this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.curp = curp;
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
        this.numeroVisitas = 0;
}
    
    

    public int getIdVisitante() {
		return idVisitante;
	}

	public void setIdVisitante(int idVisitante) {
		this.idVisitante = idVisitante;
	}

	public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNumeroVisitas(int numeroVisitas) {
        this.numeroVisitas = numeroVisitas;
    }

    public int getEdad() {
        return edad;
    }

    public int getNumeroVisitas() {
        return numeroVisitas;
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

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void aumentarNumeroVisitas() {
        numeroVisitas++;
    }
    
    public String getFullName() {
    	return this.nombre+" "+this.apellidos;
    }

    @Override
    public String toString() {
        return "Visitante{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", curp='" + curp + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", numeroVisitas=" + numeroVisitas +
                '}';
    }
}
