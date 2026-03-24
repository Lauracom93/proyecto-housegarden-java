package com.housegarden.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * ============================================================
 * ENTIDAD: Cliente
 * ============================================================
 * Clase modelo que representa un Cliente del sistema HouseGarden.
 * Mapeada a la tabla 'clientes' de MySQL mediante JPA/Hibernate.
 *
 * Spring Data JPA usa esta entidad para generar automaticamente
 * las consultas SQL de insercion, actualizacion, eliminacion
 * y busqueda sin necesidad de escribir SQL manualmente.
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    /**
     * Identificador unico del cliente.
     * Generado automaticamente por MySQL con AUTO_INCREMENT.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    /**
     * Nombre completo del cliente.
     * Campo obligatorio con maximo 45 caracteres.
     */
    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 45, message = "El nombre no puede superar 45 caracteres")
    @Column(name = "nombre")
    private String nombre;

    /**
     * Correo electronico del cliente para contacto.
     * La anotacion @Email valida automaticamente el formato.
     */
    @Email(message = "El correo no tiene un formato valido")
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;

    /**
     * Numero de telefono del cliente.
     * Se almacena como texto para soportar diferentes formatos.
     */
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;

    /**
     * Fecha en que el cliente se registro en el sistema.
     * Usa LocalDate (Java 8+) para representar solo la fecha.
     */
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    // ── Constructor vacio requerido por JPA ──────────────────────────────────
    public Cliente() {}

    // ── Getters y Setters ────────────────────────────────────────────────────
    public Integer getIdCliente()                   { return idCliente; }
    public void setIdCliente(Integer idCliente)     { this.idCliente = idCliente; }
    public String getNombre()                       { return nombre; }
    public void setNombre(String nombre)            { this.nombre = nombre; }
    public String getCorreo()                       { return correo; }
    public void setCorreo(String correo)            { this.correo = correo; }
    public String getTelefono()                     { return telefono; }
    public void setTelefono(String telefono)        { this.telefono = telefono; }
    public LocalDate getFechaRegistro()             { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fecha)   { this.fechaRegistro = fecha; }
}
