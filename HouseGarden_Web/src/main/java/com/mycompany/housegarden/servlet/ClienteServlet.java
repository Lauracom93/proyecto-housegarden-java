package com.mycompany.housegarden.servlet;

import com.mycompany.housegarden.dao.ClienteDAO;
import com.mycompany.housegarden.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Servlet que gestiona las operaciones CRUD de Clientes.
 * @author Laura Combita
 */

public class ClienteServlet extends HttpServlet {

    private final ClienteDAO dao = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if ("editar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("cliente", dao.buscarPorId(id));
                request.getRequestDispatcher("/views/clientes/formulario.jsp").forward(request, response);

            } else if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(id);
                response.sendRedirect(request.getContextPath() + "/clientes?msg=eliminado");

            } else if ("nuevo".equals(accion)) {
                request.getRequestDispatcher("/views/clientes/formulario.jsp").forward(request, response);

            } else {
                List<Cliente> lista = dao.listarTodos();
                request.setAttribute("clientes", lista);
                String msg = request.getParameter("msg");
                if (msg != null) request.setAttribute("msg", msg);
                request.getRequestDispatcher("/views/clientes/lista.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/views/clientes/lista.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String idStr   = request.getParameter("idCliente");
            String nombre  = request.getParameter("nombre");
            String correo  = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            String fechaStr = request.getParameter("fechaRegistro");

            Cliente c = new Cliente();
            c.setNombre(nombre);
            c.setCorreo(correo);
            c.setTelefono(telefono);
            c.setFechaRegistro(Date.valueOf(fechaStr));

            if (idStr != null && !idStr.isEmpty()) {
                c.setIdCliente(Integer.parseInt(idStr));
                dao.actualizar(c);
                response.sendRedirect(request.getContextPath() + "/clientes?msg=actualizado");
            } else {
                dao.insertar(c);
                response.sendRedirect(request.getContextPath() + "/clientes?msg=guardado");
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error al guardar: " + e.getMessage());
            request.getRequestDispatcher("/views/clientes/formulario.jsp").forward(request, response);
        }
    }
}
