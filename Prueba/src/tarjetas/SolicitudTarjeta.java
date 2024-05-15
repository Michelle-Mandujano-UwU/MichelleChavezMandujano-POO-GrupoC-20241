package tarjetas;

import Users.Cliente;
import java.time.LocalDateTime;

public class SolicitudTarjeta {
    
    private int idSolicitud;
    private Cliente cliente;
    private TipoTarjeta tipoTarjeta;
    private LocalDateTime fechaSolicitud;
    private EstadoSolicitud estado;

    public SolicitudTarjeta(Cliente cliente, TipoTarjeta tipoTarjeta, LocalDateTime fechaSolicitud, EstadoSolicitud estado, int idSolicitud) {
        this.cliente = cliente;
        this.tipoTarjeta = tipoTarjeta;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        
         this.idSolicitud = idSolicitud;
         
    }
    
    public SolicitudTarjeta(Cliente cliente, TipoTarjeta tipoTarjeta,int idSolicitud) {
        this.cliente = cliente;
        this.tipoTarjeta = tipoTarjeta;
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = EstadoSolicitud.EN_PROCESO;
        
        this.idSolicitud = idSolicitud;
    }
    
    public SolicitudTarjeta(){

    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "SolicitudTarjeta{" + "idSolicitud=" + idSolicitud + ", cliente=" + cliente + ", tipoTarjeta=" + tipoTarjeta + ", fechaSolicitud=" + fechaSolicitud + ", estado=" + estado + '}';
    }
    
    public String toStringCliente() {
        return "SolicitudTarjeta{" + "idSolicitud=" + idSolicitud + ", tipoTarjeta=" + tipoTarjeta + ", fechaSolicitud=" + fechaSolicitud + ", estado=" + estado + '}';
    }


    


}
