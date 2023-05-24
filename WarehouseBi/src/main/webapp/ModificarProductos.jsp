<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar Productos</title>
</head>
<body>
	

	<form action="ModificarProductos" method="post">
	
		<input type="hidden" name = "id" placeholder = "id" value="${producto.id}">
		<input type="number" name = "codigo" placeholder = "codigo" value="${producto.codigo}">
		<input type="text" name="nombre" placeholder = "nombre" value="${producto.nombre}">
		<input type="number" name = "cantidad" placeholder = "cantidad" value = "${producto.cantidad}">
		<input type="number" name ="precio" placeholder = "precio" value ="${producto.precio}">
		<input type="Date" name ="caducidad" value = "${producto.caducidad}">
		<select name="seccion">
			<option value="">Seleccione una seccion
			<c:forEach items ="${secciones}" var="seccion">
				<option value="${seccion.id}">${seccion.nombre}
			</c:forEach>
		</select>
		
		<input type ="submit" placeholder = "enviar">
	
	</form>
</body>
</html>