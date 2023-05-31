<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Productos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<style>
	tr th{ 
	background-color: blue !important;
	}
	th{
	color:white
	}
</style>

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

<form method ="post" action="EliminarProductos">
	<input type ="text" placeholder ="Introduce codigos..." name ="eliminarCodigos">
	
	<button type = "submit" name = "boton" value = "botonEliminarCodigos">Eliminar</button>
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
	      <th scope="col"></th>
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
	      <td><fmt:formatDate value="${producto.caducidad}" pattern="dd/MM/yyyy"/></td>
	      <td>${producto.seccion.nombre}</td>
	      <td><a href = "ModificarProductos?id=${producto.id}&codigo=${producto.codigo}&nombre=${producto.nombre}&cantidad=${producto.cantidad}&precio=${producto.precio}&caducidad=${producto.caducidad}&secciones=${producto.seccion.id}">Modificar</a></td>
	   	  <td><a style = "color: red; text-decoration: none;" href = "./EliminarProductos?id=${producto.id}">Eliminar</a></td>
	   	  <td>
	   	  <!-- El atributo form es para poder llamar al form que esta fuera de el for Each -->
	   	  <input type = "checkbox" name = "eliminarCheckbox[]" form ="eliminarCheckbox" value="${producto.codigo}">
	   	  </td>
	   
	    </tr>
	    </c:forEach>
	  </tbody>
	</table>
	<a href = "InsertarProductos" class = "btn btn-primary">
	Insertar
	</a>
	<form method ="post" action="EliminarProductos" id = "eliminarCheckbox">
		<button class="btn btn-danger float-end" type = "submit" name = "boton" value = "botonEliminarCheckbox">Eliminar</button>
	</form>
	
	
</div>
</body>
</html>