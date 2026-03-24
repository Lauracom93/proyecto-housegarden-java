package com.housegarden.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * ============================================================
 * ENTIDAD: Orden
 * ============================================================
 * Clase modelo que representa una Orden de compra en HouseGarden.
 * Mapeada a la tabla 'ordenes' de MySQL mediante JPA/Hibernate.
 *
 * Una orden registra la compra de productos por un cliente.
 * Estados posibles de una orden:
 *   - Pendiente  : Registrada pero no procesada
 *   - Completado : Entregada satisfactoriamente al cliente
 *   - Cancelado  : Cancelada por el cliente o el vivero
 *
 * Aprendices: Laura Combita - Diana Amaya | Ficha: 3118327
 * ============================================================
 */
@Entity
@Table(name = "ordenes")
public class Orden {

    /** Identificador unico de la orden - clave primaria auto-incremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Integer idOrden;

    /**
     * Cliente que realizo la orden de compra.
     * Relacion muchos-a-uno: un cliente puede tener varias ordenes.
     * La columna id_cliente actua como clave foranea hacia la tabla clientes.
     */
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @NotNull(message = "Debe seleccionar un cliente para la orden")
    private Cliente cliente;

    /**
     * Fecha en que se registro la orden en el sistema.
     * Usa LocalDate para representar solo la fecha sin componente de hora.
     */
    @NotNull(message = "La fecha de la orden es obligatoria")
    @Column(name = "fecha")
    private LocalDate fecha;

    /**
     * Estado actual de la orden en el proceso de gestion.
     * Valores validos: "Pendiente", "Completado", "Cancelado".
     */
    @NotBlank(message = "El estado de la orden es obligatorio")
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;

    /**
     * Valor total de la orden en pesos colombianos (COP).
     * Suma del precio unitario por cantidad de cada producto ordenado.
     */
    @NotNull(message = "El total de la orden es obligatorio")
    @DecimalMin(value = "0.0", message = "El total no puede ser negativo")
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    // ── Constructor vacio requerido por JPA ──────────────────────────────────
    public Orden() {}

    // ── Getters y Setters ────────────────────────────────────────────────────
    public Integer getIdOrden()                     { return idOrden; }
    public void setIdOrden(Integer idOrden)         { this.idOrden = idOrden; }
    public Cliente getCliente()                     { return cliente; }
    public void setCliente(Cliente cliente)         { this.cliente = cliente; }
    public LocalDate getFecha()                     { return fecha; }
    public void setFecha(LocalDate fecha)           { this.fecha = fecha; }
    public String getEstado()                       { return estado; }
    public void setEstado(String estado)            { this.estado = estado; }
    public BigDecimal getTotal()                    { return total; }
    public void setTotal(BigDecimal total)          { this.total = total; }
}
