package com.housegarden.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * ============================================================
 * ENTIDAD: Producto
 * ============================================================
 * Clase modelo que representa un Producto del vivero HouseGarden.
 * Mapeada a la tabla 'productos' de MySQL mediante JPA/Hibernate.
 *
 * Anotaciones JPA:
 *   @Entity     - Define esta clase como entidad de base de datos
 *   @Table      - Especifica la tabla 'productos' en MySQL
 *   @Id         - Marca el campo como clave primaria
 *   @GeneratedValue(IDENTITY) - MySQL genera el ID automaticamente
 *   @Column     - Mapea cada atributo a su columna en la tabla
 *   @ManyToOne  - Relacion muchos productos -> una categoria/proveedor
 *   @JoinColumn - Define la columna de clave foranea
 *
 * Anotaciones de validacion (Bean Validation):
 *   @NotBlank   - El campo no puede ser nulo ni vacio
 *   @NotNull    - El campo no puede ser nulo
 *   @Size       - Controla la longitud minima y maxima del texto
 *   @DecimalMin - Valor decimal minimo permitido
 *   @Min        - Valor entero minimo permitido
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Entity
@Table(name = "productos")
public class Producto {

    /**
     * Identificador unico del producto.
     * Clave primaria auto-incremental generada por MySQL.
     * Equivale a la columna id_producto de la tabla.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    /**
     * Nombre del producto (planta o herramienta de jardineria).
     * Campo obligatorio. Ejemplos: "Rosa Roja", "Pala de jardin".
     * Maximo 45 caracteres segun la definicion de la tabla MySQL.
     */
    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 45, message = "El nombre no puede superar 45 caracteres")
    @Column(name = "nombre")
    private String nombre;

    /**
     * Descripcion detallada del producto.
     * Almacenada como TEXT en MySQL para mayor capacidad de texto.
     * Campo opcional.
     */
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    /**
     * Precio unitario del producto en pesos colombianos (COP).
     * Precision de 10 digitos totales con 2 decimales.
     * Ejemplo: 15000.00 COP
     */
    @NotNull(message = "El precio del producto es obligatorio")
    @DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;

    /**
     * Cantidad de unidades disponibles en el inventario del vivero.
     * No puede ser un valor negativo.
     */
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock")
    private Integer stock;

    /**
     * Categoria a la que pertenece el producto.
     * Relacion muchos-a-uno con la entidad Categoria:
     * varios productos pueden pertenecer a la misma categoria.
     * La columna id_categoria actua como clave foranea.
     */
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    /**
     * Proveedor que suministra este producto al vivero.
     * Relacion muchos-a-uno con la entidad Proveedor:
     * varios productos pueden tener el mismo proveedor.
     * La columna id_proveedor actua como clave foranea.
     */
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    // ── Constructor vacio requerido por la especificacion JPA ────────────────
    public Producto() {}

    // ── Getters y Setters ────────────────────────────────────────────────────
    /** @return identificador unico del producto */
    public Integer getIdProducto()                  { return idProducto; }
    /** @param idProducto identificador a asignar */
    public void setIdProducto(Integer idProducto)   { this.idProducto = idProducto; }

    /** @return nombre del producto */
    public String getNombre()                       { return nombre; }
    /** @param nombre nombre del producto a asignar */
    public void setNombre(String nombre)            { this.nombre = nombre; }

    /** @return descripcion del producto */
    public String getDescripcion()                  { return descripcion; }
    /** @param descripcion descripcion a asignar */
    public void setDescripcion(String descripcion)  { this.descripcion = descripcion; }

    /** @return precio unitario en COP */
    public BigDecimal getPrecio()                   { return precio; }
    /** @param precio precio a asignar */
    public void setPrecio(BigDecimal precio)        { this.precio = precio; }

    /** @return cantidad disponible en inventario */
    public Integer getStock()                       { return stock; }
    /** @param stock cantidad a asignar */
    public void setStock(Integer stock)             { this.stock = stock; }

    /** @return categoria del producto */
    public Categoria getCategoria()                 { return categoria; }
    /** @param categoria categoria a asignar */
    public void setCategoria(Categoria categoria)   { this.categoria = categoria; }

    /** @return proveedor del producto */
    public Proveedor getProveedor()                 { return proveedor; }
    /** @param proveedor proveedor a asignar */
    public void setProveedor(Proveedor proveedor)   { this.proveedor = proveedor; }
}
