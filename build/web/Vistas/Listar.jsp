<%@page contentType="text/html" pageEncoding="UTF-8" import="Modelo.Persona, ModeloDAO.PersonaDAO, java.util.List, java.util.Iterator"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Listar Personas</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" />
  </head>
  <body>
    <div class="container">
      <div class="card">
      <div class="toolbar">
        <h1>Personas</h1>
        <div style="margin-left:auto">
          <a class="btn btn-primary" href="Controlador?accion=add">+ Agregar</a>
        </div>
      </div>
      <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
      %>
      <div class="alert"><%= msg %></div>
      <%
        }
      %>
      <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>CEDULA</th>
            <th>NOMBRES</th>
            <th>ACCIONES</th>
          </tr>
        </thead>
        <tbody>
          <%
            // obtener lista (desde servlet o directamente)
            List lista = (List) request.getAttribute("personas");
            if (lista == null) {
                PersonaDAO dao = new PersonaDAO();
                lista = dao.listar();
            }
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                Persona p = (Persona) it.next();
          %>
          <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getDni() %></td>
            <td><%= p.getNombre() %></td>
            <td class="actions">
              <a class="btn btn-ghost" href="Controlador?accion=editar&id=<%= p.getId() %>">Editar</a>
              <a class="btn btn-danger" href="Controlador?accion=eliminar&id=<%= p.getId() %>" onclick="return confirm('Eliminar registro?');">Eliminar</a>
            </td>
          </tr>
          <% } %>
        </tbody>
      </table>
      </div>
      </div>
    </div>
    </div>
  </body>
</html>
```
