package Sistemas;

import Sucursales.Sucursal;
import Sucursales.SucursalAcueducto;
import Sucursales.SucursalMadero;
import Users.Cliente;
//import Sucursales.SucursalMadero;
import Users.Empleados.GerenteSucursal;
import Users.utils.constantes.Rol;

import java.time.LocalDate;

public class Sistema {
    Menus menus = new Menus();
    LocalDate localDate = LocalDate.now();
    SucursalMadero sucursalMadero = new SucursalMadero(null, null);
    SucursalAcueducto sucursalAcueducto = new SucursalAcueducto(null, null);

    GerenteSucursal gerente2 = new GerenteSucursal(1, "a", null, localDate, null, "ESTADO", "a", "123", sucursalAcueducto, 123,  Rol.GERENTE_SUCURSAL, null, "123", "a");
    
    /* PRUEBA DE USUARIO */
    
    public void iniciarSistema(){

        menus.inicioSesion();
    }
    public void verificarInicioSesion(){
    }
}
