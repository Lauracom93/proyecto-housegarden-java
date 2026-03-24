package com.mycompany.housegarden.servlet;

import com.mycompany.housegarden.dao.ProductoDAO;
import com.mycompany.housegarden.model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Servlet que gestiona las operaciones CRUD de Productos.
 * Utiliza metodos GET y POST para manejar las solicitudes HTTP.
 * @author Laura Combita
 */

public class ProductoServlet extends HttpServlet {

    private final ProductoDAO dao = new ProductoDAO();

    /**
     * Metodo GET: lista todos los productos o carga uno para editar.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if ("editar".equals(accion)) {
                // Cargar producto por ID para mostrar formulario de edicion
                int id = Integer.parseInt(request.getParameter("id"));
                Producto producto = dao.buscarPorId(id);
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("/views/productos/formulario.jsp")
                       .forward(request, response);

            } else if ("eliminar".equals(accion)) {
                // Eliminar producto y redirigir al listado
                int id = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(id);
                response.sendRedirect(request.getContextPath() + "/productos?msg=eliminado");

            } else if ("nuevo".equals(accion)) {
                // Mostrar formulario vacio para nuevo producto
                request.getRequestDispatcher("/views/productos/formulario.jsp")
                       .forward(request, response);

            } else {
                // Listar todos los productos (accion por defecto)
                List<Producto> lista = dao.listarTodos();
                request.setAttribute("productos", lista);
                String msg = request.getParameter("msg");
                if (msg != null) request.setAttribute("msg", msg);
                request.getRequestDispatcher("/views/productos/lista.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/views/productos/lista.jsp").forward(request, response);
        }
    }

    /**
     * Metodo POST: recibe datos del formulario para insertar o actualizar.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            // Leer parametros del formulario HTML
            String idStr    = request.getParameter("idProducto");
            String nombre   = request.getParameter("nombre");
            String desc     = request.getParameter("descripcion");
            String precioStr = request.getParameter("precio");
            String stockStr  = request.getParameter("stock");
            String catStr    = request.getParameter("idCategoria");
            String provStr   = request.getParameter("idProveedor");

            Producto p = new Producto();
            p.setNombre(nombre);
            p.setDescripcion(desc);
            p.setPrecio(new BigDecimal(precioStr));
            p.setStock(Integer.parseInt(stockStr));
            p.setIdCategoria(catStr != null && !catStr.isEmpty() ? Integer.parseInt(catStr) : 0);
            p.setIdProveedor(provStr != null && !provStr.isEmpty() ? Integer.parseInt(provStr) : 0);

            if (idStr != null && !idStr.isEmpty()) {
                // Actualizar producto existente
                p.setIdProducto(Integer.parseInt(idStr));
                dao.actualizar(p);
                response.sendRedirect(request.getContextPath() + "/productos?msg=actualizado");
            } else {
                // Insertar nuevo producto
                dao.insertar(p);
                response.sendRedirect(request.getContextPath() + "/productos?msg=guardado");
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error al guardar: " + e.getMessage());
            request.getRequestDispatcher("/views/productos/formulario.jsp").forward(request, response);
        }
    }
}
