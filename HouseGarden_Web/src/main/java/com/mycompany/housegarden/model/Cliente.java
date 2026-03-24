package com.mycompany.housegarden.model;

import java.sql.Date;

/**
 * Clase modelo que representa un Cliente del sistema HouseGarden.
 * @author Laura Combita
 */
public class Cliente {

    private int    idCliente;
    private String nombre;
    private String correo;
    private String telefono;
    private Date   fechaRegistro;

    public Cliente() {}

    public Cliente(int idCliente, String nombre, String correo,
                   String telefono, Date fechaRegistro) {
        this.idCliente     = idCliente;
        this.nombre        = nombre;
        this.correo        = correo;
        this.telefono      = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCliente()                    { return idCliente; }
    public void setIdCliente(int idCliente)      { this.idCliente = idCliente; }

    public String getNombre()                    { return nombre; }
    public void setNombre(String nombre)         { this.nombre = nombre; }

    public String getCorreo()                    { return correo; }
    public void setCorreo(String correo)         { this.correo = correo; }

    public String getTelefono()                  { return telefono; }
    public void setTelefono(String telefono)     { this.telefono = telefono; }

    public Date getFechaRegistro()               { return fechaRegistro; }
    public void setFechaRegistro(Date fecha)     { this.fechaRegistro = fecha; }
}
