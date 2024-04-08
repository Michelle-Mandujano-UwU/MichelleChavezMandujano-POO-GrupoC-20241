package examen.u2;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Visita {
	private Empleado guiaCargo;
	
	private List<Visitante> visitantes;
	
	private double costoTotal;
	
	private int cantidadNinos;
	
	private int cantidadAdultos;
	
	private LocalDate fechaVisita;

	public Visita(Empleado guiaCargo, List<Visitante> visitantes, LocalDate fechaVisita) {
		super();
		this.guiaCargo = guiaCargo;
		this.visitantes = visitantes;
		this.fechaVisita = fechaVisita;
		
		
		this.cantidadNinos = visitantes.stream().filter(x->x.getEdad() < 18).collect(Collectors.toList()).size();
		this.cantidadAdultos = visitantes.size() - cantidadNinos;
		
		for(Visitante visitante : visitantes) {
			
			double cantDesc = (Math.round(visitante.getNumeroVisitas() / 5 * 100.0)/100.0);
			
			if(visitante.getEdad() < 18) {
				this.costoTotal+= 50 - (cantDesc * (50 * 20 / 100)); 
			}else {
				this.costoTotal+= 100 - (cantDesc * (100 * 20 / 100)); 
			}
		}
	}

	public Empleado getGuiaCargo() {
		return guiaCargo;
	}

	public void setGuiaCargo(Empleado guiaCargo) {
		this.guiaCargo = guiaCargo;
	}

	public List<Visitante> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(List<Visitante> visitantes) {
		this.visitantes = visitantes;
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}

	public int getCantidadNinos() {
		return cantidadNinos;
	}

	public void setCantidadNinos(int cantidadNinos) {
		this.cantidadNinos = cantidadNinos;
	}

	public int getCantidadAdultos() {
		return cantidadAdultos;
	}

	public void setCantidadAdultos(int cantidadAdultos) {
		this.cantidadAdultos = cantidadAdultos;
	}

	public LocalDate getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(LocalDate fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

    
}
