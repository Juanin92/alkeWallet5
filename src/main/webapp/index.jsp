<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" 
	href="${pageContext.request.contextPath}/resources/css/index.css">
<meta charset="UTF-8">
<title>Alke Wallet</title>
</head>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alke Wallet</title>
</head>
<body>
    <div class="formulario">
    	<div class="titulo">
        	<h1>ALKE WALLET</h1>
    		<h3>Iniciar Sesión</h3>
    	</div>
        <input type="hidden" id="status" value="<%= request.getAttribute(" status") %>">
        <form action="login" method="post">
            <label for="correo">Email:</label>
            <input type="email" id="correo" name="correo" placeholder="example@example.com" required />
            <label for="contrasena">Contraseña:</label>
            <input type="password" id="contrasena" name="contrasena" placeholder="*****" required />
            <input type="submit" value="Enviar" />
        </form>
        <p>Si no tiene cuenta, has <a href="registrarse">click aquí!</a></p>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script type="text/javascript">
        var status = document.getElementById("status").value;
        if (status == "failed") {
            swal.fire("Error", "Correo o contraseña equivocadas", "error");
        }
    </script>
</body>
</html>