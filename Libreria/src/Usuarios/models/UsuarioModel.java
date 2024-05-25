package Usuarios.models;
import Usuarios.Asistente;
import Usuarios.Cliente;
import Usuarios.Gerente;
import Usuarios.Usuario;

import java.util.ArrayList;

public class UsuarioModel {

    private ArrayList<Gerente> GERENTE;
    private ArrayList<Asistente> ASISTENTE;
    private ArrayList<Cliente> CLIENTE;

    public ArrayList<Gerente> getGerentes() {
        return this.GERENTE;
    }

    public ArrayList<Asistente> getAsistentes() {
        return this.ASISTENTE;
    }

    public ArrayList<Cliente> getClientes() {
        return this.CLIENTE;
    }
}
