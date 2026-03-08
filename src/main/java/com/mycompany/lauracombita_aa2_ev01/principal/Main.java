package com.mycompany.lauracombita_aa2_ev01.principal;

import com.mycompany.lauracombita_aa2_ev01.dao.ProductoDAO;
import com.mycompany.lauracombita_aa2_ev01.modelo.Producto;

public class Main {

    public static void main(String[] args) {

        Producto producto = new Producto();

        producto.setNombre("Rosa Roja");
        producto.setDescripcion("Planta ornamental");
        producto.setPrecio(15000);
        producto.setStock(20);

        ProductoDAO dao = new ProductoDAO();

        dao.guardarProducto(producto);
        dao.listarProductos();

    }

}
