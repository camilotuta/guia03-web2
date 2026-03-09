<%-- 
    Document   : Editar
    Created on : 8 mar 2026, 5:22:32 p.m.
    Author     : tutaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Modelo.Persona"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Persona</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" />
    </head>
    <body>
    <div class="container">
      <div class="card">
        <div class="toolbar">
          <h1>Editar Persona</h1>
        </div>
        <% Persona p = (Persona) request.getAttribute("persona"); %>
        <form action="Controlador" method="post">
            <input type="hidden" name="accion" value="Actualizar" />
            <input type="hidden" name="id" value="<%= (p!=null? p.getId():"") %>" />
            <div class="field">
                <label for="dni">Cédula (DNI):</label>
                <input type="number" id="dni" name="dni" value="<%= (p!=null? p.getDni():"") %>" required />
            </div>
            <div class="field">
                <label for="nombre">Nombres:</label>
                <input type="text" id="nombre" name="nombre" value="<%= (p!=null? p.getNombre():"") %>" required />
            </div>
            <div class="actions">
                <button class="btn btn-primary" type="submit">Actualizar</button>
                <a class="btn btn-secondary" href="Controlador?accion=listar">Cancelar</a>
            </div>
        </form>
      </div>
    </div>
    </body>
</html>
