package Sucursales;

import Sucursales.Utils.LasSucursales;
import Users.Usuario;
import Users.Empleados.GerenteSucursal;
import Users.utils.constantes.Rol;
import java.util.ArrayList;
import java.util.HashMap;

public class Sucursal {
    protected String nombreSucursal;
    protected String direccionSucursal;
    protected GerenteSucursal gerenteSucursal= null;

    public Sucursal(String nombreSucursal, String direccionSucursal, GerenteSucursal gerenteSucursal) {
        this.nombreSucursal = nombreSucursal;
        this.direccionSucursal = direccionSucursal;
        this.gerenteSucursal = gerenteSucursal;
    }
    public Sucursal(String nombreSucursal, String direccionSucursal) {
        this.nombreSucursal = nombreSucursal;
        this.direccionSucursal = direccionSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }

    public GerenteSucursal getGerenteSucursal() {
        return gerenteSucursal;
    }

    public void setGerenteSucursal(GerenteSucursal gerenteSucursal) {
        this.gerenteSucursal = gerenteSucursal;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                ", nombreSucursal='" + nombreSucursal + '\'' +
                ", direccionSucursal='" + direccionSucursal + '\'' +
                ", gerenteSucursal=" + gerenteSucursal +
                '}';
    }
}

//    protected String direccionSucursal;
//    Gerente gerente;
//
//
//    public Sucursal(String direccionSucursal, Gerente gerente) {
//        this.direccionSucursal = direccionSucursal;
//        this.gerente = gerente;
//    }


//    public static Usuario obtenerUsuarioPorNombreYContrasena(String nombreUsuario, String contrasena) {
//        for (HashMap<Rol, ArrayList<Usuario>> usuarioPorRol : usuarioPorSucursalYRol.values()) {
//
//            for (ArrayList<Usuario> usuarios : usuarioPorRol.values()) {
//
//                for (Usuario usuario : usuarios) {
//                    if (usuario.getUser().equals(nombreUsuario) && usuario.getPassword().equals(contrasena)) {
//                        return usuario;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    public HashMap<LasSucursales, HashMap<Rol, ArrayList<Usuario>>> getHashMap() {
//        return this.usuarioPorSucursalYRol;
//    }
//    public HashMap<Rol, ArrayList<Usuario>> getHashMapDelHashMap(LasSucursales sucursal) {
//        return this.usuarioPorSucursalYRol.get(sucursal);
//    }
//    public ArrayList<Usuario> getArrayList(LasSucursales sucursal, Rol rol) {
//        return this.usuarioPorSucursalYRol.get(sucursal).get(rol);
//    }
//    public void addGerente(Gerente gerente) {
//        this.gerente = gerente;
//    }


//    @Override
//    public String toString() {
//        return "Sucursal{" +
//                ", nombreSucursal='" + nombreSucursal + '\'' +
//                ", direccionSucursal='" + direccionSucursal + '\'' +
//                ", gerenteSucursal=" + gerenteSucursal +
//                '}';
//    }