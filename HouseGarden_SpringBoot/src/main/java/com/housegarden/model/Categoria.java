package com.housegarden.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * ============================================================
 * ENTIDAD: Categoria
 * ============================================================
 * Clase modelo que representa una Categoria de productos.
 * Mapeada a la tabla 'categorias' de MySQL mediante JPA/Hibernate.
 *
 * Las categorias permiten clasificar los productos del vivero.
 * Ejemplos: "Plantas ornamentales", "Herramientas de jardineria"
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Entity
@Table(name = "categorias")
public class Categoria {

    /** Identificador unico de la categoria - clave primaria */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    /** Nombre de la categoria - campo obligatorio */
    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;

    /** Descripcion de los productos que incluye esta categoria */
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;

    // ── Constructor vacio requerido por JPA ──────────────────────────────────
    public Categoria() {}

    // ── Getters y Setters ────────────────────────────────────────────────────
    public Integer getIdCategoria()                 { return idCategoria; }
    public void setIdCategoria(Integer id)          { this.idCategoria = id; }
    public String getNombre()                       { return nombre; }
    public void setNombre(String nombre)            { this.nombre = nombre; }
    public String getDescripcion()                  { return descripcion; }
    public void setDescripcion(String desc)         { this.descripcion = desc; }
}
