<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Productos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<form method ="post" action="Productos">
  <input type="text" placeholder="Buscar.." name="buscar">
  
  <button type="submit" name ="boton" value ="buscar"><i class="fa fa-search"></i></button>
</form>

<form method = "post" action="Productos">
	<input type = "number" placeholder = "precio min" name = "preciomin">
	<input type ="number" placeholder = "precio max" name = "preciomax">
	
	<button type ="submit" name = "boton" value= "precio"><i class="fa fa-search"></i></button>
</form>

<div class = "container">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">Id</th>
	      <th scope="col">Codigo
	      <!-- Ascendente -->
	      <a href="Productos?orden=asc" style="color: black;"><i class="fa fa-sort-asc" aria-hidden="true"></i></a>
	      <!-- Descendiente -->
	      <a href="Productos?orden=desc" style="color: black;"><i class="fa fa-sort-desc" aria-hidden="true"></i></a>
	      </th>
	      <th scope="col">Nombre</th>
	      <th scope="col">Cantidad</th>
	      <th scope="col">Precio</th>
	      <th scope="col">Caducidad</th>
	      <th scope="col">Seccion</th>	
	      <th scope="col">Modificar</th>	
	      <th scope="col">Eliminar</th>	
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
	      <td>${producto.seccion.nombre}</td>
	      <td><a href = "ModificarProductos?id=<c:out value="${producto.id}"></c:out>&codigo=<c:out value="${producto.codigo}"></c:out>&nombre=<c:out value="${producto.nombre}"></c:out>&cantidad=<c:out value="${producto.cantidad}"></c:out>&precio=<c:out value="${producto.precio}"></c:out>&caducidad=<c:out value="${producto.caducidad}"></c:out>&secciones=<c:out value="${producto.seccion.id}"></c:out>">Modificar</a></td>
	   	  <td><a style = "color: red; text-decoration: none;" href = "EliminarProductos?id=<c:out value="${producto.id}"></c:out>">- 1</a></td>
	   
	    </tr>
	    </c:forEach>
	  </tbody>
	</table>
	<a href = "InsertarProductos" class = "btn btn-primary">
	Insertar
	</a>
	
	
</div>
</body>
</html>