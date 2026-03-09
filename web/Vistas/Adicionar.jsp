<%-- Document : Adicionar Created on : 8 mar 2026, 5:22:54 p.m. Author : tutaa
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Agregar Persona</title>
    <link
      rel="stylesheet"
      href="<%=request.getContextPath()%>/css/styles.css"
    />
  </head>
  <body>
    <div class="container">
      <div class="card">
        <div class="toolbar">
          <h1>Agregar Persona</h1>
        </div>
        <form action="Controlador" method="post">
          <input type="hidden" name="accion" value="Agregar" />
          <div class="field">
            <label for="dni">Cédula (DNI):</label>
            <input type="number" id="dni" name="dni" required />
          </div>
          <div class="field">
            <label for="nombre">Nombres:</label>
            <input type="text" id="nombre" name="nombre" required />
          </div>
          <div class="actions">
            <button class="btn btn-primary" type="submit">Guardar</button>
            <a class="btn btn-secondary" href="Controlador?accion=listar"
              >Cancelar</a
            >
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
