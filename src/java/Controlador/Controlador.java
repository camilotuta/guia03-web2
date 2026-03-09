/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import ModeloDAO.PersonaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author tutaa
 */
public class Controlador extends HttpServlet {

    String listar = "Vistas/Listar.jsp";
    String add = "Vistas/Adicionar.jsp";
    String edit = "Vistas/Editar.jsp";
    PersonaDAO dao = new PersonaDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");
        if (action == null)
            action = "listar";

        switch (action) {
            case "listar":
                acceso = listar;
                request.setAttribute("personas", dao.listar());
                break;
            case "add":
                acceso = add;
                break;
            case "editar":
                acceso = edit;
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("persona", dao.list(id));
                } catch (Exception e) {
                }
                break;
            case "eliminar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                } catch (Exception e) {
                }
                acceso = listar;
                request.setAttribute("personas", dao.listar());
                break;
            default:
                acceso = listar;
                request.setAttribute("personas", dao.listar());
                break;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action == null)
            action = "listar";

        if (action.equalsIgnoreCase("Agregar")) {
            try {
                int dni = Integer.parseInt(request.getParameter("dni"));
                String nombre = request.getParameter("nombre");
                Persona per = new Persona();
                per.setDni(dni);
                per.setNombre(nombre);
                boolean ok = dao.add(per);
                if (ok) {
                    request.setAttribute("msg", "Registro agregado correctamente.");
                } else {
                    request.setAttribute("msg", "Error al agregar registro.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("Actualizar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                int dni = Integer.parseInt(request.getParameter("dni"));
                String nombre = request.getParameter("nombre");
                Persona per = new Persona();
                per.setId(id);
                per.setDni(dni);
                per.setNombre(nombre);
                boolean ok = dao.edit(per);
                if (ok) {
                    request.setAttribute("msg", "Registro actualizado correctamente.");
                } else {
                    request.setAttribute("msg", "Error al actualizar registro.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher vista = request.getRequestDispatcher(listar);
        request.setAttribute("personas", dao.listar());
        vista.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
