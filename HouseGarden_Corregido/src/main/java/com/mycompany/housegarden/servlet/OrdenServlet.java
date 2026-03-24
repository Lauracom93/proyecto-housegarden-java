package com.mycompany.housegarden.servlet;

import com.mycompany.housegarden.dao.ClienteDAO;
import com.mycompany.housegarden.dao.OrdenDAO;
import com.mycompany.housegarden.model.Cliente;
import com.mycompany.housegarden.model.Orden;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Servlet que gestiona las operaciones CRUD de Ordenes.
 * @author Laura Combita
 */

public class OrdenServlet extends HttpServlet {

    private final OrdenDAO dao = new OrdenDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if ("editar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("orden", dao.buscarPorId(id));
                // Cargar lista de clientes para el desplegable
                request.setAttribute("clientes", clienteDAO.listarTodos());
                request.getRequestDispatcher("/views/ordenes/formulario.jsp").forward(request, response);

            } else if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(id);
                response.sendRedirect(request.getContextPath() + "/ordenes?msg=eliminado");

            } else if ("nuevo".equals(accion)) {
                // Cargar lista de clientes para el desplegable
                request.setAttribute("clientes", clienteDAO.listarTodos());
                request.getRequestDispatcher("/views/ordenes/formulario.jsp").forward(request, response);

            } else {
                List<Orden> lista = dao.listarTodos();
                request.setAttribute("ordenes", lista);
                String msg = request.getParameter("msg");
                if (msg != null) request.setAttribute("msg", msg);
                request.getRequestDispatcher("/views/ordenes/lista.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/views/ordenes/lista.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String idStr      = request.getParameter("idOrden");
            String clienteStr = request.getParameter("idCliente");
            String fechaStr   = request.getParameter("fecha");
            String estado     = request.getParameter("estado");
            String totalStr   = request.getParameter("total");

            Orden o = new Orden();
            o.setIdCliente(Integer.parseInt(clienteStr));
            o.setFecha(Date.valueOf(fechaStr));
            o.setEstado(estado);
            o.setTotal(new BigDecimal(totalStr));

            if (idStr != null && !idStr.isEmpty()) {
                o.setIdOrden(Integer.parseInt(idStr));
                dao.actualizar(o);
                response.sendRedirect(request.getContextPath() + "/ordenes?msg=actualizado");
            } else {
                dao.insertar(o);
                response.sendRedirect(request.getContextPath() + "/ordenes?msg=guardado");
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error al guardar: " + e.getMessage());
            request.getRequestDispatcher("/views/ordenes/formulario.jsp").forward(request, response);
        }
    }
}
