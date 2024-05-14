<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" 
	href="${pageContext.request.contextPath}/resources/css/home.css">
    <meta charset="ISO-8859-1">
    <title>Home</title>
</head>

<body>
    <div class="menu">
         <% User usuario=(User) session.getAttribute("usuario");%>
        <input type="hidden" id="status" value="<%= session.getAttribute(" status") %>">
        <h1>Bienvenido, <c:out value="${usuario.getNombre()} ${usuario.getApellido()}"></c:out>
        </h1>
        <h3>Saldo Disponible: $<c:out value="${usuario.getSaldo()}"></c:out>
        </h3>
        <form action="operacion" method="post">
            <h4>Que necesitas realizar:</h4>
            <label for="monto">Ingrese monto:</label>
            <input type="number" id="monto" name="monto">
            <button type="submit" id="operacionDepositar" name="operacionDepositar">Depositar</button>
            <button type="submit" id="operacionRetirar" name="operacionRetirar" >Retirar</button>
        </form>
        <button id="logout"><a href="logout">Cerrar Sesión</a></button>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script type="text/javascript">
        var status = document.getElementById("status").value;
        if (status == "success") {
            swal.fire("felicitaciones", "Operación exitosa", "success");
        } else if (status == "failed") {
            swal.fire("Error", "operación no se pudo hacer", "error");
        }
    </script>
</body>
</html>