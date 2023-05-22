<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>INSERTAR PRODUCTOS</title>
</head>
<body>

	<h1 style ="color: red;">${error}</h1>

	<form action="InsertarProductos" method="post">

		<input type="number" name = "codigo" placeholder = "codigo">
		<input type="text" name="nombre" placeholder = "nombre">
		<input type="number" name = "cantidad" placeholder = "cantidad">
		<input type="number" name ="precio" placeholder = "precio">
		<input type="Date" name ="fecha">
		
		<select name="secciones">
		  <c:forEach var="seccion" items="${secciones}" >
		  	<option value ="${seccion.id}">${seccion.nombre}</option>
		  </c:forEach>
		</select>
		
		<!-- <input type="text" name= "idseccion" placeholder = "idseccion"> -->
		<input type ="submit" placeholder = "enviar">
		
	</form>
</body>	
</html>