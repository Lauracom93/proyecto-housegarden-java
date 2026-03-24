package com.housegarden.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * ============================================================
 * ENTIDAD: Proveedor
 * ============================================================
 * Clase modelo que representa un Proveedor del sistema HouseGarden.
 * Mapeada a la tabla 'proveedores' de MySQL mediante JPA/Hibernate.
 *
 * Los proveedores son las empresas que suministran las plantas
 * y herramientas que se gestionan en el vivero HouseGarden.
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Entity
@Table(name = "proveedores")
public class Proveedor {

    /** Identificador unico del proveedor - clave primaria auto-incremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    /** Nombre de la empresa proveedora - campo obligatorio */
    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;

    /** Nombre de la persona de contacto dentro de la empresa */
    @Size(max = 45)
    @Column(name = "contacto")
    private String contacto;

    /** Numero de telefono para contactar al proveedor */
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;

    // ── Constructor vacio requerido por JPA ──────────────────────────────────
    public Proveedor() {}

    // ── Getters y Setters ────────────────────────────────────────────────────
    public Integer getIdProveedor()                 { return idProveedor; }
    public void setIdProveedor(Integer id)          { this.idProveedor = id; }
    public String getNombre()                       { return nombre; }
    public void setNombre(String nombre)            { this.nombre = nombre; }
    public String getContacto()                     { return contacto; }
    public void setContacto(String contacto)        { this.contacto = contacto; }
    public String getTelefono()                     { return telefono; }
    public void setTelefono(String telefono)        { this.telefono = telefono; }
}
