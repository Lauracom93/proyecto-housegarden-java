package com.mycompany.housegarden.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Clase modelo que representa una Orden del sistema HouseGarden.
 * @author Laura Combita
 */
public class Orden {

    private int        idOrden;
    private int        idCliente;
    private Date       fecha;
    private String     estado;
    private BigDecimal total;

    public Orden() {}

    public Orden(int idOrden, int idCliente, Date fecha, String estado, BigDecimal total) {
        this.idOrden   = idOrden;
        this.idCliente = idCliente;
        this.fecha     = fecha;
        this.estado    = estado;
        this.total     = total;
    }

    public int getIdOrden()              { return idOrden; }
    public void setIdOrden(int id)       { this.idOrden = id; }

    public int getIdCliente()            { return idCliente; }
    public void setIdCliente(int id)     { this.idCliente = id; }

    public Date getFecha()               { return fecha; }
    public void setFecha(Date fecha)     { this.fecha = fecha; }

    public String getEstado()            { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public BigDecimal getTotal()         { return total; }
    public void setTotal(BigDecimal t)   { this.total = t; }
}
