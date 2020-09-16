<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscador de libros</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
var lista=[];
var i;

function setIdSelect(id){
	
	idSelect = id;
	
	var checkBox = document.getElementById("filaSelecc"+id);

	  if (checkBox.checked == true){
	    lista.push(idSelect);
	  } else if(checkBox.checked == false){
		  lista.splice(idSelect-1,1);
	  }
	  return lista;
}

function deleteRow(){

	window.location= "/borrarProductos/?id="+lista;

}

function inicio(){
	
	window.location.href="/home";
}

</script>
</head>

<body>

	<div class="container-fluid" id="cuerpoD">
		<div class="col-sm-12">
			<h1 class="mt-3 text-center">Eliminar productos</h1>
			<div class="row">
				<div class="col">
					<h4 class="text-left">Por favor, seleccione el producto que
						desea eliminar</h4>
				</div>

				<div class="row">
					<div class="col">
						<button class="text-right btn btn-success " onclick="inicio()">Volver</button>
						<button class="text-right btn btn-success " onclick="deleteRow()">Eliminar</button>
					</div>
					<div class="mr-4">
						<form action="/paginaDelete" method="get">
							<input type="number" name="numPage"
								placeholder="Ingrese N° de páginas" onclick="submit"
								class="border border-success rounded mt-1">
						</form>
					</div>
				</div>
			</div>
			<br> <br>
			<table id="table" class="table table-striped">
				<thead>

					<tr>
						<th></th>
						<th scope="col">Código</th>
						<th scope="col">Nombre</th>
						<th scope="col">Precio</th>
						<th scope="col">Stock</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${listadoProductosDelete}" var="libroD">
						<tr>
							<td><input class="checkbox" type="checkbox"
								id="filaSelecc${libroD.getId_producto()}" name="filaSelecc"
								onclick="setIdSelect(${libroD.getId_producto()})" /></td>
							<td><c:out value="${libroD.getCodigo()}"></c:out></td>
							<td><c:out value="${libroD.getNombre()}"></c:out></td>
							<td><c:out value="${libroD.getPrecio()}"></c:out></td>
							<td><c:out value="${libroD.getStock()}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<nav aria-label="Pagination">
				<ul class="pagination justify-content-center fixed-bottom">
					<c:url value="/paginaDelete" var="urlPrev">
						<c:param name="page" value="${prev}" />
						<c:param name="numPage" value="${size}"></c:param>
					</c:url>

					<c:choose>
						<c:when test="${currentPage eq 1}">
							<li class="page-item disabled"><a class="page-link"
								href="${urlPrev}">&laquo;</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="${urlPrev}">&laquo;</a></li>
						</c:otherwise>
					</c:choose>

					<c:forEach items="${paginas}" var="p">
						<c:url value="/paginaDelete" var="url">
							<c:param name="page" value="${p}" />
							<c:param name="numPage" value="${size}"></c:param>
						</c:url>
						<c:choose>
							<c:when test="${currentPage == p}">

								<li class="page-item active"><a class="page-link"
									href="${url}"><c:out value="${p}"></c:out></a></li>
							</c:when>

							<c:otherwise>
								<li class="page-item"><a class="page-link" href="${url}"><c:out
											value="${p}"></c:out></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:url value="/paginaDelete" var="urlPost">
						<c:param name="page" value="${next}" />
						<c:param name="numPage" value="${size}"></c:param>
					</c:url>

					<c:choose>
						<c:when test="${currentPage < pageNumber}">
							<li class="page-item"><a class="page-link" href="${urlPost}">&raquo;</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled"><a class="page-link"
								href="${urlPost}">&raquo;</a></li>
						</c:otherwise>
					</c:choose>

				</ul>
			</nav>
		</div>
	</div>
</body>
</html>