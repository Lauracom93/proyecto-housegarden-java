package com.mycompany.housegarden.servlet;

import com.mycompany.housegarden.dao.ProveedorDAO;
import com.mycompany.housegarden.model.Proveedor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet que gestiona las operaciones CRUD de Proveedores.
 * @author Laura Combita
 */

public class ProveedorServlet extends HttpServlet {

    private final ProveedorDAO dao = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if ("editar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("proveedor", dao.buscarPorId(id));
                request.getRequestDispatcher("/views/proveedores/formulario.jsp").forward(request, response);

            } else if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(id);
                response.sendRedirect(request.getContextPath() + "/proveedores?msg=eliminado");

            } else if ("nuevo".equals(accion)) {
                request.getRequestDispatcher("/views/proveedores/formulario.jsp").forward(request, response);

            } else {
                List<Proveedor> lista = dao.listarTodos();
                request.setAttribute("proveedores", lista);
                String msg = request.getParameter("msg");
                if (msg != null) request.setAttribute("msg", msg);
                request.getRequestDispatcher("/views/proveedores/lista.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/views/proveedores/lista.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String idStr    = request.getParameter("idProveedor");
            String nombre   = request.getParameter("nombre");
            String contacto = request.getParameter("contacto");
            String telefono = request.getParameter("telefono");

            Proveedor p = new Proveedor();
            p.setNombre(nombre);
            p.setContacto(contacto);
            p.setTelefono(telefono);

            if (idStr != null && !idStr.isEmpty()) {
                p.setIdProveedor(Integer.parseInt(idStr));
                dao.actualizar(p);
                response.sendRedirect(request.getContextPath() + "/proveedores?msg=actualizado");
            } else {
                dao.insertar(p);
                response.sendRedirect(request.getContextPath() + "/proveedores?msg=guardado");
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error al guardar: " + e.getMessage());
            request.getRequestDispatcher("/views/proveedores/formulario.jsp").forward(request, response);
        }
    }
}
