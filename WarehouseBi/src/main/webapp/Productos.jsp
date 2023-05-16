<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Productos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class = "container">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">Id</th>
	      <th scope="col">Codigo</th>
	      <th scope="col">Nombre</th>
	      <th scope="col">Cantidad</th>
	      <th scope="col">Precio</th>
	      <th scope="col">Caducidad</th>
	      <th scope="col">Id Seccion</th>	      
	    </tr>
	  </thead>
	  <tbody>
	  
	  <c:forEach items="${productos}" var="producto">
	    <tr>
	      <td style="color: black!important">${producto.id}</td>
	      <td>${producto.codigo}</td>
	      <td>${producto.nombre}</td>
	      <td>${producto.cantidad}</td>
	      <td>${producto.precio}</td>
	      <td>${producto.caducidad}</td>
	      <td>${producto.id_seccion}</td>
	      
	    </tr>
	    </c:forEach>
	  </tbody>
	</table>
</div>
</body>
</html>