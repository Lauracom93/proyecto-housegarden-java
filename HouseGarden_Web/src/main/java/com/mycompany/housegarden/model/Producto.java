package com.mycompany.housegarden.model;

import java.math.BigDecimal;

/**
 * Clase modelo que representa un Producto del sistema HouseGarden.
 * @author Laura Combita
 */
public class Producto {

    private int    idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int    stock;
    private int    idCategoria;
    private int    idProveedor;

    // Constructor vacio
    public Producto() {}

    // Constructor completo
    public Producto(int idProducto, String nombre, String descripcion,
                    BigDecimal precio, int stock, int idCategoria, int idProveedor) {
        this.idProducto  = idProducto;
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.precio      = precio;
        this.stock       = stock;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
    }

    // Getters y Setters
    public int getIdProducto()               { return idProducto; }
    public void setIdProducto(int idProducto){ this.idProducto = idProducto; }

    public String getNombre()                { return nombre; }
    public void setNombre(String nombre)     { this.nombre = nombre; }

    public String getDescripcion()                   { return descripcion; }
    public void setDescripcion(String descripcion)   { this.descripcion = descripcion; }

    public BigDecimal getPrecio()                    { return precio; }
    public void setPrecio(BigDecimal precio)         { this.precio = precio; }

    public int getStock()                    { return stock; }
    public void setStock(int stock)          { this.stock = stock; }

    public int getIdCategoria()              { return idCategoria; }
    public void setIdCategoria(int id)       { this.idCategoria = id; }

    public int getIdProveedor()              { return idProveedor; }
    public void setIdProveedor(int id)       { this.idProveedor = id; }
}
